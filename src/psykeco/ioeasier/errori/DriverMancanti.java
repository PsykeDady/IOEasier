package psykeco.ioeasier.errori;

public class DriverMancanti extends RuntimeException {
	
	public DriverMancanti(){
		super("Non sono stati trovati i driver di mysql."+
				"\nCaricami la libreria di jdbc oppure contatta il mio manutentore");
	}

}
