package psykeco.ioeasier.db;

public class ParametroTabella {
	private boolean key,not_null;
	private Object elem;
	
	public ParametroTabella(Object e, boolean chiave, boolean non_nullo){
		elem=e;
		key=chiave;
		not_null=non_nullo;
	}//Parametro tabella
	
	public Object getElem (){
		return elem;
	}
	
	public boolean isKey(){
		return key;
	}
	
	public boolean isNotNull(){
		return not_null;
	}
	

	
}//ParametroTabello
