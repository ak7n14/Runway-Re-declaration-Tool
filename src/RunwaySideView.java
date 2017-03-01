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
    public RunwaySideView(int LDAStart, int start, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength, int jpanelWidth, int jpanelHeight) {
        super(LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, jpanelWidth, jpanelHeight, 10);
    }

    //draws runway
    void drawRunway(Graphics g){
        g.setColor(Color.black);
        g.fillRect(START, RUNWAY_Y(), this.scaling(runwayLength), runwayHeight);
    }

    //draws rectangles to show scale 50 meter by 50 meter
    @Override
    public int MeterX() {
        return this.scaling(50);
    }

    public int MeterY() {
        if(ov.getOb().getHeight() < 50)
            return this.RUNWAY_Y() - ov.scalingSideHeight(10, 0);
        return this.RUNWAY_Y() - ov.scalingSideHeight(50, 0);
    }

    //overides RunwayView so no scaling occurs
    int scalingHeight(int y){
        return y;
    }
}
