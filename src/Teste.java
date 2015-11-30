
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
        
        Processo processo1_ = new Processo(1, 1, 0, 1);
        Processo processo2_ = new Processo(2, 2, 0, 2);
        Processo processo3_ = new Processo(3, 3, 0, 3);
        Processo processo4_ = new Processo(4, 4, 0, 3);
        Processo processo5_ = new Processo(5, 5, 0, 1);
        Processo processo6_ = new Processo(6, 6, 0, 2);
        Processo processo7_ = new Processo(7, 1, 16, 3);
        Processo processo8_ = new Processo(8, 2, 16, 3);
        Processo processo9_ = new Processo(9, 3, 16, 1);
        Processo processo10_ = new Processo(10, 4, 16, 2);
        Processo processo11_ = new Processo(11, 1, 27, 3);
        Processo processo12_ = new Processo(12, 2, 27, 3);
        Processo processo13_ = new Processo(13, 3, 27, 1);
        //Processo processo14_ = new Processo(14, 2, 17, 2);

        ArrayList<Processo> listaProcessos = new ArrayList<>();
        listaProcessos.add(processo1_);
        listaProcessos.add(processo2_);
        listaProcessos.add(processo3_);
        listaProcessos.add(processo4_);
        listaProcessos.add(processo5_);
        listaProcessos.add(processo6_);
        listaProcessos.add(processo7_);
        listaProcessos.add(processo8_);
        listaProcessos.add(processo9_);
        listaProcessos.add(processo10_);
        listaProcessos.add(processo11_);
        listaProcessos.add(processo12_);
        listaProcessos.add(processo13_);
        //listaProcessos.add(processo14_);
        
        StrategyEscalonador sjf = new ShortestJobFirstPreemptive(listaProcessos);
        ArrayList<Execucao> escalonamentoSJT = sjf.escalonar();
        System.out.println("ShortestJobFirst Preemptivo:");
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
