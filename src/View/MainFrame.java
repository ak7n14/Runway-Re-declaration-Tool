package View;

import Controller.GraphicsPanel;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    InputPanel inputPanel;
    TopPanel topPanel;

    private GraphicsPanel ptSide;
    private GraphicsPanel ptTop;
    public MainFrame(Airport airport, Plane plane) {

        initialize(airport,plane);
        this.plane=plane;
        tol=100;
    }
    //Initialize the panel
    public void initialize(Airport airport,Plane plane){
        frame = new JFrame(airport.getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 1307, 937);
        mainContainer = frame.getContentPane();
        JPanel mainPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        NotificationPanel notificationPanel = new NotificationPanel();
        OutputPanel outputPanel = new OutputPanel(frame,plane,tol);
        inputPanel = new InputPanel(airport,outputPanel,notificationPanel,plane,MainFrame.this);
        rightPanel.setPreferredSize(new Dimension(400,837));
        rightPanel.setBorder(BorderFactory.createTitledBorder(""));
        rightPanel.add(inputPanel);
        rightPanel.add(notificationPanel);
        JScrollPane scrollFrame = new JScrollPane(outputPanel);
        outputPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(390,270));
        rightPanel.add(scrollFrame);

        JPanel nedsPanel = new JPanel();
        nedsPanel.setLayout(new BorderLayout());
        JTabbedPane jtp = new JTabbedPane();
        ptSide = new GraphicsPanel(new Runway("X", 1000, 1700, 1500, 300, 0, 1000, 100, 2000, 500), "side", 1000, 500);
        ptTop = new GraphicsPanel(new Runway("X", 1000, 1700, 1500, 300, 0, 1000, 100, 2000, 500), "top", 1000, 500);
        ptSide.setPreferredSize(new Dimension(2000,1000));
        ptTop.setPreferredSize(new Dimension(2000,1000));


        JScrollPane side = new JScrollPane(ptSide);
        JScrollPane top = new JScrollPane(ptTop);
        jtp.addTab("Side", side);
        jtp.addTab("Top", top);

        nedsPanel.add(jtp);

        mainPanel.setLayout(new BorderLayout());
        topPanel = new TopPanel(MainFrame.this,airport,inputPanel,outputPanel);
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(rightPanel,BorderLayout.EAST);
        mainPanel.add(nedsPanel,BorderLayout.CENTER);

        mainContainer.add(mainPanel);
        frame.setVisible(true);

    }
    //=====getter methods ===
    public InputPanel getInputPanel() {
        return inputPanel;
    }

    public GraphicsPanel getPtSide() {
        return ptSide;
    }

    public GraphicsPanel getPtTop() {
        return ptTop;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }
    public void updateLogsList(int max){
        topPanel.updateLogsList(max);
    }
    public int getMaxLogDisplay(){
        return topPanel.getMaxLogDisplay();
    }
    public ArrayList<ObstacleBack> getObsList(){
        return inputPanel.getObsList();
    }
}
