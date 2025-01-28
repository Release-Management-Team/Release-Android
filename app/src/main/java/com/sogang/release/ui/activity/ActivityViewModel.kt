package com.sogang.release.ui.activity

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// Data Models
data class ActivityResult(
    val activities: List<ActivityDTO>
)

data class ActivityDTO(
    val type: String,
    val name: String,
    val description: String,
    val leader: String,
    val members: List<String>,
    val tags: List<String>,
    val state: String,
    val image: String,
    val link: String
)

data class EventResponse(
    val events: List<EventDTO>
)

data class EventDTO(
    val name: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val place: String
)

// UI State
sealed class ActivityTab {
    object Study : ActivityTab()
    object Event : ActivityTab()
}

// ViewModel
class ActivityViewModel : ViewModel() {
    var activityData by mutableStateOf<List<ActivityDTO>>(emptyList())
        private set
    var eventData by mutableStateOf<List<EventDTO>>(emptyList())
        private set

    var selectedTab by mutableStateOf<ActivityTab>(ActivityTab.Study)
        private set

    init {
        fetchActivityData()
        fetchEventData()
    }

    fun fetchActivityData() {
        viewModelScope.launch {
            // Mock data for activities
            activityData = listOf(
                ActivityDTO(
                    type = "Study",
                    name = "Kotlin Study Group",
                    description = "A group focused on learning Kotlin for Android development.",
                    leader = "Alice",
                    members = listOf("Bob", "Charlie", "Dave"),
                    tags = listOf("Kotlin", "Android", "Programming"),
                    state = "Active",
                    image = "https://example.com/image1.jpg",
                    link = "https://example.com/activity1"
                ),
                ActivityDTO(
                    type = "Workshop",
                    name = "UI/UX Design Workshop",
                    description = "An interactive workshop on modern UI/UX design principles.",
                    leader = "Eve",
                    members = listOf("Frank", "Grace"),
                    tags = listOf("Design", "Workshop"),
                    state = "Upcoming",
                    image = "https://example.com/image2.jpg",
                    link = "https://example.com/activity2"
                ),
                ActivityDTO(
                    type = "Seminar",
                    name = "AI in Healthcare",
                    description = "Exploring the role of AI technologies in modern healthcare systems.",
                    leader = "Mallory",
                    members = listOf("Trent", "Oscar"),
                    tags = listOf("AI", "Healthcare", "Innovation"),
                    state = "Completed",
                    image = "https://example.com/image3.jpg",
                    link = "https://example.com/activity3"
                )
            )
        }
    }

    fun fetchEventData() {
        viewModelScope.launch {
            // Mock data for events
            eventData = listOf(
                EventDTO(
                    name = "Tech Conference 2024",
                    description = "A conference discussing the latest trends in technology.",
                    startTime = "2024-06-01T10:00:00",
                    endTime = "2024-06-01T18:00:00",
                    place = "Tech Convention Center"
                ),
                EventDTO(
                    name = "Startup Pitch Day",
                    description = "An opportunity for startups to pitch their ideas to investors.",
                    startTime = "2024-07-15T09:00:00",
                    endTime = "2024-07-15T17:00:00",
                    place = "Startup Hub"
                ),
                EventDTO(
                    name = "Cybersecurity Summit",
                    description = "Discussing cybersecurity challenges and solutions in the digital era.",
                    startTime = "2024-08-20T08:30:00",
                    endTime = "2024-08-20T16:00:00",
                    place = "SecureTech Hall"
                )
            )
        }
    }

    fun switchTab(tab: ActivityTab) {
        selectedTab = tab
    }
}
