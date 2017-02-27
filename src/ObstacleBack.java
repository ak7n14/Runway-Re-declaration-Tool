import java.awt.*;
import java.util.ArrayList;

public class ObstacleBack extends Obstacle{
    //stores current runway
    private RunwayView currentRunway;

    private Polygon shapeSide;
    private Polygon shapeTop;

    public ObstacleBack() {
        super();
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

}
