package university.gamerp.models

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val name: String,
    val classPerson : String,
    val item: String,
    var description: String = ""
)