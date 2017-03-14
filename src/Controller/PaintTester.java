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

public class PaintTester extends JPanel {

    private RunwayView rsw;
    private Runway rw;
    private ObstacleBack obs;
    private ObstacleView obsView;

    private int start;
    private int LDAStart;

    private String type;
    //if update method called set to true
    private boolean recalculated;

    ArrayList<JLabel> jLabels;
    ArrayList<JTextField> jTextFields;

    public PaintTester(Runway runway, String type, String direction, String  takeOffOrLand, int jPanelWidth, int jPanelHeight) {

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
            rsw = new RunwayTopView(rw, this.getWidth(), this.getHeight(), direction, takeOffOrLand);
        }
        else if(type.equals("side")){
            rsw = new RunwaySideView(rw, direction, takeOffOrLand, this.getWidth(), this.getHeight());
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

    //for testing only
    //----------------------

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        rsw.drawAll(g);

        if(recalculated)
            obsView.drawShape(g);

        rsw.drawAllSeparators(g);
    }
    //--------------------------

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(1000, 500);
        jFrame.add(new PaintTester(new Runway("20R", 300, 1700, 1500, 300, 0, 1000, 500, 2000, 4000), "side", "towards", "land", 1000, 500));
        jFrame.setVisible(true);
    }

    public void update(int offsetX, int offsetZ, int RESA, int eng, ObstacleBack obs, String direction, String takeOffOrLand){

        ObstacleView obsView = new ObstacleView(obs, rsw, type, offsetX, offsetZ);

        Calculations calc = new Calculations(rw,obs.getHeight(),offsetX,RESA,eng);

        obsView.updateView(start, LDAStart, calc, direction, takeOffOrLand);
        repaint();
    }

//    public void GUI(){
//        JFrame jFrame = new JFrame();
//
//        //create labels
//        jLabels.add(new JLabel("LDAStart"));
//        jLabels.add(new JLabel("Start"));
//        jLabels.add(new JLabel("TODA Length"));
//        jLabels.add(new JLabel("TORA Length"));
//        jLabels.add(new JLabel("ASDA Length"));
//        jLabels.add(new JLabel("LDA Length"));
//        jLabels.add(new JLabel("Runway Length"));
//        jLabels.add(new JLabel("Runway Height"));
//        jLabels.add(new JLabel("Runway Number"));
//        jLabels.add(new JLabel("Offset X"));
//        jLabels.add(new JLabel("Offset Y"));
//        jLabels.add(new JLabel("Offset Z"));
//        jLabels.add(new JLabel("Coord top x,z;x2,z2;...;xn,zn"));
//        jLabels.add(new JLabel("Coord side x,y;x2,y2;...;xn,yn"));
//        jLabels.add(new JLabel("Obstacle Height"));
//
//        JButton jButton = new JButton("OK");
//
//        //create input boxes
//        for (int i = 0; i < jLabels.size(); i++){
//            jFrame.add(jLabels.get(i));
//
//            jTextFields.add(new JTextField(5));
//
//            jFrame.add(jTextFields.get(i));
//            jFrame.add(jButton);
//        }
//
//        //set data on submit
//        jButton.addActionListener(e -> {
//            //on button click
//            JFrame jFrame2 = new JFrame();
//            jFrame2.setLayout(new GridLayout(1,2));
//
//            int LDAStart = getIntField(0);
//            int start = getIntField(1);
//            int TODALength = getIntField(2);
//            int TORALength = getIntField(3);
//            int ASDALength = getIntField(4);
//            int LDALength = getIntField(5);
//            int runwayLength = getIntField(6);
//            int runwayHeight = getIntField(7);
//            String runwayNumber = getField(8);
//
//            int offsetX = getIntField(9);
//            int offsetY = getIntField(10);
//            int offsetZ = getIntField(11);
//
//            int[] obX;
//            int[] obY;
//            int[] obSideX;
//            int[] obSideY;
//
//
//            obX = getCoord(12, "x");
//
//            obY = getCoord(12, "y");
//
//
//            obSideX = getCoord(13, "x");
//
//            obSideY = getCoord(13, "y");
//
//            int obstacleHeight = getIntField(14);
//
//            //add visuals to frame
//            jFrame2.setSize(2000, 400);
////            jFrame2.add(new PaintTester("side", LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, runwayHeight, runwayNumber, offsetX, offsetY, offsetZ, obX, obY, obSideX, obSideY, obstacleHeight));
////            jFrame2.add(new PaintTester("top", LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, runwayHeight, runwayNumber, offsetX, offsetY, offsetZ, obX, obY, obSideX, obSideY, obstacleHeight));
//            jFrame2.setVisible(true);
//        });
//
//        jFrame.setLocation(0,500); //position on screen
//        jFrame.setSize(1000, 400);
//        jFrame.setLayout(new FlowLayout());
//        jFrame.setVisible(true);
//    }

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
}
