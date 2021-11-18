package university.gamerp.routes

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import university.gamerp.models.Place
import java.io.File

const val PATH = "C:/Users/7/Desktop/sets/"
val input_file = File("C:/Users/7/Desktop/Polyarnyy_Skazka-o-samoubiystve-_RuLit_Me.txt")

fun generate_text(text: String): String {
    input_file.writeText(text)
    val run = Runtime.getRuntime().exec("C:/Users/7/PycharmProjects/pythonProject7/venv/Scripts/python.exe C:/Users/7/PycharmProjects/pythonProject7/hello.py")
    run.waitFor()
    val output_file = File("C:/Users/7/Desktop/res.txt")
    return output_file.readText()
}

fun Route.generateStoryRoute() {
    route("/generateStory") {
        post {
            val data = call.receive<Place>()
            var input = File( PATH + data.setting + "/" + data.room + ".txt").readText()
            var res = ""

            data.descriptionRoom = if ((0..20).random() != 0) {
                generate_text(input)
            } else {
                input
            }


            if (data.persons.isNotEmpty()) {
                data.persons.forEach {
                    input = File( PATH + data.setting + "/" + it.classPerson + "_" + it.item + ".txt").readText()
                    it.description = if ((0..20).random() != 0) {
                        generate_text(input)
                    } else {
                        input
                    }
                }
            }

            call.respond(data)
        }

        get {
            call.respond("Здравствуйте")
        }

    }
}