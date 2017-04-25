package View;

import Model.Airport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Anish on 4/24/17.
 */
public class AirportPlanes extends JFrame {
    public AirportPlanes(){
        setTitle("Aiport/Aircraft");
        JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        JPanel airport = new JPanel();
        JPanel aircraft = new JPanel();
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

        //window to add details of a new runway
        class AddRunwayGUI extends JFrame{
            public AddRunwayGUI(ArrayList runways){
                setTitle("Add Runway");
                setSize(200,220);

                JPanel panel = new JPanel();
                getContentPane().add(panel);
                panel.setLayout(new GridLayout(10,2));

                JLabel lblDesignator = new JLabel("Designator");
                JTextField txtDesignator = new JTextField();
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

                panel.add(lblDesignator);
                panel.add(txtDesignator);
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


                setVisible(true);
            }
        }

        //runway GUI takes in an arraylist to add new runways to
        ArrayList dummyTestArrayList = new ArrayList<String>();
        btnAddRW.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                AddRunwayGUI addRWGUI = new AddRunwayGUI(dummyTestArrayList);
            }
        });

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
