import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Escalonador {
	public static void main(String args[]){
		for(String a: args){
			System.out.println(a);
		}
		
		try{
			
			Leitor l = new Leitor(args[0]);
			ArrayList<Processo> processos = l.readFile();
			
			StrategyEscalonador escalonador = null;
			ArrayList<Execucao> escalonamento;
			String escalonamentoEscolhido = args[1].toLowerCase();
			String paramentroDaEstatistica = "";
			
			if(escalonamentoEscolhido.equals("fcfs")){
				escalonador = new FirstComeFirstServed(processos);
				
			}else if(escalonamentoEscolhido.equals("rr")){
				int timeQuantum = 0;
				try{
					timeQuantum = Integer.parseInt(args[2]);
					if(timeQuantum<=0){
						throw new Exception("Time quantum invalido!!");
					}
					paramentroDaEstatistica = args[2];
				}catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
					throw new Exception("Time quantum nao definido!!");
				}
				escalonador = new RoundRobin(processos, timeQuantum);
				
			}else if(escalonamentoEscolhido.equals("sjf")){
				escalonador = new ShortestJobFirst(processos);
				
			}else if(escalonamentoEscolhido.equals("sjfp")){
				escalonador = new ShortestJobFirstPreemptive(processos);
			
			}else if(escalonamentoEscolhido.equals("priority")){
				escalonador = new Priority(processos);
			
			}else if(escalonamentoEscolhido.equals("priorityp")){
				escalonador = new PriorityPreemptive(processos);
			
			}else{
				System.out.println("Lascou!");
				throw new Exception("Voce escolheu um metodo invalido... Lamento...");
			
			}
			
			escalonamento = escalonador.escalonar();
			escalonador.gerarSaida(escalonamentoEscolhido.toUpperCase(), paramentroDaEstatistica);
			showProcessos(processos);
			show(escalonamento);
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
			System.out.println(e.getMessage());
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
