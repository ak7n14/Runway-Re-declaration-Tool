import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//Second gui for calculations


public class CalculationMainScreen {
	Container panel;
	JFrame window;
	JPanel frame;
	Airport airport;
	public CalculationMainScreen(Airport airport){
		this.airport = airport;//Selected airport from the 1st screen
	    init(airport.getName());//Initialize GUI
	}
	
	//Initialise gui
	
	void init(String name){
		
		window = new JFrame(name);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(700,400);
	    panel = window.getContentPane();
	    frame = new JPanel();
	    frame.setLayout(new FlowLayout());
		JComboBox runwayDrop = new JComboBox();
	    ArrayList<Runway> runways = airport.getRunways();
	    for(Runway runway:runways){
	    	runwayDrop.addItem(runway.getDesignator());
	    }
	    JPanel runwayPanel = new JPanel();
	    runwayPanel.add(runwayDrop);
	    frame.add(runwayPanel);
	    
	    JTextField objHeight = new JTextField("Enter Height of Object");
	    JTextField objLoc = new JTextField("Enter Location of Object");
	    
	    JPanel textPanel = new JPanel();
	    textPanel.setLayout(new GridLayout(2,1));
	    textPanel.add(objHeight);
	    textPanel.add(objLoc);
	    frame.add(textPanel);
	    
	    JComboBox direction = new JComboBox();
	    ButtonGroup group = new ButtonGroup();
	    JRadioButton landing = new JRadioButton("Landing");
	    JRadioButton takingOff= new JRadioButton("Taking Off");
	    group.add(landing);
	    group.add(takingOff);
	    landing.addActionListener(new RadioListener(direction,landing,takingOff));
	    takingOff.addActionListener(new RadioListener(direction,landing,takingOff));
	    JPanel radioPanel = new JPanel();
	    radioPanel.setLayout(new GridLayout(2,1));
	    radioPanel.add(landing);
	    radioPanel.add(takingOff);
	    frame.add(radioPanel);
	    
	    JPanel directionPanel = new JPanel();
	    directionPanel.add(direction);
	    frame.add(directionPanel);
	    
	   JPanel Buttonpanel = new JPanel();
	   JButton calculate = new JButton("Calculate");
	   Buttonpanel.add(calculate);
	   frame.add(Buttonpanel);
	   panel.add(frame);
	   window.setVisible(true);
	}
	
	class RadioListener implements ActionListener{
		JComboBox<String> direction;
		JRadioButton landing;
		JRadioButton takingOff;
		public RadioListener(JComboBox<String> direction,JRadioButton landing, JRadioButton takingOff){
			this.direction=direction;
			this.landing=landing;
			this.takingOff=takingOff;
		}

		public void actionPerformed(ActionEvent e) {
			 if(landing.isSelected()){
			    	for(int i=0;i<direction.getItemCount();i++){
			    		direction.remove(i);
			    	}
			    	direction.addItem("Towards");
			    	direction.addItem("Over");
			    }
			    if(takingOff.isSelected()){
			    	for(int i=0;i<direction.getItemCount();i++){
			    		direction.remove(i);
			    	}
			    	direction.addItem("Towards");
			    	direction.addItem("After");
			    }
			
		}

	}
}
