package mvvm.app.dragerhill.ui.activity.broadcastReceiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class NetworkChangeReceiver(
    private var statusTextView: TextView?,
    private var timeTextView: TextView?
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ConnectivityManager.CONNECTIVITY_ACTION -> handleNetworkChange(context)
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> handleAirplaneModeChange(intent)
            Intent.ACTION_BATTERY_CHANGED -> handleBatteryStatusChange(context, intent)
            BluetoothAdapter.ACTION_STATE_CHANGED -> handleBluetoothStateChange(intent)
            Intent.ACTION_TIME_TICK -> handleRealTimeChange()
        }
    }

    private fun handleNetworkChange(context: Context) {
        val isConnected = isConnected(context)
        val statusMessage = if (isConnected) {
            "Internet Connected"
        } else {
            "Internet Disconnected"
        }

        statusTextView?.text = statusMessage
        Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Network Status: $statusMessage")
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


    private fun handleAirplaneModeChange(intent: Intent) {
        val isAirplaneModeOn = intent.getBooleanExtra("state", false)
        val statusMessage = if (isAirplaneModeOn) {
            "Airplane Mode Enabled"
        } else {
            "Airplane Mode Disabled"
        }

        statusTextView?.text = statusMessage
        Toast.makeText(statusTextView?.context, statusMessage, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Airplane Mode Status: $statusMessage")
    }

    private fun handleBatteryStatusChange(context: Context, intent: Intent) {
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
        val chargingStatusMessage = if (isCharging) {
            "Device is Charging"
        } else {
            "Device is Not Charging"
        }

        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val batteryPercentage = if (level != -1 && scale != -1) {
            (level * 100) / scale
        } else {
            -1
        }

        val batteryPercentageMessage = if (batteryPercentage != -1) {
            "Battery: $batteryPercentage%"
        } else {
            "Unable to determine battery percentage"
        }

        val statusMessage = "$chargingStatusMessage\n$batteryPercentageMessage"
        statusTextView?.text = statusMessage
        Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Battery Status: $chargingStatusMessage, $batteryPercentageMessage")
    }

    private fun handleBluetoothStateChange(intent: Intent) {
        val bluetoothState =
            intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
        val bluetoothStateMessage = when (bluetoothState) {
            BluetoothAdapter.STATE_ON -> "Bluetooth is ON"
            BluetoothAdapter.STATE_OFF -> "Bluetooth is OFF"
            BluetoothAdapter.STATE_TURNING_ON -> "Turning Bluetooth ON"
            BluetoothAdapter.STATE_TURNING_OFF -> "Turning Bluetooth OFF"
            else -> "Bluetooth state UnKnown"
        }

        statusTextView?.text = bluetoothStateMessage
        Toast.makeText(statusTextView?.context, bluetoothStateMessage, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Bluetooth State: $bluetoothStateMessage")
    }

    private fun handleRealTimeChange() {
        val currentTime = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
            .format(java.util.Date())
        val statusMessage = "Current Time: $currentTime"

        timeTextView?.text = statusMessage
        Toast.makeText(statusTextView?.context, statusMessage, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Real-Time Time: $currentTime")
    }


    companion object {
        private const val TAG = "NetworkChangeReceiver"
    }
}
