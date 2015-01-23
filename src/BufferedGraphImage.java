/**
 * BufferedGraphImage inherits from BufferedGraphImage.
 * It differs by being necessarily square and of RGB type.
 * 
 * Follows the convention of RgbGraph where 0,0 is placed at the 
 * bottom left corner.
 *
 * @author chase
 *
 */
public class BufferedGraphImage extends java.awt.image.BufferedImage {
	
	//Field to make resolution easily accessible
	private int resolution;
	
	public BufferedGraphImage(int res){
		super(res,res,TYPE_INT_RGB);
		resolution = res;
		
	}
	
	/**
	 * Writes the contents of pixels to the RGB values of this.
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
