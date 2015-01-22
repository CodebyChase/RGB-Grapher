
public class RgbGraph {
	//Fields
	
	String redExpression = ""; //Expression generating reds
	String greenExpression=""; // Generates green
	String blueExpression=""; // Generates blue
	//The absolute values of the expressions at each coordinate
	int[][][] absoluteVals= new int[200][200][3];
	//The scaled values of the expression at each coordinate
	int[][][] scaledVals= new int[200][200][3];
	int minY = -1; //The bottom Y value of coordinate range
	int maxY= 1;  //The top Y value of coordinate range
	int minX = -1;// The farthest left value of coordinate range
	int maxX = 1; // Farthest right value of coordinate range

	//Constructor
	/**
	 * 
	 * @param red Expression to generate red
	 * @param green Expression to generate green
	 * @param blue  Expression to generate blue
	 * @param lowY  Minimum value of Y coordinate
	 * @param highY Maximum range of Y coordinate
	 * @param lowX  Minimum range of X coordinate
	 * @param highX MAximum range of X coordinate
	 */
	public RgbGraph(String red, String green, String blue,
			int lowY, int highY, int lowX, int highX){
		minY = lowY;
		maxY = highY;
		minX = lowX;
		maxX = highX;
		int[][][] graphValues = generateGraph(red,green,blue,
				lowY,highY,lowX,highX);
		absoluteVals=graphValues;
		scaledVals=scaleGraph(graphValues);
		
		
		
		
		
	}
	
	private static int[][][] generateGraph(String red, String
			green, String blue, int lowY, int highY, int lowX,
			int highX){
		
		return new int[1][1][1];
	};
	
	private static int[][][] scaleGraph(int[][][] absGraph){
		return new int[1][1][1];
	}




}
