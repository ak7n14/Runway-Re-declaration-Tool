package View;
import java.awt.*;

import Controller.Main;
import Model.*;
public class ObstacleView extends Obstacle{

    //stores current runway
    private RunwayView currentRunway;

    private Polygon shapeSide;
    private Polygon shapeTop;

    private ObstacleBack ob;

    private int originalOffsetX;
    //for object positioning
    private int offsetX;
    private int offsetZ;

    private String runwayType;

    public ObstacleView(ObstacleBack ob, RunwayView rv, String runwayType, int offsetX, int offsetZ) {
        super();

        this.ob = ob;
        //runway for scaling
        currentRunway = rv;

        this.runwayType = runwayType;

        //associate an object
        currentRunway.setObstacle(this);

        //used for RESA placement
        originalOffsetX = offsetX;

        //scales offset so they relate to the runway in meters
        this.offsetX = scaling(offsetX, 0);
        this.offsetZ = scalingHeight(offsetZ, 0);

        //scales for runway
        setSideX(ob.sideViewX);
        setSideY(ob.sideViewY);
        setTopX(ob.topViewX);
        setTopY(ob.topViewZ);

    }

    //draws polygon (either side or top)
    public void drawShape(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if(runwayType.equals("side")) {
            //scales image based on object before displaying
            RunwaySideView rsw = (RunwaySideView) currentRunway;
            rsw.drawScaleY(g2);

            g2.setColor(Color.RED);
            g2.fill(shapeSide);
        }
        else {
            g2.setColor(Color.RED);
            g2.fill(shapeTop);
        }

    }

    //set coords with variable number of points for side view
    //loops through coords and scales proportionally to runway (0,0) is top left of runway
    public void setSideY(int... ys){
        sideViewY = new int[ys.length];
        for(int i = 0; i < ys.length; i++){
            sideViewY[i] = scalingSideHeight(ys[i]);
        }
    }

    public void setSideX(int... xs){
        sideViewX = new int[xs.length];
        for(int i = 0; i < xs.length; i++){
            sideViewX[i] = scaling(xs[i], offsetX) + currentRunway.START;
        }
    }

    //set coords with variable number of points for top view
    public void setTopY(int... ys){
        topViewZ = new int[ys.length];
        for(int i = 0; i < ys.length; i++){
            topViewZ[i] = scalingHeight(ys[i], offsetZ) + currentRunway.RUNWAY_Y() + scalingHeight(currentRunway.runwayHeight/2,0);
        }
    }
    public void setTopX(int... xs){
        topViewX = new int[xs.length];
        for(int i = 0; i < xs.length; i++){
            topViewX[i] = scaling(xs[i], offsetX) + currentRunway.START;
        }
    }

    //create both shapes
    public void createShapes(){
        this.createSidePolygon();
        this.createTopPolygon();
    }

    //create side view of shape
    private void createSidePolygon(){
        shapeSide = new Polygon(sideViewX, sideViewY, sideViewX.length);
    }

    //create top view of shape
    private void createTopPolygon(){
        shapeTop = new Polygon(topViewX, topViewZ, topViewX.length);
    }

    //get shapes associated with obstacle
    public Polygon getShapeSide() {
        return shapeSide;
    }

    public Polygon getShapeTop() {
        return shapeTop;
    }


    //scaling for obstacles based on runway and not whole JPanel
    //scales obstacles in Jpanel based on height of obstacle
    public int scalingSideHeight(int y){
        int bottomGap = currentRunway.jpanelHeight - currentRunway.RUNWAY_Y();
        return (int)((double) currentRunway.RUNWAY_Y() - (double)y/((double)ob.getHeight()) * ((double)currentRunway.jpanelHeight - (7.0/4.0 * (double)bottomGap)));
    }

    //scales objects for JPanel in x direction proportional to runway length
    //scales objects for JPanel in x direction proportional to runway length
    public int scaling(int x, int offset){
        return (int) Math.ceil(((double)x/(double)currentRunway.getRunway().getStripLength()* ((double)currentRunway.jpanelWidth))) + offset;
    }

    //scales objects for JPanel in y direction proportional to height of runway
    public int scalingHeight(int y, int offset){
        return (int)((double)y/(double)currentRunway.getRunway().getStripWidth() * ((double)currentRunway.jpanelHeight - 2 * currentRunway.RUNWAY_Y())) - offset;
    }

    //updates with new info
    public void updateView(int start, int LDAStart, Calculations calc, String direction, String takeOfforLand){
        //updates runway info
        currentRunway.updateView(start, LDAStart, calc, direction, takeOfforLand);

        //scales for new runway
        setSideX(ob.sideViewX);
        setSideY(ob.sideViewY);
        setTopX(ob.topViewX);
        setTopY(ob.topViewZ);

        createShapes();
    }

    public ObstacleBack getOb() {
        return ob;
    }

    public int getOriginalOffsetX() { return originalOffsetX; }

    public int getOffsetX() {
        return offsetX;
    }
}
