package mvvm.app.dragerhill.commentJsonHolderApi

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.databinding.ActivityCommentApiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class CommentApiActivity : AppCompatActivity() {

    @Inject
    lateinit var commentApi: CommentApi

    private lateinit var binding: ActivityCommentApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = 18
        val apiCall = commentApi.getComments(id)

        apiCall.enqueue(object : Callback<List<CommentDataClassItem>?> {
            override fun onResponse(
                call: Call<List<CommentDataClassItem>?>,
                response: Response<List<CommentDataClassItem>?>
            ) {
                val res = response.body()
                binding.progressBar.visibility = View.GONE

                Log.d(TAG, "Api Response : $res")
                binding.txtApiRes.text = res.toString()

                res?.forEach {
//                    Log.d(TAG, "Email : ${it.email}")
//                    Log.d(TAG, "Name : ${it.name}")
                }
            }

            override fun onFailure(call: Call<List<CommentDataClassItem>?>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")

            }
        })
    }
}
