package View;

import Model.Airport;
import Model.Log;
import Model.Runway;
import Model.XMLImporter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anish on 4/19/17.
 */
public class TopPanel extends JPanel{
    MainFrame frame;
    InputPanel inputPanel;
    OutputPanel outputPanel;
    Airport airport;
    JComboBox<String> logs;
    ArrayList<Log> logsList;
    public TopPanel(MainFrame frame,Airport airport,InputPanel inputPanel,OutputPanel outputPanel){
        this.frame=frame;
        this.setLayout(new GridLayout(1,2));
        try {
            this.initialize(airport);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize(Airport airport) throws IOException {
        this.airport=airport;
        XMLImporter importer= new XMLImporter();
        logsList = importer.importLogs(airport);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        URL zoominURL = Class.class.getResource("/View/zoominicon.png");
        BufferedImage zoominimg = ImageIO.read(zoominURL);
        URL zoomoutURL = Class.class.getResource("/View/zoomouticon.png");
        BufferedImage zoomoutimg = ImageIO.read(zoomoutURL);
        JButton zoomin = new JButton();
        zoomin.setIcon(new ImageIcon(zoominimg));
        zoomin.setSize(zoomin.getPreferredSize());
        JButton zoomout = new JButton();
        zoomout.setIcon(new ImageIcon(zoomoutimg));
        zoomout.setSize(zoomout.getPreferredSize());
        JButton refresh = new JButton();
        URL settingsURL = Class.class.getResource("/View/settingsIcon.png");
        BufferedImage settingsimg = ImageIO.read(settingsURL);
        URL RefreshUrl = Class.class.getResource("/View/refreshicon.png");
        BufferedImage refreshimg = ImageIO.read(RefreshUrl);
        URL RotateLeftUrl = Class.class.getResource("/View/Rotate_left.png");
        BufferedImage RotateLeftimg = ImageIO.read(RotateLeftUrl);
        URL RotateRightUrl = Class.class.getResource("/View/rotate_right.png");
        BufferedImage RotateRightimg = ImageIO.read(RotateRightUrl);
        ImageIcon refreshIcon = new ImageIcon(refreshimg);
        refresh.setIcon(refreshIcon);
        JButton rotateRight = new JButton();
        rotateRight.setIcon(new ImageIcon(RotateRightimg));
        JButton rotateLeft = new JButton();
        logs = new JComboBox<String>();
        for(Log lg: logsList){
            logs.addItem(lg.getName());
        }
        JButton open = new JButton("Open");
        rotateLeft.setIcon(new ImageIcon(RotateLeftimg));
        JButton settings = new JButton();
        settings.setIcon(new ImageIcon(settingsimg));
        settings.addActionListener(new SettingsListener());
        panel1.add(zoomin);
        panel1.add(zoomout);
        panel1.add(refresh);
        panel1.add(rotateRight);
        panel1.add(rotateLeft);
        panel1.add(settings);
        this.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panel1);
        panel2.add(new JLabel("Logs:"));
        panel2.add(logs);
        panel2.add(open);
        this.add(panel2);

    }

    public void update(Log log) throws IOException{
        logs.addItem(log.getName());
        logsList.add(log);
        logs.updateUI();
        TopPanel.this.updateUI();
    }
    class SettingsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsGUI setGui = new SettingsGUI(frame);


        }

    }

    class logOpenListener implements ActionListener{

        InputPanel inputPanel;
        OutputPanel outputPanel;
        ArrayList<Log> logs;
        String selectedLog;

        public logOpenListener(InputPanel inputPanel,OutputPanel outputPanel,ArrayList<Log>Logs,JComboBox<String> selectedLog){
            this.inputPanel = inputPanel;
            this.outputPanel = outputPanel;
            this.logs = logs;
            this.selectedLog = selectedLog.getItemAt(selectedLog.getSelectedIndex());
        }
        public void actionPerformed(ActionEvent e) {

        }
    }
}
