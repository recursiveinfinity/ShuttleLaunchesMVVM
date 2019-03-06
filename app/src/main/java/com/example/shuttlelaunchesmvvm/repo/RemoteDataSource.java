package com.example.shuttlelaunchesmvvm.repo;

import com.example.shuttlelaunchesmvvm.Constants;
import com.example.shuttlelaunchesmvvm.data.Launch;
import com.example.shuttlelaunchesmvvm.data.ShuttleResponse;
import com.example.shuttlelaunchesmvvm.net.LaunchLibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final LaunchLibraryService launchLibraryService;

    public RemoteDataSource() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        launchLibraryService = retrofit.create(LaunchLibraryService.class);

    }

    @Override
    public void getLaunchesForDate(String date) {
        final List<Launch> launches = new ArrayList<>();
        launchLibraryService.getShuttleLaunchesForDate(date)
                .enqueue(new Callback<ShuttleResponse>() {
                    @Override
                    public void onResponse(Call<ShuttleResponse> call, Response<ShuttleResponse> response) {
                        if (response.isSuccessful() && response.body().getLaunches() != null) {
                            launches.clear();
                            launches.addAll(response.body().getLaunches());
                            setChanged();
                            notifyObservers(launches);
                        }
                    }

                    @Override
                    public void onFailure(Call<ShuttleResponse> call, Throwable t) {
                            t.printStackTrace();
                    }
                });
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
}
