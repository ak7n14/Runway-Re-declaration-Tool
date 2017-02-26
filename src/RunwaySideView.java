import javax.swing.*;
import java.awt.*;
import java.util.*;

//Initiate with runway values
//call drawAll to display
//call updateSideView + drawAll to display update
public class RunwaySideView extends RunwayView{

    //Positioning of graphic elements
    @Override
    protected int RUNWAY_Y() { return 300; }
    @Override
    protected int RUNWAY_HEIGHT() { return 10; }
    @Override
    protected int SEPARATOR_HEIGHT() { return 10; }

    //initial y position and height are constants for

    //defines size of runway
    public RunwaySideView(int start, int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength) {
        super(start, LDAStart, TODALength, TORALength, ASDALength, LDALength, runwayLength);
    }
}
