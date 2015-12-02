import java.util.ArrayList;
import java.util.TreeSet;

public class ShortestJobFirst extends StrategyEscalonador{

    public ShortestJobFirst(ArrayList<Processo> processos){
        this.processos = processos;
        this.processosEstadoPronto = new TreeSet<>();     
        this.tempoCorrente = 0;   
        
        for(Processo processo : processos){
            processo.setPrioridade(processo.getBurstTime());
            this.processosEstadoPronto.add(processo);
        }        
    }
    
    
    @Override
    public ArrayList<Execucao> escalonar() {
        
        ArrayList<Execucao> historico = new ArrayList<>();
        while(!this.processosEstadoPronto.isEmpty()){
            
            this.processoCorrente = this.getProcessoComMenorBurstTime();
            
            if(this.processoCorrente.getId() == -1){                               
                this.tempoCorrente++;
            }else{
                tempoCorrente += tempoTrocaContexto;
                int tempoNaoUsado = this.processoCorrente.mandaParaCPU(this.tempoCorrente, this.processoCorrente.getBurstTime());
                int tempoFinal = this.tempoCorrente + this.processoCorrente.getBurstTime() + tempoNaoUsado;

                Execucao execucaoCorrente = new Execucao(this.processoCorrente,this.tempoCorrente,tempoFinal);
                historico.add(execucaoCorrente);

                this.tempoCorrente = tempoFinal;            
            }
        }
        escalonamento = historico;
        return historico;
    }

    public Processo getProcessoComMenorBurstTime(){
        Processo atual = new Processo(-1,Integer.MAX_VALUE,Integer.MAX_VALUE,-1);
        for(Processo processo : this.processosEstadoPronto){
            //if(processo.getTempoChegada() <= this.tempoCorrente && processo.getBurstTime() < atual.getBurstTime()){
            if(processo.getTempoChegada() <= this.tempoCorrente && 
              (processo.getBurstTime()<atual.getBurstTime()  ||  (processo.getBurstTime()==atual.getBurstTime() && processo.getTempoChegada()<atual.getTempoChegada()) )){
                atual = processo;
            } 
        }
        this.processosEstadoPronto.remove(atual);
        return atual;
    }
    
}
