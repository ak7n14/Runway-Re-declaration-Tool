import java.awt.*;
import java.util.*;

public class Obstacle {

    private final int RUNWAY_Y = 300;

    //x and y coordinates of points in polygon for side view
    private int[] sideViewX;
    private int[] sideViewY;

    //x and y coordinates of points in polygon for top view
    private int[] topViewX;
    private int[] topViewY;

    Polygon shapeSide;
    Polygon shapeTop;

    //draws polygon
    public void drawShape(Graphics g, Polygon polygon){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(polygon);
    }

    //set coords with variable number of points for side view
    public void setSideY(int... ys){
        sideViewY = ys;
    }
    public void setSideX(int... xs) { sideViewX = xs; }

    //set coords with variable number of points for top view
    public void setTopY(int... ys){
        topViewY = ys;
    }
    public void setTopX(int... xs){
        topViewX = xs;
    }

    //create side view of shape
    public void createSidePolygon(){
        shapeSide = new Polygon(sideViewX,sideViewY, sideViewX.length - 1);
    }

    //create top view of shape
    public void createTopPolygon(){
        shapeTop = new Polygon(topViewX,topViewY, topViewX.length - 1);
    }

    //get shapes associated with obstacle
    public Polygon getShapeSide() {
        return shapeSide;
    }

    public Polygon getShapeTop() {
        return shapeTop;
    }
}
