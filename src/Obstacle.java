//Set side objects and top objects x and y coordinates for each point of polygon
//call createShapes to create side and top object
//call drawShape to display
public abstract class Obstacle {

    //x and y coordinates of points in polygon for side view
    int[] sideViewX;
    int[] sideViewY;

    //x and y coordinates of points in polygon for top view
    int[] topViewX;
    int[] topViewY;

    private String name;

    //set coords with variable number of points for side view
    //loops through coords and scales proportionally to runway (0,0) is top left of runway
    public abstract void setSideY(int... ys);
    public abstract void setSideX(int... xs);

    //set coords with variable number of points for top view
    public abstract void setTopY(int... ys);
    public abstract void setTopX(int... xs);
}
