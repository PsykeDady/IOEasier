package psykeco.ioeasier.io;

public class Output {
	
	public static String matrixToString(Object[][]matrix){
		StringBuilder sb=new StringBuilder();
		
		for(int i=0; i<matrix.length;i++){
			sb.append('|');
			for(int j=0;j<matrix[i].length;j++){
				sb.append(matrix[i][j].toString());
				if(j<matrix[i].length-1)sb.append(',');
			}//for
			sb.append("|\n");
		}//for
		
		return sb.toString();
	}//matrixToString
	
}
