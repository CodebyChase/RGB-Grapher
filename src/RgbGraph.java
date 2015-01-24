
public class RgbGraph {
	
	//The resolution of the graph
	public static final int RESOLUTION = 200;
	
	//Fields
	//The expressions which generate the graph
	String redExpression = ""; //Expression generating reds
	String greenExpression=""; // Generates green
	String blueExpression=""; // Generates blue
	
	//The unscaled values of the expressions at each coordinate
	// NOTE (0,0) is the bottom left of the graph
	double[][][] absoluteVals= new double[RESOLUTION][RESOLUTION][3];
	
	//The scaled values of the expression at each coordinate
	int[][][] scaledVals= new int[RESOLUTION][RESOLUTION][3];
	
	//The window size / limits of the graph
	double minY = -1; //The bottom Y value of coordinate range
	double maxY= 1;  //The top Y value of coordinate range
	double minX = -1;// The farthest left value of coordinate range
	double maxX = 1; // Farthest right value of coordinate range

	
	//Constructor
	/**
	 * Expressions can contain x and y as variables.
	 * lowX < highX, lowY < highY.
	 * 
	 * @param red Expression to generate red
	 * @param green Expression to generate green
	 * @param blue  Expression to generate blue
	 * @param lowY  Minimum value of Y coordinate
	 * @param highY Maximum range of Y coordinate
	 * @param lowX  Minimum range of X coordinate
	 * @param highX Maximum range of X coordinate
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
	
	/**
	 * Getter method for scaledVals
	 * @return
	 */
	public int[][][] getScaledGraph(){
		return this.scaledVals;
	}
	
	/**
	 * Getter method for absoluteVals
	 */
	public double [][][] getUnscaledGraph(){
		return this.absoluteVals;
	}
	
	
	
	/**
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param lowY
	 * @param highY
	 * @param lowX
	 * @param highX
	 * @return
	 */
	
    private static double[][][] generateGraph(String red, String
			green, String blue, double lowY, double highY, double lowX,
			double highX){
    	
    	//initialize input
    	double[][][] output = new double[RESOLUTION][RESOLUTION][3];
    	
    	//create parser
    	org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
    	myParser.addStandardFunctions();
    	myParser.addStandardConstants();
    	myParser.setImplicitMul(true);
		
    	//the range of x and y in the window
    	double xRange = Math.abs(highX-lowX);
    	double yRange = Math.abs(highY-lowY);
    	
    	//The increment between each coordinate defined by
    	// The range divided by the resolution
    	double resolution = RESOLUTION;
    	double xIncrement = xRange/resolution;
    	double yIncrement = yRange/resolution;
    	
    	//Fill in the graph at each coordinate
    	//by evaluating the corresponding expression using the parser
    	for (int i=0; i<(RESOLUTION); i++){
    		for (int j=0; j<(RESOLUTION); j++){
    			String colorVal = red;
    			String cv1 = colorVal.replaceAll("x","("+ 
    					(lowX+xIncrement*i) + ")");
    			String cv2 = cv1.replaceAll("y","("+ 
    					(lowY+yIncrement*j) + ")");
    			myParser.parseExpression(cv2);
    			double redVal = myParser.getValue();
    			output[i][j][0] = redVal;
    			
    			colorVal = green;
    			cv1 = colorVal.replaceAll("x","("+ (lowX+xIncrement*i) + ")");
    			cv2 = cv1.replaceAll("y","("+ (lowY+yIncrement*j) + ")");
    			myParser.parseExpression(cv2);
    			double greenVal = myParser.getValue();
    			output[i][j][1] = greenVal;
    			
    			colorVal = blue;
    			cv1 = colorVal.replaceAll("x","("+(lowX+xIncrement*i) + ")");
    			cv2 = cv1.replaceAll("y","("+ (lowY+yIncrement*j) + ")");
    			myParser.parseExpression(cv2);
    			double blueVal = myParser.getValue();
    			output[i][j][2] = blueVal;
    		}
    	}	
    	return output;
	}
	/**
	 *@params absGraph: a RESOLUTION x RESOLUTION x 3 array of doubles
	 *@return output: a linear scaling of absGraph to the range of 0-255
	 *				  of ints. Intended to be used to color pixels of a 
	 *				  RESOLUTION x RESOLUTION image.
	 *
	 *
	 *
	 */
	private static int[][][] scaleGraph(double[][][] absGraph){
		//initialize output
		int[][][] output = new int[RESOLUTION][RESOLUTION][3];
		
		//Initializing variables for the loop
		//The min and max value of R,G, and B
		double minRed = absGraph[0][0][0];
		double maxRed = absGraph[0][0][0];
		double minGreen = absGraph[0][0][1];
		double maxGreen = absGraph[0][0][1];
		double minBlue = absGraph[0][0][2];
		double maxBlue = absGraph[0][0][2];
		
		//initialize the variables for R, G, and B at each coordinate
		double red = absGraph[0][0][0];
		double green = absGraph[0][0][1];
		double blue = absGraph[0][0][2];
		
		//Find the min and max values
		for(int i=0; i<(RESOLUTION); i++){
			for(int j=0; j<(RESOLUTION); j++){
				red = absGraph[i][j][0];
				green = absGraph[i][j][1];
				blue = absGraph[i][j][2];
				
				if (red < minRed)
					minRed = red;
				if (red > maxRed)
					maxRed = red;
				if (green < minGreen)
					minGreen = green;
				if (green > maxGreen)
					maxGreen = green;
				if (blue < minBlue)
					minBlue = blue;
				if (blue > maxBlue)
					maxBlue = blue;
				
			}
		}
		//calculate ranges
		double redRange = maxRed - minRed;
		double greenRange = maxGreen - minGreen;
		double blueRange = maxBlue - minBlue;
		
		//Scale down/up all values
		for(int i=0; i<(RESOLUTION); i++){
			for(int j=0; j<(RESOLUTION); j++){
				//collect RGBs
				red = absGraph[i][j][0];
				green = absGraph[i][j][1];
				blue = absGraph[i][j][2];
				
				//Subtract off minimum, divide by range, mult by 255
				red = ((red-minRed)/redRange)*255;
				green = ((green-minGreen)/greenRange)*255;
				blue = ((blue-minBlue)/blueRange)*255;
				
				//round down and save to output
				int intRed = (int) red;
				int intGreen = (int) green;
				int intBlue = (int) blue;
				
				output[i][j][0] = intRed;
				output[i][j][1] = intGreen;
				output[i][j][2] = intBlue;
			
			}
		}
		
		return output;	
			
	}
	




}
