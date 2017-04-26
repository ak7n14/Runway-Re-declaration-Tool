package View;

import Controller.GraphicsPanel;
import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    GraphicsPanel ptTop;
    GraphicsPanel ptSide;

    int maxLogDisplay;
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
        ptTop = frame.getPtTop();
        ptSide = frame.getPtSide();

        this.airport=airport;
        maxLogDisplay=10;
        XMLImporter importer= new XMLImporter();
        logsList = importer.importLogs(airport);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        URL zoominURL = Class.class.getResource("/View/zoominicon.png");
        BufferedImage zoominimg = ImageIO.read(zoominURL);
        URL zoomoutURL = Class.class.getResource("/View/zoomouticon.png");
        BufferedImage zoomoutimg = ImageIO.read(zoomoutURL);
        JButton zoomin = new JButton();
        zoomin.setToolTipText("Zoom in!");
        zoomin.setIcon(new ImageIcon(zoominimg));
        zoomin.setSize(zoomin.getPreferredSize());
        zoomin.addActionListener(actionEvent -> {
            if(!ptTop.isZoom()) {
                ptTop.setZoom(true);
            }
            if(!ptSide.isZoom()) {
                ptSide.setZoom(true);
            }
            //zoom in
            ptTop.incrementZoom();
            ptSide.incrementZoom();
            ptTop.repaint();
            ptSide.repaint();
        });

        JButton zoomout = new JButton();
        zoomout.setToolTipText("Zoom Out!");
        zoomout.addActionListener(actionEvent -> {
            if(ptTop.getZoomNum() > 1){
                ptTop.decrementZoom();
                ptSide.decrementZoom();
                ptTop.repaint();
                ptSide.repaint();
            }
        });

        zoomout.setIcon(new ImageIcon(zoomoutimg));
        zoomout.setSize(zoomout.getPreferredSize());
        URL settingsURL = Class.class.getResource("/View/settingsIcon.png");
        BufferedImage settingsimg = ImageIO.read(settingsURL);
        URL RotateRightUrl = Class.class.getResource("/View/rotate_right.png");
        BufferedImage RotateRightimg = ImageIO.read(RotateRightUrl);
        JButton rotateRight = new JButton();
        rotateRight.setToolTipText("Rotate");
        rotateRight.addActionListener(actionEvent -> {
            ptTop.toggleTurnToCompassHeading();
            ptTop.repaint();
        });
        rotateRight.setIcon(new ImageIcon(RotateRightimg));
        logs = new JComboBox<String>();
        if(logsList.size()<=maxLogDisplay){
            for(Log lg: logsList){
                logs.addItem(lg.getName());
            }
        }else{
            for(int i=0;i<maxLogDisplay;i++){
                logs.addItem(logsList.get(logsList.size()-1-i).getName());
            }
        }

        JButton open = new JButton("Open");
        open.setToolTipText("Open Log!");
        open.addActionListener(new logOpenListener(logsList,logs));
        JButton settings = new JButton();
        settings.setToolTipText("Settings");
        settings.setIcon(new ImageIcon(settingsimg));
        settings.addActionListener(new SettingsListener());
        panel1.add(zoomin);
        panel1.add(zoomout);
        panel1.add(rotateRight);
        panel1.add(settings);
        this.setBorder(BorderFactory.createTitledBorder(""));
        this.add(panel1);
        panel2.add(new JLabel("Logs:"));
        panel2.add(logs);
        panel2.add(open);
        this.add(panel2);

    }
    public void updateLogsList(int MaxSize){
        maxLogDisplay=MaxSize;
        logs.removeAllItems();
        if(logsList.size()<=maxLogDisplay){
            for(Log lg: logsList){
                logs.addItem(lg.getName());
            }
        }else{
            for(int i=0;i<maxLogDisplay;i++){
                logs.addItem(logsList.get(logsList.size()-1-i).getName());
            }
        }
        logs.updateUI();
        TopPanel.this.updateUI();
    }

    public void update(Log log) throws IOException{
        logsList.add(log);
        updateLogsList(maxLogDisplay);
        logs.updateUI();
        TopPanel.this.updateUI();
    }
    class SettingsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsGUI setGui = new SettingsGUI(frame);


        }

    }

    public int getMaxLogDisplay() {
        return maxLogDisplay;
    }

    class logOpenListener implements ActionListener{
        ArrayList<Log>logs;
        String selectedLog;
        JComboBox<String>logComboBox;
        public logOpenListener(ArrayList<Log>logs,JComboBox<String>selectedLog){
            this.logs =logs;
            logComboBox = selectedLog;
        }
        public void actionPerformed(ActionEvent e) {
            selectedLog = logComboBox.getItemAt(logComboBox.getSelectedIndex());
            Log log = null;
            for(Log lg:logs){
                if(lg.getName().equals(selectedLog)){
                  log = lg;
                  break;
                }
            }
            LogWindow window = new LogWindow(log);
        }
    }
}
