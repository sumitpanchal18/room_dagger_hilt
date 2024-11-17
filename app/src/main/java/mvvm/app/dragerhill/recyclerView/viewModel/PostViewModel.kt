package mvvm.app.dragerhill.recyclerView.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mvvm.app.dragerhill.commentJsonHolderApi.view.Result
import mvvm.app.dragerhill.recyclerView.model.Post
import mvvm.app.dragerhill.recyclerView.model.PostRepo
import mvvm.app.dragerhill.utils.Constant
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepo: PostRepo
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<List<Post>>>()
    val uiState: LiveData<Result<List<Post>>> get() = _uiState

    fun fetchPosts() {
        _uiState.value = Result.Loading

        viewModelScope.launch {
            try {
                val response = postRepo.getPosts(Constant.ID)

                if (response.isSuccessful) {
                    _uiState.value =
                        Result.Success(response.body() ?: emptyList())
                } else {
                    _uiState.value =
                        Result.Error("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value =
                    Result.Error("Failed to fetch posts: ${e.message}")
            }
        }
    }
}
