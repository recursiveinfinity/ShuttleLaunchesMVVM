package com.example.shuttlelaunchesmvvm.net;

import com.example.shuttlelaunchesmvvm.Constants;
import com.example.shuttlelaunchesmvvm.data.ShuttleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LaunchLibraryService {

    @GET(Constants.SHUTTLE_ENDPOINT)
    Call<ShuttleResponse> getShuttleLaunchesForDate(@Path("date") String date);
}
