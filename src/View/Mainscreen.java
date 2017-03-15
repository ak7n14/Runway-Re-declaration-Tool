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
	private JFrame frame;
	private JTextField obsDisCL;
	private JTextField obsDistTh;
	int RESA;//Runway End Safety Area (RESA)
	int engineBlastAllowance;//blast allowance depends on the aircraft
	/**
	 * Create the application.
	 */
	public Mainscreen(Airport airport, Plane plane) {
		initialize(airport,plane);
		RESA=240;
		engineBlastAllowance=300;//For now later can be taken as input(Depends on flight)
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Airport airport ,Plane plane) {
		
		XMLImporter importer = new XMLImporter();
		ArrayList<ObstacleBack> obsList= importer.importObstacles();
		ArrayList<Runway>runWayList = airport.getRunways();
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
		graphicsPanel.setBackground(Color.GREEN);
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
		
		obsDisCL = new JTextField();
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
		
		obsDistTh = new JTextField();
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
		actionComboBox.addItem("Land");
		actionComboBox.addItem("Take off");
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
		inputPanel.setBackground(Color.orange);
		inputPanel.setOpaque(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 30;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		frame.setVisible(true);
//		btnCalculate.addActionListener(new CalculateListener(importer,airport,runWayComboBox,obsComboBox,obsDistTh,obsDisCL,RESA,engineBlastAllowance,towardsAway));
		btnCalculate.addActionListener(new CalculateListener());
        //NEED RUNWAY
        GraphicsPanel side = new GraphicsPanel(new Runway("20R", 1000, 1700, 1500, 300, 0, 1000, 500, 1750, 1000), "side", graphicsPanel.getWidth(), graphicsPanel.getHeight()/2);
        GraphicsPanel top = new GraphicsPanel(new Runway("20R", 1000, 1700, 1500, 300, 0, 1000, 500, 1750, 1000), "top", graphicsPanel.getWidth(), graphicsPanel.getHeight()/2);
        graphicsPanel.add(side);
        graphicsPanel.add(top);
	}

	public void setEngineBlastAllowance(int engineBlastAllowance) {
		this.engineBlastAllowance = engineBlastAllowance;
	}
	
	public void setRESA(int rESA) {
		RESA = rESA;
	}
	
	class CalculateListener implements ActionListener{
//		Runway runway;
//		int obsHeight;
//		int locTh;
//		int locCL;
//		int resa;
//		int eng;
//		String direction;
//      GraphicsPanel top; //ted
//      GraphicsPanel side; //ted
//		public CalculateListener(XMLImporter imp,Airport airport,JComboBox<String> runWayComboBox, JComboBox<String> obsComboBox,JTextField locTh,JTextField locCL,int resa,int eng,JComboBox<String> direction, /*ted*/ GraphicsPanel top, GraphicsPanel side /*ted*/){
//			this.runway=airport.getRunwayByDesignator(runWayComboBox.getItemAt(runWayComboBox.getSelectedIndex()));
//			this.obsHeight = imp.getObsticalByName(obsComboBox.getItemAt(obsComboBox.getSelectedIndex())).getHeight();
//			this.locTh = Integer.parseInt(locTh.getText());
//			this.locCL = Integer.parseInt(locCL.getText());
//			this.resa = resa;
//			this.eng = eng;
//			this.direction=direction.getItemAt(direction.getSelectedIndex());
//			this.top = top; /*ted*/
//          this.side = side /*ted*/
//		}
	public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			Calculations calc = new Calculations(runway, obsHeight, locTh, resa, eng);
//			calc.calculateLda(direction);
//			System.out.println(calc.getReLda());
//          top.updatePaint(calc, int offsetZ, ObstacleBack obs, direction, String takeOffOrLand) /*ted*/
//          side.updatePaint(calc, int offsetZ, ObstacleBack obs, direction, String takeOffOrLand) /*ted*/
			NotificationWindow notification = new NotificationWindow();
		}
//		
	}
	
	class SettingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			SettingsGUI setGui = new SettingsGUI();
			
		}
		
	}
	
	

}
