package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Airport;
import Model.Plane;

import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Mainscreen {

	private JFrame frame;
	private JTextField obsDisCL;
	private JTextField ObsDistTh;

	/**
	 * Create the application.
	 */
	public Mainscreen(Airport airport, Plane plane) {
		initialize(airport,plane);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Airport airport ,Plane plane) {
		frame = new JFrame(airport.getName());
		frame.setBounds(1500, 1500, 1025, 828);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel graphicsPanel = new JPanel();
		GridBagConstraints gbc_graphicsPanel = new GridBagConstraints();
		gbc_graphicsPanel.gridheight = 2;
		gbc_graphicsPanel.gridwidth = 24;
		gbc_graphicsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_graphicsPanel.fill = GridBagConstraints.BOTH;
		gbc_graphicsPanel.gridx = 0;
		gbc_graphicsPanel.gridy = 0;
		frame.getContentPane().add(graphicsPanel, gbc_graphicsPanel);
		
		JPanel inputPanel = new JPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.gridheight = 2;
		gbc_inputPanel.insets = new Insets(0, 0, 5, 0);
		gbc_inputPanel.gridwidth = 6;
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridx = 25;
		gbc_inputPanel.gridy = 0;
		frame.getContentPane().add(inputPanel, gbc_inputPanel);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_inputPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_inputPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		JLabel lblInputPanel = new JLabel("Input Panel");
		GridBagConstraints gbc_lblInputPanel = new GridBagConstraints();
		gbc_lblInputPanel.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputPanel.gridx = 0;
		gbc_lblInputPanel.gridy = 0;
		inputPanel.add(lblInputPanel, gbc_lblInputPanel);
		
		JLabel lblAllDistancesIn = new JLabel("All Distances in Meters");
		lblAllDistancesIn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblAllDistancesIn = new GridBagConstraints();
		gbc_lblAllDistancesIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllDistancesIn.gridx = 2;
		gbc_lblAllDistancesIn.gridy = 1;
		inputPanel.add(lblAllDistancesIn, gbc_lblAllDistancesIn);
		
		JLabel lblChooseRunway = new JLabel("Choose Runway");
		lblChooseRunway.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblChooseRunway = new GridBagConstraints();
		gbc_lblChooseRunway.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseRunway.gridx = 0;
		gbc_lblChooseRunway.gridy = 3;
		inputPanel.add(lblChooseRunway, gbc_lblChooseRunway);
		
		JComboBox runWayComboBox = new JComboBox();
		GridBagConstraints gbc_runWayComboBox = new GridBagConstraints();
		gbc_runWayComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_runWayComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_runWayComboBox.gridx = 2;
		gbc_runWayComboBox.gridy = 3;
		inputPanel.add(runWayComboBox, gbc_runWayComboBox);
		
		JLabel lblChooseObsticle = new JLabel("Choose Obsticle");
		lblChooseObsticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblChooseObsticle.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblChooseObsticle = new GridBagConstraints();
		gbc_lblChooseObsticle.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseObsticle.gridx = 0;
		gbc_lblChooseObsticle.gridy = 4;
		inputPanel.add(lblChooseObsticle, gbc_lblChooseObsticle);
		
		JComboBox obsComboBox = new JComboBox();
		GridBagConstraints gbc_obsComboBox = new GridBagConstraints();
		gbc_obsComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_obsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_obsComboBox.gridx = 2;
		gbc_obsComboBox.gridy = 4;
		inputPanel.add(obsComboBox, gbc_obsComboBox);
		
		JLabel lblEnterObsticleLocation = new JLabel("Distance from CentreLine");
		lblEnterObsticleLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblEnterObsticleLocation = new GridBagConstraints();
		gbc_lblEnterObsticleLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterObsticleLocation.gridx = 0;
		gbc_lblEnterObsticleLocation.gridy = 5;
		inputPanel.add(lblEnterObsticleLocation, gbc_lblEnterObsticleLocation);
		
		obsDisCL = new JTextField();
		GridBagConstraints gbc_obsDisCL = new GridBagConstraints();
		gbc_obsDisCL.insets = new Insets(0, 0, 5, 5);
		gbc_obsDisCL.fill = GridBagConstraints.HORIZONTAL;
		gbc_obsDisCL.gridx = 2;
		gbc_obsDisCL.gridy = 5;
		inputPanel.add(obsDisCL, gbc_obsDisCL);
		obsDisCL.setColumns(10);
		
		JLabel lblDistanceFromThreshold = new JLabel("Distance from Threshold");
		lblDistanceFromThreshold.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblDistanceFromThreshold = new GridBagConstraints();
		gbc_lblDistanceFromThreshold.insets = new Insets(0, 0, 5, 5);
		gbc_lblDistanceFromThreshold.gridx = 0;
		gbc_lblDistanceFromThreshold.gridy = 6;
		inputPanel.add(lblDistanceFromThreshold, gbc_lblDistanceFromThreshold);
		
		ObsDistTh = new JTextField();
		GridBagConstraints gbc_ObsDistTh = new GridBagConstraints();
		gbc_ObsDistTh.insets = new Insets(0, 0, 5, 5);
		gbc_ObsDistTh.fill = GridBagConstraints.HORIZONTAL;
		gbc_ObsDistTh.gridx = 2;
		gbc_ObsDistTh.gridy = 6;
		inputPanel.add(ObsDistTh, gbc_ObsDistTh);
		ObsDistTh.setColumns(10);
		
		JLabel lblSelectAction = new JLabel("Select Action");
		lblSelectAction.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSelectAction = new GridBagConstraints();
		gbc_lblSelectAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectAction.gridx = 0;
		gbc_lblSelectAction.gridy = 7;
		inputPanel.add(lblSelectAction, gbc_lblSelectAction);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 7;
		inputPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblSelectDirection = new JLabel("Select Direction");
		lblSelectDirection.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSelectDirection = new GridBagConstraints();
		gbc_lblSelectDirection.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectDirection.gridx = 0;
		gbc_lblSelectDirection.gridy = 8;
		inputPanel.add(lblSelectDirection, gbc_lblSelectDirection);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 8;
		inputPanel.add(comboBox_1, gbc_comboBox_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 9;
		inputPanel.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1,2));	
		JButton btnSettings = new JButton("Settings");
		panel.add(btnSettings);
		JButton btnCalculate = new JButton("Calculate");
		panel.add(btnCalculate);
		
		JPanel resultsPanel = new JPanel();
		GridBagConstraints gbc_resultsPanel = new GridBagConstraints();
		gbc_resultsPanel.gridwidth = 3;
		gbc_resultsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_resultsPanel.fill = GridBagConstraints.BOTH;
		gbc_resultsPanel.gridx = 0;
		gbc_resultsPanel.gridy = 12;
		inputPanel.add(resultsPanel, gbc_resultsPanel);
	}

}
