package com.nickzim.newsaggregator.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nickzim.newsaggregator.R;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        SwipeRefreshLayout refreshLayout = findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                InputActivity.super.recreate();
            }
        });

        TextView msg = findViewById(R.id.netStatus);
        msg.setText("Отсутствует интернет соединение");

        if (hasConnection(InputActivity.this)) {
            Intent intent = new Intent(InputActivity.this, FeedsListActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiInfo == null){
            return false;
        }

        if (wifiInfo.isConnected()) {
            return true;
        } else {
            wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return wifiInfo.isConnected();
        }
    }
}
