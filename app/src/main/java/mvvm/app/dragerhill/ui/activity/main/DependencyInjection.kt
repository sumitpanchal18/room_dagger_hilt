package mvvm.app.dragerhill.ui.activity.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.databinding.ActivityDependencyInjectionBinding
import javax.inject.Inject

@AndroidEntryPoint
class DependencyInjection : AppCompatActivity() {

    @Inject
    lateinit var car: Car

    private lateinit var binding: ActivityDependencyInjectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDependencyInjectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drive()
    }

    private fun drive() {
        car.engine()
    }
}

class Car @Inject constructor(){
    fun engine() {
        Log.d(TAG, "engine start")
    }
}