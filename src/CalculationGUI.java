import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class CalculationGUI{
	
	XMLImporter importer = new XMLImporter();
	ArrayList<Airport> airports;
	ArrayList<Plane> planesList;
	Container panel;
	JFrame window;
	JPanel frame;
	Plane plane;
	Airport airport;
	public CalculationGUI(){
		airports = importer.importAirports();
		planesList = importer.importPlanes();
		initFirstFrame();
	    
	}
	
	void initFirstFrame(){
		window = new JFrame("Choose Airport");
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(500,100);
	    panel = window.getContentPane();
	    panel.setLayout(new FlowLayout());
	    frame=new JPanel();
	    frame.setLayout(new FlowLayout());
	    JButton submit = new JButton("Submit");
	    JComboBox dropDown = new JComboBox();
	    JComboBox planes = new JComboBox();
	    dropDown.setSelectedIndex(-1);
	    planes.setSelectedIndex(-1);
	    for(Airport airport : airports){
	    	dropDown.addItem(airport.getName());
	    	
	    }
	    for(Plane plane : planesList){
	    	planes.addItem(plane.getName());
	    	
	    }
	    frame.add(dropDown);
	    frame.add(planes);
	    frame.add(submit);
	    panel.add(frame);
	    window.setVisible(true);
	    submit.addActionListener(new SubmitActionListener(window,dropDown,planes));
	}
	
	void initSecondFrame(String airportName,String planeName){
		airport = importer.getAirportByName(airportName);
		plane =importer.getPlaneByName(planeName);
		
	}
	
	class SubmitActionListener implements ActionListener{
		JFrame frame;
		JComboBox dropdown;
		JComboBox planeDrop;
		public SubmitActionListener(JFrame window,JComboBox dropDown,JComboBox planes){
			frame = window;
			dropdown = dropDown;
			planeDrop=planes;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			initSecondFrame((String)dropdown.getSelectedItem(),(String)planeDrop.getSelectedItem());
			CalculationMainScreen gui2 = new CalculationMainScreen(airport,plane);
			frame.setVisible(false);
			
		}
		
	}
		
}





