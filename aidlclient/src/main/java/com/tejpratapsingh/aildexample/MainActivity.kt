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
import com.tejpratapsingh.aildlib.ICalculator
import com.tejpratapsingh.aildlib.ICalculatorCallback
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var aidlService: ICalculator? = null
    private val aidlCallback: ICalculatorCallback = object : ICalculatorCallback.Stub() {
        override fun onAdded(result: Int) {
            Toast.makeText(
                applicationContext,
                String.format(Locale.getDefault(), "Sum is: %d", result),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private lateinit var binding: ActivityMainBinding

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            aidlService = ICalculator.Stub.asInterface(service)
            aidlService?.registerListener(aidlCallback)

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
            aidlService?.add(2, 2)
        }
    }
}