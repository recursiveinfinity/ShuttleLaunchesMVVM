package com.example.shuttlelaunchesmvvm.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.shuttlelaunchesmvvm.data.Launch;
import com.example.shuttlelaunchesmvvm.repo.DataSource;
import com.example.shuttlelaunchesmvvm.repo.LaunchRepository;
import com.example.shuttlelaunchesmvvm.repo.LocalDataSource;
import com.example.shuttlelaunchesmvvm.repo.RemoteDataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeViewModel implements Observer {

    private final DataSource launchRepository;

    private final MutableLiveData<List<Launch>> launchLiveData = new MutableLiveData<>();


    public HomeViewModel() {
        launchRepository = new LaunchRepository(new LocalDataSource(), new RemoteDataSource());
    }

    public LiveData<List<Launch>> getLaunchLiveData() {
        return launchLiveData;
    }

    public void getLaunches(String value) {
        launchRepository.setObserver(this);
        launchRepository.getLaunchesForDate(value);
    }

    @Override
    public void update(Observable observable, Object result) {
        List<Launch> launches = (List<Launch>) result;
        launchLiveData.setValue(launches);
    }
}
