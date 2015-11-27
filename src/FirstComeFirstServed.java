
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
        int ordem = 0;
        for(Processo processo : processos){
            processo.setPrioridade(ordem);
            processosEstadoPronto.add(processo);
            ordem++;
        }
        tempoCorrente = 0;
    }
    public ArrayList<Execucao> escalonar(){
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
