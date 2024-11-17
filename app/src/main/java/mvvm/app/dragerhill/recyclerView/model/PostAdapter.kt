package mvvm.app.dragerhill.recyclerView.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mvvm.app.dragerhill.databinding.ItemPostBinding

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(post: Post) {
            binding.apply {
                tvPostTitle.text = "Post Title : ${post.title}"
                tvPostBody.text = "Post Body : ${post.body}"
                tvPostId.text = "Post Id : ${post.id}"
                tvPostUserId.text = "Post UserId : ${post.userId}"
            }
        }
    }
}
