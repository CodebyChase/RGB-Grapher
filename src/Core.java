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
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter an expression");
		String expression = sc.nextLine();
		System.out.println("enter another");
		String expression2 = sc.nextLine();
		System.out.println("and a third");
		String expression3 = sc.nextLine();
		
		sc.close();
		
		RgbGraph graph = new RgbGraph(expression,expression2,expression3,
				-10,10,-10,10);
		
		int[][][] pixels = graph.getScaledGraph();
		BufferedGraphImage img = new BufferedGraphImage(RgbGraph.RESOLUTION);
		
		img.fillGraphImage(pixels);
		File outputfile = new File("graph.png");
		
		
		ImageIO.write(img, "png", outputfile);
		
	}
}


