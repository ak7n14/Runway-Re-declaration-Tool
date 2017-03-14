package View;
import Model.Runway;

import java.awt.*;

//Initiate with runway values
//call drawAll to display
//call updateSideView + drawAll to display update
public class RunwaySideView extends RunwayView{

    //Positioning of graphic elements
    @Override
    protected int RUNWAY_Y() { return 300; }
    //initial y position and height are constants for

    //defines size of runway
    public RunwaySideView(Runway runway, String direction, String takeOfforLand, int jpanelWidth, int jpanelHeight) {
        super(runway, jpanelWidth, jpanelHeight, 10, direction, takeOfforLand);
    }

    //draws runway, seperators and labels
    public void drawAll(Graphics g){
        this.drawRunway(g);
        this.drawClearWay(g);
        this.drawStopWay(g);
        //draws separator labels
        this.drawLabels(g);
        this.drawScaleX(g);
        //drawALS(g);
    }

    //draws runway
    public void drawRunway(Graphics g){
        g.setColor(Color.black);
        g.fillRect(100, RUNWAY_Y(), this.scaling(runwayLength), runwayHeight);
    }

    //draws rectangles to show scale 50 meter by 50 meter
    @Override
    public int MeterX() {
        return this.scaling(50);
    }

    public int MeterY() {
        if(ov.getOb().getHeight() < 50)
            return this.RUNWAY_Y() - ov.scalingSideHeight(10);
        return this.RUNWAY_Y() - ov.scalingSideHeight(50);
    }

    //draws als
    //UNTESTED
    public void drawALS(Graphics g){
        g.setColor(new Color(0xC5461B));
        int x = scaling(ov.getOb().getLength() + ov.getOriginalOffsetX());
        int y = ov.scalingSideHeight(ov.getOb().getHeight());
        int mody = y;

        //accounts for direction
        if (getTakeOffOrLand() == "Taking off"){
            mody *= -1;
        }
        g.drawLine(x, y, x + mody * 50, RUNWAY_Y());
    }
    //overides RunwayView so no scaling occurs
    public int scalingHeight(int y){
        return y;
    }
}
