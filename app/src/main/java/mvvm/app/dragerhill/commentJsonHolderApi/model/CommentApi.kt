package mvvm.app.dragerhill.commentJsonHolderApi.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApi {
    @GET("comments")
    suspend fun getComments(
        @Query("id") id: Int
    ): Response<List<CommentDataClassItem>>
}