import java.util.ArrayList;
import java.util.TreeSet;


public class Priority extends StrategyEscalonador{
	
	Priority(ArrayList<Processo> processos){
		processosEstadoPronto = new TreeSet<Processo>(); 
		this.processos = processos;
		for(Processo processo : processos){
			processosEstadoPronto.add(processo);
		}
		tempoCorrente = getMenorTempoDeChegada();
	}
	
	@Override
	public ArrayList<Execucao> escalonar() {
		ArrayList<Execucao> historico = new ArrayList<>();
		while(!processosEstadoPronto.isEmpty()){
            processoCorrente = pollProcessoDisponivelComMaiorPrioridade();
            
            if(processoCorrente.getTempoChegada() > tempoCorrente){
                tempoCorrente = processoCorrente.getTempoChegada();
            }
            tempoCorrente += tempoTrocaContexto;
            processoCorrente.mandaParaCPU(tempoCorrente, processoCorrente.getBurstTime());
            int tempoFinal = tempoCorrente + processoCorrente.getBurstTime();
            Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
            historico.add(execucaoCorrente);
            tempoCorrente = tempoFinal;                
        }
        escalonamento = historico;
		return historico;
	}
	
	private int getMenorTempoDeChegada(){
		int menorTempoDeChegada = Integer.MAX_VALUE;
		for(Processo p : processosEstadoPronto){
			if(p.getTempoChegada() < menorTempoDeChegada ){
				menorTempoDeChegada = p.getTempoChegada();
			}
		}
		
		return menorTempoDeChegada;	
	}
	
	private Processo pollProcessoDisponivelComMaiorPrioridade(){
		Processo processoEscolhido = processosEstadoPronto.first();
		boolean condicao;
		for(Processo p : processosEstadoPronto){
			if(p.getPrioridade() > processoEscolhido.getPrioridade()){
				condicao = p.getTempoChegada() <= tempoCorrente || ((processoEscolhido.getTempoChegada() > tempoCorrente) && (p.getTempoChegada() < processoEscolhido.getTempoChegada()));
			}
			else {
				condicao = p.getTempoChegada() < processoEscolhido.getTempoChegada();
			}	
 
			if(condicao){
				processoEscolhido = p;
			}
		
		}
		processosEstadoPronto.remove(processoEscolhido);
		return processoEscolhido;
	}

	
	

}
