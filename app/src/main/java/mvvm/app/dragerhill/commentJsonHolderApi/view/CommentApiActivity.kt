package mvvm.app.dragerhill.commentJsonHolderApi.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.commentJsonHolderApi.viewModel.CommentViewModel
import mvvm.app.dragerhill.databinding.ActivityCommentApiBinding

@AndroidEntryPoint
class CommentApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentApiBinding

    private val commentViewModel: CommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = 1

        commentViewModel.getComments(id).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    result.data?.forEach {
                        Log.d("TAG", "Name: ${it.name}")
                    }
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("TAG", result.message)
                }
            }
        }
    }
}
