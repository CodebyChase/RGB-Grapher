import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class GrapherGuiMainFrame extends JFrame{
	
	//Finals for window size
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 450;
	
	//Final for width of a Field in JLabels
	private static final int FIELD_WIDTH = 10;
	
	// panel which holds all the Labels and Textfields
	private JPanel inputPanel = new JPanel();
	
	//panel which holds the output image
	private JPanel picturePanel = new JPanel();
	
	//All the used JLabels
	private JLabel redExpress;  // Labels to indicate expression textfields
	private JLabel greenExpress;
	private JLabel blueExpress;
	private JLabel yLowValue;	// bottom boundary of window
	private JLabel yHighValue;   // Top boundary of window
	private JLabel xLowValue;   // Left boundary of window
	private JLabel xHighValue;   // Right boundary of window
	private JLabel outputImage;  //Label holding the output image
	
	private JTextField redTextField;  // Text fields to collect expressions
	private JTextField greenTextField;
	private JTextField blueTextField;
	private JTextField minY;		//text fields to collect window range	
	private JTextField maxY;
	private JTextField minX;
	private JTextField maxX;
	
	private JButton calculate;  // Button to run the calculation
	
	/**
	 * Builds a GUI frame for creating a Buffered Graph image
	 * @throws IOException shouldn't ever raise unless the graph.png
	 * file is somehow written to a directory where write/read access isn't
	 * permitted
	 */
	public GrapherGuiMainFrame() throws IOException{
		GridLayout grid = new GridLayout(2,1);
		
		setLayout(grid);
		
		createRedTextField();
		createGreenTextField();
		createBlueTextField();
		createyLowValueTextField();
		createyHighValueTextField();
		createxLowValueTextField();
		createxHighValueTextField();
		createCalculateButton();
		createImageLabel();
		
		createInputPanel();
		createPicturePanel();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}

	/**
	 * Creates and assigns objects for redExpress and redTextField
	 */
	private void createRedTextField(){
		redExpress = new JLabel("RED EXPRESSION: ");
		
		final String DEFAULT_RED_EXPRESSION = "cos(x) + 5sin(y)";
		redTextField = new JTextField(FIELD_WIDTH);
		redTextField.setText(DEFAULT_RED_EXPRESSION);
		
	}
	/**
	 * Creates and assigns objects for greenExpress and greenTextField
	 */
	private void createGreenTextField(){
		greenExpress = new JLabel("GREEN EXPRESSION: ");

		final String DEFAULT_GREEN_EXPRESSION = "(1/x) + 5y";
		greenTextField = new JTextField(FIELD_WIDTH);
		greenTextField.setText(DEFAULT_GREEN_EXPRESSION);
		
	}
	
	/**
	 * Creates and assigns objects for blueExpress and blueTextField
	 */
	private void createBlueTextField(){
		blueExpress = new JLabel("BLUE EXPRESSION: ");

		final String DEFAULT_BLUE_EXPRESSION = "x^(1/2) - (y^2)";
		blueTextField = new JTextField(FIELD_WIDTH);
		blueTextField.setText(DEFAULT_BLUE_EXPRESSION);
	}
	
	/**
	 * Creates and assigns objects for yLowValue and minY
	 */
	private void createyLowValueTextField(){
		yLowValue = new JLabel("BOTTOM WINDOW BOUND: ");
		
		final String DEFAULT_MIN_RANGE = "-10";
		
		
		minY = new JTextField(FIELD_WIDTH);
		
		
		minY.setText(DEFAULT_MIN_RANGE);
		
	}
	
	/**
	 * Creates and assigns objects for yHighvalue and maxY
	 */
	private void createyHighValueTextField(){
		yHighValue = new JLabel("UPPER WINDOW BOUND: ");
		final String DEFAULT_MAX_RANGE = "10";
		
		maxY = new JTextField(FIELD_WIDTH);
		
		maxY.setText(DEFAULT_MAX_RANGE);
		
		
		
	}

	/**
	 * Creates and assigns objects for xLowValue and minX
	 */
	private void createxLowValueTextField(){
		xLowValue = new JLabel("LEFT WINDOW BOUND: ");
		
		final String DEFAULT_MIN_RANGE = "-10";
		
		
		minX = new JTextField(FIELD_WIDTH);
		
		
		minX.setText(DEFAULT_MIN_RANGE);
		
		
	}
	
	/**
	 * Creates and assigns objects for xHighVlaue and maxX
	 */
	private void createxHighValueTextField(){
		xHighValue = new JLabel("RIGHT WINDOW BOUND: ");
		final String DEFAULT_MAX_RANGE = "10";
		
		maxX = new JTextField(FIELD_WIDTH);
		maxX.setText(DEFAULT_MAX_RANGE);
	}
	
	/**
	 * Creates a BufferedImage object from graph.png, and inserts the 
	 * BufferedImage into the outputImage JLabel
	 * @throws IOException
	 */
	private void createImageLabel() throws IOException{
		BufferedImage myPicture = ImageIO.read(new File("graph.png"));
		outputImage = new JLabel(new ImageIcon(myPicture));

	}
	
	/**
	 * class AddCalculateListener implements ActionListener
	 * 
	 * fulfills the ActionListener interface to handle what happens when the
	 * user presses the CALCULATE button in the GUI
	 * @author chase
	 *
	 */
	class AddCalculateListener implements ActionListener
	{	
		/**
		 * On event, collect all the inputs, and recalculate graph.png based 
		 * upon them. Then update the displayed image
		 */
		public void actionPerformed(ActionEvent event)
		{
			
			String redE = redTextField.getText();
			String greenE = greenTextField.getText();
			String blueE = blueTextField.getText();
			
			double ymin = Double.parseDouble(minY.getText());
			double ymax = Double.parseDouble(maxY.getText());
			double xmin = Double.parseDouble(minX.getText());
			double xmax = Double.parseDouble(maxX.getText());
			
			
			RgbGraph graph = new RgbGraph(redE,greenE, blueE,
					ymin,ymax,xmin,xmax);
			
			int[][][] pixels = graph.getScaledGraph();
			
			BufferedGraphImage i = new BufferedGraphImage(RgbGraph.RESOLUTION);
			
			i.fillGraphImage(pixels);
			
			File outputfile = new File("graph.png");
			
			try {
				ImageIO.write(i, "png", outputfile);
			} catch (IOException e) {
				//Should only throw if write access is not available 
				e.printStackTrace();
			}
			
			ImageIcon updatedPicture = new ImageIcon();
			
			updatedPicture.setImage(i);
            outputImage.setIcon(updatedPicture);
            
			
			
			
			
			
		}
	}
	
	/**
	 * Creates the CALCULATE button, and adds a listener to it.
	 */
	private void createCalculateButton(){
		calculate = new JButton("Calculate");
		
		ActionListener listener = new AddCalculateListener();
		calculate.addActionListener(listener);
	}
	
	/**
	 * Adds all the input components to the inputPanel, and adds the inputPanel
	 * to the Frame
	 */
	private void createInputPanel(){
		
		GridLayout grid = new GridLayout(0,2);
		
		inputPanel.setLayout(grid);
		
		inputPanel.add(redExpress);
		inputPanel.add(redTextField);
		inputPanel.add(greenExpress);
		inputPanel.add(greenTextField);
		inputPanel.add(blueExpress);
		inputPanel.add(blueTextField);
		inputPanel.add(xLowValue);
		inputPanel.add(minX);
		inputPanel.add(xHighValue);
		inputPanel.add(maxX);
		inputPanel.add(yLowValue);
		inputPanel.add(minY);
		inputPanel.add(yHighValue);
		inputPanel.add(maxY);
		inputPanel.add(calculate);
		inputPanel.add(outputImage);
		
		
		
		add(inputPanel);
		
		
	}
	
	/**
	 * Adds the outputImage to the picturePanel, and adds the
	 * picturePanel to the Frame
	 */
	private void createPicturePanel(){
		picturePanel.add(outputImage);
		
		add(picturePanel);
	}
	
}
