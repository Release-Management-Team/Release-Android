import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            // Mock data for books
            booksData = listOf(
                BookDTO(
                    id = "1",
                    title = "Kotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin ProgrammingKotlin Programming",
                    availability = "available",
                    author = "JetBrains",
                    tags = listOf("Programming", "Kotlin"),
                    image = "https://example.com/image1.jpg"
                ),
                BookDTO(
                    id = "2",
                    title = "Compose Essentials",
                    availability = "rented",
                    author = "Google",
                    tags = listOf("Android", "Compose"),
                    image = "https://example.com/image2.jpg"
                ),
                BookDTO(
                    id = "3",
                    title = "Clean Code",
                    availability = "unavailable",
                    author = "Robert C. Martin",
                    tags = listOf("Programming", "Best Practices"),
                    image = "https://example.com/image3.jpg"
                ),
                BookDTO(
                    id = "4",
                    title = "Introduction to Algorithms",
                    availability = "available",
                    author = "Thomas H. Cormen",
                    tags = listOf("Algorithms", "Data Structures"),
                    image = "https://example.com/image4.jpg"
                ),
                BookDTO(
                    id = "5",
                    title = "Artificial Intelligence: A Modern Approach",
                    availability = "rented",
                    author = "Stuart Russell, Peter Norvig",
                    tags = listOf("AI", "Machine Learning"),
                    image = "https://example.com/image5.jpg"
                )
            )
        }
    }
}