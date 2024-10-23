package mvvm.app.dragerhill.commentJsonHolderApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApi {

    @GET("comments")
    fun getComments(@Query("id") id: Int): Call<List<CommentDataClassItem>>
}