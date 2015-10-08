package com.nibbledebt.nibble.listeners;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;
import com.nibbledebt.nibble.registration.RegistrationIntentService;

/**
 * Created by ralam on 10/7/15.
 */
public class GcmInstanceListener extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

}
