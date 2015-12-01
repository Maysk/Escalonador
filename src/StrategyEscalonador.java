/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CaioViktor
 */
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.FileWriter;
import java.io.BufferedWriter;
// import java.lang.management.OperatingSystemMXBean;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
public abstract class StrategyEscalonador {
    protected ArrayList<Processo> processos;
    protected TreeSet<Processo> processosEstadoPronto;
    protected Processo processoCorrente;
    protected int tempoCorrente;
    protected ArrayList<Execucao> escalonamento;
    private FileWriter file;
    private BufferedWriter saida;
    protected long systemTimeInit;
    
    public abstract ArrayList<Execucao> escalonar();

    public void gerarSaida(String algoritmo,String parametros){ 
        try{ 
            file = new FileWriter("estatisticas.txt",true); 
            saida = new BufferedWriter(file); 
             
            double tempototal = 0; 
            double mediaThroughput = 0;
            double mediaTurnaround = 0;
            double mediaEspera = 0;
            double mediaResposta = 0;
            double mediaTrocaContexto;
            double usoCPU = 0;
            long systemTime     = System.nanoTime();
            long processCpuTime = 0;
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	        processCpuTime = operatingSystemMXBean.getProcessCpuTime();


            for(Processo a : processos){
            	tempototal+= a.getBurstTime();
            	mediaTurnaround+= a.getTurnAround();
            	mediaEspera+= a.getTempoEspera();
            	mediaResposta+= a.getTempoResposta();
            }

            mediaThroughput = processos.size() / tempototal;
            mediaTurnaround/= processos.size();
            mediaEspera/= processos.size();
            mediaResposta/= processos.size();

            mediaTrocaContexto = escalonamento.size() / processos.size();

            


	        // System.out.println("Processadores:"+operatingSystemMXBean.getAvailableProcessors()+"  - "+processCpuTime+" - "+systemTimeInit +" - " + systemTime +" = " + (processCpuTime/(systemTime - systemTimeInit)));

	        usoCPU = (processCpuTime/(systemTime - systemTimeInit));

            saida.write(algoritmo + " " + parametros); 
            saida.newLine(); 
            saida.write("Tempo total de processamento: " + tempototal); 
            saida.newLine(); 
            // saida.write("Percentual	de utilização de cada CPU: " + usoCPU +"\tUso médio: "+ (usoCPU/operatingSystemMXBean.getAvailableProcessors())); 
            saida.write("Percentual	de utilização média de cada CPU: " + (usoCPU/operatingSystemMXBean.getAvailableProcessors())); 
            saida.newLine(); 
            saida.write("Média Throughput dos processos: " + mediaThroughput); 
            saida.newLine(); 
            saida.write("Média Turnaround dos processos: " + mediaTurnaround); 
            saida.newLine(); 
            saida.write("Média Espera dos processos: " + mediaEspera); 
            saida.newLine(); 
            saida.write("Média Resposta dos processos: " + mediaResposta); 
            saida.newLine(); 
            saida.write("Média Troca de Contextos dos processos: " + mediaTrocaContexto); 
            saida.newLine(); 
            saida.write("Numero	de Processos executados: " + processos.size()); 
            saida.newLine(); 
            saida.newLine(); 
            saida.flush();
            saida.close();
            file.close();
        }catch(Exception e){System.out.println("Erro durante geração da saída:\n"+e);} 
    } 
}
