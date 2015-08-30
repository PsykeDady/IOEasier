package psykeco.ioeasier.errori;

public class EccezioneScrittura extends RuntimeException {
	
	private static String message="Ho incontrato un errore di scrittura, verifica lo stato"+
			" del tuo hard disk o controlla che tu abbia i permessi per scrivere nella directory ";
	
	public EccezioneScrittura() {
		super(message);
	}

	public EccezioneScrittura(String path) {
		super(message+path);
	}

}
