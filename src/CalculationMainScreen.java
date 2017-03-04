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
	int changedResa;
	int changedEng;
	int tol;
	public CalculationMainScreen(Airport airport,Plane plane){
		this.airport = airport;//Selected airport from the 1st screen
	    init(airport.getName());//Initialize GUI
	    calculationPanel = new JPanel();
	    this.plane=plane;
	    this.changedResa=240;
	    this.changedEng=300;
	    this.tol=100;
	}
	
	//Initialise gui
	
	void init(String name){
		
		//Basic window Declarations
		window = new JFrame(name);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	    window.setSize(1250,420);
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
	    runwayPanel.add(new JLabel("Choose Runway:"));
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
	    
	    //buttons for submitting
	   JPanel Buttonpanel = new JPanel();
	   Buttonpanel.setLayout(new GridLayout(2,1));
	   JButton calculate = new JButton("Calculate");
	   JButton settings = new JButton("Change Default Vaules");
	   Buttonpanel.add(calculate);
	   Buttonpanel.add(settings);
	   frame.add(Buttonpanel);
	   panel.add(frame);
	   window.setVisible(true);
	   

	   calculate.addActionListener(new CalculateListener(runwayDrop,objHeight,objLoc,landing,towards,airport,objLocCentreLine,changedResa,changedEng));
	   settings.addActionListener(new SettingsListener());
	}
	
	public void printIncorrectValues(){
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(2,1));
		calculationPanel.setBackground(Color.ORANGE);
		calculationPanel.setOpaque(true);
		calculationPanel.add(new JLabel("Please Recheck Values"));
		calculationPanel.add(new JLabel("Values of obsicle hight and locations cannot be negative"));
		panel.add(calculationPanel);
		calculationPanel.updateUI();
		frame.updateUI();
	}
	
	//Adding labels to the screen when object is too far from the center line
	//Runway does not have to be re calculated
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
	
		//Checking if plane is landin
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
	
		//If plane is taking off
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
	
	//prints labels with details to screen when plane is landing over the object
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
		 
		   //Updating color of the panel depending on if the runway distance is sufficient or not
		   if(calc.getReLda()>plane.getMinLandingDis()+tol){
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
	
	//prints details to screen when plane is landing to the object
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
		
		//Changing color of the panel according to the condition(If runway is sufficient)
		if(calc.getReLda()>plane.getMinLandingDis()+tol){
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
	
	//prints details to screen when plane is taking off towards the object
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
		
		//Changing color of the panel according to the condition(If runway is sufficient)
		if(calc.getReTORA()>plane.getMinTakeoffDis()+tol 
				&& calc.getReTODA()>plane.getMinTakeoffDis()+tol 
				&& calc.getReASDA()>plane.getMinTakeoffDis()+tol ){
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
		
		
		//Changing color of the panel according to the condition(If runway is sufficient)
		if(calc.getReTORA()>plane.getMinTakeoffDis()+tol 
				&& calc.getReTODA()>plane.getMinTakeoffDis()+tol 
				&& calc.getReASDA()>plane.getMinTakeoffDis()+tol ){
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
	
	
	public void setChangedResa(int changedResa) {
		this.changedResa = changedResa;
	}
	
	
	public void setChangedEng(int changedEng) {
		this.changedEng = changedEng;
	}
	
	
	public void setTol(int tol) {
		this.tol = tol;
	}
	
	
	public int getChangedResa() {
		return changedResa;
	}
	
	public int getChangedEng() {
		return changedEng;
	}
	
	public int getTol() {
		return tol;
	}
	
	
	class SettingsGUI{
		Container panel;
		JFrame window;
		JPanel frame;
		JPanel change;
		JPanel text;
		
		public void init3(){
			window = new JFrame("Change Preferences");
			window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
		    window.setSize(300,200);
		    panel = window.getContentPane();
		    panel.setLayout(new FlowLayout());
		    frame = new JPanel();//main frame
		    frame.setLayout(new FlowLayout());//layout of main frame
		    panel.add(frame);
		    text = new JPanel();
		    text.setLayout(new GridLayout(3,2));
		    text.add(new JLabel("RESA:"));
		    JTextField resa = new JTextField(String.valueOf(getChangedResa()));
		    text.add(resa);
		    text.add(new JLabel("Engine Blast Allowence"));
		    JTextField eng = new JTextField(String.valueOf(getChangedEng()));
		    text.add(eng);
		    text.add(new JLabel("Tolerance:"));
		    JTextField tol = new JTextField(String.valueOf(getTol()));
		    text.add(tol);
		    panel.add(text);
		    change = new JPanel();
		    change.setLayout(new GridLayout(2,1));
		    JButton submit = new JButton("Change");
		    submit.addActionListener(new SubListener(Integer.parseInt(resa.getText()),Integer.parseInt(eng.getText()),Integer.parseInt(tol.getText()),window));
		    change.add(submit);
		    change.add(new JLabel("All values autotyped are default"));
		    panel.add(change);
		    window.setVisible(true);
		}
	}
	class SubListener implements ActionListener{

		int RESA;
		int Eng;
		int tol;
		JFrame window2;
		public SubListener(int resa,int eng,int Tol,JFrame window2){
			this.RESA=resa;
			this.Eng = eng;
			this.tol=Tol;
			this.window2=window2;
		}
		public void actionPerformed(ActionEvent e) {
			setChangedEng(Eng);
			setChangedResa(RESA);
			setTol(tol);
			window2.setVisible(false);
		}
		
	}
	class SettingsListener implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			SettingsGUI temp = new SettingsGUI();
			temp.init3();
		}
		
	}
	
	//Listener for the calculate button
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
		int RESA;
		int Eng;
		
		//Default constructions for initializations
		public CalculateListener(JComboBox runwayDrop,JTextField objHeight,JTextField objLoc,JRadioButton landing,JRadioButton towards,Airport airport,JTextField obsLocCentreLine,int RESA,int Eng){	
			this.runwayDrop=runwayDrop;
			runwayDrop.setSelectedIndex(-1);
			this.objHeight = objHeight;
			this.objLoc=objLoc;
			this.landing = landing;
			this.direction=towards;
			this.airport=airport;
			temp= obsLocCentreLine;
			this.RESA=RESA;
			this.Eng= Eng;
			
		}
		
		//Calls correct methods for calculations and printing to the screen
		public void actionPerformed(ActionEvent e) {
			obsLocCentreLine = Integer.parseInt(temp.getText());
			Runway runway = airport.getRunwayByDesignator((String) runwayDrop.getItemAt(runwayDrop.getSelectedIndex()));
			calc = new Calculations(runway,Integer.parseInt(objHeight.getText()),Integer.parseInt(objLoc.getText()),RESA,Eng);
			if(Integer.parseInt(objHeight.getText())<0
					|| Integer.parseInt(objLoc.getText())<0
					|| obsLocCentreLine<0){
				printIncorrectValues();
			}
			else{
			
			
			
			
			
			
			if(obsLocCentreLine>75){
				if (landing.isSelected())
					printObsOutOfRunway(calc,"Landing");//Calling landing case
				else
					printObsOutOfRunway(calc,"Taking");//Calling taking off case
			}
			else{
			if(landing.isSelected()){//Cases of landing
				if(direction.isSelected()){
					calc.calculateLda("Towards");//When landing towards
					printCalcLandTowards(calc);
				
				}else{
					calc.calculateLda("Over");	//When landing over
					printCalcLandOver(calc);
				}

			}
			else{//Cases of taking off
				if(direction.isSelected()){
					calc.calculateTORA("Towards");//When taking off towards the object
					printCalcTakeOffTowards(calc);
				}
				else{
					calc.calculateTORA("After");//When taking off after the object
					printTakeOffAfter(calc);
					}
				}
			
			}
			}
		}	
	}
}
	
	
	