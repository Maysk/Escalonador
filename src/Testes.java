import java.util.ArrayList;


public class Testes {
	protected static final int tempoTrocaContexto = 10;
	protected static final int delta = -3;
	
	public static ArrayList<Processo> getCasoDeTeste(int i) throws Exception{
		ArrayList<Processo> l = new ArrayList<Processo>();
		switch(i){
		case 1:	//Chegam no mesmo tempo
			l.add(new Processo(0,2,0,0));
			l.add(new Processo(1,2,0,1));
			break;
		case 2:	//P1 tem prioridade maior e chega depois 
			l.add(new Processo(0,2,0,0));
			l.add(new Processo(1,2, tempoTrocaContexto+delta,1));
			break;
		case 3:	//P1 tem prioridade maior e chega muito depois
			l.add(new Processo(0,2,0,0));
			l.add(new Processo(1,2, tempoTrocaContexto + 20,1));
			break;
		case 4:	//P1 tem prioridade menor e chega depois
			l.add(new Processo(0,2,0,1));
			l.add(new Processo(1,2, tempoTrocaContexto+delta, 0));
			break;
		case 5:	//P1 tem prioridade igual e chega depois
			l.add(new Processo(0,2,0,0));
			l.add(new Processo(1,2, tempoTrocaContexto+delta, 0));
			break;
		default:
			throw new Exception("Que vacilo...");
		}
		return l;
		
	}
	
	 

	
	
	
	
	
	public static ArrayList<Processo> getCasoDeTeste5(){
		ArrayList<Processo> l = new ArrayList<Processo>();

		return l;
	}
	
	public static void show(ArrayList<Execucao> escalonamento){
        for(Execucao e : escalonamento)
            System.out.println(e);
    }
    public static void showProcessos(ArrayList<Processo> processos){
        for(Processo p : processos)
            System.out.println(p + " TurnAround: " + p.getTurnAround() + " Tempo de Espera: " + p.getTempoEspera() + " Tempo de Resposta: " + p.getTempoResposta());
    }
	
	public static void main(String args[]) throws Exception{
		int numeroDeCasosDeTeste = 5;
		StrategyEscalonador escalonador;
		ArrayList<Execucao> escalonamento;
		ArrayList<Processo> processos;
		
		
		for(int i = 1; i<=numeroDeCasosDeTeste; i++){
			System.out.println("\n\nCaso de Teste "+i+":");
			processos = getCasoDeTeste(i);
			escalonador = new PriorityPreemptive(processos);
			escalonamento = escalonador.escalonar();
			showProcessos(processos);
			show(escalonamento);
		}
		
	}
	
	
	
	
	
	
	
	
}
