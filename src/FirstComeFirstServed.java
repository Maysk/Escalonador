
import java.util.ArrayList;
import java.util.TreeSet;
/**
 *
 * @author CaioViktor
 */
public class FirstComeFirstServed extends StrategyEscalonador{
    public FirstComeFirstServed(ArrayList<Processo> processos){
        processosEstadoPronto = new TreeSet<>();
        this.processos = processos;
        for(Processo processo : processos){
            processo.setPrioridade(processo.getTempoChegada());
            processosEstadoPronto.add(processo);
        }
        tempoCorrente = 0;
    }
    public ArrayList<Execucao> escalonar(){
        ArrayList<Execucao> historico = new ArrayList<>();
        while(!processosEstadoPronto.isEmpty()){
            processoCorrente = processosEstadoPronto.pollFirst();
            
            if(processoCorrente.getTempoChegada() > tempoCorrente){
              tempoCorrente = processoCorrente.getTempoChegada();
            }
            tempoCorrente += tempoTrocaContexto;
            int tempoNaoUsado = processoCorrente.mandaParaCPU(tempoCorrente, processoCorrente.getBurstTime());
            int tempoFinal = tempoCorrente + processoCorrente.getBurstTime() - tempoNaoUsado;
            Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
            historico.add(execucaoCorrente);
            tempoCorrente = tempoFinal;
        }
        escalonamento = historico;
        return historico;
    }
}
