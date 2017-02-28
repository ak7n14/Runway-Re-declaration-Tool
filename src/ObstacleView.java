import java.awt.*;
import java.util.ArrayList;

public class ObstacleView extends Obstacle{

    //stores current runway
    private RunwayView currentRunway;

    private Polygon shapeSide;
    private Polygon shapeTop;

    private ObstacleBack ob;


    public ObstacleView(ObstacleBack ob, RunwayView rv) {
        super();

        this.ob = ob;
        //runway for scaling
        currentRunway = rv;

        //scales for runway
        setSideX(ob.sideViewX);
        setSideY(ob.sideViewY);
        setTopX(ob.topViewX);
        setTopY(ob.topViewY);

    }

    //draws polygon (either side or top)
    public void drawShape(Graphics g, String sideOrTop){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);

        if(sideOrTop.equals("side")) {
            g2.fill(shapeSide);
        }
        else {
            g2.fill(shapeTop);
        }
    }

    public void setCurrentRunway(RunwayView rv){
        currentRunway = rv;
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
            sideViewX[i] = currentRunway.scaling(xs[i]) + currentRunway.START;
        }
    }

    //set coords with variable number of points for top view
    public void setTopY(int... ys){
        topViewY = new int[ys.length];
        for(int i = 0; i < ys.length; i++){
            topViewY[i] = currentRunway.scalingHeight(ys[i]) + currentRunway.RUNWAY_Y();
        }
    }
    public void setTopX(int... xs){
        topViewX = new int[xs.length];
        for(int i = 0; i < xs.length; i++){
            topViewX[i] = currentRunway.scaling(xs[i]) + currentRunway.START;
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
        shapeTop = new Polygon(topViewX, topViewY, topViewX.length);
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
    int scalingSideHeight(int y){
        return (int)((double) currentRunway.RUNWAY_Y() - (double)y/((double)ob.getHeight() + currentRunway.jpanelHeight - currentRunway.RUNWAY_Y() - currentRunway.RUNWAY_HEIGHT()) * ((double)currentRunway.jpanelHeight));
    }

    //updates with new info
    public void updateView(int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength){
        //updates runway info
        currentRunway.updateView(LDAStart, TODALength, TORALength, ASDALength, LDALength);

        //scales for new runway
        setSideX(ob.sideViewX);
        setSideY(ob.sideViewY);
        setTopX(ob.topViewX);
        setTopY(ob.topViewY);
    }
}
