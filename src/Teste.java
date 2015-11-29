
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CaioViktor
 */
public class Teste {
    public static void main(String args[]){
        /*
    	Processo p1 = new Processo(1, 24, 11, 0);
        Processo p2 = new Processo(2, 3, 10, 0);
        Processo p3 = new Processo(3, 3, 28, 0);
        ArrayList<Processo> processos = new ArrayList<>();
        processos.add(p1);
        processos.add(p2);
        processos.add(p3);
        StrategyEscalonador FCFS = new FirstComeFirstServed(processos);
        ArrayList<Execucao> escalonamento = FCFS.escalonar();
        System.out.println("FCFS:");
        showProcessos(processos);
        show(escalonamento);
        
        Processo p4 = new Processo(1, 53, 22, 0);
        Processo p5 = new Processo(2, 17, 271, 0);
        Processo p6 = new Processo(3, 68, 21, 0);
        Processo p7 = new Processo(4, 24, 21, 0);
        ArrayList<Processo> processos2 = new ArrayList<>();
        processos2.add(p4);
        processos2.add(p5);
        processos2.add(p6);
        processos2.add(p7);
        StrategyEscalonador RR = new RoundRobin(processos2,20);
        ArrayList<Execucao> escalonamento2 = RR.escalonar();
        System.out.println("\n\nRR:");
        showProcessos(processos2);
        show(escalonamento2);
        
    	
    	Processo processo1 = new Processo(1, 24, 13, 0);
        Processo processo2 = new Processo(2, 3, 10, 1);
        Processo processo3 = new Processo(3, 3, 28, 0);
        ArrayList<Processo> processos3 = new ArrayList<>();
        processos3.add(processo1);
        processos3.add(processo2);
        processos3.add(processo3);
        StrategyEscalonador priority = new Priority(processos3);
        ArrayList<Execucao> escalonamento3 = priority.escalonar();
        System.out.println("Priority:");
        showProcessos(processos3);
        show(escalonamento3);
        
    	

    	Processo processo1 = new Processo(1, 3, 0, 1);
        Processo processo2 = new Processo(2, 3, 1, 2);
        Processo processo3 = new Processo(3, 3, 2, 3);
        ArrayList<Processo> processos4 = new ArrayList<>();
        processos4.add(processo1);
        processos4.add(processo2);
        processos4.add(processo3);
        StrategyEscalonador priorityp = new PriorityPreemptive(processos4);
        ArrayList<Execucao> escalonamento4 = priorityp.escalonar();
        System.out.println("PriorityP:");
        showProcessos(processos4);
        show(escalonamento4);
        */
        
    
        //Testes SJF:
        //Processo(ID,burstTime,TempoChegada,prioridade)
        Processo processo1 = new Processo(1, 7, 0, 1);
        Processo processo2 = new Processo(2, 4, 2, 2);
        Processo processo3 = new Processo(3, 1, 4, 3);
        Processo processo4 = new Processo(4, 4, 5, 3);
        
        Processo processo1_ = new Processo(1, 2, 0, 1);
        Processo processo2_ = new Processo(2, 4, 4, 2);
        Processo processo3_ = new Processo(3, 8, 5, 3);
        Processo processo4_ = new Processo(4, 5, 5, 3);
        ArrayList<Processo> listaProcessos = new ArrayList<>();
        listaProcessos.add(processo1_);
        listaProcessos.add(processo2_);
        listaProcessos.add(processo3_);
        listaProcessos.add(processo4_);
        StrategyEscalonador sjf = new ShortestJobFirst(listaProcessos);
        ArrayList<Execucao> escalonamentoSJT = sjf.escalonar();
        System.out.println("ShortestJobFirst:");
        showProcessos(listaProcessos);
        show(escalonamentoSJT);
        
    }
    
    
    public static void show(ArrayList<Execucao> escalonamento){
        for(Execucao e : escalonamento)
            System.out.println(e);
    }
    public static void showProcessos(ArrayList<Processo> processos){
        for(Processo p : processos)
            System.out.println(p + " TurnAround: " + p.getTurnAround() + " Tempo de Espera: " + p.getTempoEspera() + " Tempo de Resposta: " + p.getTempoResposta());
    }
}
