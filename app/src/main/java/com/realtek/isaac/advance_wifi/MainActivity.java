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

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private static String logtag = "RTK-WIFI";
    private static WifiManager mWifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button get_feature = (Button)findViewById(R.id.button1);
        final Button pno_feature = (Button)findViewById(R.id.button2);
        final Button clean = (Button)findViewById(R.id.button3);
        get_feature.setOnClickListener(GetFeatureListener);
        pno_feature.setOnClickListener(GetPNOListener);
        clean.setOnClickListener(CleanListener);
        mWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
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

    private OnClickListener GetFeatureListener = new OnClickListener() {
        public void onClick (View v) {
            final TextView text = (TextView)findViewById(R.id.textView2);
            boolean mRttSupport = mWifiManager.isDeviceToApRttSupported();
            text.setText(" "+ mRttSupport);
        }
    };
    private OnClickListener GetPNOListener = new OnClickListener() {
        public void onClick (View v) {
            final TextView text = (TextView)findViewById(R.id.textView3);
            boolean mPnoSupport = mWifiManager.isPreferredNetworkOffloadSupported();
            text.setText(" "+ mPnoSupport);
        }
    };
    private OnClickListener CleanListener = new OnClickListener() {
        public void onClick (View v) {
            final TextView t2 = (TextView)findViewById(R.id.textView2);
            final TextView t3 = (TextView)findViewById(R.id.textView3);
            t2.setText("--");
            t3.setText("--");
        }
    };
}
