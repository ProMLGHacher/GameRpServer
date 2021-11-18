package university.gamerp.routes

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import university.gamerp.models.Place
import java.io.File

//путь к сетам
const val PATH = "C:/Users/7/Desktop/sets/"
//путь к выходному файлу питон скрипта
const val OUTPUT_FILE_PATH = "C:/Users/7/Desktop/res.txt"
//путь к входному файлу питон скрипта
const val INPUT_FILE_PATH = "C:/Users/7/Desktop/Polyarnyy_Skazka-o-samoubiystve-_RuLit_Me.txt"
//путь к питону
const val PYTHON_PATH = "C:/Users/7/PycharmProjects/pythonProject7/venv/Scripts/python.exe"
//путь к питон скрипту
const val PYTHON_FILE_PATH = "C:/Users/7/PycharmProjects/pythonProject7/hello.py"

val input_file = File(INPUT_FILE_PATH)

fun generate_text(text: String): String {
    input_file.writeText(text)
    val run = Runtime.getRuntime().exec("$PYTHON_PATH $PYTHON_FILE_PATH")
    run.waitFor()
    val output_file = File(OUTPUT_FILE_PATH)
    return output_file.readText()
}

fun Route.generateStoryRoute() {
    route("/generateStory") {
        post {
            print("Request\n")
            val data = call.receive<Place>()
            var input = File( PATH + data.setting + "/" + data.room + ".txt").readText()

            data.descriptionRoom = if ((0..10).random() != 1) {
                generate_text(input)
            } else {
                input
            }


            if (data.persons.isNotEmpty()) {
                data.persons.forEach {
                    input = File( PATH + data.setting + "/" + it.classPerson + "_" + it.item + ".txt").readText()
                    it.description = if ((0..7).random() != 1) {
                        generate_text(input)
                    } else {
                        input
                    }
                }
            }

            call.respond(data)
        }

        get {
            call.respond("Ok")
        }

    }
}