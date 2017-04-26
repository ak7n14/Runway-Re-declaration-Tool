package View;
import Model.ColourPalette;
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
    public RunwaySideView(Runway runway, int jpanelWidth, int jpanelHeight) {
        super(runway, jpanelWidth, jpanelHeight, 10);
    }

    //draws runway, seperators and labels
    public void drawAll(Graphics2D g){
        this.drawRunway(g);
        if(getTakeOffOrLand() == "Taking off") {
            this.drawClearWay(g);
            this.drawStopWay(g);
        }
        //draws separator labels
        this.drawLabels(g);
        this.drawScaleX(g);
    }

    //draws runway
    public void drawRunway(Graphics2D g){
        g.setColor(ColourPalette.black);
        int runwayDraw;
        if(getTakeOffOrLand().equals("Taking off")){
            runwayDraw = getRunwayEnds().get("TORA");
        }
        else {
            runwayDraw = getRunwayEnds().get("LDA");
        }
        runwayRect = new Rectangle(START, RUNWAY_Y(), this.scaling(runwayDraw) + this.scaling(getStart()), this.scalingHeight(runwayHeight));
        g.fill(runwayRect);
    }

    //draws rectangles to show scale 50 meter by 50 meter
    @Override
    public int MeterX() {
        return this.scaling(50);
    }

    public int MeterY() {
        if(getOv().getOb().getHeight() < 5)
            return this.RUNWAY_Y() - getOv().scalingSideHeight(1);
        if(getOv().getOb().getHeight() < 10)
            return this.RUNWAY_Y() - getOv().scalingSideHeight(5);
        else if(getOv().getOb().getHeight() < 50)
            return this.RUNWAY_Y() - getOv().scalingSideHeight(10);
        return this.RUNWAY_Y() - getOv().scalingSideHeight(50);
    }

    //draws als
    public void drawALS(Graphics2D g){
        g.setColor(ColourPalette.red);

        //front of object
        int x1 = getOv().getOb().getLength() + getOv().getOriginalOffsetX();
        int x = scaling(x1);
        //top of object
        int y = getOv().scalingSideHeight(getOv().getOb().getHeight());
        int y1 = getOv().scalingSideHeight(getOv().getOb().getHeight()/2);

        //height x 50
        int mody1 = getCalc().getALS();
        int mody = scaling(mody1);

        boolean takeOff = getTakeOffOrLand().equals("Taking off");
        boolean towards = getDirection().equals("Towards");

        //accounts for direction
        if (takeOff && towards){
            int width = g.getFontMetrics().stringWidth("TOCS");
            mody *= -1;
            x += START;
            g.drawString("TOCS", x + mody/2 - width - 5, RUNWAY_Y()/2);
        }

        if(!takeOff && !towards){
            x += START;
            g.drawString("ALS", x + mody/2 + 5, RUNWAY_Y()/2);
        }

        //if both false or both true
        if(!(takeOff ^ towards)){
            g.setColor(ColourPalette.blue);
            g.drawLine(x, y, x + mody, RUNWAY_Y());
        }

    }
    //overides RunwayView so no scaling occurs
    public int scalingHeight(int y){
        return y;
    }

}
