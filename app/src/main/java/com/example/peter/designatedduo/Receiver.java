package com.example.peter.designatedduo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import java.nio.channels.Channel;

/**
 * Created by KK on 11/9/2014.
 */
public class Receiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private Channel mChannel;
    private FriendListActivity mActivity;

    public Receiver(WifiP2pManager manager, Channel channel, FriendListActivity mActivity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = mActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
           String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // wifi p2p enabled
            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            } {
                return;
            }
        }
    }
}
