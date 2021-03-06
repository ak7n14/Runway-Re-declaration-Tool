package View;

import Model.Obstacle;
import Model.ObstacleBack;
import Model.XMLExporter;
import Model.XMLImporter;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

public class SettingsGUI extends JFrame{

	XMLImporter importer = new XMLImporter();
	XMLExporter exporter = new XMLExporter();

	public SettingsGUI(MainFrame ms){
		setTitle("Settings");
		JTabbedPane jtp = new JTabbedPane();

		getContentPane().add(jtp);

		JPanel general = new JPanel();
		JPanel obstacle = new JPanel();

		jtp.addTab("General", general);
		jtp.addTab("Obstacle", obstacle);

		//general tab
		general.setLayout(new GridLayout(5, 2));
		JLabel lblRecents = new JLabel("Size of history to display");
		JLabel lblRESA = new JLabel("RESA");
		JLabel lblBlast = new JLabel("Default engine blast");
		JTextField txtRecents = new JTextField(String.valueOf(ms.getMaxLogDisplay()));
		JTextField txtRESA = new JTextField(ms.getInputPanel().getRESA()+"");
		JTextField txtBlast = new JTextField(ms.getInputPanel().getEngineBlastAllowance()+"");
		JButton btnSave = new JButton("Save");
	//Change general settings on button click
		btnSave.addActionListener(e ->
		{
			int Blast;
			int RESA;
			int Recents;
			try{
				Blast=Integer.parseInt(txtBlast.getText());
				RESA=Integer.parseInt(txtRESA.getText());
				Recents=Integer.parseInt(txtRecents.getText());
			}catch (NumberFormatException er) {
				general.add(new JLabel("Invalid inputs please check!"));
				general.updateUI();
				return;
			}
			if(Blast<0||RESA<0||Recents<0){
				general.add(new JLabel("Invalid inputs please check!"));
				general.updateUI();
				return;
			}
			ms.getInputPanel().setRESA(RESA);
			ms.getInputPanel().setEngineBlastAllowance(Blast);
			ms.updateLogsList(Recents);
			this.dispose();
		});

		general.add(lblRecents);
		general.add(txtRecents);
		general.add(lblRESA);
		general.add(txtRESA);
		general.add(lblBlast);
		general.add(txtBlast);
		general.add(btnSave);



		//tabs in obstacle
		JTabbedPane ojtp = new JTabbedPane();
		obstacle.setLayout(new GridLayout(1,1));
		obstacle.add(ojtp);

		JPanel oImp = new JPanel();
		oImp.setLayout(new GridLayout(2,2));
		JLabel lbloImp = new JLabel("Name of obstacle XML file:");
		JTextField txtoImp = new JTextField();
		txtoImp.setEditable(false);
		JButton btnoImp = new JButton("Load File");
		btnoImp.addActionListener(new FileChooser(SettingsGUI.this,"Open",txtoImp));
		JButton btoImpSub = new JButton("Submit");
		btoImpSub.addActionListener(new ActionListener() {
			@Override
			//Adding new Obstacles from XML file
			public void actionPerformed(ActionEvent e) {
				ArrayList<ObstacleBack> temp,original;
				original=ms.getObsList();
				temp = importer.importCustomObstacles(txtoImp.getText());
				for(int  i =0;i<temp.size();i++){
					for(int j=0;j<original.size();j++){
						if(original.get(j).getName().equals(temp.get(i).getName())){
							temp.remove(i);
						}
					}
				}
				original.addAll(temp);
				exporter.exportObstacles("Obstacles",original);
				ms.getInputPanel().updateObsList();
				SettingsGUI.this.dispose();
			}
		});

		oImp.add(lbloImp);
		oImp.add(txtoImp);
		oImp.add(btnoImp);
		oImp.add(btoImpSub);
		JPanel oExp = new JPanel();
		JPanel oAdd = new JPanel();
		oAdd.setLayout(new GridLayout(5,2));
		JLabel lbloName = new JLabel("Name");
		JTextField txtoName = new JTextField();
		JLabel lbloHeight = new JLabel("Height");
		JTextField txtoHeight = new JTextField();
		JLabel lbloLength = new JLabel("Length");
		JTextField txtoLength = new JTextField();
		JLabel lbloDepth = new JLabel("Depth");
		JTextField txtoDepth = new JTextField();
		JButton btnoSave = new JButton("Save");
		btnoSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int height=0,length=0,depth=0;
				try{
					height=Integer.parseInt(txtoHeight.getText());
					length=Integer.parseInt(txtoLength.getText());
					depth=Integer.parseInt(txtoDepth.getText());
				}catch (NumberFormatException er){
					oAdd.add(new JLabel("Invalid inputs please check!"));
					oAdd.updateUI();
					return;
				}
				if(height<0||length<0||depth<0){
					oAdd.add(new JLabel("Invalid inputs please check!"));
					oAdd.updateUI();
					return;
				}
				ObstacleBack obs = new ObstacleBack(txtoName.getText(),height,length,depth);
				ms.getObsList().add(obs);
				exporter.exportObstacles("Obstacles",ms.getObsList());
				ms.getInputPanel().updateObsList();
				SettingsGUI.this.dispose();
			}
		});

		oAdd.add(lbloName);
		oAdd.add(txtoName);
		oAdd.add(lbloHeight);
		oAdd.add(txtoHeight);
		oAdd.add(lbloLength);
		oAdd.add(txtoLength);
		oAdd.add(lbloDepth);
		oAdd.add(txtoDepth);
		oAdd.add(btnoSave);

		ojtp.addTab("Import", oImp);
		ojtp.addTab("Add", oAdd);

		setSize(400,200);
		setLocationRelativeTo(null);
		setVisible(true);

	}

}

