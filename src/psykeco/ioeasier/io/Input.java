package psykeco.ioeasier.io;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Input {
	
	private static Scanner sc;
	
	private Input(){}
	
	public static int leggiInt(String args){
		sc= new Scanner(System.in);
		int x=-1;
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextInt();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		}
		return x;
	}//leggiInt(String)
	
	public static char leggiChar(String args){
		sc= new Scanner(System.in);
		char x=' ';
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextLine().charAt(0);
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
			}catch(IndexOutOfBoundsException i){
				secure=false;
			}
		}
		return x;
	}//leggiChar(String)
	
	public static boolean leggiBool(String args){
		sc=new Scanner (System.in);
		boolean x,secure;
		x=secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextBoolean();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		
		}//while
		return x;
		
	}//leggiBool(String)
	
	public static short leggiShort (String args){
		sc= new Scanner(System.in);
		short x=-1;
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextShort();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		}
		return x;
	}//leggiShort(String)
	
	public static long leggiLong (String args){
		sc= new Scanner(System.in);
		long x=-1;
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextLong();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		}
		return x;
	}//leggiLong(String)
	
	public static byte leggiByte (String args){
		sc= new Scanner(System.in);
		byte x=-1;
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextByte();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		}
		return x;
	}//leggiByte(String)
	
	public static double leggiDouble(String args){
		sc= new Scanner(System.in);
		double x=-1;
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextDouble();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		}
		return x;
	}//leggiDouble(String)
	
	public static float leggiFloat(String args){
		sc= new Scanner(System.in);
		float x=-1;
		boolean secure=false;
		while(!secure){
			try{
				System.out.print(args);
				x=sc.nextFloat();
				secure=true;
			}catch(InputMismatchException e){
				secure=false;
				sc=new Scanner(System.in);
			}
		}
		return x;
	}//leggiFloat(String)
	
	public static String leggiParola(String args){
		sc=new Scanner(System.in);
		String line="",word="";
		boolean secure=false;
		
		while(!secure){
			try{
				System.out.print(args);
				line=sc.nextLine();
				word=line.substring(0,line.indexOf(" "));
				secure=true;
			}
			catch(IndexOutOfBoundsException i){
				word=line;
				secure=true;
			}
		}//while
		return word;
	}//leggiParola
	
	public static String leggiFrase(String args){
		sc=new Scanner(System.in);
		String line="";
		boolean secure=false;
		
		while(!secure){
			
			System.out.print(args);
			line=sc.nextLine();
			secure=true;
			
		}//while
		return line;
	}

	public static int leggiInt(){
		return leggiInt("");
	}

	public static char leggiChar(){
		return leggiChar("");
	}

	public static boolean leggiBool (){
		return leggiBool("");
	}
	
	public static short leggiShort(){
		return leggiShort("");
	}
	
	public static long leggiLong(){
		return leggiLong("");
	}
	
	public static byte leggiByte(){
		return leggiByte("");
	}
	
	public static double leggiDouble(){
		return leggiDouble("");
	}
	
	public static float leggiFloat(){
		return leggiFloat("");
	}
	
	public static String leggiParola(){
		return leggiParola("");
	}
	
	public static String leggiFrase(){
		return leggiFrase("");
	}
	
}//Input
