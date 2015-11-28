import java.io.*;
import java.util.ArrayList;

public class Leitor {
	private String path;
	
	public Leitor(String path){
		this.path = path;
	} 
	
	public ArrayList<Processo> readFile() throws FileNotFoundException, IOException {
		ArrayList<Processo> processos = new ArrayList<Processo>();
		
		FileReader fr = new FileReader(this.path);
		BufferedReader buff = new BufferedReader(fr);
		
		String line;
		String splitedLine[];
		
		while((line = buff.readLine()) != null){
			splitedLine = line.split(",");
			processos.add(convertToProcess(splitedLine));
		}
		return processos;
					
	}

	private Processo convertToProcess(String[] s){
		Processo p = new Processo();
		try{
			p.setTempoChegada(Integer.parseInt(s[0]));
			p.setId(Integer.parseInt(s[1]));
			p.setBurstTime(Integer.parseInt(s[2]));
			p.setPrioridade(Integer.parseInt(s[3]));
			return p;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.print("\nProvavelmente alguma linha do arquivo de entrada não está de acordo com o padrão...");
		}
		return p;
		
	}
	
}
