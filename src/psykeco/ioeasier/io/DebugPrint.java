package psykeco.ioeasier.io;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Debug Print definisce un tool basilare per generare scritte output per il debug.<br>
i flag global_mode e debug_mode si possono abilitare/disattivare globalmente e localmente gli output generati.<br>

esistono tre costruttori per DebugPrint:<br>
<table>
	<tr> <td>-<b>default</b></td> <td>usa lo standard error come output di stampa-debug</td> </tr>
	<tr> <td>-<b>String</b></td> <td>con la stringa passata costruisce un file e lo usa come output di stampa-debug</td> </tr>
	<tr> <td>-<b>PrintStream</b></td> <td>usa il PrintStream passato come output di stampa-debug*</td> </tr>
</table>
<br>
Note:
<pre>*=non conviene usare questo costruttore inizializzando direttamente all'interno lo stream, a causa dell'eccezione IO da catturare</pre>
 * @author PsykeDady
 *
 */
public class DebugPrint {
	
	public static boolean global_mode=false;
	public boolean debug_mode=false;
	private boolean close=false;
	private PrintStream out;
	
	public DebugPrint(){
		constructor(System.out);
	}
	
	public DebugPrint(PrintStream out){
		constructor(out);
	}
	
	public DebugPrint(String s) {
		this(s,false);
	}
	
	public DebugPrint(String s, boolean append) {
		try {
			java.util.List<String> l=new java.util.LinkedList<>();
			java.io.File f=new java.io.File(s); 
			if(f.canRead() && append)
				l=FileUtility.fileUnlimSplitter(f, '\n');
			PrintStream ps=new PrintStream(s);
			if(append)
				for (String linea:l) ps.append(linea+'\n');
			constructor(ps);
		}catch(IOException io) {
			System.err.println("non e' stato possibile attivare la modalit\u00e0 debug!");
			System.exit(-1);
		}
	}
	
	private void constructor(PrintStream out) {
		this.out=out;
	}
	
	public void flush() {
		
		if(close) return;
		
		if(debug_mode){
			out.flush();
		}
	}
	
	public void close() {
		
		if(close)return;
		
		if (canPrint()){
			out.close();
			close=true;
		}
	}
	
	private boolean canPrint() {
		return debug_mode&&global_mode;
	}

	
	public void print (boolean arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (char arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (int arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (long arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (float arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (double arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (char[] arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (String arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void print (Object arg){
		if(canPrint()){
			out.print(arg);
		}
	}
	
	public void println(){
		if (canPrint()){
			out.println();
		}
	}
	
	public void println(boolean arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(char arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(int arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(long arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(float arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(double arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(char[] arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(String arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public void println(Object arg){
		if (canPrint()){
			out.println(arg);
		}
	}
	
	public PrintStream printf(String format, Object ... args){
		return (canPrint())? 
				out.printf(format, args):
					null;
	}
	
	
}
