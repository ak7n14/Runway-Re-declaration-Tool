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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//Second gui for calculations


public class CalculationMainScreen {
	Container panel;
	JFrame window;
	JPanel frame;
	Airport airport;
	JPanel calculationPanel;
	public CalculationMainScreen(Airport airport){
		this.airport = airport;//Selected airport from the 1st screen
	    init(airport.getName());//Initialize GUI
	    calculationPanel = new JPanel();
	}
	
	//Initialise gui
	
	void init(String name){
		
		//Basic window Declarations
		window = new JFrame(name);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(700,400);
	    panel = window.getContentPane();
	    panel.setLayout(new GridLayout(2,1));
	    frame = new JPanel();//main frame
	    frame.setLayout(new FlowLayout());//layout of main frame
	    
	    //Dropdown menu to select runway
		JComboBox runwayDrop = new JComboBox();
	    ArrayList<Runway> runways = airport.getRunways();
	    for(Runway runway:runways){
	    	runwayDrop.addItem(runway.getDesignator());
	    }
	    JPanel runwayPanel = new JPanel();
	    runwayPanel.add(runwayDrop);
	    frame.add(runwayPanel);//Adding runway to main frame
	    
	    
	    //Textboxes for taking inputs
	    JTextField objHeight = new JTextField("0");
	    JTextField objLoc = new JTextField("0");
	    
	    //Creating a panel for the 2 text boxes and adding them to the main frame
	    JPanel textPanel = new JPanel();
	    textPanel.setLayout(new GridLayout(2,2));
	    JLabel heightLabel = new JLabel("Enter Height of Object");
	    textPanel.add(heightLabel);
	    textPanel.add(objHeight);
	    JLabel locationLabel = new JLabel("Enter Location of Object");
	    textPanel.add(locationLabel);
	    textPanel.add(objLoc);
	    frame.add(textPanel);
	    
	    
	    //radio button and button group for take off or landing
	    ButtonGroup group = new ButtonGroup();
	    JRadioButton landing = new JRadioButton("Landing");
	    JRadioButton takingOff= new JRadioButton("Taking Off");
	    group.add(landing);
	    group.add(takingOff);
	    JPanel radioPanel = new JPanel();
	   
	    //Adding to the main frame
	    radioPanel.setLayout(new GridLayout(2,1));
	    radioPanel.add(landing);
	    radioPanel.add(takingOff);
	    frame.add(radioPanel);
	   
	    //Direction panel
	    
	    JPanel directionPanel = new JPanel();
	    directionPanel.setLayout(new GridLayout(2,1));
	    JRadioButton towards = new JRadioButton ("towards");
	    JRadioButton awayOver = new JRadioButton("Away/Over");
	    ButtonGroup directiongroup = new ButtonGroup();
	    directiongroup.add(towards);
	    directiongroup.add(awayOver);
	    directionPanel.add(towards);
	    directionPanel.add(awayOver);
	    frame.add(directionPanel);
	    
	    //buttons fot submitting
	   JPanel Buttonpanel = new JPanel();
	   JButton calculate = new JButton("Calculate");
	   
	   Buttonpanel.add(calculate);
	   frame.add(Buttonpanel);
	   panel.add(frame);
	   window.setVisible(true);
	   

	   calculate.addActionListener(new CalculateListener(runwayDrop,objHeight,objLoc,landing,towards,airport));
	}
	//prints details to screen
	public void printCalcLandOver(Calculations calc){
		   calculationPanel.removeAll();
		   calculationPanel.updateUI();
		   calculationPanel.setLayout(new GridLayout(3,2));
		   calculationPanel.add(new JLabel("Re- calculated LDA = "));
		   calculationPanel.add(new JLabel("TORA - Obstacle Location - ALS -60"));
		   calculationPanel.add(new JLabel(""));
		   String equation = String.format("=%d - %d - %d - %d",calc.getRunway().getTORA(),calc.getObsLoc(),calc.getALS(),60);
		   calculationPanel.add(new JLabel (equation));
		   calculationPanel.add(new JLabel(""));
		   calculationPanel.add(new JLabel(String.format("= %d",calc.getReLda())));
		   panel.add(calculationPanel);
		   frame.updateUI();
	}
	
	//prints details to screen
	public void printCalcLandTowards(Calculations calc){
	    calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(3,2));
		calculationPanel.add(new JLabel("Re- calculated LDA = "));
		calculationPanel.add(new JLabel("Obstacle Location - RESA - 60"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d - %d - %d",calc.getObsLoc(),calc.getRESA(),60);
		calculationPanel.add(new JLabel (equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReLda())));
		panel.add(calculationPanel);
		frame.updateUI();
	}
	//prints details to screen
	public void printCalcTakeOffTowards(Calculations calc){
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(5,2));
		calculationPanel.add(new JLabel("Re- calculated TORA = "));
		calculationPanel.add(new JLabel("Obstacle Location + Threshold Displacement - ALS - 60"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d + %d - %d -%d",calc.getObsLoc(),calc.getRunway().getThreasholdDisplacement(),calc.getALS(),60);
		calculationPanel.add(new JLabel (equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated TODA = Re- calculated TORA"));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated ASDA = Re- calculated TORA"));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReTORA())));
		panel.add(calculationPanel);
		frame.updateUI();
	}
	//Prints details to screen
	public void printTakeOffAfter(Calculations calc){
		
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(9,2));
		calculationPanel.add(new JLabel("Re- calculated TORA = "));
		calculationPanel.add(new JLabel("TORA - Obtacle Location - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d - %d - %d",calc.getRunway().getTORA(),calc.getObsLoc(),calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel (equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated TODA = "));
		calculationPanel.add(new JLabel("TODA - Obtacle Location - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation1 = String.format("=%d - %d - %d",calc.getRunway().getTODA(),calc.getObsLoc(),calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel (equation1));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReTODA())));
		calculationPanel.add(new JLabel("Re- calculated ASDA = "));
		calculationPanel.add(new JLabel("ASDA - Obtacle Location - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation2 = String.format("=%d - %d - %d",calc.getRunway().getASDA(),calc.getObsLoc(),calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel (equation2));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d",calc.getReASDA())));
		panel.add(calculationPanel);
		frame.updateUI();
		
		
	}
	class CalculateListener implements ActionListener{

		Calculations calc;
		JComboBox runwayDrop;
		JTextField objHeight;
		JTextField objLoc;
		JRadioButton landing;
		JRadioButton direction;
		Runway runway;
		Airport airport;
		public CalculateListener(JComboBox runwayDrop,JTextField objHeight,JTextField objLoc,JRadioButton landing,JRadioButton towards,Airport airport){	
			this.runwayDrop=runwayDrop;
			runwayDrop.setSelectedIndex(-1);
			this.objHeight = objHeight;
			this.objLoc=objLoc;
			this.landing = landing;
			this.direction=towards;
			this.airport=airport;
			
			
		}
		//Calls correct methods for calculations and printing to the screen
		public void actionPerformed(ActionEvent e) {
			
			Runway runway = airport.getRunwayByDesignator((String) runwayDrop.getItemAt(runwayDrop.getSelectedIndex()));
			calc = new Calculations(runway,Integer.parseInt(objHeight.getText()),Integer.parseInt(objLoc.getText()));
			if(landing.isSelected()){
				if(direction.isSelected()){
					calc.calculateLda("Towards");
					printCalcLandTowards(calc);
				
				}else{
					calc.calculateLda("Over");	
					printCalcLandOver(calc);
				}

			}
			else{
				if(direction.isSelected()){
					calc.calculateTORA("Towards");
					printCalcTakeOffTowards(calc);
				}
				else{
					calc.calculateTORA("After");
					printTakeOffAfter(calc);
				}
			}
			
		}
		
	}	

}
	
	
	