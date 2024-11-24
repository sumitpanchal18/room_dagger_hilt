package mvvm.app.dragerhill.postRequest

data class PostRequest(
    val title: String,
    val body: String,
    val userId: Int
)

data class PostResponse(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)
