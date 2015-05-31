package psykeco.ioeasier.errori;

public class DriverMancanti extends RuntimeException {
	public final static String MESSAGGIO="Non sono stati trovati i driver di mysql."+
			"\nCaricami la libreria di jdbc in libs oppure contatta il mio manutentore";
	public DriverMancanti(){
		super(MESSAGGIO);
	}
	public boolean equals(Object o){
		
		return true;
	}

}
