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
	JComboBox dropDown;
	JComboBox planes;
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

		dropDown = new JComboBox();
		planes = new JComboBox();
		dropDown.setSelectedIndex(-1);
		planes.setSelectedIndex(-1);
		Add.addActionListener(new AddButtonListener(dropDown,planes,airports,planesList));
		for(Airport airport : airports){//Adding airports to the dropdown list
			dropDown.addItem(airport.getName());

		}
		for(Plane plane : planesList){//Adding planes
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
	//Update the airports.
	public void updateAirportsList(){
		dropDown.removeAllItems();
		for(Airport airport : airports){
			dropDown.addItem(airport.getName());
		}
		dropDown.updateUI();
		frame.updateUI();
	}
	//update planes list
	public void updatePlanesList(){
		planes.removeAllItems();
		for(Plane pl : planesList){
			planes.addItem(pl.getName());
		}
		planes.updateUI();
		frame.updateUI();
	}
	//initialize the second screen
	void initSecondFrame(String airportName,String planeName){
		XMLImporter importer= new XMLImporter();
		importer.importAirports();
		importer.importPlanes();
		airport = importer.getAirportByName(airportName);
		plane =importer.getPlaneByName(planeName);

	}
//=======getter methods====
	public ArrayList<Plane> getPlanesList() {
		return planesList;
	}

	public ArrayList<Airport> getAirports() {
		return airports;
	}

	public XMLImporter getImporter() {
		return importer;
	}
// Initialize new screen on button click
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
	//Add new airports and aircrafts
	class AddButtonListener implements ActionListener{
		JComboBox airport;
		JComboBox plane;
		ArrayList<Airport> airports;
		ArrayList<Plane>planes;
		public AddButtonListener(JComboBox dropDown,JComboBox planes,ArrayList<Airport> airports,ArrayList<Plane> planesList){

		}
		public void actionPerformed(ActionEvent e) {
			AirportPlanes ap = new AirportPlanes(CalculationGUI.this);
		}
	}
}





