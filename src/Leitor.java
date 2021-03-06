import java.io.*;
import java.util.ArrayList;

public class Leitor {
	private String path;
	
	public Leitor(String path){
		this.path = path;
	} 
	
	public ArrayList<Processo> readFile() throws FileNotFoundException, IOException, Exception {
		ArrayList<Processo> processos = new ArrayList<Processo>();	
		FileReader fr = new FileReader(this.path);
		BufferedReader buff = new BufferedReader(fr);
		
		String line;
		String splitedLine[];
		while((line = buff.readLine()) != null){			
			splitedLine = line.split(",");
			processos.add(convertToProcess(splitedLine));
		}
		buff.close();
		fr.close();
		return processos;			
	}

	private Processo convertToProcess(String[] s) throws Exception{
		Processo p = null;
		try{
			int id = Integer.parseInt(s[1].trim());
			int burstTime = Integer.parseInt(s[2].trim());
			int tempoChegada = Integer.parseInt(s[0].trim());
			int prioridade = Integer.parseInt(s[3].trim());
			p = new Processo(id, burstTime, tempoChegada, prioridade);
		}catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
			throw new Exception("Tem algo errado com o arquivo de entrada. Da uma checada e depois volta. ;)");
		}
		return p;
	}
	
}
