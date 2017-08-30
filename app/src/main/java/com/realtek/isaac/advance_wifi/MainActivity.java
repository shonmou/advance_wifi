package com.realtek.isaac.advance_wifi;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.net.wifi.aware.WifiAwareManager;
import android.content.pm.PackageManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text5 = (TextView) findViewById(R.id.textView5);

        findViewById(R.id.ap_rtt_support).setOnClickListener(mClickListener);
        findViewById(R.id.clean).setOnClickListener(mClickListener);
        findViewById(R.id.pno_support).setOnClickListener(mClickListener);
        findViewById(R.id.nan_support).setOnClickListener(mClickListener);
        findViewById(R.id.enable_nan).setOnClickListener(mClickListener);
        findViewById(R.id.disable_nan).setOnClickListener(mClickListener);

        mWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        mWifiAwareManager = (WifiAwareManager)getSystemService(Context.WIFI_AWARE_SERVICE);
        mNanSupport = getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE);
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

    private View.OnClickListener mClickListener;

    {
        mClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ap_rtt_support:
                        mRttSupport = mWifiManager.isDeviceToApRttSupported();
                        text2.setText(" " + mRttSupport);
                        break;
                    case R.id.clean:
                        mRttSupport = false;
                        mPnoSupport = false;
                        mNanSupport = false;
                        text2.setText("--");
                        text3.setText("--");
                        text5.setText("--");
                        break;
                    case R.id.pno_support:
                        mPnoSupport = mWifiManager.isPreferredNetworkOffloadSupported();
                        text3.setText(" " + mPnoSupport);
                        break;
                    case R.id.nan_support:
                        text5.setText(" " + mNanSupport);
                        break;
                    case R.id.enable_nan:
                        break;
                    case R.id.disable_nan:
                        break;
                }
            }
        };
    }
}
