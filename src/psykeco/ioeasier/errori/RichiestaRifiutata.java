package psykeco.ioeasier.errori;

public class RichiestaRifiutata extends RuntimeException {
	public final static String MESSAGGIO="Errore di connessione al db.\nProbabilmente sono errori di accesso dovuti ad utente\n, password sbagliati o"
			+ "un comando errato di mysql.\nSe si ripete contatta il mio manutentore!";
	public RichiestaRifiutata(){
		super(MESSAGGIO);
	}
	public boolean equals (Object o){
		if(o==null) return false;
		if(o==this)return true;
		return o instanceof RichiestaRifiutata;
	}
}
