package mvvm.app.dragerhill.recyclerView.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.databinding.ActivityRvMainBinding

@AndroidEntryPoint
class RvMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRvMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, PostFragment())
                .commit()
        }
    }
}
