import com.sun.deploy.util.ArrayUtil;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class ObstacleBack extends Obstacle{
    
    //highest point of shape
    private int height;
    private String name;

    public ObstacleBack(String name, int height) {
        super();
        this.name = name;
        this.height = height;
    }

    //set coords with variable number of points for side view
    //loops through coords and scales proportionally to runway (0,0) is top left of runway
    public void setSideY(int... ys){
        sideViewY = ys;
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

    //returns max y coord
    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
}
