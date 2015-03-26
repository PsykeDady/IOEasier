package psykeco.ioeasier.errori;

public class ErrorePermessiFile extends RuntimeException {

	
	public static final String MESSAGGIO1="Non sono riuscito ad accere al file";
	public static final String MESSAGGIO2=". Credo tu non abbia i permessi. Contatta il mio manutentore";
	public ErrorePermessiFile() {
		super(MESSAGGIO1+MESSAGGIO2);
	}
	
	public ErrorePermessiFile(String pathname) {
		super(MESSAGGIO1+':'+pathname+MESSAGGIO2);
	}

}
