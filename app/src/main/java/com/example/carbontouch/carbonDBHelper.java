package com.example.carbontouch;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class carbonDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "carbon.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    public enum score_table implements BaseColumns {
        TABLE_NAME("scores"),
        COLUMN_SCORE("score"),
        COLUMN_DATE("date"),
        COLUMN_USERNAME("username");

        private final String text;

        score_table(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public carbonDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_SCORES_TABLE = "CREATE TABLE IF NOT EXISTS " +
                score_table.TABLE_NAME + " ( " +
                score_table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                score_table.COLUMN_SCORE + " INTEGER, " +
                score_table.COLUMN_DATE + " STRING," +
                score_table.COLUMN_USERNAME + " STRING," +
                "FOREIGN KEY(" + score_table.COLUMN_USERNAME + ") REFERENCES users(username)" + " )";
        final String SQL_CREATE_USERS = "CREATE TABLE IF NOT EXISTS " +
                "users" + " ( " +
                "username" + " STRING PRIMARY KEY, " +
                "password" + " STRING" +" )";

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + "users");
        db.execSQL("DROP TABLE IF EXISTS " + score_table.TABLE_NAME);

        onCreate(db);
    }

    public void addScore(int score, String date, String username) {
        db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(score_table.COLUMN_SCORE.toString(), score);
        values.put(score_table.COLUMN_DATE.toString(), date);
        values.put(score_table.COLUMN_USERNAME.toString(), username);

        db.insert(score_table.TABLE_NAME.toString(), null, values);

    }

    public List<Score> getAllScores(String username) {
        db = getReadableDatabase();

        List<Score> scoreList = new ArrayList<>();
        username = "'" + username + "'";
        Cursor cursor = db.rawQuery("SELECT * FROM " + score_table.TABLE_NAME + " WHERE " +
                score_table.COLUMN_USERNAME + " = " + username, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(score_table._ID.toString()));
                @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex(score_table.COLUMN_SCORE.toString()));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(score_table.COLUMN_DATE.toString()));

                Score newScore = new Score(id, score, date);
                scoreList.add(newScore);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return scoreList;
    }

    public boolean addUser(String username, String password) {
        db = getWritableDatabase();
        // Check if username already exists
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        db.insert("users", null, values);
        return true;
    }

    public boolean checkUser(String username, String password) {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}

