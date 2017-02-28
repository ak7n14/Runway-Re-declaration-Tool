import com.sun.deploy.util.ArrayUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class ObstacleBack extends Obstacle{
    //stores current runway
    private RunwayView currentRunway;

    private Polygon shapeSide;
    private Polygon shapeTop;

    //highest point of shape
    private int height;

    public ObstacleBack() {
        super();
    }

    //set coords with variable number of points for side view
    //loops through coords and scales proportionally to runway (0,0) is top left of runway
    public void setSideY(int... ys){
        sideViewY = ys;
        height = maxArray(sideViewY);
    }

    public void setSideX(int... xs){
        sideViewX = xs;
    }

    //set coords with variable number of points for top view
    public void setTopY(int... ys){
        topViewY = ys;
    }
    public void setTopX(int... xs){
        topViewX = xs;
    }

    //calculates maximum number in an array
    public int maxArray(int[] ary){
        int max = ary[0];

        for (int i = 1; i < ary.length; i++) {
            if (ary[i] > max) {
                max = ary[i];
            }
        }

        return max;
    }

    //returns max y coord
    public int getHeight() {
        return height;
    }
}
