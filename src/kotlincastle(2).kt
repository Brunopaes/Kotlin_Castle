const val WELCOME	= "Bem vindo ao	Castelo	de Kotlin!"	
const val OBJETIVO = "Seu objetivo será encotrar a coroa sagrada em um dos aposentos do castelo."	
const val SAIR = "Até a próxima!"
const val ALTURA = 4 // Altura do tabuleiro (eixo y)
const val LARGURA = 4 // LArgura do tabuleiro (eixo x)
var x:Int = 2 // Posicao X inicial do jogador
var y:Int = 3 // Posiao Y inicial do jogador
var tabuleiro = Tabuleiro() // instancia o tabuleiro

fun main(args:Array<String>){

    println(WELCOME)
    println(OBJETIVO)
    print(">> ")

    var	comando	= readLine()
    
    while (comando!!.toUpperCase() != "SAIR") {
        println(converteMaiusculo(getPalavras(comando)))
        interpretar(converteMaiusculo(getPalavras(comando)))
        print(">> ")
        comando	= readLine()
    }

    println(SAIR)
}

fun interpretar(palavras:List<String>) {
    if (palavras[0] == "IR") {
        ir(palavras)
    } else {
        println("Comando inválido!")
    }
}

fun ir(palavras:List<String>) {
    // IR NORTE, IR PARA O NORTE, IR PARA NORTE..., NORTE
    if (palavras.contains("NORTE") && y > 0) {
        y-- // y controla eixo NORTE / SUL
    } else if (palavras.contains("SUL") && y < ALTURA - 1) {
        y++ // y controla eixo NORTE / SUL
    } else if (palavras.contains("LESTE") && x < LARGURA - 1) {
        x++ // x controla eixo LESTE / OESTE
    }else if (palavras.contains("OESTE") && x > 0) {
        x-- // x controla eixo LESTE / OESTE
    }else {
        println("Movimento inválido!")
    }
    println ("X = $x, Y = $y")
    println (tabuleiro.getPosicao(x, y).descricao)
}
fun getPalavras(comando:String?):List<String> {
    return comando!!.split(" ")
}

fun converteMaiusculo(palavras:List<String>):List<String> {
    var ret:MutableList<String> = mutableListOf<String>()	
    for (p in palavras) {
        ret.add(p.toUpperCase())
    }
    return ret
}

// representa o tabuleiro 4 x 4 posicoes
class Tabuleiro {

// VERSAO 2.0
var tabuleiro = arrayOf<Array<Posicao>>(arrayOf<Posicao>(Posicao("Você está na cozinha", _sul = true), Posicao("Você está na cozinha", _sul = true, _leste = true), Posicao("Você está no corredor interno", _oeste = true, _sul = true), Posicao("Você está na sala do trono", _sul = true)),
		arrayOf<Posicao>(Posicao("Você está na cozinha", _leste = true), Posicao("Você está na cozinha", _oeste = true, _norte = true), Posicao("Você está no corredor interno", _norte = true, _sul = true), Posicao("Você está na sala do trono. Tem um Goblin que gosta de café doce aqui...", _norte = true, _sul = true)),
		arrayOf<Posicao>(Posicao("Você está no quarto escuro", _sul = true), Posicao("Você está no corredor interno", _leste = true, _oeste = true, _sul = true), Posicao("Você está no corredor interno", _norte = true, _sul = true, _leste = true, _oeste = true), Posicao("Você está no corredor interno", _oeste = true, _sul = true)), 
		arrayOf<Posicao>(Posicao("Você está no jardim", _norte = true), Posicao("Você está no corredor interno", _norte = true), Posicao("Você está na entrada principal", _norte = true), Posicao("Você está no corredor interno", _norte = true))) 

fun getPosicao(x:Int, y:Int):Posicao {
    return tabuleiro[y][x]
}
}

// representa cada uma das posicoes do tabuleiro
class Posicao(descricao_:String,
              _norte:Boolean = false,
              _sul:Boolean = false,
              _leste:Boolean = false,
              _oeste:Boolean = false) {
    var descricao = descricao_
    var norte = _norte
    var sul = _sul
    var leste = _leste
    var oeste = _oeste
}