package com.example.carbontouch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CarbonIntensityService {
    @GET("v1/latest")
    Call<CarbonIntensityResponse> getLatestCarbonIntensity(
            @Query("countryCode") String countryCode,
            @Header("auth-token") String authToken
    );
}
