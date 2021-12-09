import javax.security.sasl.AuthorizeCallback

enum class TabuleiroEvento { VITORIA, DERROTA }
class Tabuleiro( val qdLinhas: Int, val qdColunas: Int, qdMinas: Int){
    private val campos = Arraylist<Arraylis<Campo>>()
    private val  callbacks = Arraylist<(TabuleiroEvento) -> Unit>()

    init {
        gerarCampo()
        associaarVizinhos()
        sortearMinas()
    }
    private fun gerarCampo(){
        for(linha in 0 until qdLinhas)
            campo.add(arraylist())
        for(coluna in 0 until qdColunas)
            var novocampo = campo (linha, coluna)
        campos[linha].add(novocampo)
    }
    private fun associarVizinhos(){

    }
    private fun sortearMinas(){
    }

    fun ForEachCampo(callback: (Campo) -> Unit){
        campos.forEach {linha -> linha.forEach(callback)} //percorrer o tabuleiro todo

    }
}




