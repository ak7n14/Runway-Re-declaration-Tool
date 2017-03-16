package Controller;
import javax.swing.*;

import Model.Calculations;
import Model.Runway;
import Model.ObstacleBack;
import View.ObstacleView;
import View.RunwaySideView;
import View.RunwayTopView;
import View.RunwayView;

import java.awt.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {

    private RunwayView rsw;
    private Runway rw;
    private ObstacleView obsView;

    private int start;
    private int LDAStart;

    private String type;
    //if update method called set to true
    private boolean recalculated;

    ArrayList<JLabel> jLabels;
    ArrayList<JTextField> jTextFields;

    public GraphicsPanel(Runway runway, String type, int jPanelWidth, int jPanelHeight) {

        this.setSize(jPanelWidth, jPanelHeight);
        jLabels = new ArrayList<>();
        jTextFields = new ArrayList<>();

        //initalise not an update
        recalculated = false;
        rw = runway;
        this.start = 0;
        this.LDAStart = 0;

        this.type = type;

        //creates runway
        if(type.equals("top")) {
            rsw = new RunwayTopView(rw, this.getWidth(), this.getHeight());
        }
        else if(type.equals("side")){
            rsw = new RunwaySideView(rw,  this.getWidth(), this.getHeight());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(!(rsw.getRunway().getDesignator() == "X")) {
            rsw.drawAll(g);

            //only if update
            if (recalculated)
                obsView.drawShape(g);

            //only draw ALS/TOCS if it is a side view and this is an update
            if (recalculated && rsw instanceof RunwaySideView)
                ((RunwaySideView) rsw).drawALS(g);

            rsw.drawAllSeparators(g);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(1000, 500);
        GraphicsPanel pt = new GraphicsPanel(new Runway("20R", 1000, 1700, 1500, 300, 0, 1000, 500, 2000, 1000), "side", 1000, 500);
        jFrame.add(pt);
        jFrame.setVisible(true);
        pt.updatePaint(new Calculations(pt.getRw(), 20, 1000), 0, new ObstacleBack("nuke", 20, 100, 100), "Towards", "Taking off");
    }


    public void updatePaint(Calculations calc, int offsetZ, ObstacleBack obs, String direction, String takeOffOrLand){

        //recalc start points
        try {
            start = calc.getStartPoint(takeOffOrLand, direction);
            LDAStart = start;
        } catch (Exception e) {
            System.out.println("invalid input");
        }

        obsView = new ObstacleView(obs, rsw, type, calc.getObsLoc(), offsetZ);

        //update objects and signal update as true
        obsView.updateView(start, LDAStart, calc, direction, takeOffOrLand);
        recalculated = true;

        repaint();
    }

    public Runway getRw() {
        return rw;
    }

    public void setRunway(Runway runway) { rsw.setRunway(runway); rw = runway; }
}
