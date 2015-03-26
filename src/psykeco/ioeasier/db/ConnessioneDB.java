package psykeco.ioeasier.db;

import java.sql.*;

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
