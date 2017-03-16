package View;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.GraphicsPanel;
import Model.Airport;
import Model.Calculations;
import Model.ObstacleBack;
import Model.Plane;
import Model.Runway;
import Model.XMLImporter;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Mainscreen {
    //TODO: Make action Listener for runway
	private JFrame frame;
	private JTextField obsDisCL;
	private JTextField obsDistTh;
	int RESA;//Runway End Safety Area (RESA)
	int engineBlastAllowance;//blast allowance depends on the aircraft
    XMLImporter importer;
    ArrayList<ObstacleBack> obsList;
    ArrayList<Runway>runWayList;
    GraphicsPanel side;
    GraphicsPanel top;
	JPanel calculationPanel;
	Plane plane;
	int tol;
    /**
	 * Create the application.
	 */
	public Mainscreen(Airport airport, Plane plane) {

	    initialize(airport,plane);
		RESA=240;
		engineBlastAllowance=300;//For now later can be taken as input(Depends on flight)
		this.plane=plane;
		tol=100;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Airport airport ,Plane plane) {
        importer = new XMLImporter();
        obsList= importer.importObstacles();
        runWayList = airport.getRunways();
		frame = new JFrame(airport.getName());
		frame.setBounds(0, 0, 1307, 837);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		frame.setPreferredSize(new Dimension(1107,837));

		JPanel graphicsPanel = new JPanel();
		graphicsPanel.setLayout(new GridLayout(2,1));
		GridBagConstraints gbc_graphicsPanel = new GridBagConstraints();
		gbc_graphicsPanel.gridheight = 2;
		gbc_graphicsPanel.gridwidth = 30;
		gbc_graphicsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_graphicsPanel.fill = GridBagConstraints.BOTH;
		gbc_graphicsPanel.gridx = 0;
		gbc_graphicsPanel.gridy = 0;
		frame.getContentPane().add(graphicsPanel, gbc_graphicsPanel);

        System.out.println(graphicsPanel.getSize() + " " + graphicsPanel.getHeight());

		JPanel inputPanel = new JPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.insets = new Insets(0, 0, 5, 0);
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridx = 30;
		gbc_inputPanel.gridy = 0;
		frame.getContentPane().add(inputPanel, gbc_inputPanel);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_inputPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_inputPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		JLabel lblInputPanel = new JLabel("Input Panel");
		GridBagConstraints gbc_lblInputPanel = new GridBagConstraints();
		gbc_lblInputPanel.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputPanel.gridx = 1;
		gbc_lblInputPanel.gridy = 0;
		inputPanel.add(lblInputPanel, gbc_lblInputPanel);
		
		JLabel lblAllDistancesIn = new JLabel("All Distances in Meters");
		lblAllDistancesIn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAllDistancesIn = new GridBagConstraints();
		gbc_lblAllDistancesIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllDistancesIn.gridx = 2;
		gbc_lblAllDistancesIn.gridy = 1;
		inputPanel.add(lblAllDistancesIn, gbc_lblAllDistancesIn);
		
		
		JComboBox<String> runWayComboBox = new JComboBox<String>();
		for (Runway runways : runWayList){
			runWayComboBox.addItem(runways.getDesignator());
		}
		runWayComboBox.setSelectedIndex(-1);
		JLabel lblChooseRunway = new JLabel("Choose Runway");
		lblChooseRunway.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblChooseRunway = new GridBagConstraints();
		gbc_lblChooseRunway.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseRunway.gridx = 1;
		gbc_lblChooseRunway.gridy = 2;
		inputPanel.add(lblChooseRunway, gbc_lblChooseRunway);
		GridBagConstraints gbc_runWayComboBox = new GridBagConstraints();
		gbc_runWayComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_runWayComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_runWayComboBox.gridx = 2;
		gbc_runWayComboBox.gridy = 2;
		inputPanel.add(runWayComboBox, gbc_runWayComboBox);
	
		
		JComboBox<String> obsComboBox = new JComboBox<String>();
		for(ObstacleBack obs: obsList){
			obsComboBox.addItem(obs.getName());
		}
		obsComboBox.setSelectedIndex(-1);
		JLabel lblChooseObsticle = new JLabel("Choose Obsticle");
		lblChooseObsticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblChooseObsticle.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblChooseObsticle = new GridBagConstraints();
		gbc_lblChooseObsticle.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseObsticle.gridx = 1;
		gbc_lblChooseObsticle.gridy = 3;
		inputPanel.add(lblChooseObsticle, gbc_lblChooseObsticle);
		GridBagConstraints gbc_obsComboBox = new GridBagConstraints();
		gbc_obsComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_obsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_obsComboBox.gridx = 2;
		gbc_obsComboBox.gridy = 3;
		inputPanel.add(obsComboBox, gbc_obsComboBox);
		
		JLabel lblEnterObsticleLocation = new JLabel("Distance from CentreLine");
		lblEnterObsticleLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblEnterObsticleLocation = new GridBagConstraints();
		gbc_lblEnterObsticleLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterObsticleLocation.gridx = 1;
		gbc_lblEnterObsticleLocation.gridy = 4;
		inputPanel.add(lblEnterObsticleLocation, gbc_lblEnterObsticleLocation);
		
		obsDisCL = new JTextField("0");
		GridBagConstraints gbc_obsDisCL = new GridBagConstraints();
		gbc_obsDisCL.insets = new Insets(0, 0, 5, 5);
		gbc_obsDisCL.fill = GridBagConstraints.HORIZONTAL;
		gbc_obsDisCL.gridx = 2;
		gbc_obsDisCL.gridy = 4;
		inputPanel.add(obsDisCL, gbc_obsDisCL);
		obsDisCL.setColumns(10);
		
		JLabel lblDistanceFromThreshold = new JLabel("Distance from Threshold");
		lblDistanceFromThreshold.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblDistanceFromThreshold = new GridBagConstraints();
		gbc_lblDistanceFromThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblDistanceFromThreshold.gridx = 1;
		gbc_lblDistanceFromThreshold.gridy = 5;
		inputPanel.add(lblDistanceFromThreshold, gbc_lblDistanceFromThreshold);
		
		obsDistTh = new JTextField("0");
		GridBagConstraints gbc_obsDistTh = new GridBagConstraints();
		gbc_obsDistTh.insets = new Insets(0, 0, 5, 5);
		gbc_obsDistTh.fill = GridBagConstraints.HORIZONTAL;
		gbc_obsDistTh.gridx = 2;
		gbc_obsDistTh.gridy = 5;
		inputPanel.add(obsDistTh, gbc_obsDistTh);
		obsDistTh.setColumns(10);
		
		JLabel lblSelectAction = new JLabel("Select Action");
		lblSelectAction.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSelectAction = new GridBagConstraints();
		gbc_lblSelectAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectAction.gridx = 1;
		gbc_lblSelectAction.gridy = 6;
		inputPanel.add(lblSelectAction, gbc_lblSelectAction);
		
		JComboBox<String> actionComboBox = new JComboBox<String>();
		actionComboBox.addItem("Landing");
		actionComboBox.addItem("Taking off");
		actionComboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_actionComboBox = new GridBagConstraints();
		gbc_actionComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_actionComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_actionComboBox.gridx = 2;
		gbc_actionComboBox.gridy = 6;
		inputPanel.add(actionComboBox, gbc_actionComboBox);
		
		JLabel lblSelectDirection = new JLabel("Select Direction from Centerline");
		lblSelectDirection.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSelectDirection = new GridBagConstraints();
		gbc_lblSelectDirection.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectDirection.gridx = 1;
		gbc_lblSelectDirection.gridy = 7;
		inputPanel.add(lblSelectDirection, gbc_lblSelectDirection);
		
		JComboBox<String> leftRight = new JComboBox<String>();
		leftRight.addItem("Left");
		leftRight.addItem("Right");
		leftRight.setSelectedIndex(-1);
		GridBagConstraints gbc_leftRight = new GridBagConstraints();
		gbc_leftRight.insets = new Insets(0, 0, 5, 5);
		gbc_leftRight.fill = GridBagConstraints.HORIZONTAL;
		gbc_leftRight.gridx = 2;
		gbc_leftRight.gridy = 7;
		inputPanel.add(leftRight, gbc_leftRight);
		
		JLabel lblSelectDirectionOf = new JLabel("Select Direction of Action");
		lblSelectDirectionOf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSelectDirectionOf = new GridBagConstraints();
		gbc_lblSelectDirectionOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectDirectionOf.gridx = 1;
		gbc_lblSelectDirectionOf.gridy = 8;
		inputPanel.add(lblSelectDirectionOf, gbc_lblSelectDirectionOf);
		
		JComboBox<String> towardsAway = new JComboBox<String>();
		towardsAway.addItem("Towards");
		towardsAway.addItem("Away");
		towardsAway.setSelectedIndex(-1);
		GridBagConstraints gbc_towardsAway = new GridBagConstraints();
		gbc_towardsAway.insets = new Insets(0, 0, 5, 5);
		gbc_towardsAway.fill = GridBagConstraints.HORIZONTAL;
		gbc_towardsAway.gridx = 2;
		gbc_towardsAway.gridy = 8;
		inputPanel.add(towardsAway, gbc_towardsAway);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 9;
		inputPanel.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1,2));	
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new SettingsListener());
		btnSettings.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		panel.add(btnSettings);
		JButton btnCalculate = new JButton("Calculate");
		
		btnCalculate.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		panel.add(btnCalculate);
		
		calculationPanel = new JPanel();
		calculationPanel.setSize(300,300);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 30;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(calculationPanel, gbc_panel_1);
		frame.setVisible(true);
		btnCalculate.addActionListener(new CalculateListener(airport,runWayComboBox,importer,obsComboBox,obsDistTh,obsDisCL,RESA,engineBlastAllowance,leftRight,actionComboBox,towardsAway));
		side = new GraphicsPanel(new Runway("X", 1000, 1700, 1500, 300, 0, 1000, 500, 1750, 1000), "side", graphicsPanel.getWidth(), graphicsPanel.getHeight()/2);
        top = new GraphicsPanel(new Runway("X", 1000, 1700, 1500, 300, 0, 1000, 500, 1750, 1000), "top", graphicsPanel.getWidth(), graphicsPanel.getHeight()/2);
        graphicsPanel.add(side);
        graphicsPanel.add(top);
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
			calculationPanel.setLayout(new GridLayout(3, 2));
			JLabel label2 = new JLabel(String.format("TORA = %d meters", calc.getRunway().getTORA()));
			label2.setBackground(Color.GREEN);
			label2.setOpaque(true);
			calculationPanel.add(label2);
			JLabel label3 = new JLabel(String.format("TODA = %d meters", calc.getRunway().getTODA()));
			label3.setBackground(Color.GREEN);
			label3.setOpaque(true);
			calculationPanel.add(label3);
			JLabel label4 = new JLabel(String.format("ASDA = %d meters", calc.getRunway().getASDA()));
			label4.setBackground(Color.GREEN);
			label4.setOpaque(true);
			JLabel l2 = new JLabel("");
			l2.setBackground(Color.GREEN);
			l2.setOpaque(true);
			calculationPanel.add(label4);
			calculationPanel.add(l2);
		}
		calculationPanel.setSize(300,300);
		calculationPanel.updateUI();
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
	}
	//prints details to screen when plane is landing to the object
	public void printCalcLandTowards(Calculations calc) {

		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(6, 2));
		calculationPanel.add(new JLabel("Obstacle Location from threshold = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
		calculationPanel.add(new JLabel("RESA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRESA())));
		calculationPanel.add(new JLabel("Re- calculated LDA = "));
		calculationPanel.add(new JLabel("Obstacle Location From Threshold - RESA - 60"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d - %d - %d", calc.getObsLoc(), calc.getRESA(), 60);
		calculationPanel.add(new JLabel(equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReLda())));

		//Changing color of the panel according to the condition(If runway is sufficient)
		if (calc.getReLda() > plane.getMinLandingDis() + tol) {
			calculationPanel.setBackground(Color.GREEN);
		} else if (calc.getReLda() < plane.getMinLandingDis()) {
			calculationPanel.setBackground(Color.RED);
			calculationPanel.add(new JLabel("Minimum Distance required to land"));
			calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinLandingDis())));
		} else {
			calculationPanel.setBackground(Color.YELLOW);
		}
		calculationPanel.setOpaque(true);
	}
	//prints details to screen when plane is taking off towards the object
	public void printCalcTakeOffTowards(Calculations calc) {
		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(10, 2));
		calculationPanel.add(new JLabel("TORA = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
		calculationPanel.add(new JLabel("Threshold Displacement = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getRunway().getThreasholdDisplacement())));
		calculationPanel.add(new JLabel("ALS = "));
		calculationPanel.add(new JLabel(String.format("%d meters", calc.getALS())));
		calculationPanel.add(new JLabel("Re- calculated TORA = "));
		calculationPanel.add(new JLabel("Obstacle Location + Threshold Displacement - ALS - 60"));
		calculationPanel.add(new JLabel(""));
		String equation = String.format("=%d + %d - %d -%d", calc.getObsLoc(), calc.getRunway().getThreasholdDisplacement(), calc.getALS(), 60);
		calculationPanel.add(new JLabel(equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated TODA = Re- calculated TORA"));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated ASDA = Re- calculated TORA"));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReTORA())));

		//Changing color of the panel according to the condition(If runway is sufficient)
		if (calc.getReTORA() > plane.getMinTakeoffDis() + tol
				&& calc.getReTODA() > plane.getMinTakeoffDis() + tol
				&& calc.getReASDA() > plane.getMinTakeoffDis() + tol) {
			calculationPanel.setBackground(Color.GREEN);
		} else if (calc.getReTORA() < plane.getMinTakeoffDis()
				&& calc.getReTODA() < plane.getMinTakeoffDis()
				&& calc.getReASDA() < plane.getMinTakeoffDis()) {
			calculationPanel.setBackground(Color.RED);
			calculationPanel.add(new JLabel("Minimum Distance required to take off"));
			calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinTakeoffDis())));
		} else {
			calculationPanel.setBackground(Color.YELLOW);
		}
		calculationPanel.setOpaque(true);
	}
	//Prints details to screen
	public void printTakeOffAfter(Calculations calc) {

		calculationPanel.removeAll();
		calculationPanel.updateUI();
		calculationPanel.setLayout(new GridLayout(15, 2));
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
		String equation = String.format("=%d - %d - %d", calc.getRunway().getTORA(), calc.getObsLoc(), calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel(equation));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReTORA())));
		calculationPanel.add(new JLabel("Re- calculated TODA = "));
		calculationPanel.add(new JLabel("TODA - Obtacle Location From Threshold - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation1 = String.format("=%d - %d - %d", calc.getRunway().getTODA(), calc.getObsLoc(), calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel(equation1));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReTODA())));
		calculationPanel.add(new JLabel("Re- calculated ASDA = "));
		calculationPanel.add(new JLabel("ASDA - Obtacle Location - Engine Blast Allowence"));
		calculationPanel.add(new JLabel(""));
		String equation2 = String.format("=%d - %d - %d", calc.getRunway().getASDA(), calc.getObsLoc(), calc.getEngineBlastAllowance());
		calculationPanel.add(new JLabel(equation2));
		calculationPanel.add(new JLabel(""));
		calculationPanel.add(new JLabel(String.format("= %d meters", calc.getReASDA())));


		//Changing color of the panel according to the condition(If runway is sufficient)
		if (calc.getReTORA() > plane.getMinTakeoffDis() + tol
				&& calc.getReTODA() > plane.getMinTakeoffDis() + tol
				&& calc.getReASDA() > plane.getMinTakeoffDis() + tol) {
			calculationPanel.setBackground(Color.GREEN);
		} else if (calc.getReTORA() < plane.getMinTakeoffDis()
				&& calc.getReTODA() < plane.getMinTakeoffDis()
				&& calc.getReASDA() < plane.getMinTakeoffDis()) {
			calculationPanel.setBackground(Color.RED);
			calculationPanel.add(new JLabel("Minimum Distance required to take off"));
			calculationPanel.add(new JLabel(String.format("= %d meters", plane.getMinTakeoffDis())));
		} else {
			calculationPanel.setBackground(Color.YELLOW);
		}
		calculationPanel.setOpaque(true);
	}

	public void setEngineBlastAllowance(int engineBlastAllowance) {
		this.engineBlastAllowance = engineBlastAllowance;
	}
	
	public void setRESA(int rESA) {
		RESA = rESA;
	}
	
	class CalculateListener implements ActionListener{
		Runway runway;
		Airport airport;
		XMLImporter importer;
		ObstacleBack obs;
		int obsHeight;
		int obsLocThreshold;
		int obsLocCenteLine;
		int RESA;
		int eng;
		String Side;
		String Action;
		JTextField obsLocCL;
		JTextField obsLocTH;
		String Direction;
		String Obsticle;
		JComboBox<String> rw;
		JComboBox<String>sd;
		JComboBox<String>ac;
		JComboBox<String>dr;
		JComboBox<String>ob;

//      GraphicsPanel top; //ted
//      GraphicsPanel side; //ted
//		public CalculateListener(, /*ted*/ GraphicsPanel top, GraphicsPanel side /*ted*/){


//			this.top = top; /*ted*/
//          this.side = side /*ted*/
//		}

		public CalculateListener(Airport airport,JComboBox<String>runway,XMLImporter importer,JComboBox<String>obs,JTextField obsLocTH,
								 JTextField obsLocCL,int RESA,int Eng,JComboBox<String> Side,JComboBox<String>  Action,JComboBox<String>  Direction){
			this.airport=airport;
			this.rw = runway;
			this.importer = importer;
			this.obsLocTH=obsLocTH;
			this.obsLocCL=obsDisCL;
			this.RESA=RESA;
			this.eng= Eng;
			this.sd=Side;
			this.ac= Action;
			this.dr=Direction;
			ob = obs;

		}
		public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
				Side=sd.getItemAt(sd.getSelectedIndex());

				runway=airport.getRunwayByDesignator(rw.getItemAt(rw.getSelectedIndex()));
				Action=ac.getItemAt(ac.getSelectedIndex());
				Direction=dr.getItemAt(dr.getSelectedIndex());
				Obsticle = ob.getItemAt(ob.getSelectedIndex());
				obsLocCenteLine=Integer.parseInt(obsLocCL.getText());
				obsLocThreshold = Integer.parseInt(obsLocTH.getText());
				obs=importer.getObsticalByName(Obsticle);

				if(sd!=null&&obsLocCenteLine>=0 &&obsLocThreshold>=0&&Obsticle!=null&&Action!=null&&Direction!=null&&Obsticle!=null&&runway!=null){


					obsHeight=obs.getHeight();

					Calculations calc = new Calculations(runway, obs.getHeight(), obsLocThreshold);
					if(obsLocCenteLine>runway.getRunwayWidth()/4){
						if (Action=="Landing")
							printObsOutOfRunway(calc,"Landing");//Calling landing case
						else
							printObsOutOfRunway(calc,"Taking");//Calling taking off case
					}
					else{
						if(Action=="Landing"){//Cases of landing
							if(Direction=="Towards"){
								calc.calculateLda("Towards");//When landing towards
								printCalcLandTowards(calc);

							}else{
								calc.calculateLda("Away");	//When landing over
								printCalcLandOver(calc);
							}

						}
						else{//Cases of taking off
							if(Direction=="Towards"){
								calc.calculateTORA("Towards");//When taking off towards the object
								printCalcTakeOffTowards(calc);
							}
							else{
								calc.calculateTORA("Away");//When taking off after the object
								printTakeOffAfter(calc);
							}
						}

					}
						//change direction left or right of center line
//						int offsetZ = obsLocCenteLine;
//						if (Side == "Left") {
//							offsetZ *= 1;
//						}
//
//						top.setRunway(runway);
//						side.setRunway(runway);
//						top.updatePaint(calc, offsetZ, obs, Direction, Action);
//						side.updatePaint(calc, offsetZ, obs, Direction, Action);


					}
				else{
					NotificationWindow notification = new NotificationWindow("Invalid");
				}
		}
//		
	}
	
	class SettingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SettingsGUI setGui = new SettingsGUI(Mainscreen.this);
			
		}
		
	}
	
	

}
