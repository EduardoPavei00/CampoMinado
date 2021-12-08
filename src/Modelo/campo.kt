package Model

enum class CampoEvento { ABERTURA, MARCACAO, DESMACAÇAO, EXPLOSAO, REINICIALIZACAO }

data class Campo (val linha:Int,val coluna: Int ){

    private val vizinhos = ArrayList<Campo>()
    private val callbacks = ArrayList<(Campo, CampoEvento) -> Unit>()//funçoes que vao ser disparada depois do evento acontecer

    var marcado: Boolean = false
    var aberto: Boolean = false
    var minado: Boolean = false

    //apenas para leitura
    val desmarcado: Boolean get() = !marcado
    val fechado: Boolean get() = !aberto
    val seguro: Boolean get() = !minado
    val objetivoAlcancado: Boolean get() = seguro && aberto || minado && marcado
    val qtdeVizinhosMinados: Int get() = vizinhos.filter { it.minado }.size// quando abrir coloca o numero de vizinhos minados
    val vizinhacasegura: Boolean
        get() = vizinhos.map { it.seguro }.reduce { resultado, seguro -> resultado && seguro  }

    fun addVizinho(vizinho: Campo){
        vizinhos.add(vizinho)
    }
    fun onEvento(callback: (Campo, CampoEvento) -> Unit){
        callbacks.add(callback)
    }
    fun abrir() {
        if (fechado) {
            aberto = true
            if (minado) {
                callbacks.forEach { it(this, CampoEvento.EXPLOSAO) }
            } else {
                callbacks.forEach {
                    it(this, CampoEvento.ABERTURA)
                    vizinhos.filter { it.fechado && it.seguro && vizinhacasegura }
                        .forEach { it.abrir() }// abrir tudo que possivel
                }
            }
        }
        fun altererarMarcacao() {
            if (fechado){
        marcado = !marcado
        val evento = if (marcado) CampoEvento.MARCACAO else CampoEvento.DESMACAÇAO
        callbacks.forEach { it(this, evento) }
        }
    }

    fun minar(){
        minado = true
    }
    fun reiniciar(){
        aberto = false
        minado = false
        marcado = false
        callbacks.forEach{ it( this, CampoEvento.REINICIALIZACAO)}
    }
}