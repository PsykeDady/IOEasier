package psykeco.ioeasier.db;

import java.sql.*;

/**
 * classe di utilita', statica. Serve a creare una connessione con il Database attraverso il solo richiamo
 * del metodo statico {@link #createInstance(String...)} o a distruggere la connessione attraverso {@link #destroy()}.
 * La connessione poi potra' essere utilizzata con {@link psykeco.ioeasier.db.MySqlConnection}
 * @author psykedady
 *
 */
public class ConnessioneDB {
	
	private static Connection c=null;
	
	private ConnessioneDB(){}
	
	public static void createInstance(String ...credenziali){
		if(c==null)c=MySqlConnection.connect(credenziali);
	}
	
	public static Connection getConnect(){
		return c;
	}
	
	public static void destroy(){
		
		if(c==null)
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		c=null;
	}

}
