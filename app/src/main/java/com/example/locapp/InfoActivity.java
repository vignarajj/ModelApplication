package com.example.locapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vignaraj on 13/12/2016.
 */
public class InfoActivity extends Activity {
    TextView txt_battery, txt_wifi, txt_bluetooth;
    ImageView imageBatteryState;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setExistTheme(this);
        setContentView(R.layout.activity_info);
        imageBatteryState = (ImageView) findViewById(R.id.imageViewBatteryState);
        txt_battery = (TextView) findViewById(R.id.status_battery);
        txt_wifi = (TextView) findViewById(R.id.status_wifi);
        txt_bluetooth = (TextView) findViewById(R.id.status_bluetooth);
        //Battery Status
        this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        // Wifi status
        DisplayWifiState();
        this.registerReceiver(this.myWifiReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        // Bluetooth status
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        this.registerReceiver(bluetoothReceiver, filter);
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);


            txt_battery.setText(
                    " Health : " + health + "\n" +
                            " Level : " + level + " % " + "\n" +
                            " Plugged : " + plugged + "\n" +
                            " Present : " + present + "\n" +
                            " Scale : " + scale + "\n" +
                            " Status : " + status + "\n" +
                            " Technology : " + technology + "\n" +
                            " Temperature : " + temperature + "\n" +
                            " Voltage : " + voltage + "\n");
            imageBatteryState.setImageResource(icon_small);
        }
    };
    /*Wifi Status*/
    private BroadcastReceiver myWifiReceiver
            = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            NetworkInfo networkInfo = (NetworkInfo) arg1.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                DisplayWifiState();
            }
        }
    };

    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        txt_bluetooth.setText("Bluetooth off");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        txt_bluetooth.setText("Turning Bluetooth off...");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        txt_bluetooth.setText("Bluetooth on");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        txt_bluetooth.setText("Turning Bluetooth on...");
                        break;
                }
            } else {
                txt_bluetooth.setText("Bluetooth off");
            }
        }
    };

    private void DisplayWifiState() {

        ConnectivityManager myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager myWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
        String macAddress, IPaddress, ssId, BssId, speed, rssi, connected;

        macAddress = myWifiInfo.getMacAddress();

        if (myNetworkInfo.isConnected()) {
            int myIp = myWifiInfo.getIpAddress();
            connected = " CONNECTED ";

            int intMyIp3 = myIp / 0x1000000;
            int intMyIp3mod = myIp % 0x1000000;

            int intMyIp2 = intMyIp3mod / 0x10000;
            int intMyIp2mod = intMyIp3mod % 0x10000;

            int intMyIp1 = intMyIp2mod / 0x100;
            int intMyIp0 = intMyIp2mod % 0x100;

            IPaddress = String.valueOf(intMyIp0)
                    + "." + String.valueOf(intMyIp1)
                    + "." + String.valueOf(intMyIp2)
                    + "." + String.valueOf(intMyIp3);

            ssId = myWifiInfo.getSSID();
            BssId = myWifiInfo.getBSSID();

            speed = String.valueOf(myWifiInfo.getLinkSpeed()) + " " + WifiInfo.LINK_SPEED_UNITS;
            rssi = String.valueOf(myWifiInfo.getRssi());
            txt_wifi.setText(connected + "\n" + " Mac Address : " + macAddress + "\n" + " IP Address : " + IPaddress + "\n"
                    + " SSID : " + ssId + "\n" + " BSSID : " + BssId + "\n" + " Speed : " + speed + "\n" + " RSSI : " + rssi);
        } else {
            connected = " DIS-CONNECTED! ";
            txt_wifi.setText(connected);
        }

    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothReceiver);
    }
}
