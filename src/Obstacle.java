import java.awt.*;
import java.util.*;

//Set side objects and top objects x and y coordinates for each point of polygon
//call createShapes to create side and top object
//call drawShape to display
public class Obstacle {

    //stores current runway
    private RunwayView currentRunway;

    //x and y coordinates of points in polygon for side view
    private ArrayList<Integer> sideViewX;
    private ArrayList<Integer> sideViewY;

    //x and y coordinates of points in polygon for top view
    private ArrayList<Integer> topViewX;
    private ArrayList<Integer> topViewY;

    private Polygon shapeSide;
    private Polygon shapeTop;

    public Obstacle() {
        sideViewX = new ArrayList<>();
        sideViewY = new ArrayList<>();
        topViewX = new ArrayList<>();
        topViewY = new ArrayList<>();

    }

    //draws polygon
    public void drawShape(Graphics g, Polygon polygon){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(polygon);
    }

    public void setCurrentRunway(RunwayView rv){
        currentRunway = rv;
    }

    //set coords with variable number of points for side view
    //loops through coords and scales proportionally to runway (0,0) is top left of runway
    public void setSideY(int... ys){
        for(int y : ys){
            sideViewY.add(currentRunway.scalingHeight(y) + 3 * currentRunway.START);
        }
    }

    public void setSideX(int... xs){
        for(int x : xs){
            sideViewX.add(currentRunway.scaling(x) + 3 * currentRunway.START);
        }
    }

    //set coords with variable number of points for top view
    public void setTopY(int... ys){
        for(int y : ys){
            topViewY.add(currentRunway.scalingHeight(y) + 3 * currentRunway.START);
        }
    }
    public void setTopX(int... xs){
        for(int x : xs){
            topViewY.add(currentRunway.scaling(x) + 3 * currentRunway.START);
        }
    }

    //create both shapes
    public void createShapes(){
        this.createSidePolygon();
        this.createTopPolygon();
    }

    //create side view of shape
    private void createSidePolygon(){
        shapeSide = new Polygon(convertToArray(sideViewX),convertToArray(sideViewY), sideViewX.size());
    }

    //create top view of shape
    private void createTopPolygon(){
        shapeTop = new Polygon(convertToArray(topViewX),convertToArray(topViewY), topViewX.size());
    }

    //get shapes associated with obstacle
    public Polygon getShapeSide() {
        return shapeSide;
    }

    public Polygon getShapeTop() {
        return shapeTop;
    }

    //takes an arraylist puts it into a stream and then maps each element to itself and place elements in int array
    //Integer arraylists dont map to int arrays, this is the solution
    private int[] convertToArray(ArrayList<Integer> al){
        return al.stream().mapToInt(i->i).toArray();
    }
}
