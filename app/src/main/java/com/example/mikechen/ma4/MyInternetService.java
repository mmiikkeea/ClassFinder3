package com.example.mikechen.ma4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyInternetService extends Service {
    public MyInternetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
