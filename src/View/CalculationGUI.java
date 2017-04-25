package View;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import Controller.*;
import Model.*;

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
	//Initialize the gUI
	void initFirstFrame(){
		window = new JFrame("Choose Airport");
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(500,100);
	    panel = window.getContentPane();
	    panel.setLayout(new FlowLayout());
	    frame=new JPanel();
	    frame.setLayout(new FlowLayout());
	    JButton submit = new JButton("Submit");
	    JButton Add=new JButton("Add more");

	    JComboBox dropDown = new JComboBox();
	    JComboBox planes = new JComboBox();
	    dropDown.setSelectedIndex(-1);
	    planes.setSelectedIndex(-1);
		Add.addActionListener(new AddButtonListener(dropDown,planes,airports,planesList));
	    for(Airport airport : airports){
	    	dropDown.addItem(airport.getName());
	    	
	    }
	    for(Plane plane : planesList){
	    	planes.addItem(plane.getName());
	    	
	    }
	    frame.add(dropDown);
	    frame.add(planes);
	    JPanel buttonPanel =new JPanel();
	    buttonPanel.setLayout(new GridLayout(2,1));
	    buttonPanel.add(submit);
	    buttonPanel.add(Add);
	    frame.add(buttonPanel);
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
			frame.setEnabled(false);
			frame.dispose();
			MainFrame gui2 = new MainFrame(airport,plane);

			
		}
		
	}
	class AddButtonListener extends JFrame implements ActionListener{
		JComboBox airport;
		JComboBox plane;
		ArrayList<Airport> airports;
		ArrayList<Plane>planes;
		public AddButtonListener(JComboBox dropDown,JComboBox planes,ArrayList<Airport> airports,ArrayList<Plane> planesList){

		}
		public void actionPerformed(ActionEvent e) {
				AirportPlanes ap = new AirportPlanes();
		}
	}
}





