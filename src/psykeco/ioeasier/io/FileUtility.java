package psykeco.ioeasier.io;

import java.io.*;
import java.util.LinkedList;

import psykeco.ioeasier.errori.*;


public final class FileUtility {

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
		

		try {
			if(!nuovo.exists()) nuovo.createNewFile();
			DataOutputStream dataoutput= new DataOutputStream(new FileOutputStream(nuovo));
			int i=0;
			for(String parola:contenuto ){
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
	 * questo metodo ritorna la lista dei file presenti nella cartella dir, o
	 * nel caso in cui non sia una cartella ritorna null
	 * @param dir: la cartella da leggere
	 * @return array di nomi di file in dir o null se dir non e' una cartella
	 */
	public static String[] listFile(File dir){
		if(!dir.isDirectory()) return null;
		File[] files=dir.listFiles();
		String [] filesname= new String[files.length];
		for (int i=0; i<files.length;i++){
			filesname[i]=files[i].getName();
		}
		return filesname;
	}//listFile
	
	
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
	
	
}//FileUtility



