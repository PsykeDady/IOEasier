package psykeco.ioeasier.db;

public class ParametroTabella<E> {
	public static enum Tipo{
		VARCHAR,
		INT,
		DECIMAL,
		CHAR,
		BLOB,
		BINARY,
		LONGBLOB,
		MEDIUMBLOB,
		TINYBLOB,
		VARBINARY,
		DATE,
		TIME,
		TIMESTAMP,
		DATETIME;
	}//enum
	private boolean key,not_null;
	private E elem;
	private String nome_parametro;
	private Tipo tipo;
	private int range_tipo; //dipende daltipo
	
	public ParametroTabella(E e,Tipo tipo, String nome, int range_tipo , boolean chiave, boolean non_nullo){
		elem=e;
		this.tipo=tipo;
		this.nome_parametro=nome;
		this.range_tipo=range_tipo;
		key=chiave;
		not_null=non_nullo;
	}//Parametro tabella
	
	public E getElem (){
		return elem;
	}
	
	public boolean isKey(){
		return key;
	}
	
	public boolean isNotNull(){
		return not_null;
	}
	
	public String getNomeParametro(){ return nome_parametro;}
	
	public Tipo getTipo(){ return tipo;}

	
}//ParametroTabella
