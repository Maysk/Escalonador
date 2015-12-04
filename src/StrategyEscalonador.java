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
public abstract class StrategyEscalonador {
    protected ArrayList<Processo> processos;
    protected TreeSet<Processo> processosEstadoPronto;
    protected Processo processoCorrente;
    protected int tempoCorrente;
    protected ArrayList<Execucao> escalonamento;
    protected final int tempoTrocaContexto = 10;
    
    public abstract ArrayList<Execucao> escalonar();

    public void gerarSaida(String algoritmo,String parametros){ 
        try{ 
        	FileWriter file;
            BufferedWriter saida;
        	file = new FileWriter("estatisticas.txt",true); 
            saida = new BufferedWriter(file); 
            double tempototal = 0; 
            double mediaThroughput = 0;
            double mediaTurnaround = 0;
            double mediaEspera = 0;
            double mediaResposta = 0;
            double mediaTrocaContexto;
            double usoCPU = 0;

            for(Processo a : processos){
            	tempototal+= a.getBurstTime();
            	mediaTurnaround+= a.getTurnAround();
            	mediaEspera+= a.getTempoEspera();
            	mediaResposta+= a.getTempoResposta();
            }

           
            mediaTurnaround/= processos.size();
            mediaEspera/= processos.size();
            mediaResposta/= processos.size();

            mediaTrocaContexto =(double) escalonamento.size() / processos.size();

            int inicioProcessamento = escalonamento.get(0).getTempoInicio() - tempoTrocaContexto;
            int fimProcessamento = escalonamento.get(escalonamento.size() - 1).getTempoFim();
	    usoCPU = 100 * tempototal/(fimProcessamento - inicioProcessamento);
            mediaThroughput = (double ) processos.size() / (fimProcessamento - inicioProcessamento);

            saida.write(algoritmo + " " + parametros); 
            saida.newLine(); 
            saida.write("Tempo total de processamento: " + tempototal); 
            saida.newLine(); 
            saida.write("Percentual	de utilização da CPU(%): " + usoCPU); 
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
