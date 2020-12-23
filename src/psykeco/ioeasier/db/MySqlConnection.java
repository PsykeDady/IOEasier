package psykeco.ioeasier.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;



/**
 * Gestore delle connessioni di mysql, la classe permette di avviare una connessione,
 * scambiare dati con mysql tirando fuori i ResultSet, dire se sei connesso o no,
 * le cause della mancanza di connessione e altro.
 * 
 * Per Connettere al database la classe da chiamare tuttavia e' {@link psykeco.ioeasier.db.ConnessioneDB},
 * prima di instanziare questa classe basta chiamare {@link psykeco.ioeasier.db.ConnessioneDB #createInstance(String...)} e
 * automaticamente si istanzia la connessione verso il db.
 * @author archdady
 *
 */
public class MySqlConnection {
	/**
	 * stato di connessione
	 */
	private boolean connesso= false;
	/**
	 * Connessione al database
	 */
	private Connection connessione;
	
	/**
	 * variabile che indica se si vogliono indietro i messaggi di errore 
	 */
	private static boolean debug=false;

	
	
	public MySqlConnection(){
		connessione=ConnessioneDB.getConnect();
		connesso=connessione!=null;
	}//MySqlConnection()
	
	
	/**
	 * inserendo true in questo metodo si dichiara di voler indietro
	 * i messaggi di errore gestiti automaticamente dal programma
	 * @param debug_on
	 */
	public static void setDebugMode(boolean debug_on){debug=debug_on;}
		
	
	/**
	 * @return true se connesso
	 */
	public boolean statoConnessione(){
		return connesso;
	}
	
	/**
	 *  @return lo stato del debug
	 */
	public boolean statoDebug(){
		return debug;
	}


	
	public void chiusura(){
		if(!connesso) return;
		try{
			connessione.close();
			connesso=!connesso;
		}catch(SQLException s){
			//richiestarifiutata TODO
		}
	}//chiusura
	
	/**
	 * Crea un Database con il nome passato, sostituendo gli spazi con delle sottolineature
	 * 
	 * @param nomeDB : nome del db, ogni spazio verra' rimpiazzato con un underscore
	 * @return true se la creazione ha avuto successo
	 */
	public boolean creaDB(String nomeDB){
		
		if(!connesso) return false;
		
		String query="create database "+nomeDB;
		
		nomeDB=nomeDB.replace(' ', '_');
		
		try {
			connessione.createStatement().execute(query);
			return true;
		} catch (SQLException e) {
			if(debug)e.printStackTrace();
			// richiesta rifiutata TODO
			return false;
		}
		
	}//creaDB
	
	/**
	 * verifica che esista il database passato come parametro
	 * @param nomeDB: il nome del db da controllare
	 * @return true se nomeDB e' un db presente
	 */
	public boolean existDB(String nomeDB){
		
		if(!connesso) throw new IllegalArgumentException() ;
		
		String query="select schema_name from information_schema.schemata where schema_name='"+nomeDB+'\'';
		
		try {
			ResultSet rs=connessione.createStatement().executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			if(debug)e.printStackTrace();
			return false;
			// richiesta rifiutata TODO
		}//try-catch
		
	}//existDB
	
	/**
	 * Tenta di eliminare il database passato.
	 * @param nomeDB nome del database da eliminare
	 * @return true se l'eliminazione e' andata a buon fine
	 */
	public boolean dropDB (String nomeDB){
		
		if(!connesso) return false; 
		
		String query = "drop database "+nomeDB;
		
		try{
			connessione.createStatement().execute(query);
			return true;
		}catch(SQLException s){
			if(debug)s.printStackTrace();
			return false;
		}//try-catch
		
		
	}//dropDB
	

	/**preleva la connessione per fare query 
	 * 
	 * @return la connessione
	 */
	public Connection getConnection(){
		return connessione;
	}
	
	/**
	 *esegue un comando mysql e restituisce true se e' stato eseguito correttamente.<BR>
	 *Nel caso in cui non sia stata creata una connessione, ci sia un errore nella richiesta
	 *o comunque la richiesta non fosse andata a buon termine,  ritorna false.
	 *
	 *@param il comando sql da eseguire
	 *
	 *@return lo stato della richiesta
	 */
	public boolean esegui(String comando){
		if(!connesso) return false;
		try{
			connessione.createStatement().execute(comando);
			return true;
		}catch(SQLException s){
			if(debug)s.printStackTrace();
			return false;
		}//try-catch
	}//esegui
	
	/**
	 * esegue una query e ritorna il ResultSet associato.<BR>
	 * Se avviene un errore, o il db non e' connesso allora il valore
	 * ritornato e' null.
	 * 
	 * @param la query da eseguire
	 * 
	 * @return il ResultSet corrispondente
	 * 
	 */
	public ResultSet query(String query){
		if(!connesso) return null;
		try{
			return  connessione.createStatement().executeQuery(query);
		}catch(SQLException s){
			if(debug)s.printStackTrace();
			return null;
		}//try-catch
	}//query
	
	
	/**
	 * restituisce una lista di database creati con l'account e la password
	 * ricevuti
	 * @param credenziali : vettore di credenziali, dove credenziali[0]:nome mysql, credenziali[1]:password
	 * @return una lista di database
	 */
	public LinkedList<String> listDB (){
		
		ResultSet rs;
		LinkedList<String > ret= new LinkedList<String>();
		try{
			rs=query("show databases");
			while(rs.next()){
				ret.add(rs.getString(1).trim());
			}//while
		
		}catch(SQLException s){
			//return null;
			s.printStackTrace();
			throw new IllegalStateException("Errore di connessione");
		}//catch
		return ret;
		
	}//listDB
	

	
	
	
	
	

}//classe MySqlConnection
