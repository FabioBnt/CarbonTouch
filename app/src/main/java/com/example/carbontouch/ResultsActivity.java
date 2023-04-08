package com.example.carbontouch;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView averageTextView;
    private ImageView scoreImageView;
    private TextView descriptionTextView;

    private CarbonIntensityService carbonIntensityService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initialize views
        scoreTextView = findViewById(R.id.score_textview);
        averageTextView = findViewById(R.id.average_textview);
        scoreImageView = findViewById(R.id.score_imageview);
        descriptionTextView = findViewById(R.id.description_textview);

        // Get the user's score from the intent
        int userScore = getIntent().getIntExtra("result", 0);
        String userScoreString = getString(R.string.score) + " ";
        userScoreString += String.valueOf(userScore);
        scoreTextView.setText(userScoreString);

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.co2signal.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the API service
        carbonIntensityService = retrofit.create(CarbonIntensityService.class);

        // Make the API call to get the average carbon intensity
        Call<CarbonIntensityResponse> call = carbonIntensityService.getLatestCarbonIntensity("FR", "yyVi0Bt0mn5XtK21fb8Pu2yBsUxswPuu");
        call.enqueue(new Callback<CarbonIntensityResponse>() {
            @Override
            public void onResponse(Call<CarbonIntensityResponse> call, Response<CarbonIntensityResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Get the average carbon intensity from the response
                    double averageCarbonIntensity = response.body().getData().getCarbonIntensity();

                    // Display the average carbon intensity
                    String averageText = getString(R.string.average) + " ";
                    averageText += String.valueOf(averageCarbonIntensity) + " gCO2/kWh";
                    averageTextView.setText(averageText);

                    // Compare the scores and highlight the higher one
                    if (userScore > averageCarbonIntensity) {
                        averageTextView.setTextColor(Color.parseColor("#00C853")); // Green color
                        scoreTextView.setTextColor(Color.parseColor("#FF0000")); // Red color
                        scoreImageView.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                        scoreImageView.setColorFilter(Color.parseColor("#FF0000"));
                        descriptionTextView.setText(String.format(getString(R.string.description_higher), String.valueOf((int) ((userScore - averageCarbonIntensity) / averageCarbonIntensity * 100))));
                    } else {
                        averageTextView.setTextColor(Color.parseColor("#FF0000")); // Red color
                        scoreTextView.setTextColor(Color.parseColor("#00C853")); // Green color
                        scoreImageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                        descriptionTextView.setText(String.format(getString(R.string.description_lower), String.valueOf((int) ((averageCarbonIntensity - userScore) / userScore * 100))));
                    }
                } else {
                    // Handle error
                    Toast.makeText(ResultsActivity.this, "Error getting average carbon intensity", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CarbonIntensityResponse> call, Throwable t) {
                // Handle error
                Toast.makeText(ResultsActivity.this, "Error getting average carbon intensity", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
