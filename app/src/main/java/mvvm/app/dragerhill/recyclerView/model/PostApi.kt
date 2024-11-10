package mvvm.app.dragerhill.recyclerView.model

import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}
