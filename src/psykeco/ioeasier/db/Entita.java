package psykeco.ioeasier.db;
import java.util.*;
public abstract class Entita {
	private MySqlConnection msc = new MySqlConnection();
	public abstract ParametroTabella<Object>[] getParametri ();
	public abstract String nomeTabella();//tra virgolette `
	public abstract String nomeDB();//tra virgolette `
	public void createTable(){
		StringBuilder comando=new StringBuilder ("create table "+nomeDB()+'.'+nomeTabella()+"(\n");
		LinkedList<String>keys=new LinkedList<String>();
		ParametroTabella[] parametri=getParametri();
		for(int i=0;i<parametri.length;i++){
			comando.append(parametri[i].getNomeParametro());
		}
		
	}//createTable
}
