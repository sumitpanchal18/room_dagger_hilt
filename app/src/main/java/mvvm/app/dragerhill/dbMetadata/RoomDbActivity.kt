package mvvm.app.dragerhill.dbMetadata

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.R
import mvvm.app.dragerhill.databinding.ActivityRoomDbBinding

@AndroidEntryPoint
class RoomDbActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomDbBinding

    private val metaDataViewModel: MetaDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomDbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        metaDataViewModel.allMetadataLiveDataClass.observe(this) { metadataList ->
            loadImage(metadataList)
        }

        binding.btnClick.setOnClickListener {
            metaDataViewModel.metadataSave()
        }

        binding.btnDelete.setOnClickListener {
            metaDataViewModel.deleteInvalid()
        }
    }

    private fun loadImage(metadataList: List<MetaData>) {
        if (metadataList.isNotEmpty()) {
            Log.d(TAG, "MetaDataList: $metadataList")
            val lastMetaData = metadataList.last()
            val imageResId = lastMetaData.image
            val timestamp = lastMetaData.time
            val id = lastMetaData.id

            Log.d(TAG, "Image resource ID from metadata: $imageResId")
            Log.d(TAG, "ID resource ID from metadata: $id")

            try {
                binding.imageView.setImageResource(imageResId)
                binding.timestampTextView.text = timestamp
            } catch (e: Exception) {
                Log.e(TAG, "Set Image Resource error: ${e.message}")
            }
        } else {
            binding.imageView.setImageResource(R.drawable.ic_launcher_foreground)
            binding.timestampTextView.text = ""
        }
    }
}
