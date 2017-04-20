package View;

import Model.Airport;
import Model.Plane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Anish on 4/19/17.
 */
public class MainFrame {
    int RESA;//Runway End Safety Area (RESA)
    int engineBlastAllowance;//blast allowance depends on the aircraft
    Plane plane;
    int tol;
    private JFrame frame;
    private Container mainContainer;
    public MainFrame(Airport airport, Plane plane) {

        initialize(airport,plane);
        RESA=240;
        engineBlastAllowance=300;//For now later can be taken as input(Depends on flight)
        this.plane=plane;
        tol=100;
    }

    public void initialize(Airport airport,Plane plane){
        frame = new JFrame(airport.getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1307, 837);
        mainContainer = frame.getContentPane();
        JPanel mainPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel notificationPanel = new JPanel();
        JPanel outputPanel = new JPanel();
        notificationPanel.setPreferredSize(new Dimension(390,107));
        inputPanel.setPreferredSize(new Dimension(390,300));
        outputPanel.setPreferredSize(new Dimension(390,285));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Panel"));
        notificationPanel.setBorder(BorderFactory.createTitledBorder("Notification Panel"));
        outputPanel.setBorder(BorderFactory.createTitledBorder("Output Panel"));
//        rightPanel.setLayout(null);
        rightPanel.setPreferredSize(new Dimension(400,837));
        rightPanel.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel.add(inputPanel);
        rightPanel.add(notificationPanel);
        rightPanel.add(outputPanel);
        inputPanel.setLocation(0,0);
        notificationPanel.setLocation(0,350);
        outputPanel.setLocation(0,487);
        JPanel nedsPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        TopPanel topPanel = new TopPanel(MainFrame.this);
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(rightPanel,BorderLayout.EAST);
        mainPanel.add(nedsPanel,BorderLayout.CENTER);

        mainContainer.add(mainPanel);
        frame.setVisible(true);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
    }

}
