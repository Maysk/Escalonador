
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
        for(Processo processo : processos){
            processo.setPrioridade(processo.getTempoChegada());
            processosEstadoPronto.add(processo);
        }
        tempoCorrente = 0;
    }
    public ArrayList<Execucao> escalonar(){
        ArrayList<Execucao> historico = new ArrayList<>();
        int contadorPrioridade  = processosEstadoPronto.last().getPrioridade() + 1;
        while(!processosEstadoPronto.isEmpty()){
            processoCorrente = processosEstadoPronto.pollFirst();
            int passadas = 0;
            while(processoCorrente.getTempoChegada() > tempoCorrente){
                if(processosEstadoPronto.isEmpty() || passadas == processos.size()){
                    tempoCorrente = processoCorrente.getTempoChegada();
                }else{
                    processoCorrente.setPrioridade(contadorPrioridade);
                    processosEstadoPronto.add(processoCorrente);
                    contadorPrioridade++;
                    passadas++;
                    processoCorrente = processosEstadoPronto.pollFirst();
                }
            }
            
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
