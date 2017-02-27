import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class CalculationGUI{
	
	XMLImporter importer = new XMLImporter();
	ArrayList<Airport> airports;
	Container panel;
	JFrame window;
	JPanel frame;
	
	Airport airport;
	public CalculationGUI(){
		airports = importer.importAirports();
		initFirstFrame();
	    
	}
	
	void initFirstFrame(){
		window = new JFrame("Choose Airport");
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(300,100);
	    panel = window.getContentPane();
	    panel.setLayout(new FlowLayout());
	    frame=new JPanel();
	    frame.setLayout(new FlowLayout());
	    JButton submit = new JButton("Submit");
	    JComboBox dropDown = new JComboBox();
	    dropDown.setSelectedIndex(-1);
	    for(Airport airport : airports){
	    	dropDown.addItem(airport.getName());
	    	
	    }
	    frame.add(dropDown);
	    frame.add(submit);
	    panel.add(frame);
	    window.setVisible(true);
	    submit.addActionListener(new SubmitActionListener(window,dropDown));
	}
	
	void initSecondFrame(String airportName){
		airport = importer.getAirportByName(airportName);
	}
	
	class SubmitActionListener implements ActionListener{
		JFrame frame;
		JComboBox dropdown;
		public SubmitActionListener(JFrame window,JComboBox dropDown){
			frame = window;
			dropdown = dropDown;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			initSecondFrame((String)dropdown.getSelectedItem());
			CalculationMainScreen gui2 = new CalculationMainScreen(airport);
			frame.setVisible(false);
			
		}
		
	}
		
}





