
public class RgbGraph {
	//Fields
	
	String redExpression = ""; //Expression generating reds
	String greenExpression=""; // Generates green
	String blueExpression=""; // Generates blue
	//The absolute values of the expressions at each coordinate
	double[][][] absoluteVals= new double[200][200][3];
	//The scaled values of the expression at each coordinate
	int[][][] scaledVals= new int[200][200][3];
	double minY = -1; //The bottom Y value of coordinate range
	double maxY= 1;  //The top Y value of coordinate range
	double minX = -1;// The farthest left value of coordinate range
	double maxX = 1; // Farthest right value of coordinate range

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
			double lowY, double highY, double lowX, double highX){
		minY = lowY;
		maxY = highY;
		minX = lowX;
		maxX = highX;
		double[][][] graphValues = generateGraph(red,green,blue,
				lowY,highY,lowX,highX);
		absoluteVals=graphValues;
		scaledVals=scaleGraph(graphValues);
		
		
		
		
		
	}
	
    private static double[][][] generateGraph(String red, String
			green, String blue, double lowY, double highY, double lowX,
			double highX){
    	
    	double[][][] output = new double[200][200][3];
    	
    	org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
    	myParser.addStandardFunctions();
    	myParser.addStandardConstants();
    	myParser.setImplicitMul(true);
		
    	double xRange = Math.abs(highX-lowX);
    	double yRange = Math.abs(highY-lowY);
    	
    	double xIncrementer = xRange/200.0;
    	double yIncrementer = yRange/200.0;
    	
    	for (int i=0; i<201; i++){
    		for (int j=0; j<201; j++){
    			String colorVal = red;
    			colorVal.replaceAll("x","("+ (lowX+xIncrementer*i) + ")");
    			colorVal.replaceAll("y","("+ (lowY+yIncrementer*j) + ")");
    			myParser.parseExpression(colorVal);
    			double redVal = myParser.getValue();
    			output[i][j][0] = redVal;
    			
    			colorVal = green;
    			colorVal.replaceAll("x","("+ (lowX+xIncrementer*i) + ")");
    			colorVal.replaceAll("y","("+ (lowY+yIncrementer*j) + ")");
    			myParser.parseExpression(colorVal);
    			double greenVal = myParser.getValue();
    			output[i][j][1] = greenVal;
    			
    			colorVal = green;
    			colorVal.replaceAll("x","("+ (lowX+xIncrementer*i) + ")");
    			colorVal.replaceAll("y","("+ (lowY+yIncrementer*j) + ")");
    			myParser.parseExpression(colorVal);
    			double blueVal = myParser.getValue();
    			output[i][j][2] = blueVal;
    		}
    	}	
		return output;
	}
	
	private static int[][][] scaleGraph(double[][][] absGraph){
		return new int[1][1][1];
	}




}
