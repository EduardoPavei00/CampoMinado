import javax.security.sasl.AuthorizeCallback

enum class TabuleiroEvento { VITORIA, DERROTA }
    class Tabuleiro( val qdLinhas: Int, val qdColunas: Int, qdMinas: Int){
    private val campos = Arraylist<Arraylis<Campo>>()
    private val  callbacks = Arraylist<(TabuleiroEvento) -> Unit>()

    init {
        gerarcampo()
        associaarVizinhos()
        sortearMinas()
    }
        private fun gerarcampo()
    }
        private fun associarVizinhos(){
    }
        private fun sortearMinas(){
        }

    fun ForEachCampo(callback: (Campo) -> Unit){
        campos.forEach {linha -> linha.forEach(callback)} //percorrer o tabuleiro todo

    }
    }




