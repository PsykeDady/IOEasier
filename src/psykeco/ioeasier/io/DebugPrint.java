package psykeco.ioeasier.io;

import java.io.PrintStream;

public class DebugPrint {
	
	public static boolean debug_mode=false;
	private boolean close=false;
	private PrintStream out;
	
	public DebugPrint(boolean debug_mode){
		this(System.out,debug_mode);
	}
	
	public DebugPrint(PrintStream out,boolean debug_mode){
		
		this.out=out;
		this.debug_mode=debug_mode;

	}
	
	public void flush() {
		
		if(close) return;
		
		if(debug_mode){
			out.flush();
		}
	}
	
	public void close() {
		
		if(close)return;
		
		if (debug_mode){
			out.close();
			close=true;
		}
	}

	
	public void print (boolean arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (char arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (int arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (long arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (float arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (double arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (char[] arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (String arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void print (Object arg){
		if(debug_mode){
			out.print(arg);
		}
	}
	
	public void println(){
		if (debug_mode){
			out.println();
		}
	}
	
	public void println(boolean arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(char arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(int arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(long arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(float arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(double arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(char[] arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(String arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public void println(Object arg){
		if (debug_mode){
			out.println(arg);
		}
	}
	
	public PrintStream printf(String format, Object ... args){
		return (debug_mode)? 
				out.printf(format, args):
					null;
	}
	
}