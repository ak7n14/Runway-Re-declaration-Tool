import java.awt.*;
import java.util.*;

public class Obstacle {

    private final int RUNWAY_Y = 300;

    //x and y coordinates of points in polygon
    private int[] x;
    private int[] y;

    Polygon shape;

    //draws polygon
    public void drawShape(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fill(shape);
    }

    //set coords with variable number of points
    public void setY(int... ys){
        y = ys;
    }

    public void setX(int... xs){
        x = xs;
    }

    public void createPolygon(Graphics g){
        shape = new Polygon(x,y,x.length - 1);
        this.drawShape(g);
    }
}
