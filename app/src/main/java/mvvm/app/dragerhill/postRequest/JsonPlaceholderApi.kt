package mvvm.app.dragerhill.postRequest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceholderApi {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>

    @POST("posts")
    fun createPost(@Body postRequest: PostRequest): Call<PostResponse>
}
