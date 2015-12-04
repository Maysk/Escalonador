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
		Processo processoFalso = new Processo(0,0,Integer.MAX_VALUE,0);
		int tempoFinal=0;
		processoCorrente = null;
		//TODO: Adiocionar o tempo de troca de contexto.
		while(!processosEstadoPronto.isEmpty()){
			if(processoCorrente == null){
				processoCorrente = pollProcessoDisponivelComMaiorPrioridade();
			}
			
			if(processoCorrente.getTempoChegada() > tempoCorrente){
                tempoCorrente = processoCorrente.getTempoChegada();
            }
			tempoCorrente = tempoCorrente + tempoTrocaContexto;
			tempoFinal = tempoCorrente + processoCorrente.getBurstTimeRestante();
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
				if(processoCorrente.getTempoChegada() == tempoCorrente - tempoTrocaContexto){
					quantidadeDeTempo = processoCandidato.getTempoChegada() - processoCorrente.getTempoChegada() - tempoTrocaContexto;
				}
				else{
					quantidadeDeTempo = processoCandidato.getTempoChegada() - processoCorrente.getTempoChegada() - tempoCorrente;
				}
				
				if(quantidadeDeTempo <0){quantidadeDeTempo = 0;}
				
				
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
