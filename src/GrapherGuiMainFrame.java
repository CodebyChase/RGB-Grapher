import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GrapherGuiMainFrame extends JFrame {
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 100;
	
	private static final int FIELD_WIDTH = 10;
	
	private JLabel redExpress;
	private JLabel greenExpress;
	private JLabel blueExpress;
	private JLabel yRange;
	private JLabel xRange;
	
	private JTextField redTextField;
	private JTextField greenTextField;
	private JTextField blueTextField;
	private JTextField minY;
	private JTextField maxY;
	private JTextField minX;
	private JTextField maxX;
	
	private JButton calculate;
	
	public GrapherGuiMainFrame(){
		createRedTextField();
		createGreenTextField();
		createBlueTextField();
		createYRangeTextField();
		createXRangeTextField();
		createCalculateButton();
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
	
	class AddCalculateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
		}
	}
	
	
	private void createCalculateButton(){
		
	}
	
	private void createPanel(){
		JPanel panel = new JPanel();
		
	}
	
	
}
