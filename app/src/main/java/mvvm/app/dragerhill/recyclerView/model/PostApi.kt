package mvvm.app.dragerhill.recyclerView.model

import mvvm.app.dragerhill.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET(Constant.POST_API_ENDPOINT)
    suspend fun getPosts(/*@Query("id") id: Int*/): Response<List<Post>>
}
