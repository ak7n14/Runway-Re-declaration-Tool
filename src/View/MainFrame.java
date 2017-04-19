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
        JPanel nedsPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        TopPanel topPanel = new TopPanel(MainFrame.this);
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(nedsPanel,BorderLayout.CENTER);
        mainContainer.add(mainPanel);
        frame.setVisible(true);
    }
}
