import java.util.ArrayList;
import java.util.TreeSet;


public class PriorityPreemptive extends StrategyEscalonador{
	
	PriorityPreemptive(ArrayList<Processo> processos){
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
		systemTimeInit = System.nanoTime();
		Processo processoFalso = new Processo(0,0,Integer.MAX_VALUE,0);
		processoCorrente = null;
		while(!processosEstadoPronto.isEmpty()){
			if(processoCorrente == null){
				processoCorrente = pollProcessoDisponivelComMaiorPrioridade();
			}
			
			if(processoCorrente.getTempoChegada() > tempoCorrente){
                tempoCorrente = processoCorrente.getTempoChegada();
            }
			
			int tempoFinal = tempoCorrente + processoCorrente.getBurstTimeRestante();
			Processo processoCandidato = processoFalso;
			for (Processo a : processosEstadoPronto){
				if(a.getPrioridade() > processoCorrente.getPrioridade() && a.getTempoChegada() <= tempoFinal && a.getPrioridade() >= processoCandidato.getPrioridade() && a.getTempoChegada() < processoCandidato.getTempoChegada()){
					processoCandidato = a;
				}           	
			}
        
			if(processoCandidato.getPrioridade() <= processoCorrente.getPrioridade() || processoCandidato.getTempoChegada()  > tempoFinal){
				processoCorrente.mandaParaCPU(tempoCorrente, processoCorrente.getBurstTime());            
				Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
				historico.add(execucaoCorrente);
				tempoCorrente = tempoFinal;
				processoCorrente = null;
			}
			else{
				int quantidadeDeTempo; 
				if(processoCorrente.getTempoChegada() == tempoCorrente){
					quantidadeDeTempo = processoCandidato.getTempoChegada() - processoCorrente.getTempoChegada();
				}
				else{
					quantidadeDeTempo = processoCandidato.getTempoChegada() - processoCorrente.getTempoChegada() - tempoCorrente;
				}
				tempoFinal = tempoCorrente + quantidadeDeTempo;
				processoCorrente.mandaParaCPU(tempoCorrente, quantidadeDeTempo);
				Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
				historico.add(execucaoCorrente);
				tempoCorrente = tempoFinal;
				processosEstadoPronto.add(processoCorrente);
				processosEstadoPronto.remove(processoCandidato);
				processoCorrente = processoCandidato;
				
				
			}
			
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
		boolean condicao1, condicao2;
		for(Processo p : processosEstadoPronto){
			condicao1 = p.getPrioridade() > processoEscolhido.getPrioridade() && p.getTempoChegada() <= tempoCorrente;
			condicao2 = p.getPrioridade() == processoEscolhido.getPrioridade() && p.getTempoChegada() < processoEscolhido.getTempoChegada() && p.getTempoChegada() < tempoCorrente ;	
			if(condicao1 || condicao2){
				processoEscolhido = p;
			}
		
		}
		processosEstadoPronto.remove(processoEscolhido);
		return processoEscolhido;
	}

}
