package psykeco.ioeasier.errori;

public class EccezioneLettura extends RuntimeException{

	public EccezioneLettura() {
		super("Qualcosa e' andato male nella lettura del file\n Probabilmente non hai"+
				"i permessi per leggerlo, la testina del disco si e' rotta, "+
				"il computer e' esploso o non te lo vuole far leggere.\n Contatta il "+
				"mio manutentore!");
	}
	
	public EccezioneLettura(String path){
		super ("Qualcosa e' andato male nella lettura del file"+path+
				"\n Probabilmente non hai i permessi per leggerlo,"+
				" la testina del disco si e' rotta, il computer e' esploso o"
				+ " non te lo vuole far leggere.\n Contatta il "+
				"mio manutentore!");
	}
	

}
