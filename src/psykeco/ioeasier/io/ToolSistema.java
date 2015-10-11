package psykeco.ioeasier.io;

import java.awt.*;
import java.awt.datatransfer.*;

public class ToolSistema {
	
	
	private ToolSistema(){}
	
	public static void addStringToClipboard(String arg){
		Clipboard c= Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection s=new StringSelection(arg);
		
		c.setContents(s, s);
	}
	

}
