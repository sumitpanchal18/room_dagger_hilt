package mvvm.app.dragerhill.ui.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mvvm.app.dragerhill.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.shimmerLayout.startShimmer()
        simulateDataLoading()
    }

    @SuppressLint("SetTextI18n")
    private fun simulateDataLoading() {
        android.os.Handler().postDelayed({
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = android.view.View.GONE

            binding.shimmerTextView.text = "Data Loaded"
            binding.shimmerTextView.setTextColor(resources.getColor(android.R.color.black))
        }, 30000)
    }
}
