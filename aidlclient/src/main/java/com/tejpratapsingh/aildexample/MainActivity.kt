package com.tejpratapsingh.aildexample

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tejpratapsingh.aildexample.databinding.ActivityMainBinding
import com.tejpratapsingh.aildlib.BuildConfig
import com.tejpratapsingh.aildlib.IExampleAidlInterface

class MainActivity : AppCompatActivity() {

    private var aidlService: IExampleAidlInterface? = null

    private lateinit var binding: ActivityMainBinding

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            aidlService = IExampleAidlInterface.Stub.asInterface(service)
            Toast.makeText(applicationContext, "Service Connected", Toast.LENGTH_LONG).show()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidlService = null
            Toast.makeText(applicationContext, "Service Disconnected", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent()
        serviceIntent.component = ComponentName(BuildConfig.AIDL_PACKAGE, BuildConfig.AIDL_SERVICE)

        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)

        binding.buttonCallService.setOnClickListener {
            aidlService?.basicTypes(0, 0, false, 0f, 0.0, "")
        }
    }
}