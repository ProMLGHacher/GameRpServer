package university.gamerp.models

import kotlinx.serialization.Serializable

@Serializable
data class Place (
    val setting: String,
    val room: String,
    val persons: List<Person>,
    var descriptionRoom: String = ""
)