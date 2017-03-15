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

        recalculated = false;
        rw = runway;
        this.start = 0;
        this.LDAStart = 0;
//        int[] x = {0, 100, 300, 100, 0};
//        int[] y = {0, 0, 100, 200, 200};
//
//        rsw = new RunwayTopView(100, 0, 900, 300, 400, 300, 1000,500, "10R", 1000, 400);
//        obs = new ObstacleBack("nuclear bomb mk2 v1.2.3 heavy armory edition", 200);
//
//        obs.setSideX(x);
//        obs.setSideY(y);
//        obs.setTopX(x);
//        obs.setTopY(y);
//
//        obsView = new ObstacleView(obs, rsw, "top", 100, 0, 300);
//        obsView.createShapes();

        this.type = type;

        //creates runway
        if(type.equals("top")) {
            rsw = new RunwayTopView(rw, this.getWidth(), this.getHeight());
        }
        else if(type.equals("side")){
            rsw = new RunwaySideView(rw,  this.getWidth(), this.getHeight());
        }


//        //creates obstacle
//        if(type.equals("side")||type.equals("top")){
//            obs = new ObstacleBack("nuclear bomb", obstacleHeight);
//
//            obs.setSideX(obSideX);
//            obs.setSideY(obSideY);
//            obs.setTopX(obX);
//            obs.setTopY(obY);
//
//            obsView = new ObstacleView(obs, rsw, type, offsetX, offsetY, offsetZ);
//            obsView.createShapes();
//        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        rsw.drawAll(g);

        if(recalculated)
            obsView.drawShape(g);

        if(recalculated && rsw instanceof RunwaySideView)
            ((RunwaySideView) rsw).drawALS(g);

        rsw.drawAllSeparators(g);
    }

    //for testing only
    //----------------------
    public Calculations createCalculator(int offsetX, int RESA, int eng, ObstacleBack obs){
        return new Calculations(rw,obs.getHeight(),offsetX,RESA,eng);
    }
    //--------------------------

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(1000, 500);
        GraphicsPanel pt = new GraphicsPanel(new Runway("20R", 1000, 1700, 1500, 300, 0, 1000, 500, 2000, 1000), "side", 1000, 500);
        jFrame.add(pt);
        jFrame.setVisible(true);
        pt.updatePaint(new Calculations(pt.getRw(), 20, 1000), 0, new ObstacleBack("nuke", 20, 100, 100), "Towards", "Taking off");
    }


    public void updatePaint(Calculations calc, int offsetZ, ObstacleBack obs, String direction, String takeOffOrLand){


        try {
            start = calc.getStartPoint(takeOffOrLand, direction);
            LDAStart = start;
        } catch (Exception e) {
            System.out.println("invalid input");
        }

        obsView = new ObstacleView(obs, rsw, type, calc.getObsLoc(), offsetZ);

        obsView.updateView(start, LDAStart, calc, direction, takeOffOrLand);
        recalculated = true;

        repaint();
    }

    //get text from textbox
    public String getField(int loc){
        return jTextFields.get(loc).getText();
    }

    //get int value of field
    public int getIntField(int loc){
        return Integer.parseInt(getField(loc));
    }

    //get coords
    public int[] getCoord(int loc, String xOrY){
        //loops through all coords
        String[] split = getField(loc).split(";");
        int[] coords = new int[split.length];
        for(int i = 0; i < split.length; i++) {
            //gets x and y
            String[] xy = split[i].split(",");

            if (xOrY.equals("x")) {
                coords[i] = Integer.parseInt(xy[0]);
            } else {
                coords[i] = Integer.parseInt(xy[1]);
            }
        }

        return coords;
    }

    public Runway getRw() {
        return rw;
    }
}
