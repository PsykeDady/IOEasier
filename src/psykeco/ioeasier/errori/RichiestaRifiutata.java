package psykeco.ioeasier.errori;

public class RichiestaRifiutata extends RuntimeException {
	public RichiestaRifiutata(){
		super("Errore di connessione al db.\nProbabilmente sono errori di accesso dovuti ad utente, password sbagliati."
				+ "Oppure comando errato di mysql. Contatta il mio manutentore!");
	}
}
