package mvvm.app.dragerhill.dbPrivateKey

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PrivateKeyViewModel @Inject constructor(
    private val privateKeyRepo: PrivateKeyRepo
) : ViewModel() {

    val allPrivateKeyLiveData: LiveData<List<PrivateKey>> = privateKeyRepo.getAllPrivateKey()

    @SuppressLint("SimpleDateFormat")
    fun privateKeyAdd() {
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val cd = SimpleDateFormat("ddMMyyyyhhmmss").format(Date())
        Log.d("Current Date", "Current Date & Time is : $currentDate")

        viewModelScope.launch {
            createPk(
                PrivateKey(
                    0, 1807, "ABC", "valid", "MAC_11_1_1_$cd",
                    "MAC_11_2_1_$cd", 12121, currentDate, 18
                )
            )
        }
    }

    fun generateJsonFile(context: Context) {
        viewModelScope.launch {
            val privateKeyItems = privateKeyRepo.getListPrivateKey()

            if (privateKeyItems.isEmpty()) {
                Log.e("TAG", "Database is empty")
                Toast.makeText(context, "Database is empty", Toast.LENGTH_SHORT).show()
                return@launch
            }

            val privateKeyData = PrivateKeyMain("privatekey", privateKeyItems)
            val gson = Gson()
            val jsonString = gson.toJson(privateKeyData)

            val fileName = "private_key_data.json"
            val file = File(context.getExternalFilesDir(null), fileName)
            Log.d("TAG", "File Path : $file")

            try {
                val writer = FileWriter(file)
                writer.write(jsonString)
                writer.close()
                Log.i("TAG", "Json File Generated successfully")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun createPk(privateKey: PrivateKey) {
        viewModelScope.launch {
            privateKeyRepo.createPrivateKey(privateKey)
        }
    }

    fun updateReplyById() {
        viewModelScope.launch {
            privateKeyRepo.updateReplayById()
        }
    }

    fun deleteAllMetadata() {
        viewModelScope.launch {
            privateKeyRepo.deleteAllPk()
        }
    }

    fun deleteById() {
        viewModelScope.launch {
            privateKeyRepo.deleteById()
        }
    }
}
