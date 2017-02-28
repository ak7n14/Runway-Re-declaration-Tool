import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
	Plane plane;
	public CalculationMainScreen(Airport airport,Plane plane){
		this.airport = airport;//Selected airport from the 1st screen
	    init(airport.getName());//Initialize GUI
	    calculationPanel = new JPanel();
	    this.plane=plane;
	}
	
	//Initialise gui
	
	void init(String name){
		
		//Basic window Declarations
		window = new JFrame(name);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(1120,600);
	    panel = window.getContentPane();
	    panel.setLayout(new GridLayout(3,1));
	    panel.setLayout(new FlowLayout());
	    frame = new JPanel();//main frame
	    frame.setLayout(new FlowLayout());//layout of main frame
	    //Dropdown menu to select runway
		JComboBox<String> runwayDrop = new JComboBox<String>();
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
	    JTextField objLocCentreLine = new JTextField("0");
	    
	    //Creating a panel for the 2 text boxes and adding them to the main frame
	    JPanel textPanel = new JPanel();
	    textPanel.setLayout(new GridLayout(3,2));
	    JLabel heightLabel = new JLabel("Enter Height of Object in meters");
	    textPanel.add(heightLabel);
	    textPanel.add(objHeight);
	    JLabel locationLabel = new JLabel("Enter Location of Object from threshold in meters");
	    textPanel.add(locationLabel);
	    textPanel.add(objLoc);
	    JLabel locationLabel2 = new JLabel("Enter Location of Object from center line in meters");
	    textPanel.add(locationLabel2);
	    textPanel.add(objLocCentreLine);
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
	    JRadioButton towards = new JRadioButton ("Towards");
	    JRadioButton awayOver = new JRadioButton("Away/Over");
	    ButtonGroup directiongroup = new ButtonGroup();
	    directionPanel.setSize(new Dimension(100,100));
	    directiongroup.add(towards);
	    directiongroup.add(awayOver);
	    directionPanel.add(towards);
	    directionPanel.add(awayOver);
	    frame.add(directionPanel).setSize(1000, 100);
	    
	    //buttons fot submitting
	   JPanel Buttonpanel = new JPanel();
	   JButton calculate = new JButton("Calculate");
	   Buttonpanel.add(calculate);
	   frame.add(Buttonpanel);
	   panel.add(frame);
	   window.setVisible(true);
	   

	   calculate.addActionListener(new CalculateListener(runwayDrop,objHeight,objLoc,landing,towards,airport,objLocCentreLine));
	}
	
	public void printObsOutOfRunway(Calculations calc,String Direction){
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setBackground(Color.GREEN);
		calculationPanel.setOpaque(true);
		
		JLabel label = new JLabel("Object out of graded Area");
		
		label.setBackground(Color.GREEN);
		label.setOpaque(true);
		calculationPanel.add(label);
		JLabel l = new JLabel();
		l.setBackground(Color.green);
		l.setOpaque(true);
		calculationPanel.add(l);
		if(Direction =="Landing"){
			calculationPanel.setLayout(new GridLayout(2,2));
			JLabel label1= new JLabel("LDA");
			label1.setOpaque(true);
			label1.setBackground(Color.GREEN);
			JLabel label2= new JLabel(String.format("= %d meters",calc.getRunway().getLDA()));
			label2.setBackground(Color.GREEN);
			label2.setOpaque(true);
			calculationPanel.add(label1);
			calculationPanel.add(label2);
		}
		
		else {
			calculationPanel.setLayout(new GridLayout(3,2));
			JLabel label2= new JLabel(String.format("TORA = %d meters",calc.getRunway().getTORA()));
			label2.setBackground(Color.GREEN);
			label2.setOpaque(true);
			calculationPanel.add(label2);
			JLabel label3= new JLabel(String.format("TODA = %d meters",calc.getRunway().getTODA()));
			label3.setBackground(Color.GREEN);
			label3.setOpaque(true);
			calculationPanel.add(label3);
			JLabel label4= new JLabel(String.format("ASDA = %d meters",calc.getRunway().getASDA()));
			label4.setBackground(Color.GREEN);
			label4.setOpaque(true);
			JLabel l2 = new JLabel("");
			l2.setBackground(Color.GREEN);
			l2.setOpaque(true);
			calculationPanel.add(label4);
			calculationPanel.add(l2);
		}
		panel.add(calculationPanel);
		calculationPanel.updateUI();
		frame.updateUI();
		
	}
	//prints details to screen
	public void printCalcLandOver(Calculations calc){
		   calculationPanel.removeAll();
		   calculationPanel.updateUI();
		   calculationPanel.setLayout(new GridLayout(7,2));
		   calculationPanel.add(new JLabel("TORA = "));
		   calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
		   calculationPanel.add(new JLabel("Obstacle Location from threshold = "));
		   calculationPanel.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
		   calculationPanel.add(new JLabel("ALS = "));
		   calculationPanel.add(new JLabel(String.format("%d meters", calc.getALS())));
		   calculationPanel.add(new JLabel("Re- calculated LDA = "));
		   calculationPanel.add(new JLabel("TORA - Obstacle Location From Threshold - ALS -60"));
		   calculationPanel.add(new JLabel(""));
		   String equation = String.format("=%d - %d - %d - %d",calc.getRunway().getTORA(),calc.getObsLoc(),calc.getALS(),60);
		   calculationPanel.add(new JLabel (equation));
		   calculationPanel.add(new JLabel(""));
		   calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReLda())));
		   if(calc.getReLda()>plane.getMinLandingDis()+100){
			   calculationPanel.setBackground(Color.GREEN);
		   }
		   else if(calc.getReLda()<plane.getMinLandingDis()){
			   calculationPanel.setBackground(Color.RED);
			   calculationPanel.add(new JLabel("Minimum Distance required to land"));
			   calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinLandingDis())));
		   }
		   else{
			   calculationPanel.setBackground(Color.YELLOW);
		   }
		   calculationPanel.setOpaque(true);
		   panel.add(calculationPanel);
		   frame.updateUI();
	}
	
	//prints details to screen
	public void printCalcLandTowards(Calculations calc){
		
	    calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(6,2));
		calculationPanel.add(new JLabel("Obstacle Location from threshold = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
		calculationPanel.add(new JLabel("RESA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRESA())));
		calculationPanel.add(new JLabel("Re- calculated LDA = "));
		calculationPanel.add(new JLabel("Obstacle Location From Threshold - RESA - 60"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d - %d - %d",calc.getObsLoc(),calc.getRESA(),60);
		calculationPanel.add(new JLabel (equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReLda())));
		if(calc.getReLda()>plane.getMinLandingDis()+100){
			   calculationPanel.setBackground(Color.GREEN);
		   }
		   else if(calc.getReLda()>plane.getMinLandingDis()){
			   calculationPanel.setBackground(Color.RED);
			   calculationPanel.add(new JLabel("Minimum Distance required to land"));
			   calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinLandingDis())));
		   }
		   else{
			   calculationPanel.setBackground(Color.YELLOW);
		   }
		calculationPanel.setOpaque(true);
		panel.add(calculationPanel);
		frame.updateUI();
	}
	//prints details to screen
	public void printCalcTakeOffTowards(Calculations calc){
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(10,2));
		calculationPanel.add(new JLabel("TORA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
		calculationPanel.add(new JLabel("Threshold Displacement = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getThreasholdDisplacement())));
		calculationPanel.add(new JLabel("ALS = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getALS())));
		calculationPanel.add(new JLabel("Re- calculated TORA = "));
		calculationPanel.add(new JLabel("Obstacle Location + Threshold Displacement - ALS - 60"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d + %d - %d -%d",calc.getObsLoc(),calc.getRunway().getThreasholdDisplacement(),calc.getALS(),60);
		calculationPanel.add(new JLabel (equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated TODA = Re- calculated TORA"));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated ASDA = Re- calculated TORA"));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReTORA())));
		if(calc.getReTORA()>plane.getMinTakeoffDis()+100 
				&& calc.getReTODA()>plane.getMinTakeoffDis()+100 
				&& calc.getReASDA()>plane.getMinTakeoffDis()+100 ){
			   calculationPanel.setBackground(Color.GREEN);
		   }
		   else if(calc.getReTORA()<plane.getMinTakeoffDis()
				   && calc.getReTODA()<plane.getMinTakeoffDis()
				   && calc.getReASDA()<plane.getMinTakeoffDis()){
			   calculationPanel.setBackground(Color.RED);
			   calculationPanel.add(new JLabel("Minimum Distance required to take off"));
			   calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinTakeoffDis())));
		   }
		   else{
			   calculationPanel.setBackground(Color.YELLOW);
		   }
		calculationPanel.setOpaque(true);
		panel.add(calculationPanel);
		frame.updateUI();
	}
	//Prints details to screen
	public void printTakeOffAfter(Calculations calc){
		
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(15,2));
		calculationPanel.add(new JLabel("TORA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
		calculationPanel.add(new JLabel("TODA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getTODA())));
		calculationPanel.add(new JLabel("ASDA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getASDA())));
		calculationPanel.add(new JLabel("Obstacle Location From Threshold = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
		calculationPanel.add(new JLabel("Obstacle Location From Threshold = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getEngineBlastAllowance())));
		calculationPanel.add(new JLabel("Re- calculated TORA = "));
		calculationPanel.add(new JLabel("TORA - Obtacle Location - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d - %d - %d",calc.getRunway().getTORA(),calc.getObsLoc(),calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel (equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated TODA = "));
		calculationPanel.add(new JLabel("TODA - Obtacle Location From Threshold - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation1 = String.format("=%d - %d - %d",calc.getRunway().getTODA(),calc.getObsLoc(),calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel (equation1));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReTODA())));
		calculationPanel.add(new JLabel("Re- calculated ASDA = "));
		calculationPanel.add(new JLabel("ASDA - Obtacle Location - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation2 = String.format("=%d - %d - %d",calc.getRunway().getASDA(),calc.getObsLoc(),calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel (equation2));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters",calc.getReASDA())));
		if(calc.getReTORA()>plane.getMinTakeoffDis()+100 
				&& calc.getReTODA()>plane.getMinTakeoffDis()+100 
				&& calc.getReASDA()>plane.getMinTakeoffDis()+100 ){
			   calculationPanel.setBackground(Color.GREEN);
		   }
		   else if(calc.getReTORA()<plane.getMinTakeoffDis()
				   && calc.getReTODA()<plane.getMinTakeoffDis()
				   && calc.getReASDA()<plane.getMinTakeoffDis()){
			   calculationPanel.setBackground(Color.RED);
			   calculationPanel.add(new JLabel("Minimum Distance required to take off"));
			   calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinTakeoffDis())));
		   }
		   else{
			   calculationPanel.setBackground(Color.YELLOW);
		   }
		calculationPanel.setOpaque(true);
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
		int obsLocCentreLine;
		JTextField temp;
		public CalculateListener(JComboBox runwayDrop,JTextField objHeight,JTextField objLoc,JRadioButton landing,JRadioButton towards,Airport airport,JTextField obsLocCentreLine){	
			this.runwayDrop=runwayDrop;
			runwayDrop.setSelectedIndex(-1);
			this.objHeight = objHeight;
			this.objLoc=objLoc;
			this.landing = landing;
			this.direction=towards;
			this.airport=airport;
			temp= obsLocCentreLine;
			
		}
		//Calls correct methods for calculations and printing to the screen
		public void actionPerformed(ActionEvent e) {
			obsLocCentreLine = Integer.parseInt(temp.getText());
			Runway runway = airport.getRunwayByDesignator((String) runwayDrop.getItemAt(runwayDrop.getSelectedIndex()));
			calc = new Calculations(runway,Integer.parseInt(objHeight.getText()),Integer.parseInt(objLoc.getText()));
			if(obsLocCentreLine>75){
				if (landing.isSelected())
					printObsOutOfRunway(calc,"Landing");
				else
					printObsOutOfRunway(calc,"Taking");
			}
			else{
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
}
	
	
	