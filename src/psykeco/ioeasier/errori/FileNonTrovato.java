package psykeco.ioeasier.errori;

public class FileNonTrovato extends RuntimeException {

	public FileNonTrovato() {
		super("file non trovato!");
	}

	public FileNonTrovato(String path) {
		super("file "+path+" non trovato!");
		// TODO Auto-generated constructor stub
	}

	

}//FileNonTrovato
