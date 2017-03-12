package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import Model.Airport;
import Model.Plane;

import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Insets;

public class Mainscreen {

	private JFrame frame;

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
		frame.setBounds(1500, 1500, 1025, 837);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel graphicsPanel = new JPanel();
		GridBagConstraints gbc_graphicsPanel = new GridBagConstraints();
		gbc_graphicsPanel.gridwidth = 26;
		gbc_graphicsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_graphicsPanel.fill = GridBagConstraints.BOTH;
		gbc_graphicsPanel.gridx = 0;
		gbc_graphicsPanel.gridy = 0;
		frame.getContentPane().add(graphicsPanel, gbc_graphicsPanel);
		
		JPanel inputPanel = new JPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.gridwidth = 5;
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridx = 26;
		gbc_inputPanel.gridy = 0;
		frame.getContentPane().add(inputPanel, gbc_inputPanel);
		inputPanel.setLayout(new GridLayout(3,1));
		
		JPanel panel_1 = new JPanel();
		inputPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblInputArea = new JLabel("Input Area");
		panel_1.add(lblInputArea);
		
		
		
	}

}
