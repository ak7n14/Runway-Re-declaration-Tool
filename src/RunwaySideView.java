import java.awt.*;

//Initiate with runway values
//call drawAll to display
//call updateSideView + drawAll to display update
public class RunwaySideView extends RunwayView{

    //Positioning of graphic elements
    @Override
    protected int RUNWAY_Y() { return 300; }
    @Override
    protected int RUNWAY_HEIGHT() { return 10; }

    //initial y position and height are constants for

    //defines size of runway
    public RunwaySideView(int LDAStart, int start, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength, int jpanelWidth, int jpanelHeight) {
        super(LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, jpanelWidth, jpanelHeight);
    }

    //draws runway
    void drawRunway(Graphics g){
        g.setColor(Color.black);
        g.fillRect(START, RUNWAY_Y(), this.scaling(runwayLength), RUNWAY_HEIGHT());
    }

}
