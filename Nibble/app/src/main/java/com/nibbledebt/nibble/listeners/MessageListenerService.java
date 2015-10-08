package com.nibbledebt.nibble.listeners;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by ralam on 10/7/15.
 */
public class MessageListenerService extends GcmListenerService {

    public void onMessageReceived(String from, Bundle data) {
        Log.i("BADGE", data.getString("badge"));
    }
}
