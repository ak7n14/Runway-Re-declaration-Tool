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

    public RunwayTopView(int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength, int runwayNumberR) {
        super(LDAStart, TODALength, TORALength, ASDALength, LDALength, runwayLength);
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

        Stroke dashed = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{15, 5}, 0);

        g2.setStroke(dashed);
        g2.setColor(Color.WHITE);
        g2.drawLine(START + 50, DASHED_HEIGHT, START + this.getRunwayLength() - 50, DASHED_HEIGHT);
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
        g2.drawString(runwayString,  - RUNWAY_Y() - RUNWAY_HEIGHT()/2 - metrics.stringWidth(runwayString)/2, START + this.getRunwayLength() - 10);

        //LEFT NUMBER
        //makes text vertical facing towards left edge of runway
        g2.rotate(-Math.PI);
        //gets the runway number and appends side letter (subtract from 36 to get opposite direction
        runwayString = (36 - runwayNumberR) + "L";

        //second parameter centers text on runway
        //due to rotation axes flip,  x' = y, y' = -x
        g2.drawString(runwayString,  RUNWAY_Y() + RUNWAY_HEIGHT()/2 - metrics.stringWidth(runwayString)/2, -START - 10);

        //reset rotation to normal
        g2.rotate(-Math.PI/2);
    }
}
