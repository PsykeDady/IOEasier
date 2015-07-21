package psykeco.ioeasier.io;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class FileImpostazioni {
	private Map<String,String> impostazioni=new HashMap<String,String>();
	private String nome_file;
	private File file;
	public static boolean debug_mode=false;
	private boolean caricato=false;
	
	/**
	 * setta i parametri nome file e file,  carica il file
	 * 
	 * @param nome_file, senza alcuna modifica
	 */
	public FileImpostazioni(String nome_file){
		
		
		
		file=new File(nome_file);
		
		file=FileUtility.hiddenFileName(file);
		nome_file=file.getName();
		if(file.exists()){
			
			
			
			carica();
			
		}
		
	}
	
	public boolean esiste(){
		
		return file.exists();
	}
	
	/**
	 * 
	 * @return true se riesce a creare il file o se questo esisteva di gia'
	 */
	public static boolean creaFile(String nome_file){
		File file=new File(nome_file);
		File h_file=FileUtility.hiddenFileName(file);
		if(!h_file.exists()){
			try {
				h_file.createNewFile();
				
			} catch (IOException e) {
				if(debug_mode)
					e.printStackTrace();
				return false;
			}
			if(! h_file.isHidden()){
				FileUtility.hideFile(h_file);
			}
		}
		
		return true;
	}//creaFile
	

	/**carica il file 
	 * @return true se lo ha caricato correttamente, false se non lo ha caricato
	 * o era già caricato*/
	public boolean carica(){
		if(!esiste()||caricato)return false;
		
		LinkedList<String> righe=FileUtility.fileUnlimSplitter(file, '\n');
		
		//ogni riga sara' del tipo "nomeImpostazione valore"
		
		if(righe.size()==0)return true;//e' caricato ma non ci sono impostazioni
		impostazioni=new HashMap<String,String>();
		for(String riga:righe){
			String [] coppia=riga.split(" ");
			if(coppia.length!=2){
				continue;
			}
			impostazioni.put(coppia[0], coppia[1]);
		}
		
		//profilo caricato crrettamente
		
		caricato=true;
		
		return true;
	}//carica
	
	public boolean caricato(){return caricato;}
	
	public HashMap<String,String> impostazioni(){
		if(! caricato)return null;
		return new HashMap<String,String> (impostazioni);
	}
	
	public boolean add(String nome,String value){
		if(!caricato)return false;
		if(nome==null||nome.equals("")||nome.contains(" ")||
				value==null||value.equals("")||value.contains(" "))
			return false;
		
		if(impostazioni.containsKey(nome))return false;
		
		impostazioni.put(nome, value);
		return true;
	}
	
	public boolean update(String nome,String value){
		if(!caricato)return false;
		if(nome==null||nome.equals("")||nome.contains(" ")||
				value==null||value.equals("")||value.contains(" "))
			return false;
		
		if(! impostazioni.containsKey(nome))return false;
		
		impostazioni.put(nome, value);
		return true;
	}
	
	public boolean remove(String nome){
		if(!caricato)return false;
		if(nome==null||nome.equals("")||nome.contains(" "))
			return false;
		
		return impostazioni.remove(nome)!=null;
	}
	
	/**
	 * 
	 * @param nome chiave
	 * @return il valore associato al nome o null se non c e o non e caricato il file
	 */
	public String getValue(String nome){
		if(!caricato)return null;
		if(nome==null||nome.equals("")||nome.contains(" "))
			return null;
		
		return impostazioni.get(nome);
	}

	public void write() {
		if(!caricato||impostazioni==null)return ;
		
		LinkedList<String>filew=new LinkedList<String>();
		
		int i=0;
		
		Set <String > ks=impostazioni.keySet();
		
		for(String nome:ks){
			String riga=nome+' '+impostazioni.get(nome);
			riga=(i<ks.size()-1)?riga+'\n':riga;
			filew.add(riga);
		}
		
		FileUtility.fileWriter(file.getAbsolutePath(), filew, "");
		
	}//write
	
}//file impostazioni
