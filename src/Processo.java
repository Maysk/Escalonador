/**
 *
 * @author CaioViktor
 */
public class Processo implements Comparable{
    private int id;//Indetificador do processo;
    private int burstTime;//Quantidade de tempo de processamento inteiro do processo;
    private int tempoChegada;//tempo de chegada do processo ;
    private int prioridade; // Ordenador do processo;
    private int turnAround;// Tempo do início ao fim do processo
    private int tempoEspera;//Tempo que o processo passou na fila de prontos + fila de espera
    private int tempoResposta;//Tempo até haver o primeiro processamento;
    private int burstTimeRestante;//Tempo sobrando até o processo ser acabado
    private int esperandoDesde;//Tempo de quando ele começou a espera

    //Construtores
    public Processo(int id, int burstTime, int tempoChegada, int prioridade){
        this.id = id;
        this.burstTime = burstTime;
        this.tempoChegada = tempoChegada;
        this.prioridade = prioridade;
        turnAround = 0;
        tempoEspera = 0;
        tempoResposta = Integer.MAX_VALUE;
        burstTimeRestante = burstTime;
        esperandoDesde = tempoChegada;
    }
    
    public Processo(){
        this(0,0,0,0);
    }
    //Construtores
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getTurnAround() {
        return turnAround;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public int getTempoResposta() {
        return tempoResposta;
    }

    public int getBurstTimeRestante() {
        return burstTimeRestante;
    }

    public void setTurnAround(int turnAround) {
        this.turnAround = turnAround;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public void setTempoResposta(int tempoResposta) {
        this.tempoResposta = tempoResposta;
    }

    public void setBurstTimeRestante(int burstTimeRestante) {
        this.burstTimeRestante = burstTimeRestante;
    }
    
    //Getters & Setters
    
    public String toString(){
        return tempoChegada + ", " + id + ", " + burstTime + ", " + prioridade;
    }
    
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Processo))
            return false;
        Processo outro = (Processo) o;
        return !(this.getId() != outro.getId() || this.getBurstTime() != outro.burstTime || this.getTempoChegada() != outro.tempoChegada);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.burstTime;
        hash = 83 * hash + this.tempoChegada;
        return hash;
    }
    
    public int compareTo(Object o){
        if(!(o instanceof Processo))
            throw new IllegalArgumentException("Tipo do argumento deve ser Processo");
        Processo outro = (Processo) o;
        int retorno ;
        if(outro.equals(this))
            retorno = 0;
        else
            retorno = this.prioridade - outro.prioridade;
        if(retorno == 0)
            retorno = this.getId() - outro.getId();
        return retorno;
    }
    public int mandaParaCPU(int tempoCorrente,int quantidadeTempo){ //Atualiza dados do processo e retorna tempo não usado em CPU caso quantidadeTempo seja > que burstTimeRestante
        int tempoNaoUsado = 0;
        if(burstTimeRestante == 0)
            return quantidadeTempo;
        if(burstTime == burstTimeRestante)//primeira execuão
            tempoResposta = tempoCorrente - tempoChegada;
        	if(tempoResposta<0){
        		tempoResposta = 0;
        	}
        
        tempoEspera += tempoCorrente - esperandoDesde;
        esperandoDesde = tempoCorrente + quantidadeTempo;
        
        if(tempoEspera<0){
        	tempoEspera = 0;
        }
        
        if(quantidadeTempo > burstTimeRestante){
            tempoNaoUsado = quantidadeTempo - burstTimeRestante;
        }
        burstTimeRestante = burstTimeRestante - quantidadeTempo;
        
        if(burstTimeRestante <= 0){
            burstTimeRestante = 0;
            turnAround = tempoCorrente + quantidadeTempo - tempoNaoUsado - tempoChegada;
            esperandoDesde = turnAround;
        }
        return tempoNaoUsado;
    }
}
