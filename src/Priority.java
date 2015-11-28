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
            processoCorrente = pollProcessoComMenorTempoDeChegada();
            int tempoNaoUsado = processoCorrente.mandaParaCPU(tempoCorrente, processoCorrente.getBurstTime());
            int tempoFinal = tempoCorrente + processoCorrente.getBurstTime() - tempoNaoUsado;
            Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
            historico.add(execucaoCorrente);
            tempoCorrente = tempoFinal;                
        }
		
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
	
	private Processo pollProcessoComMenorTempoDeChegada(){
		Processo processoEscolhido = processosEstadoPronto.last();
		for(Processo p : processosEstadoPronto){
			if(p.getTempoChegada() < processoEscolhido.getTempoChegada()){
				processoEscolhido = p;
			}
		}
		processosEstadoPronto.remove(processoEscolhido);
		return processoEscolhido;
	}

	
	

}
