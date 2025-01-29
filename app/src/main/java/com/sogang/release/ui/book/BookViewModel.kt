import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sogang.release.network.RetrofitClient
import com.sogang.release.utils.UserPreferences
import kotlinx.coroutines.launch


data class BooksResponse(
    val books: List<BookDTO>
)

data class BookDTO(
    val id: String,
    val title: String,
    val availability: String,
    val author: String,
    val tags: List<String>,
    val image: String
)

class BookViewModel : ViewModel() {
    var booksData by mutableStateOf<List<BookDTO>>(emptyList())
        private set

    init {
        fetchBooksData()
    }

    private fun fetchBooksData() {
        viewModelScope.launch {
            try {
                val accessToken = UserPreferences.getAccessToken()
                val response = RetrofitClient.bookService.getBook("Bearer $accessToken")
                booksData = response.books
                println("Successfully fetched book list!")
            } catch (e: retrofit2.HttpException) {
                println("에러1: ${e.response()?.errorBody()?.string()}")
            } catch (e: java.net.UnknownHostException) {
                println("에러2")
            } catch (e: Exception) {
                println("에러3: ${e.message}")
            }
        }
    }
}