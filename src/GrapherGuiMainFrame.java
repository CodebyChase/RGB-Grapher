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

public class GrapherGuiMainFrame extends JFrame{
	
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 100;
	
	private static final int FIELD_WIDTH = 10;
	
	private JPanel panel = new JPanel();
	
	private JLabel redExpress;
	private JLabel greenExpress;
	private JLabel blueExpress;
	private JLabel yRange;
	private JLabel xRange;
	private JLabel outputImage;
	
	private JTextField redTextField;
	private JTextField greenTextField;
	private JTextField blueTextField;
	private JTextField minY;
	private JTextField maxY;
	private JTextField minX;
	private JTextField maxX;
	
	private JButton calculate;
	
	public GrapherGuiMainFrame() throws IOException{
		createRedTextField();
		createGreenTextField();
		createBlueTextField();
		createYRangeTextField();
		createXRangeTextField();
		createCalculateButton();
		createImageLabel();
		createPanel();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}

	private void createRedTextField(){
		redExpress = new JLabel("RED: ");
		
		final String DEFAULT_RED_EXPRESSION = "cos(x) + 5sin(y)";
		redTextField = new JTextField(FIELD_WIDTH);
		redTextField.setText(DEFAULT_RED_EXPRESSION);
		
	}
	
	private void createGreenTextField(){
		greenExpress = new JLabel("GREEN: ");

		final String DEFAULT_GREEN_EXPRESSION = "(1/x) + 5y";
		greenTextField = new JTextField(FIELD_WIDTH);
		greenTextField.setText(DEFAULT_GREEN_EXPRESSION);
		
	}
	
	private void createBlueTextField(){
		blueExpress = new JLabel("BLUE: ");

		final String DEFAULT_BLUE_EXPRESSION = "x^(1/2) - (y^2)";
		blueTextField = new JTextField(FIELD_WIDTH);
		blueTextField.setText(DEFAULT_BLUE_EXPRESSION);
	}
	
	private void createYRangeTextField(){
		yRange = new JLabel("Y RANGE: ");
		
		final String DEFAULT_MIN_RANGE = "-10";
		final String DEFAULT_MAX_RANGE = "10";
		
		minY = new JTextField(FIELD_WIDTH);
		maxY = new JTextField(FIELD_WIDTH);
		
		minY.setText(DEFAULT_MIN_RANGE);
		maxY.setText(DEFAULT_MAX_RANGE);
	}

	private void createXRangeTextField(){
		xRange = new JLabel("X RANGE: ");
		
		final String DEFAULT_MIN_RANGE = "-10";
		final String DEFAULT_MAX_RANGE = "10";
		
		minX = new JTextField(FIELD_WIDTH);
		maxX = new JTextField(FIELD_WIDTH);
		
		minX.setText(DEFAULT_MIN_RANGE);
		maxX.setText(DEFAULT_MAX_RANGE);
		
	}
	
	private void createImageLabel() throws IOException{
		BufferedImage myPicture = ImageIO.read(new File("graph.png"));
		outputImage = new JLabel(new ImageIcon(myPicture));

	}
	
	class AddCalculateListener implements ActionListener
	{
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ImageIcon updatedPicture = new ImageIcon("graph.png");
            outputImage.setIcon(updatedPicture);
			
			
			
			
			
		}
	}
	
	
	private void createCalculateButton(){
		calculate = new JButton("Calculate");
		
		ActionListener listener = new AddCalculateListener();
		calculate.addActionListener(listener);
	}
	
	private void createPanel(){
		
		
		panel.add(redExpress);
		panel.add(redTextField);
		panel.add(greenExpress);
		panel.add(greenTextField);
		panel.add(blueExpress);
		panel.add(blueTextField);
		panel.add(xRange);
		panel.add(minX);
		panel.add(maxX);
		panel.add(yRange);
		panel.add(minY);
		panel.add(maxY);
		panel.add(calculate);
		panel.add(outputImage);
		
		add(panel);
		
		
	}
	
	
}
