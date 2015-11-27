/**
 *
 * @author CaioViktor
 */
public class Execucao {
    private Processo processo;
    private int tempoInicio;
    private int tempoFim;

    public Execucao(Processo processo, int tempoInicio, int tempoFim) {
        this.processo = processo;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
    }
    public Execucao() {
        this(null,0,0);
    }
    
    
    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public int getTempoInicio() {
        return tempoInicio;
    }

    public void setTempoInicio(int tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public int getTempoFim() {
        return tempoFim;
    }

    public void setTempoFim(int tempoFim) {
        this.tempoFim = tempoFim;
    }
    
    public String toString(){
        return "P" + processo.getId() + ": " + tempoInicio + " - " + tempoFim;
    }
    
}
