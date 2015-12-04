
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
        
        String entrada = "processos.csv";
        String parametros[] = {entrada+" fcfs",entrada+" rr 3",entrada+" rr 7",entrada+" rr 20",entrada+" sjf",entrada+" sjfp",entrada+" priority",entrada+" priorityp"};
        for(int i = 0 ; i < parametros.length ; i++){
            String parametro[] = parametros[i].split(" ");
            Escalonador.main(parametro);
        }
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
