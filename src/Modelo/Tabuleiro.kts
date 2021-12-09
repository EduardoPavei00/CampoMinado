import javax.security.sasl.AuthorizeCallback
import kotlin.random.Random

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
        novocampo.onEvento(this :: verificarVitoriaOuDerrota)
        campos[linha].add(novocampo)
    }
    private fun associarVizinhos() {
        forEachCampo { associarVizinhos(it) }
    }
    private fun associarVizinhos(campo :campo){
        val(linha, coluna) = campo
        val linhas = arrayOf(linha - 1, linha, linha + 1)
        val colunas = arrayOf(coluna - 1, coluna, linha + 1)

        linhas.forEach{l ->
            colunas.forEach{c ->
                val atual = campos.GetOrNull(l)?.GetOrNull(c)
                atual?.takeIf {campo != it}?.let{ campo.addVizinho(it)}
            }
        }
    }
    private fun sortearMinas(){
        val gerador = Random()

        var linhaSorteada = -1
        var colunaSorteada = -1
        var qdMinasAtual = 0

        while (qtMinasAtual < this.qdMinas){
            linhaSorteada = gerador.nextInt(qdLinhas)
            colunaSorteada = gerador.nextInt(qdcolunas) // colocando as minas até numero maximo de minas

            val campoMinado = campos[linhaSorteada][colunaSorteada]// se o campo tiver seguro, coloque uma mina
            if (campoMinado.seguro) {
                campoMinado.minar()
                qdMinasAtual++
            }
        }
    }
    private fun objetivoAlcancado(): Boolean{
        var jogadorGanhou = true
        forEachCampo { if (!it.objetivoAlcancado) jogadorGanhou = false }
        return jogadorGanhou
    }
    private fun verificarVitoriaOuDerrota(campo: Campo, evento: CampoEvento)
    if( evento == CampoEvento.EXPLOSAO){
        callbacks.forEach { it ( TabuleiroEvento.DERROTA ) }
        else if (objetivoAlcancado()){
            callbacks.forEach { it ( TabuleiroEvento.VITORIA) }
        }
    }
    fun forEachCampo(callback: (Campo) -> Unit){
        campos.forEach {linha -> linha.forEach(callback)} //percorrer o tabuleiro todo
    }
    fun onEvento(callback: (TabuleiroEvento) -> Unit){
        callbacks.add(callback)
    }
    fun reiniciar(){
        forEachCampo { it.reiniciar() }
        sortearMinas()
    }
}




