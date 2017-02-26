import java.awt.*;
import java.util.*;

//Set side objects and top objects x and y coordinates for each point of polygon
//call createShapes to create side and top object
//call drawShape to display
public class Obstacle {

    private final int RUNWAY_Y = 300;

    //x and y coordinates of points in polygon for side view
    private int[] sideViewX;
    private int[] sideViewY;

    //x and y coordinates of points in polygon for top view
    private int[] topViewX;
    private int[] topViewY;

    private Polygon shapeSide;
    private Polygon shapeTop;

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
    public void setSideX(int... xs) {
        sideViewX = xs;
    }

    //set coords with variable number of points for top view
    public void setTopY(int... ys){
        topViewY = ys;
    }
    public void setTopX(int... xs){
        topViewX = xs;
    }

    //create both shapes
    public void createShapes(){
        this.createSidePolygon();
        this.createTopPolygon();
    }

    //create side view of shape
    private void createSidePolygon(){
        shapeSide = new Polygon(sideViewX,sideViewY, sideViewX.length);
    }

    //create top view of shape
    private void createTopPolygon(){
        shapeTop = new Polygon(topViewX,topViewY, topViewX.length);
    }

    //get shapes associated with obstacle
    public Polygon getShapeSide() {
        return shapeSide;
    }

    public Polygon getShapeTop() {
        return shapeTop;
    }
}
