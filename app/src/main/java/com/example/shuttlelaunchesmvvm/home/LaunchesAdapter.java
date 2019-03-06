package com.example.shuttlelaunchesmvvm.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shuttlelaunchesmvvm.R;
import com.example.shuttlelaunchesmvvm.data.Launch;

import java.util.ArrayList;
import java.util.List;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.LaunchViewHolder> {

    private List<Launch> launches = new ArrayList<>();

    public void setData(List<Launch> data) {
        launches.clear();
        launches.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LaunchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_launch, viewGroup, false);
        return new LaunchViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchViewHolder launchViewHolder, int position) {
            launchViewHolder.bind(launches.get(position));
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    static class LaunchViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvLaunch;

        public LaunchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLaunch = itemView.findViewById(R.id.tvLaunch);
        }

        public void bind(Launch launch) {
            tvName.setText(launch.getName());
            tvLaunch.setText(launch.getRocket().getName());
        }
    }
}
