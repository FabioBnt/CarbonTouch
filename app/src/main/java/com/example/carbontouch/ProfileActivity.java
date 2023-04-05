package com.example.carbontouch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    private ListView scoreListView;
    private ArrayList<Score> scores;
    private carbonDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        scoreListView = findViewById(R.id.score_list_view);
        scores = new ArrayList<>();

        // Retrieve scores from SQLite database
        dbHelper = new carbonDBHelper(this);
        List<Score> scores = readScoresFromDB();

        // Set up adapter for list view
        ScoreAdapter adapter = new ScoreAdapter(this, R.layout.list_item_layout, scores);
        scoreListView.setAdapter(adapter);
    }

    private List<Score> readScoresFromDB() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                carbonDBHelper.score_table._ID,
                carbonDBHelper.score_table.COLUMN_SCORE.toString(),
                carbonDBHelper.score_table.COLUMN_DATE.toString()
        };
        Cursor cursor = db.query(
                carbonDBHelper.score_table.TABLE_NAME.toString(),
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Score> scores = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(carbonDBHelper.score_table._ID.toString()));
            int score = cursor.getInt(cursor.getColumnIndexOrThrow(carbonDBHelper.score_table.COLUMN_SCORE.toString()));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(carbonDBHelper.score_table.COLUMN_DATE.toString()));
            Score scoreObject = new Score(id, score, date);
            scores.add(scoreObject);
        }
        cursor.close();

        return scores;
    }

    private class ScoreAdapter extends ArrayAdapter<Score> {

        public ScoreAdapter(Context context, int resource, List<Score> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
            }

            Score score = getItem(position);

            // Set date
            TextView dateTextView = convertView.findViewById(R.id.dateTextView);
            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            String dateString = format.format(score.getDate());
            dateTextView.setText(dateString);

            // Set color based on score
            View colorView = convertView.findViewById(R.id.colorView);
            int scoreColor;
            if (score.getScore() >= 80) {
                scoreColor = ContextCompat.getColor(getContext(), R.color.score_green);
            } else if (score.getScore() >= 60) {
                scoreColor = ContextCompat.getColor(getContext(), R.color.score_yellow);
            } else {
                scoreColor = ContextCompat.getColor(getContext(), R.color.score_red);
            }
            colorView.setBackgroundColor(scoreColor);

            // Set score
            TextView scoreTextView = convertView.findViewById(R.id.scoreTextView);
            String scoreString = Integer.toString(score.getScore());
            scoreTextView.setText(scoreString);

            return convertView;
        }
    }
}

