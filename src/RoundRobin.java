
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author CaioViktor
 */
public class RoundRobin extends StrategyEscalonador{
    private int timeQuantum;
    public RoundRobin(ArrayList<Processo> processos,int timeQuantum){
        processosEstadoPronto = new TreeSet<>();
        this.timeQuantum = timeQuantum;
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
        int contadorPrioridade  = processosEstadoPronto.size() + 1;
        while(!processosEstadoPronto.isEmpty()){
            processoCorrente = processosEstadoPronto.pollFirst();
            int tempoNaoUsado = processoCorrente.mandaParaCPU(tempoCorrente, timeQuantum);
            int tempoFinal = tempoCorrente + timeQuantum - tempoNaoUsado;
            Execucao execucaoCorrente = new Execucao(processoCorrente, tempoCorrente, tempoFinal);
            historico.add(execucaoCorrente);
            tempoCorrente = tempoFinal;
            if(processoCorrente.getBurstTimeRestante() > 0){
                processoCorrente.setPrioridade(contadorPrioridade);
                processosEstadoPronto.add(processoCorrente);
                contadorPrioridade++;
            }
                
        }
        return historico;
    }
}
