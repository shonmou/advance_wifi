package com.realtek.isaac.advance_wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static String logtag = "RTK-WIFI";
    private static WifiManager mWifiManager;
    private static WifiAwareManager mWifiAwareManager;
    private TextView text2;
    private TextView text3;
    private TextView text5;
    private boolean mRttSupport = false;
    private boolean mPnoSupport = false;
    private boolean mNanSupport = false;
    private Button nan_en;
    private Button nan_dis;

    private void update_screen() {
        mRttSupport = mWifiManager.isDeviceToApRttSupported();
        mPnoSupport = mWifiManager.isPreferredNetworkOffloadSupported();
        mNanSupport = getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE);

        text2.setText(" " + mRttSupport);
        text3.setText(" " + mPnoSupport);
        text5.setText(" " + mNanSupport);

        if (mNanSupport) {
            nan_en.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*TODO: */
                    Toast.makeText(getApplicationContext(), "attach nan", Toast.LENGTH_LONG).show();
                }
            });
            nan_dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*TODO*/
                    Toast.makeText(getApplicationContext(), "dis nan", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            nan_en.setEnabled(false);
            nan_dis.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text5 = (TextView) findViewById(R.id.textView5);

        nan_en = (Button) findViewById(R.id.enable_nan);
        nan_dis = (Button) findViewById(R.id.disable_nan);

        mWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        mWifiAwareManager = (WifiAwareManager)getSystemService(Context.WIFI_AWARE_SERVICE);

        /* register a broadcast receiver for wifi aware state change*/
        IntentFilter filter =
                new IntentFilter(WifiAwareManager.ACTION_WIFI_AWARE_STATE_CHANGED);
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (mWifiAwareManager.isAvailable()) {
                    /*TODO...*/
                } else {
                    /*TODO...*/
                }
            }
        };
        this.registerReceiver(br, filter);
        update_screen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
