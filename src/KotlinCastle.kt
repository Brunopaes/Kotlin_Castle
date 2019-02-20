class Messages {
    companion object {
        const val welcomeMessage = "Bem vindo ao castelo de Kotlin!"
        const val goalMessage = "Seu objetivo será encontrar a coroa sagrada em um dos aposentos do castelo"
        const val exit = "Até a próxima!"
    }
}


fun main(args: Array<String>) {
    val obj = Messages.Companion


    println(obj.welcomeMessage)

    do {
        println(obj.goalMessage)

    } while(readLine()!!.toUpperCase() != "SAIR")

    println(obj.exit)

}
