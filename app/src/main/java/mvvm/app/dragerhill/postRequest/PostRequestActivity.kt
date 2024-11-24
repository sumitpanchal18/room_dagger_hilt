package mvvm.app.dragerhill.postRequest

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mvvm.app.dragerhill.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PostRequestActivity : AppCompatActivity() {

    private lateinit var apiService: JsonPlaceholderApi
    private lateinit var responseTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_request)

        responseTextView = findViewById(R.id.responseTextView)
        val postButton: Button = findViewById(R.id.postButton)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // The base URL for the API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(JsonPlaceholderApi::class.java)

        // Button click listener to trigger the POST request first, and then GET request
        postButton.setOnClickListener {
            makePostRequest()
        }
    }

    private fun makePostRequest() {
        // Creating an example PostRequest object
        val postRequest = PostRequest(
            title = "Hello, Retrofit!",
            body = "This is a POST request example in Android.",
            userId = 1
        )

        // Perform POST request to create the post
        apiService.createPost(postRequest).enqueue(object : Callback<PostResponse> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    // Display success message for POST request
                    responseTextView.text = "Post created successfully:\n\n$responseBody"

                    // After POST is successful, perform GET request to fetch all posts
                    makeGetRequest()
                } else {
                    responseTextView.text = "Error: ${response.code()} ${response.message()}"
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                responseTextView.text = "Failed to create post: ${t.message}"
            }
        })
    }

    private fun makeGetRequest() {
        // Perform GET request to fetch all posts
        apiService.getPosts().enqueue(object : Callback<List<PostResponse>> {
            override fun onResponse(call: Call<List<PostResponse>>, response: Response<List<PostResponse>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    // Display the list of posts
                    responseTextView.append("\n\nAll Posts:\n$responseBody")
                    Log.d(TAG, "onResponse: $responseBody")
                } else {
                    responseTextView.append("\nError: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                responseTextView.append("\nFailed to fetch posts: ${t.message}")
            }
        })
    }
}
