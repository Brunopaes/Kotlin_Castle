// System constants
const val welcome = "Welcome to the Kotlin Castle!"
const val goal = "Your goal is find the sacred crown and don't get killed."
const val exit_ = "See you soon!!\nByyee\n"
const val message_commands = "Use the commands in portuguese! Without money for converting to english!\n the object names are in spanglish"

// Board size
const val width = 4
const val height = 4

// Initial position
var x: Int = 2
var y: Int = 3

var board: Board = Board()

var carry_list: MutableList<Item> = mutableListOf()

var goblin = true
var end = true

abstract class Item constructor(desc: String) {
    var desc: String? = desc
}

// Items (Scenario objects)
class Candle : Item(desc = "Candle")

class Key : Item(desc = "Key")

class Chest : Item(desc = "Chest")

class Sword : Item(desc = "Sword")

class Coffee : Item(desc = "Coffee")

class Sugar : Item(desc = "Sugar")

class Crown : Item(desc = "Crown")


// Get and Parse Command
fun advance(words: List<String>) {
    if (words.contains("NORTE") && board.get_position(x, y).north) {
        if (y > 0) {
            if (x == 3 && y == 1 && goblin) {
                print("Goblin: Strange language qwerty qwertyui gfdsa (You'll shall not pass!!!)")
                return
            }
            y -= 1
            println("Position on the board: $x, $y")
            print(">> ")

        }

        else {
            println("Not allowed!")
            print(">> ")
        }
    }

    else if (words.contains("SUL") && board.get_position(x, y).south) {
        if (y < height - 1) {
            y += 1

            println("Position on the board: $x, $y")
            print(">> ")
        }

        else {
            println("Not allowed!")
            print(">> ")
        }
    }

    else if (words.contains("LESTE") && board.get_position(x, y).east) {
        if (x < width - 1) {
            x += 1
            println("Position on the board: $x, $y")
            print(">> ")
        }

        else {
            println("Not allowed!")
            print(">> ")
        }
    }

    else if (words.contains("OESTE") && board.get_position(x, y).west) {
        if (x > 0) {
            x -= 1
            println("Position on the board: $x, $y")
            print(">> ")

        }

        else {
            println("Not allowed!")
            print(">> ")
        }
    }

    else {
        println("Exception at <08ab12fa1> More than 9000 pirateNinjas attacked you!")
    }

    println(board.get_position(x, y).description)


    if (x == 0 && y == 2) {
        var temVela = false
        for (obj in carry_list) {

            temVela = (obj is Candle)
            if (temVela) {
                break
            }
        }

        if (temVela) {
            println("You have a candle! Use it for exploring the environment.")
            print(">> ")
        }

        else {
            println("You dumb ass! Entered on a dark room and get killed by an Goblin")

            print("Game Over")

            return
        }
    }
}

fun get_words(command_: String?): List<String> {
    return command_!!.split(" ")
}

fun upper_conversor(comando: List<String>): List<String> {
    val ret: MutableList<String> = mutableListOf<String>()

    for (p in comando) {
        ret.add(p.toUpperCase())
    }

    return ret
}

fun interpreter(words: List<String>) {
    if (words[0] == "IR") {
        advance(words)
        see()
    }

    else if (words.contains("CARGA")) {
        if (carry_list.size == 0) {
            println("There are no objects for carry")
        }

        else {
            carry()
        }
    }

    else if (words.contains("PEGAR")) {
        get_item()
    }

    else if (words.contains("SOLTAR")) {
        drop()
    }

    else if (words.contains("ABRIR")) {
        open()
    }

    else if (words.contains("ATACAR")) {
        fight()
    }

    else {
        println("Invalid Command")
    }
}


// Actions
fun see() {
    for (obj in board.get_position(x, y).items_list) {
        println("Object: " + obj.desc)
    }
}

fun get_item() {
    for (obj in board.get_position(x, y).items_list) {

        if (carry_list.size > 3 || obj.equals(3)) {
            println("You are already carrying 3 items! Please Drop one")
        }

        else if (carry_list.size <= 3 && !carry_list.contains(Chest()) || obj.equals(board.get_position(x, y).items_list)) {
            println(obj.desc)

            if (x == 3 && y == 3 && obj is Chest) {
                carry_list.remove(obj)
                println("${obj.desc} Removed")
            }

            else {
                carry_list.add(obj)
                println("${obj.desc} Being carried")
            }
        }

        else {
            println("não exite objeto aqui")
        }

        if (obj is Crown) {
            endgame(end)
        }
    }
}

fun drop() {
    for (obj in board.get_position(x, y).items_list)
        if (carry_list.contains(obj)) {
            carry_list.remove(obj)
            println("${obj.desc} droped")
        }

        else {
            println("You do not have this object")
        }
}

fun carry() {
    for (obj in carry_list) {

        println("Carried Objects: ${obj.desc}")
    }
}

fun open() {
    if (x == 3 && y == 3) {
        var is_key = false
        var is_chest = false

        for (obj in carry_list) {
            is_key = obj is Key

            if (is_key) {
                break
            }

            is_chest = obj is Key

            if (is_chest) {
                break
            }
        }

        if (is_key) {
            println("Opening the chest!")
            println("There's a sugar bag inside it")
            board.get_position(3, 3).add_item(Sugar())

        }

        else if (x == 3 && y == 3) {
            println("No keys - find it and come back here for discovering new piroquio's secrets!")
            print(">> ")
        }
    }

    else {
        println("There's nothing for opening!")
        println(">> ")
    }
}

fun fight() {
    if (x == 3 && y == 1) {
        var is_sword = false
        var is_coffee = false
        var is_sugar = false

        for (obj in carry_list) {
            is_sword = obj is Sword
            if (is_sword) {
                break

            }
        }

        if (is_sword) {
            println("Killed by a Goblin")
        }

        else {
            println("Killed by a swordless Gobril")
        }

        for (obj in carry_list) {
            is_coffee = obj is Coffee
            if (is_coffee) {
                break
            }
        }

        if (is_coffee) {
            println("You gave a sugarless coffee for a Goblin! Goblin thinks coffee precious! You got in trouble")
        }

        else {
            println("What a shame! You dont have coffee")

        }

        for (obj in carry_list) {
            is_sugar = obj is Sugar
            if (is_sugar) {
                break
            }
        }

        if (is_sword && is_coffee && is_sugar) {
            goblin = false
            println("The precious coffee killed the Goblin! Go ahead")
            print(">> ")
        }

        else {
            println("Items missing")
            print(">> ")
        }

    }

    else {
        print("There's nothing to attack!")
        print(">> ")
    }
}

fun endgame(fim: Boolean) {
    if (fim) {
        print("You got it! End game")
    }

    else {
        print("Take the crown and end the game!")
    }
}

// Board Structure and Position
class Board {
    var board = arrayOf<Array<PlayerPosition>>(
        arrayOf<PlayerPosition>(
            PlayerPosition("You are in the kitchen 1", south_ = true),
            PlayerPosition("You are in the kitchen 2", south_ = true, east_ = true),
            PlayerPosition("You are in the within corridor 1", west_ = true, south_ = true),
            PlayerPosition("It appears to be GoT! You are in Throne Room", south_ = true)
        ),
        arrayOf<PlayerPosition>(
            PlayerPosition("You are in the kitchen 3", east_ = true, north_ = true),
            PlayerPosition("You are in the kitchen 4", west_ = true, north_ = true),
            PlayerPosition("You are in the within corridor 2", north_ = true, south_ = true),
            PlayerPosition("It appears to be GoT! You are in Throne Room!\n There's a Goblin inside it (He likes non sugarless coffee)", north_ = true, south_ = true)
        ),
        arrayOf<PlayerPosition>(
            PlayerPosition("Yoy are in a dark room", south_ = true, east_ = true),
            PlayerPosition("You are in the within corridor 3", east_ = true, west_ = true, south_ = true),
            PlayerPosition("You are in the within corridor 4", north_ = true, south_ = true, east_ = true, west_ = true),
            PlayerPosition("You are in the within corridor 5", west_ = true, south_ = true, north_ = true)
        ),
        arrayOf<PlayerPosition>(
            PlayerPosition("Wow! In a garden you are", north_ = true),
            PlayerPosition("You are in the within corridor", north_  = true),
            PlayerPosition("You ate at the main entrance", north_ = true),
            PlayerPosition("You are in the within corridor", north_ = true)
        )
    )

    fun get_position(x: Int, y: Int): PlayerPosition {
        return board[y][x]
    }

    init {
        get_position(0, 0).add_item(Coffee())
        get_position(0, 1).add_item(Sword())
        get_position(0, 3).add_item(Key())
        get_position(1, 3).add_item(Candle())
        get_position(3, 0).add_item(Crown())
        get_position(3, 3).add_item(Chest())

    }
}

class PlayerPosition(desc_: String, north_: Boolean = false, south_: Boolean = false, east_: Boolean = false, west_: Boolean = false) {
    var description = desc_
    var north = north_
    var south = south_
    var east = east_
    var west = west_

    var items_list = mutableListOf<Item>()

    fun add_item(objeto: Item) {
        items_list.add(objeto)
    }

    fun pop_item(objeto: Item) {
        items_list.remove(objeto)
    }
}

// Main function
fun main(args: Array<String>) {
    println(welcome)
    println(goal)
    println(message_commands)

    print(">> ")

    var command = readLine()

    println("Command: $command")

    while (command!!.toUpperCase() != "SAIR") {

        print(">> ")
        println(upper_conversor(get_words(command)))

        interpreter(upper_conversor(get_words(command)))
        command = readLine()

    }
    println(exit_)
}
