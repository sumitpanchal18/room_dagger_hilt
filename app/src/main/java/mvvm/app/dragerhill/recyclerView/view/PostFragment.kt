package mvvm.app.dragerhill.recyclerView.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mvvm.app.dragerhill.R
import mvvm.app.dragerhill.commentJsonHolderApi.view.Result
import mvvm.app.dragerhill.databinding.FragmentPostBinding
import mvvm.app.dragerhill.recyclerView.model.PostAdapter
import mvvm.app.dragerhill.recyclerView.viewModel.PostViewModel

@AndroidEntryPoint
class PostFragment : Fragment(R.layout.fragment_post) {

    private val postViewModel: PostViewModel by viewModels()
    private lateinit var binding: FragmentPostBinding
    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostBinding.bind(view)

        postAdapter = PostAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
        }

        postViewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Result.Loading -> {
                    binding.progressBarFrag.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }

                is Result.Success -> {
                    binding.progressBarFrag.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    postAdapter = PostAdapter(state.data!!)
                    binding.recyclerView.adapter = postAdapter
                }

                is Result.Error -> {
                    binding.progressBarFrag.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        postViewModel.fetchPosts()
    }
}
