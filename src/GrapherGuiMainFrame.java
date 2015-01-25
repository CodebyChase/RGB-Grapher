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
		
	}
	
	private void createGreenTextField(){
		
	}
	
	private void createBlueTextField(){
		
	}
	
	private void createYRangeTextField(){
		
	}

	private void createXRangeTextField(){
		
	}
	
	private void createCalculateButton(){
		
	}
	
	private void createPanel(){
		
	}
	
	
}
