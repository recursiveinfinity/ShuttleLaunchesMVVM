package com.example.shuttlelaunchesmvvm.repo;

import java.util.Observable;
import java.util.Observer;

public class LaunchRepository extends Observable implements Observer, DataSource {

    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    public LaunchRepository(DataSource localDataSource, DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getLaunchesForDate(String date) {
            //TODO if we have internet Connection we get it from API else from DB
        remoteDataSource.setObserver(this);
        remoteDataSource.getLaunchesForDate(date);
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void update(Observable observable, Object result) {
        setChanged();
        notifyObservers(result);
    }
}
