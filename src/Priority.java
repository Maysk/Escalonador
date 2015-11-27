import java.util.ArrayList;
import java.util.TreeSet;


public class Priority extends StrategyEscalonador{
	
	Priority(ArrayList<Processo> processos){
		processosEstadoPronto = new TreeSet<Processo>();
		this.processos = processos;
		for(Processo processo : processos){
			processosEstadoPronto.add(processo);
		}
		tempoCorrente = 0;
	}
	
	@Override
	public ArrayList<Execucao> escalonar() {
		ArrayList<Execucao> historico = new ArrayList<>();
		while(!processosEstadoPronto.isEmpty()){
            processoCorrente = processosEstadoPronto.pollFirst();
            int tempoNaoUsado = processoCorrente.mandaParaCPU(tempoCorrente, processoCorrente.getBurstTime());
            int tempoFinal = tempoCorrente + processoCorrente.getBurstTime() - tempoNaoUsado;
            Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
            historico.add(execucaoCorrente);
            tempoCorrente = tempoFinal;                
        }
		
		
		return historico;
	}

}
