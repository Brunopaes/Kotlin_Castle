class Messages {
    companion object {
        const val welcomeMessage = "Bem vindo ao castelo de Kotlin!"
        const val goalMessage = "Seu objetivo será encontrar a coroa sagrada em um dos aposentos do castelo"
        const val exit = "Até a próxima!"
    }
}

fun interpreter(command: List<String>?) {
    if (command?.contains("IR")!!) {
        println("Hello")
    }
}

fun getWords(command: String?): List<String>? {
    return command?.split(" ")
}

fun boardFormat() {
    val height = 4; val width = 4
    val x = 2; val y = 3
}

fun main(args: Array<String>) {
    val obj = Messages.Companion

    println(obj.welcomeMessage)
    println(obj.goalMessage)

    do {
        var command = readLine()?.toUpperCase()

        interpreter(getWords(command))

    } while(command != "SAIR")

    println(obj.exit)

}