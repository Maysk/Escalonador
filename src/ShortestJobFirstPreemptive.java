import java.util.ArrayList;
import java.util.TreeSet;

public class ShortestJobFirstPreemptive extends StrategyEscalonador{

    public ShortestJobFirstPreemptive(ArrayList<Processo> processos){
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
        int tempoNaoUsado, tempoInicial = -1, tempoFinal;
        ArrayList<Execucao> historico = new ArrayList<>();
        systemTimeInit = System.nanoTime();
        this.processoCorrente = new Processo(-1,Integer.MAX_VALUE,Integer.MAX_VALUE,-1);
        
        while(!this.processosEstadoPronto.isEmpty()){            
            Processo proximoProcesso = this.getProcessoComMenorBurstTime();
            
            if(!(this.processoCorrente.getId() == -1) || !(proximoProcesso.getId() == -1)){ //Se ha um processo disponivel
            	if(this.processoCorrente.getId() == proximoProcesso.getId()){ //Se ainda continua no mesmo processo
            		tempoNaoUsado = this.processoCorrente.mandaParaCPU(this.tempoCorrente, 1);
            		
            		if(this.processoCorrente.getBurstTimeRestante() == 0){ //Se processo acabou a execucao
            			historico.add(new Execucao(this.processoCorrente,tempoInicial,this.tempoCorrente+1));
            			this.processosEstadoPronto.remove(this.processoCorrente);            			
            		}
            		
            	}else{ //Se precisa mudar o processo
            		if(this.processoCorrente.getId() != -1 && this.processoCorrente.getBurstTimeRestante() != 0){ 
            			//relativo a primeira entrada || caso tenha que trocar por um ja terminado            		
            			historico.add(new Execucao(this.processoCorrente,tempoInicial,this.tempoCorrente));            			
            		}            		
            		tempoInicial = this.tempoCorrente;
            		this.processoCorrente = proximoProcesso;
            		tempoNaoUsado = this.processoCorrente.mandaParaCPU(this.tempoCorrente, 1);
            		
            		if(this.processoCorrente.getBurstTimeRestante() == 0){ //Se processo acabou a execucao
            			historico.add(new Execucao(this.processoCorrente,tempoInicial,this.tempoCorrente+1));
            			this.processosEstadoPronto.remove(this.processoCorrente);            			
            		}
            	}
            }          	
            this.tempoCorrente = this.tempoCorrente + 1;
        }
        escalonamento = historico;
        return historico;
    }
    
    
    //Mesmo metodo do ShortestJobFirst normal, com diferenca que agora ele nao remove do conjunto
    public Processo getProcessoComMenorBurstTime(){
        Processo atual = new Processo(-1,Integer.MAX_VALUE,Integer.MAX_VALUE,-1);
        for(Processo processo : this.processosEstadoPronto){
            //if(processo.getTempoChegada() <= this.tempoCorrente && processo.getBurstTimeRestante() < atual.getBurstTimeRestante()){
            if(processo.getTempoChegada() <= this.tempoCorrente && 
              (processo.getBurstTimeRestante()<atual.getBurstTimeRestante()  ||  (processo.getBurstTimeRestante()==atual.getBurstTimeRestante() && processo.getTempoChegada()<atual.getTempoChegada()) )){
                atual = processo;
            } 
        }
        return atual;
    }    
}
