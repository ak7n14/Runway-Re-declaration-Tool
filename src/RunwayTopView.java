import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.SplittableRandom;

//Initiate with runway values
//call drawAll to display
//call updateView + drawAll to display update
//runwayNumberR should be the number of runway on right side
public class RunwayTopView extends RunwayView{

    private int runwayNumberR;

    @Override
    protected int RUNWAY_Y() { return 100; }
    @Override
    protected int RUNWAY_HEIGHT() { return 200; }
    private final int DASHED_HEIGHT = RUNWAY_Y() + (RUNWAY_HEIGHT()/2);

    public RunwayTopView(int LDAStart, int start, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength, int runwayNumberR, int jpanelWidth, int jpanelHeight) {
        super(LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, jpanelWidth, jpanelHeight);
        this.runwayNumberR = runwayNumberR;
    }

    //draws runway, seperators and labels
    public void drawAll(Graphics g){
        this.drawRunway(g);
        this.drawAllSeparators(g);
        //draws separator labels
        this.drawLabels(g);

        //draw centre line in middle of runway
        Graphics2D g2 = (Graphics2D) g;
        this.drawCenterLine(g2);
        this.drawRunwayNumbers(g2);
    }

    //draws a white dashed line in middle of runway
    private void drawCenterLine(Graphics2D g2){

        //create a dashed format for line
        Stroke dashed = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{15, 5}, 0);

        //set format of line and draw it
        g2.setStroke(dashed);
        g2.setColor(Color.WHITE);
        g2.drawLine(START + 50, DASHED_HEIGHT, START + this.scaling(this.getRunwayLength()) - 50, DASHED_HEIGHT);
    }

    //draws left and right runway numbers
    private void drawRunwayNumbers(Graphics2D g2){
        g2.setColor(Color.WHITE);
        //increase size of font
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        //used to get string length so it can be centred
        FontMetrics metrics = g2.getFontMetrics();

        //RIGHT NUMBER
        //makes text vertical facing towards right edge of runway
        g2.rotate(-Math.PI/2);
        //gets the runway number and appends side letter
        String runwayString = runwayNumberR + "R";

        //second parameter centers text on runway
        //due to rotation axes flip, x' = -y, y' = x
        //gets runway end shifts by 10 left (scales
        //shifts left by START
        g2.drawString(runwayString,  - RUNWAY_Y() - RUNWAY_HEIGHT()/2 - metrics.stringWidth(runwayString)/2, this.scaling(START + this.getRunwayLength() - 10) - START);

        //LEFT NUMBER
        //makes text vertical facing towards left edge of runway
        g2.rotate(-Math.PI);
        //gets the runway number and appends side letter (subtract from 36 to get opposite direction
        runwayString = (36 - runwayNumberR) + "L";

        //second parameter centers text on runway
        //due to rotation axes flip,  x' = y, y' = -x
        //shifts right by START and 10
        //shifts right by 10
        g2.drawString(runwayString,  RUNWAY_Y() + RUNWAY_HEIGHT()/2 - metrics.stringWidth(runwayString)/2, -this.scaling(START + 10) - START);

        //reset rotation to normal
        g2.rotate(-Math.PI/2);
    }
}