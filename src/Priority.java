import java.util.ArrayList;
import java.util.TreeSet;


public class Priority extends StrategyEscalonador{
	ListaDeProcessos lProcess;
	Priority(ArrayList<Processo> processos){
		lProcess = new ListaDeProcessos();
		this.processos = processos;
		for(Processo processo : processos){
			lProcess.addProcess(processo);
		}
		tempoCorrente = lProcess.getMenorTempoDeChegada();
	}
	
	@Override
	public ArrayList<Execucao> escalonar() {
		ArrayList<Execucao> historico = new ArrayList<>();
		
		while(!lProcess.isEmpty()){
            processoCorrente = lProcess.pollProcessoComMenorTempoDeChegada();
            int tempoNaoUsado = processoCorrente.mandaParaCPU(tempoCorrente, processoCorrente.getBurstTime());
            int tempoFinal = tempoCorrente + processoCorrente.getBurstTime() - tempoNaoUsado;
            Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
            historico.add(execucaoCorrente);
            tempoCorrente = tempoFinal;                
        }
		
		return historico;
	}

}
