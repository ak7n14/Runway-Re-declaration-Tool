package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anish on 4/24/17.
 */
public class AirportPlanes extends JFrame {
    XMLExporter exp;
    public AirportPlanes(CalculationGUI calcuGUI){
        exp=new XMLExporter();
        setTitle("Aiport/Aircraft");
        JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        JPanel airport = new JPanel();
        JPanel aircraft = new JPanel();
        CalculationGUI cal = calcuGUI;
        jtp.addTab("Airport", airport);
        jtp.addTab("Aircraft", aircraft);
        JTabbedPane apjtp = new JTabbedPane();
        airport.setLayout(new GridLayout(1,1));
        airport.add(apjtp);

        JPanel apImp = new JPanel();
        apImp.setLayout(new GridLayout(2,2));
        JLabel lblapImp = new JLabel("Name of aiport XML file:");
        JTextField txtapImp = new JTextField();
        txtapImp.setEditable(false);
        JButton btnapImp = new JButton("Load File");
        btnapImp.addActionListener(new FileChooser(AirportPlanes.this,"Open",txtapImp));
        JButton btnapImpSub = new JButton("Submit");
        btnapImpSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XMLImporter imp = new XMLImporter();
                ArrayList<Airport> temp,airports;
                airports=calcuGUI.getAirports();
                temp= calcuGUI.getImporter().importCustomAirports(txtapImp.getText());
                for(int i=0;i<temp.size();i++){
                    for(int j=0;j<airports.size();j++){
                        if(airports.get(j).getName().equals(temp.get(i).getName())){
                            temp.remove(i);
                        }
                    }
                }
                airports.addAll(temp);
                exp.exportAiports("Airports",airports);
                cal.updateAirportsList();
                AirportPlanes.this.dispose();
            }
        });
        apImp.add(lblapImp);
        apImp.add(txtapImp);
        apImp.add(btnapImp);
        apImp.add(btnapImpSub);
        JPanel apAdd = new JPanel();
        apAdd.setLayout(new GridLayout(3,2));
        JLabel lblapName = new JLabel("Name");
        JTextField txtapName = new JTextField();
        JLabel lblapNoRW = new JLabel("Number of runways");
        JTextField txtapNoRW = new JTextField("0");
        txtapNoRW.setEditable(false);
        JButton btnAddRW = new JButton("Add a runway");

        JButton btnapSave = new JButton("Save");
        ArrayList<Runway> runways= new ArrayList<Runway>();

        //window to add details of a new runway
        class AddRunwayGUI extends JFrame{
            public AddRunwayGUI(ArrayList runways,JTextField noRunways){
                setTitle("Add Runway");
                setSize(355,320);

                JPanel panel = new JPanel();
                getContentPane().add(panel);
                panel.setLayout(new GridLayout(12,2));

                JLabel lblDesignator = new JLabel("Designator");
                JTextField txtDesignator = new JTextField();
                JLabel lblThreshold = new JLabel("Threshold Displacement");
                JTextField txtThreshold= new JTextField();
                JLabel lblLDA = new JLabel("LDA");
                JTextField txtLDA = new JTextField();
                JLabel lblASDA = new JLabel("ASDA");
                JTextField txtASDA = new JTextField();
                JLabel lblTORA = new JLabel("TORA");
                JTextField txtTORA = new JTextField();
                JLabel lblTODA = new JLabel("TODA");
                JTextField txtTODA = new JTextField();
                JLabel lblLength = new JLabel("Length");
                JTextField txtLength = new JTextField();
                JLabel lblWidth = new JLabel("Width");
                JTextField txtWidth = new JTextField();
                JLabel lblStripLength = new JLabel("StripLength");
                JTextField txtStripLength = new JTextField();
                JLabel lblStripWidth = new JLabel("StripWidth");
                JTextField txtStripWidth = new JTextField();
                JButton btnSave = new JButton("Add runway");
                panel.add(new JLabel(""));
                panel.add(new JLabel("All distances in meters"));
                panel.add(lblDesignator);
                panel.add(txtDesignator);
                panel.add(lblThreshold);
                panel.add(txtThreshold);
                panel.add(lblLDA);
                panel.add(txtLDA);
                panel.add(lblASDA);
                panel.add(txtASDA);
                panel.add(lblTORA);
                panel.add(txtTORA);
                panel.add(lblTODA);
                panel.add(txtTODA);
                panel.add(lblLength);
                panel.add(txtLength);
                panel.add(lblWidth);
                panel.add(txtWidth);
                panel.add(lblStripLength);
                panel.add(txtStripLength);
                panel.add(lblStripWidth);
                panel.add(txtStripWidth);
                panel.add(btnSave);
                btnSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int LDA=0,threshold=0,ASDA=0,TORA=0,TODA=0,Length=0,Width=0,StripLength=0,StripWidth=0;
                        try{
                            LDA = Integer.parseInt(txtLDA.getText());
                            threshold = Integer.parseInt(txtThreshold.getText());
                            ASDA = Integer.parseInt(txtASDA.getText());
                            TORA = Integer.parseInt(txtTORA.getText());
                            Length=Integer.parseInt(txtLength.getText());
                            Width=Integer.parseInt(txtWidth.getText());
                            StripLength=Integer.parseInt(txtStripLength.getText());
                            StripWidth= Integer.parseInt(txtStripLength.getText());
                        }catch (NumberFormatException er){
                            panel.add(new JLabel("Invalid Inputs Please Check"));
                            panel.updateUI();
                            return;
                        }
                        if(LDA<0||threshold<0||ASDA<0||TODA<0||TORA<0||Length<0||Width<0||StripLength<0||StripWidth<0){
                            panel.add(new JLabel("Invalid Inputs Please Check"));
                            panel.updateUI();
                            return;
                        }
                        runways.add(new Runway(txtDesignator.getText(),TORA,TODA,ASDA,LDA,threshold,Length,Width,StripLength,StripWidth));
                        noRunways.setText(String.valueOf(runways.size()));
                        noRunways.updateUI();
                        AddRunwayGUI.this.dispose();
                    }

                });


                setVisible(true);
            }
        }
        btnAddRW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRunwayGUI gui = new AddRunwayGUI(runways,txtapNoRW);
            }
        });
        btnapSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airport ap = new Airport(txtapName.getText(),runways);
                ArrayList<Airport> airports=calcuGUI.getAirports();
                airports.add(ap);
                exp.exportAiports("Airports",airports);
                cal.updateAirportsList();
                AirportPlanes.this.dispose();
            }
        });
        //runway GUI takes in an arraylist to add new runways to
        ArrayList dummyTestArrayList = new ArrayList<String>();

        apAdd.add(lblapName);
        apAdd.add(txtapName);
        apAdd.add(lblapNoRW);
        apAdd.add(txtapNoRW);
        apAdd.add(btnAddRW);
        apAdd.add(btnapSave);

        apjtp.addTab("Import", apImp);
        apjtp.addTab("Add", apAdd);
        //tabs in aircraft
        JTabbedPane acjtp = new JTabbedPane();
        aircraft.setLayout(new GridLayout(1,1));
        JPanel acImp = new JPanel();
        acImp.setLayout(new GridLayout(2,2));
        JLabel lblacImp = new JLabel("Name of aircaft XML file:");
        JTextField txtacImp = new JTextField();
        JButton btnacImp = new JButton("Load File");
        txtacImp.setEditable(false);
        btnacImp.addActionListener(new FileChooser(AirportPlanes.this,"Open",txtacImp));
        JButton btnacImpSub = new JButton("Submit");
        btnacImpSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XMLImporter imp = new XMLImporter();
                ArrayList<Plane> temp,planes;
                planes=calcuGUI.getPlanesList();
                temp= calcuGUI.getImporter().importCustomPlanes(txtacImp.getText());
                for(int i=0;i<temp.size();i++){
                    for(int j=0;j<planes.size();j++){
                        if(planes.get(j).getName().equals(temp.get(i).getName())){
                            temp.remove(i);
                        }
                    }
                }
                planes.addAll(temp);
                exp.exportPlanes("Planes",planes);
                cal.updatePlanesList();
                AirportPlanes.this.dispose();
            }
        });
        acImp.add(lblacImp);
        acImp.add(txtacImp);
        acImp.add(btnacImp);
        acImp.add(btnacImpSub);
        JLabel lblacExp = new JLabel("Name of aircraft XML file:");
        JPanel acAdd = new JPanel();
        acAdd.setLayout(new GridLayout(4,2));
        JLabel lblacName = new JLabel("Name");
        JTextField txtacName = new JTextField();
        JLabel lblacTake = new JLabel("Min take off length (m)");
        JTextField txtacTake = new JTextField();
        JLabel lblacLand = new JLabel("Min landing length (m)");
        JTextField txtacLand = new JTextField();
        JButton btnacSave = new JButton("Save");
        btnacSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minTFLen=0,minLanLen = 0;
                try{
                    minTFLen=Integer.parseInt(txtacLand.getText());
                    minLanLen = Integer.parseInt(txtacLand.getText());
                }catch (NumberFormatException er){
                    acAdd.add(new JLabel("Invalid inputs please check!"));
                    acAdd.updateUI();
                    return;
                }
                if(minLanLen<0||minTFLen<0){
                    JFrame frame = new JFrame("INVALID");
                    frame.getContentPane().add(new JLabel("Invalid inputs!"));
                    frame.setSize(300,100);
                    frame.setVisible(true);
                    return;
                }
                Plane plane = new Plane(txtacName.getText(),minLanLen,minTFLen);
                calcuGUI.getPlanesList().add(plane);
                exp.exportPlanes("Planes",calcuGUI.getPlanesList());
                calcuGUI.updatePlanesList();
                AirportPlanes.this.dispose();
            }
        });
        acAdd.add(lblacName);
        acAdd.add(txtacName);
        acAdd.add(lblacTake);
        acAdd.add(txtacTake);
        acAdd.add(lblacLand);
        acAdd.add(txtacLand);
        acAdd.add(btnacSave);

        acjtp.addTab("Import", acImp);
        acjtp.addTab("Add", acAdd);

        aircraft.add(acjtp);

        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
