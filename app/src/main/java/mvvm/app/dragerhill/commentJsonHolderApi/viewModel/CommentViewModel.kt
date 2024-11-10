package mvvm.app.dragerhill.commentJsonHolderApi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import mvvm.app.dragerhill.commentJsonHolderApi.model.CommentApi
import mvvm.app.dragerhill.commentJsonHolderApi.view.Result
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentApi: CommentApi
) : ViewModel() {
    fun getComments(id: Int) = liveData(Dispatchers.IO) {
        emit(Result.Loading)

        try {
            val response = commentApi.getComments(id)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()))
            } else {
                emit(Result.Error("API call failed with status: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(Result.Error("API call failed: ${e.message}"))
        }
    }
}
