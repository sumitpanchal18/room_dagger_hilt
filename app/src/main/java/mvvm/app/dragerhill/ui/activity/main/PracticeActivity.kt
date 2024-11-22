package mvvm.app.dragerhill.ui.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mvvm.app.dragerhill.databinding.ActivityPracticeBinding

data class Pair<T, U>(val first: T, val second: U)

class PracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.shimmerLayout.startShimmer()
//        simulateDataLoading()


//        Pair kotlin android.
        val pair1 = Pair(10, 20)
        val pair2 = Pair(10, 20)

        println(pair1.first)
        println(pair2.second)


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
