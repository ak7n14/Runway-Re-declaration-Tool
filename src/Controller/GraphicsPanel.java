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
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {

    private RunwayView rsw;
    private Runway rw;
    private ObstacleView obsView;

    private int start;
    private int LDAStart;

    private int jPanelHeight;
    private int jPanelWidth;

    private String type;
    //if update method called set to true
    private boolean recalculated;
    private boolean turnToCompassHeading;
    private boolean zoom;
    private int zoomNum;

    ArrayList<JLabel> jLabels;
    ArrayList<JTextField> jTextFields;

    public GraphicsPanel(Runway runway, String type, int jPanelWidth, int jPanelHeight) {

        this.jPanelWidth = jPanelWidth;
        this.jPanelHeight = jPanelHeight;

        this.setSize(jPanelWidth, jPanelHeight);
        jLabels = new ArrayList<>();
        jTextFields = new ArrayList<>();

        //initalise not an update
        recalculated = false;
        turnToCompassHeading = false;
        rw = runway;
        this.start = 0;
        this.LDAStart = 0;

        this.type = type;

        //creates runway
        if(type.equals("top")) {
            rsw = new RunwayTopView(rw, jPanelWidth, jPanelHeight);
        }
        else if(type.equals("side")){
            rsw = new RunwaySideView(rw,  jPanelWidth, jPanelHeight);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //for resetting rotation after
        AffineTransform old = g2.getTransform();

        //rotates to compass heading
        //-90 to start at 0 degrees then use runway * 10 to get degree position
        if(turnToCompassHeading && rsw instanceof  RunwayTopView){

            //rotates by designator around center of JPanel
            Rectangle runway = rsw.getRunwayRect();
            g2.rotate(Math.toRadians((((RunwayTopView) rsw).getRunwayNum() * 10) - 90), runway.getCenterX(),runway.getCenterY() + 200);
        }
        if (zoom) {
            if(rsw instanceof RunwaySideView) {
                //y axis stays the same, x axis is zoomed and jpanel is increased in size to allow for this and allow scrollinf
                g2.scale(zoomNum,1);
                setPreferredSize(new Dimension(jPanelWidth * 2 * zoomNum, jPanelHeight));
            }
            else{
                //same as above but y-axis is zoomed as well.
                g2.scale(zoomNum,zoomNum);
                setPreferredSize(new Dimension(jPanelWidth * 2 * zoomNum, jPanelHeight * zoomNum));
            }
        }

        if(!(rsw.getRunway().getDesignator() == "X")) {
            rsw.drawAll(g2);

            //shifts runway under arrow
            if(rsw instanceof RunwayTopView) {
                g2.translate(1, 200);
            }

            //only if update
            if (recalculated)
                obsView.drawShape(g2);

            //only draw ALS/TOCS if it is a side view and this is an update
            if (recalculated && rsw instanceof RunwaySideView)
                ((RunwaySideView) rsw).drawALS(g2);

            rsw.drawAllSeparators(g2);
        }

        //resets transformations for when paint method is rerun so changes don't stack
        g2.setTransform(old);
    }

    public void setZoomNum(int zoomNum){
        this.zoomNum = zoomNum;
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(2000, 1000);
        jFrame.setLayout(new GridLayout(2,1));
        GraphicsPanel ptSide = new GraphicsPanel(new Runway("20R", 1000, 1700, 1500, 300, 0, 1000, 100, 2000, 500), "side", 1000, 500);
        GraphicsPanel ptTop = new GraphicsPanel(new Runway("20R", 1000, 1700, 1500, 300, 0, 1000, 100, 2000, 500), "top", 1000, 500);
        JScrollPane scrollPane = new JScrollPane(ptSide);
        JScrollPane scrollPane2 = new JScrollPane(ptTop);
        jFrame.add(scrollPane);
        jFrame.add(scrollPane2);
        ptSide.setZoomNum(1);
        ptTop.setZoomNum(1);
//        ptSide.toggleTurnToCompassHeading();
//        ptTop.toggleTurnToCompassHeading();
        jFrame.setVisible(true);
        ptSide.updatePaint(new Calculations(ptSide.getRw(), 20, 1000), 0, new ObstacleBack("nuke", 20, 100, 100), "Towards", "Taking off");
        ptTop.updatePaint(new Calculations(ptTop.getRw(), 20, 1000), 0, new ObstacleBack("nuke", 20, 100, 100), "Towards", "Taking off");

    }

    public void toggleTurnToCompassHeading() {
        turnToCompassHeading = !turnToCompassHeading;
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

    public void setZoom(boolean zoom){
        this.zoom = zoom;
    }

    public boolean isZoom() {
        return zoom;
    }

    public int getZoomNum() {
        return zoomNum;
    }

    public void incrementZoom(){
        zoomNum++;
    }

    public void decrementZoom(){
        zoomNum--;
    }

}
