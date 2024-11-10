package mvvm.app.dragerhill.recyclerView.model

import retrofit2.Response
import javax.inject.Inject

class PostRepo @Inject constructor(private val postApi: PostApi) {
    suspend fun getPosts(): Response<List<Post>> {
        return postApi.getPosts()
    }
}
