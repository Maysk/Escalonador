import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
	public static void main(String args[]){
		for(String a: args){
			System.out.println(a);
		}
		try{
			//Tenta ler o arquivo
			Leitor l = new Leitor(args[0]);
			ArrayList<Processo> processos = l.readFile();
			
			StrategyEscalonador escalonador;
			ArrayList<Execucao> escalonamento;
			
			if(args[1].equalsIgnoreCase("fcfs")){
				escalonador = new FirstComeFirstServed(processos);
				
			} else if(args[1].equalsIgnoreCase("rr")){
				System.out.println("Round Robin foi escolhido!");
				int quantum = Integer.parseInt(args[2]);
				escalonador = new RoundRobin(processos, quantum);
				
			} else if(args[1].equalsIgnoreCase("sjf")){
				System.out.println("Ainda não implementado, mas calma que jaja aparece.");
				
			} else if(args[1].equalsIgnoreCase("sjfp")){
				System.out.println("Ainda não implementado, mas calma que jaja aparece.");
			} else if(args[1].equalsIgnoreCase("priority")){
				escalonador = new Priority(processos);
			} else if(args[1].equalsIgnoreCase("priorityp")){
				escalonador = new PriorityPreemptive(processos);
			}
			else{
				throw new Exception("Escalonador Invalido");
			}
			
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Faltam argumentos!");
		}
		catch(FileNotFoundException e){
			System.out.println("Arquivo nao achado!");
		}
		catch(IOException e){
			System.out.println("Problema na leitura!");
		}
		catch(Exception e){
			e.printStackTrace();
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
