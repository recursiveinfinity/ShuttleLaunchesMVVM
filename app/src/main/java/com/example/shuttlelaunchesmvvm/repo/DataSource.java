package com.example.shuttlelaunchesmvvm.repo;

import java.util.Observer;

public interface DataSource {
    void getLaunchesForDate(String date);
    void setObserver(Observer observer);
}
