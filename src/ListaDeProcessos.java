import java.util.TreeSet;


public class ListaDeProcessos {
	private TreeSet<Processo> processos; 
	
	ListaDeProcessos(){
		processos = new TreeSet<Processo>();
	}
	
	public void addProcess(Processo p){
		processos.add(p);
	}
	
	public boolean isEmpty(){
		return processos.isEmpty();
	}
	
	public int getMenorTempoDeChegada(){
		int menorTempoDeChegada = Integer.MAX_VALUE;
		
		for(Processo p : processos){
			if(p.getTempoChegada() < menorTempoDeChegada ){
				menorTempoDeChegada = p.getTempoChegada();
			}
		}
		
		return menorTempoDeChegada;	
	}
	
	public Processo pollProcessoComMenorTempoDeChegada(){
		Processo processoEscolhido = processos.last();
		
		for(Processo p : processos){
			if(p.getTempoChegada() < processoEscolhido.getTempoChegada()){
				processoEscolhido = p;
			}
		}
		
		processos.remove(processoEscolhido);
		return processoEscolhido;
		
	}
	

	
	
	
}
