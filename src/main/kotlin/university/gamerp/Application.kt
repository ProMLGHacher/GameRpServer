package university.gamerp

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import university.gamerp.plugins.configureRouting

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    //plugins
    install(ContentNegotiation) {
        gson()
        gson {
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }

    //Routes
    configureRouting()

}
