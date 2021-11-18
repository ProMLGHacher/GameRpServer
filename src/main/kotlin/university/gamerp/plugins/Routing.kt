package university.gamerp.plugins

import io.ktor.application.*
import io.ktor.routing.*
import university.gamerp.routes.generateStoryRoute

fun Application.configureRouting() {
    routing {

        generateStoryRoute()

    }
}
