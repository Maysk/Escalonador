import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
	public static void main(String args[]){
		for(String a: args){
			System.out.println(a);
		}
		
		try{
			Leitor l = new Leitor(args[0]);
			ArrayList<Processo> processos = l.readFile();
			StrategyEscalonador escalonador;
			ArrayList<Execucao> escalonamento;
			escalonador = new FirstComeFirstServed(processos);
			escalonamento = escalonador.escalonar();
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
