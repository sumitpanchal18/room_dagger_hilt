package mvvm.app.dragerhill.recyclerView.model

import retrofit2.Response
import javax.inject.Inject

class PostRepo @Inject constructor(private val postApi: PostApi) {
    suspend fun getPosts(id : Int): Response<List<Post>> {
        return postApi.getPosts(id)
    }
}
