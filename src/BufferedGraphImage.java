
public class BufferedGraphImage extends java.awt.image.BufferedImage {
	//Field to make resolution easily accessible
	private int resolution;
	
	public BufferedGraphImage(int res){
		super(res,res,TYPE_INT_RGB);
		resolution = res;
		
	}
	
	/**
	 * Writes the RGB values of this to the values of pixels.
	 * RgbGraph places 0,0 at the bottom left, while BufferedImage places it 
	 * at the top left. This is accounted for here
	 * 
	 * 
	 * @param pixels a resolution by resolution by 
	 * 				3 array of ints ranging from 0-255
	 */
	public void fillGraphImage(int[][][] pixels){
		for (int i = 0; i < (this.resolution); i++){
			for (int j=(this.resolution-1); j > (-1); j--){
				int r = pixels[i][j][0];
				int g = pixels[i][j][1];
				int b = pixels[i][j][2];
				
				int color = (r<<16)|(g<<8)|b;
				this.setRGB(i,j,color);
			}
			
		}
			
	}

}
