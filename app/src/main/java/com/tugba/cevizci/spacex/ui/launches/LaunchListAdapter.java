package com.tugba.cevizci.spacex.ui.launches;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugba.cevizci.spacex.R;
import com.tugba.cevizci.spacex.data.Launch;
import com.tugba.cevizci.spacex.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaunchListAdapter extends RecyclerView.Adapter<LaunchListAdapter.ViewHolder> {

    private List<Launch> launches;
    private LaunchesListener listener;

    private Context context;

    public LaunchListAdapter(List<Launch> launches, LaunchesListener listener) {
        this.launches = launches;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_launch,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtLaunch.setText(launches.get(position).missionName);
        holder.txtLaunchDate.setText(DateUtils.convertToDate(launches.get(position).launchDateLocal));
        Picasso.with(context).load(launches.get(position).links.missionPatch).noFade().into(holder.ivLaunch);

    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    public void updateLaunches(List<Launch> launches) {
        this.launches = launches;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_launch)
        ImageView ivLaunch;

        @BindView(R.id.txt_launch)
        TextView txtLaunch;

        @BindView(R.id.txt_launch_date)
        TextView txtLaunchDate;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.launch_container)
        void onCityContainerClicked() {
            listener.onLaunchClick(launches.get(getAdapterPosition()));
        }
    }

    public interface LaunchesListener {

        void onLaunchClick(Launch clickedLaunch);
    }
}
