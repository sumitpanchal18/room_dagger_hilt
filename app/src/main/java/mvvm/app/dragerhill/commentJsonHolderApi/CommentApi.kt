package mvvm.app.dragerhill.commentJsonHolderApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApi {

    @GET("comments")
    fun getComments(@Query("email") email: String): Call<List<CommentDataClassItem>>
}