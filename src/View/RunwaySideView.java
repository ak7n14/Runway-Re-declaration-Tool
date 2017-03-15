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
        drawALS(g);
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
        if(getOv().getOb().getHeight() < 50)
            return this.RUNWAY_Y() - getOv().scalingSideHeight(10);
        return this.RUNWAY_Y() - getOv().scalingSideHeight(50);
    }

    //draws als
    //UNTESTED
    public void drawALS(Graphics g){
        g.setColor(Color.RED);

        //front of object
        int x1 = getOv().getOb().getLength() + getOv().getOriginalOffsetX();
        int x = scaling(x1) + START;
        //top of object
        int y = getOv().scalingSideHeight(getOv().getOb().getHeight());

        //height x 50
        int mody1 = getCalc().getALS();
        int mody = scaling(mody1);

        boolean takeOff = getTakeOffOrLand() == "Taking off";
        boolean towards = getDirection() == "Towards";

        //accounts for direction
        if (takeOff && towards){
            int width = g.getFontMetrics().stringWidth("TOCS");
            mody *= -1;
            mody1 *= -1;
            g.drawString("TOCS", x + mody/2 - width, (3 * y)/2);
        }

        if(!takeOff && !towards){
            g.drawString("ALS", x + mody/2, (3 * y)/2);
        }

        //if both false or both true
        if(!(takeOff ^ towards)){
            g.setColor(Color.BLUE);
            g.drawLine(x, y, x + mody, RUNWAY_Y());
            drawSeparator(g,  x1 + mody1, 0);
        }

    }
    //overides RunwayView so no scaling occurs
    public int scalingHeight(int y){
        return y;
    }
}
