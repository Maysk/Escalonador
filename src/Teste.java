
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
        Processo p1 = new Processo(1, 24, 0, 0);
        Processo p2 = new Processo(2, 3, 90, 0);
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
        
        Processo p4 = new Processo(1, 53, 0, 0);
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
