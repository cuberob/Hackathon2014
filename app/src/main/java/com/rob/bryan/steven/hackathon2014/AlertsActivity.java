package com.rob.bryan.steven.hackathon2014;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rob.bryan.steven.hackathon2014.activities.BaseActivity;
import com.rob.bryan.steven.hackathon2014.object.Alert;

import java.util.ArrayList;


public class AlertsActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Alert> alerts = new ArrayList<Alert>();
        alerts.add(new Alert("Light is defect", Alert.AlertType.LIGHT));
        alerts.add(new Alert("Refrigerator is too warm", Alert.AlertType.TEMPERATURE));
        alerts.add(new Alert("Room is too cold", Alert.AlertType.TEMPERATURE));
        alerts.add(new Alert("Window is open", Alert.AlertType.PROXIMITY));
        alerts.add(new Alert("Sound level is too noisy", Alert.AlertType.SOUND));

        mAdapter = new AlertsAdapter(alerts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_alerts;
    }

    class AlertsAdapter extends RecyclerView.Adapter<AlertsAdapter.ViewHolder> {
        private ArrayList<Alert> alerts;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public CardView card;

            public ViewHolder(CardView card) {
                super(card);
                this.card = card;
            }
        }

        public AlertsAdapter(ArrayList<Alert> alerts) {
            this.alerts = alerts;
        }

        @Override
        public AlertsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView card = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_alert, parent, false);

            ViewHolder holder = new ViewHolder(card);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Alert alert = alerts.get(position);

            TextView title = (TextView) holder.card.findViewById(R.id.alert_name);
            title.setText(alert.getName());

            TextView type = (TextView) holder.card.findViewById(R.id.alert_type);
            type.setText(alert.getAlertTypeString());

            TextView time = (TextView) holder.card.findViewById(R.id.alert_time);
            time.setText(DateUtils.getRelativeTimeSpanString(alert.getAlertTime(), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));

            ImageView icon = (ImageView) holder.card.findViewById(R.id.alert_image);
            icon.setImageDrawable(getResources().getDrawable(alert.getIconID()));
        }

        @Override
        public int getItemCount() {
            return alerts.size();
        }
    }

}