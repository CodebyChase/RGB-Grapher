/**
* @author chase
* Contains main and executes the rest of the program
*/
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Core {

	/**
	 * @param args is not used
	 * 
	 */
	public static void main(String[] args) throws IOException{
		GrapherGuiMainFrame frame = new GrapherGuiMainFrame();
		frame.setTitle("RGB-GRAPHER");
		frame.setDefaultCloseOperation(GrapherGuiMainFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}


