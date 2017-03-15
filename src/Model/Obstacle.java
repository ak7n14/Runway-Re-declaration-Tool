package Model;

//Set side objects and top objects x and y coordinates for each point of polygon
//call createShapes to create side and top object
//call drawShape to display
public abstract class Obstacle {

    //x and y coordinates of points in polygon for side view
    public int[] sideViewX;
    public int[] sideViewY;

    //x and y coordinates of points in polygon for top view
    public int[] topViewX;
    public int[] topViewZ;


}
