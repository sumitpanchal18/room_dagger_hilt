package mvvm.app.dragerhill.commentJsonHolderApi.model

data class CommentDataClassItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

class CommentDataClass : ArrayList<CommentDataClassItem>()