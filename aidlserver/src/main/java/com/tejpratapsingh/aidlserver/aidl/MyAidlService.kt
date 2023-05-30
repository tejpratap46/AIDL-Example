package com.tejpratapsingh.aidlserver.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyAidlService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return IExampleAidlImpl
    }
}