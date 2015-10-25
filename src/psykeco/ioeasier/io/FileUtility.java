package psykeco.ioeasier.io;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import psykeco.ioeasier.errori.*;


public final class FileUtility {
	
	public enum SistemaOperativo{
		WINDOWS,
		LINUX,
		MAC,
		UNIX_GENERICO,
		OTHER
	}

	private FileUtility() {
		// non instanziabile
	}

	/**
	 * Questo metodo prende il file {@code read} in lettura, lo legge per caratteri,
	 * e lo suddivide in piu' stringhe separate dal parametro {@code separator}
	 * in ingresso. Il vettore di stringhe creato deve avere una dimensione 
	 * predefinita, impostata con il parametro {@code length}.
	 * 
	 * Se qualcuno dei parametri inseriti non e' corretto, possono verificarsi
	 * varie eccezioni 
	 *  
	 * @param read, i file da leggere
	 * @param separator, il carattere per cui vogliamo separare le parole
	 * @param length, la lunghezza predefinita del vettore
	 * 
	 * @return il vettore di stringhe da ritornare
	 * 
	 * @throws FileNonTrovato se non trova il file
	 * 
	 * @throws IndexOutOfBoundsException se il vettore era creato troppo piccolo in base alle 
	 * 			informazioni contenute (il risultato non era aspettato quindi)
	 * 
	 * @throws IllegalArgumentException normalmente non verificabile
	 *  
	 * @throws EccezioneScrittura. possibile errore di accesso
	 */
	public static String[] fileSplitter(File read ,char separator, int length) {
		if(read==null||! read.exists()) return null;
		
		boolean hide_in_windows=nascostoInWindows(read);
		
		if(hide_in_windows) showFileInWindows(read);
		
		String linea="";
		if(length<0)return null;
		String[] scomposto=new String[length];
		
		
		
		try{
			DataInputStream datainput= new DataInputStream(new FileInputStream(read));
			//inizio il salvataggio
			
			int c=0;
			int size = datainput.available();
			for(int i=0; i<size;i+=2){
				char lettera=datainput.readChar();
				if(lettera==separator){
					scomposto[c++]=linea;//aggiungo la linea corrente all'array delle info
					linea="";//azzero linea
					
					
				}//if fine linea
				else {
					linea=linea+lettera;
				}//fine else di composizione linea
			}//fine for lettura
			if(linea!= null){
				scomposto[c]=linea;
			}
			//finita la lettura, chiudo il file
			datainput.close();
		} catch (FileNotFoundException e) {
			throw new FileNonTrovato(read.getAbsolutePath());
		} catch (IndexOutOfBoundsException i){
			throw new IndexOutOfBoundsException("Il parametro length non e' giusto\n"
					+ "Il vettore deve essere creato + grande!");
		}catch(EOFException e){
			throw new IllegalArgumentException("Il file non conteneva le informazioni aspettate");
		}
		catch (IOException ecc) {
			throw new EccezioneScrittura(read.getAbsolutePath());
		}//try-catch
		
		if(hide_in_windows) hideFileInWindows(read);
		
		return scomposto;
	}//fileSplitter
	
	/**
	 * fineUnlimSplitter  prende il file {@code read} in lettura, lo legge per caratteri,
	 * e lo suddivide in piu' stringhe separate dal parametro {@code separator}
	 * in ingresso. Non ha limiti di lunghezza, legge fino a quando non incontra 
	 * il carattere di fine file
	 * 
	 * Se qualcuno dei parametri inseriti non e' corretto, possono verificarsi
	 * varie eccezioni
	 *  
	 * @param read, i file da leggere
	 *
	 * @param separator cio' che delimita una Stringa
	 * @return La lista di Stringhe divise
	 */
	public static LinkedList<String> fileUnlimSplitter(File read ,char separator) {
		String linea="";
		LinkedList<String> scomposto=new LinkedList<String>();
		
		boolean hide_in_windows=nascostoInWindows(read);
		
		if(hide_in_windows) showFileInWindows(read);
		try{
			DataInputStream datainput= new DataInputStream(new FileInputStream(read));
			//inizio il salvataggio
			int size=datainput.available();
			char lettera=' ';
			for(int i=0; i<size;i+=2){
				lettera=datainput.readChar();
				if(lettera==separator){
					scomposto.add(linea);//aggiungo la linea corrente all'array delle info
					linea="";//azzero linea
					
				}//if fine linea
				else {
					linea=linea+lettera;
				}//fine else di composizione linea
			}//fine for lettura
			if (lettera!=separator){
				scomposto.add(linea);
			}
			//finita la lettura, chiudo il file
			datainput.close();
		} catch (FileNotFoundException e) {
			throw new FileNonTrovato(read.getAbsolutePath());
		} catch (IndexOutOfBoundsException i){
			throw new IndexOutOfBoundsException("Il parametro length non e' giusto\n"
					+ "Il vettore deve essere creato + grande!");
		}catch(EOFException e){
			return scomposto;
		}
		catch (IOException ecc) {
			throw new EccezioneScrittura(read.getAbsolutePath());
		}//try-catch
		
		if(hide_in_windows) hideFileInWindows(read);
		
		return scomposto;
	}//fileSplitter
	
	/**
	 * scrittore di file, prende il file che si chiama "path" (dove path e' l'intero path del file), se non esiste lo 
	 * crea, se esiste lo sovrascrive (cancella l'intero contenuto!) e scrive il contenuto elemento per elemento della 
	 * lista "contenuto" separando ogni elemento con la Stringa "separators"
	 * @param path il path de file da creare
	 * @param contenuto una lista che ha per ogni elemento una Stringa da inserire nel file
	 * @param separators una stringa separatore
	 */
	public static void fileWriter(String path,LinkedList<String> contenuto, String separators){
		
		File nuovo= new File(path);
		
		boolean hide_in_windows=nascostoInWindows(nuovo);
		
		if(hide_in_windows) showFileInWindows(nuovo);
		
		try {
			if(!nuovo.exists()) nuovo.createNewFile();
			DataOutputStream dataoutput= new DataOutputStream(new FileOutputStream(nuovo));
			int i=0;
			for(String parola:contenuto ){
				if(parola==null||parola.equals(""))continue;
				dataoutput.writeChars(parola);
				i++;
				if(i<=contenuto.size())dataoutput.writeChars(separators);
			}
			dataoutput.close();
		} catch (FileNotFoundException e) {
			throw new FileNonTrovato(nuovo.getAbsolutePath());
		} catch (IOException e) {
			throw new EccezioneScrittura(nuovo.getAbsolutePath());
		}
		
		if(hide_in_windows) hideFileInWindows(nuovo);
		
	}//fileWriter
	
	public static File fileBack(String suffisso,File contenuto){
		
		if(!contenuto.exists())throw new FileNonTrovato(contenuto.getAbsolutePath());
		if(suffisso.trim().length()==0)suffisso=".back";
		File back=new File(contenuto.getAbsoluteFile()+suffisso);
		
		
			try {
				DataOutputStream dataoutput=new DataOutputStream(new FileOutputStream(back));
				DataInputStream datainput=new DataInputStream(new FileInputStream(contenuto));
				int length=datainput.available();
				for (int i=0;i<length;i+=2){
					dataoutput.writeChar(datainput.readChar());
				}
				dataoutput.close();
				datainput.close();
			} catch (FileNotFoundException e) {
				throw new FileNonTrovato(back.getAbsolutePath()+" o "+contenuto.getAbsolutePath());
			} catch (IOException e) {
				throw new ErrorePermessiFile(back.getAbsoluteFile()+" o "+contenuto.getAbsolutePath());
			}
			
		return back;
	}//fileback
	
	/**
	 * nasconde un file in base al sistema operativo in cui si trova
	 * 
	 * @return true se il file e' stato nascosto o lo era gia,
	 * 		false se non e stato possibile
	 */
	public static boolean hideFile(File f){
		if(f==null){
			System.err.println("File nullo!!");
			
			return false;
		}
		
		if(! f.exists()) return false;
		
		if(f.isHidden())return true;
		
		
		
		if(selectOS().contains("win")){
			hideFileInWindows(f);
			
		}else{
			//suppongo sia di unix, allora il file viene nascosto rinominandolo
			
			//controllo sia gia' nascosto
			
			f.renameTo(hiddenFileName(f));
		}
		
		
		
		return true;
	}//hideFile(File)
	
	/**
	 * mostra (nel senso contrario di rendere nascosto) un file nel file system corrente
	 * @return true se ha apportato cambiamenti
	 */
	public static boolean showFile(File f){
		if(f==null){
			System.err.println("File nullo!!");
			
			return false;
		}
		
		if(! f.exists()) return false;
		
		if(! f.isHidden())return true;
		
		if(selectOS().contains("win")){
			showFileInWindows(f);
			
		}else{
			//suppongo sia di unix, allora il file viene scoperto rinominandolo
			
			
			return f.renameTo(showFileName(f));
		}
		
		return !f.isHidden();
	}
	
	
	/** 
	 * @param il file originale
	 * 
	 * @return il relativo file nascosto
	 */
	
	public static File hiddenFileName(File f){
		if(f.isHidden())return f;
		
		if(selectOS().contains("win"))return f;
		
		return new File(f.getParentFile().getAbsolutePath()+
				File.separatorChar+'.'+f.getName());
	}
	
	
	
	/**
	 * rimuove il file o la directory f, possono essere  specificati dei parametri <pre>
	 * 
	 * 		-r per rimuovere ricorsivamente se f è una directory
	 * 
	 * </pre>
	 * @param f : file o directory 
	 * @param args : argomenti aggiuntivi
	 * @return true se il file è stato eliminato correttamente
	 */
	public static boolean deleteFile(File f, String args){
		
		if(f==null) return false;
		if(args==null)args="";
		
		boolean recursive=args.equalsIgnoreCase("r");
		
		return (recursive)?deleteFileR(f):f.delete();
		
	}
	/**
	 * elimina ricorsivamente la cartella f e tutto il contenuto. Se f non 
	 * è una directory lo elimina ed esce
	 * @param f: file o directory
	 * @return true se f è eliminato
	 */
	private static boolean deleteFileR(File f){
		if(!f.exists()) {
			return false;
		}if(!f.isDirectory()){
			return f.delete();
		}
		File[] files=f.listFiles();
		boolean result=true;
		for(File x:files){
			result = deleteFileR(x) & result ;
		}
		f.delete();
		return result;
	}//deleteFileR
	
	/**	 * 
	 * @param file nascosto
	 * 
	 * @return ritorna il nome del file "scoperto" (nel senso contrario a nascosto)
	 */
	/** 
	 * @param il file originale
	 * 
	 * @return il relativo file nascosto
	 */
	
	public static File showFileName(File f){
		if(!f.isHidden())return f;
		
		if(selectOS().contains("win"))return f;
		
		String nome=f.getName();
		
		for(int i=0; i<nome.length();i++){
			char latano=nome.charAt(i);
			if(latano!='.'){
				nome=nome.substring(i);
				break;
			}
		}
		
		
		return new File(f.getParentFile().getAbsolutePath()+
				File.separatorChar+nome);
	}
	
	/**
	 * @return il sistema operativo corrente in string 
	 */
	public static String selectOS(){
		String os_name=System.getProperty("os.name").toLowerCase();
		
		
		return os_name;
	}
	
	
	/**
	 * questo metodo ritorna la lista dei file presenti nella cartella dir, o
	 * nel caso in cui non sia una cartella ritorna null
	 * preleva i file nascosti
	 * @param dir: la cartella da leggere
	 * @return array di nomi di file in dir o null se dir non e' una cartella
	 */
	public static String[] listFile(File dir){
		if(!dir.isDirectory()) return null;
		File[] files=dir.listFiles();
		String [] filesname= new String[files.length];
		for (int i=0; i<files.length;i++){
			String nome_file=files[i].getName();
			
			filesname[i]=nome_file;
		}
		return filesname;
	}//listFile
	
	/**
	 * questo metodo ritorna la lista dei file presenti nella cartella dir, o
	 * nel caso in cui non sia una cartella ritorna null
	 * preleva i file nascosti
	 * @param dir: la cartella da leggere
	 * @return array di nomi di file in dir o null se dir non e' una cartella
	 */
	public static String[] listVisibleFile(File dir){
		String[] files=listFile(dir);
		
		if(files==null)return files;
		
		LinkedList<String> list=new LinkedList<String>();
		
		for(int i=0; i<files.length;i++){
			if(files[i].charAt(0)=='.'){
				continue;
			}
			list.add(files[i]);
		}
		
		return list.toArray(new String[list.size()]);
		
	}//listVisibleFile
	
	public static void textFileWriter(String path,LinkedList<String> contenuto,String separators){
		File nuovo= new File(path);
		boolean hide_in_win=nascostoInWindows(nuovo);
		
		if(hide_in_win) showFileInWindows(nuovo);
		
		try {
			if(!nuovo.exists()) nuovo.createNewFile();
			PrintWriter dataoutput=new PrintWriter(nuovo);
			int index=0;
			for(String parola:contenuto ){
				
				dataoutput.print(parola);
				
				if(index<contenuto.size()-1){
					dataoutput.print(separators);
				}
				
			}
			dataoutput.close();
		} catch (FileNotFoundException e) {
			throw new FileNonTrovato(nuovo.getAbsolutePath());
		} catch (IOException e) {
			throw new EccezioneScrittura(nuovo.getAbsolutePath());
		}
		
		if(hide_in_win) hideFileInWindows(nuovo);
		
	}
	
	
	public static boolean creaFileEParenti(File f){
		
		if(f.exists()) return true;
		
		else {
			f.getParentFile().mkdirs();
			try {
				return f.createNewFile();
			} catch (IOException e) {
				throw new ErrorePermessiFile();
			}
		}//lese
	}//creaFileEParenti
	
	
	/** 
	 * @return notifica se il file � nascosto sotto il sistema operativo windows, 
	 * 			questo stato pu� generare errori di scrittura quindi � bene controllarlo
	 */
	public static boolean nascostoInWindows(File f){ return selectOS().contains("win")&&f.isHidden();}
	
	/**
	 * questo metodo nasconde un file sotto sistema operativo windows
	 */
	private static void hideFileInWindows(File f){
		if(
				f==null
				||!f.exists()
				||f.isHidden()
				||! selectOS().contains("win") 
		) return;
		
		try{
			Process p = Runtime.getRuntime().exec("attrib +h " + f.getAbsolutePath());
			p.waitFor(); 
		}catch(IOException  ie){
			ie.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//hideFileInWindows
	
	/**
	 * questo metodo mostra (inteso come contrario di nasconde) un file in Windows
	 */
	
	private static void showFileInWindows(File f){
		if(
				f==null
				||!f.exists()
				||!f.isHidden()
				||!selectOS().contains("win") 
		) return;
		
		try{
			Process p = Runtime.getRuntime().exec("attrib -h " + f.getAbsolutePath());
			p.waitFor(); 
		}catch(IOException  ie){
			ie.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//hideFileInWindows
	
}//FileUtility



