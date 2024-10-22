package mvvm.app.dragerhill.dbPrivateKey

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.databinding.ActivityPrivateKeyBinding

@AndroidEntryPoint
class PrivateKeyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivateKeyBinding
    private val privateKeyViewModel: PrivateKeyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivateKeyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPkClick.setOnClickListener {
            privateKeyViewModel.privateKeyAdd()
        }

        binding.btnPkDelete.setOnClickListener {
            privateKeyViewModel.deleteAllMetadata()
        }

        binding.btnPkDeleteById.setOnClickListener {
            privateKeyViewModel.deleteById()
        }

        privateKeyViewModel.allPrivateKeyLiveData.observe(this) {
            Log.i("PrivateKey", "All private key data : $it")
            it.forEach { pk ->
                Log.e("PrivateKey", "All private key Timestamp's : ${pk.timestamp}")
            }
        }

        binding.btnJson.setOnClickListener {
            privateKeyViewModel.generateJsonFile(this)
        }

        binding.btnPkUpdate.setOnClickListener {
            privateKeyViewModel.updateReplyById()
        }
    }
}
