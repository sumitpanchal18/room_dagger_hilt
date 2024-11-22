package mvvm.app.dragerhill.ui.activity.broadcastReceiver

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mvvm.app.dragerhill.R
import mvvm.app.dragerhill.databinding.ActivityBrBinding

class BrActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrBinding

    private lateinit var systemStatusReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        systemStatusReceiver = NetworkChangeReceiver(binding.networkStatusTextView,binding.txtTime)

        val filter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            addAction(Intent.ACTION_TIME_TICK)
        }

        registerReceiver(systemStatusReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemStatusReceiver)
    }
}
