package View;
import Model.Runway;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.SplittableRandom;

//Initiate with runway values
//call drawAll to display
//call updateView + drawAll to display update
//runwayNumberR should be the number of runway on right side
public class RunwayTopView extends RunwayView{

    //stores number and letter
    private String runwayNumber;

    @Override
    protected int RUNWAY_Y() { return 100; }
    private final int DASHED_HEIGHT = RUNWAY_Y() + this.scalingHeight(runwayHeight/2);

    public RunwayTopView(Runway runway, int jpanelWidth, int jpanelHeight) {
        super(runway, jpanelWidth, jpanelHeight, runway.getRunwayWidth());
        this.runwayNumber = runway.getDesignator();
    }

    //draws runway, seperators and labels
    public void drawAll(Graphics g){
        drawClearedAndGraded(g);
        this.drawRunway(g);
        if(getTakeOffOrLand() == "Taking off")
            this.drawClearWay(g);
        this.drawStopWay(g);
        this.drawAllSeparators(g);
        //draws separator labels
        this.drawLabels(g);

        //draw centre line in middle of runway
        Graphics2D g2 = (Graphics2D) g;
        this.drawCenterLine(g2);
        this.drawRunwayNumbers(g2);
        this.drawScaleX(g);
        this.drawScaleY(g);
    }

    //draws a polygon
    public void drawClearedAndGraded(Graphics g){
        //polygon thing
        int x4 = scaling(runwayLength -300) +START;
        int x5 = scaling(runwayLength -150) +START;
        int x6 = scaling(60 + runwayLength)+START;
        int x2 = scaling(210-60)+START;
        int x3 = scaling(360-60)+START;

        int[] x = {0+START-scaling(60),x2,x3,x4,x5,x6,x6,x5,x4,x3,x2,0+START-scaling(60)};

        int y1 = scalingHeight(75 + runwayHeight/2) + RUNWAY_Y();
        int y2 = scalingHeight(105 + runwayHeight/2) + RUNWAY_Y();
        int y3 = scalingHeight(-75 + runwayHeight/2) + RUNWAY_Y();
        int y4 = scalingHeight(-105 + runwayHeight/2) + RUNWAY_Y();
        int[] y = {y1,y1,y2,y2,y1,y1,y3,y3,y4,y4,y3,y3};

        int xBlock[] = {START -scaling(60),x6,x6,START -scaling(60)};
        int yBlock[] = {scalingHeight(150 + runwayHeight/2)+RUNWAY_Y(),scalingHeight(150 + runwayHeight/2) +RUNWAY_Y(),scalingHeight(-150 + runwayHeight/2)+RUNWAY_Y(),scalingHeight(-150 + runwayHeight/2)+RUNWAY_Y()};

        //big rectangle thing around polygon
        Polygon polygon2 = new Polygon(xBlock,yBlock,xBlock.length);
        g.setColor(Color.magenta);
        g.fillPolygon(polygon2);

        Polygon polygon = new Polygon(x,y,x.length);
        g.setColor(Color.cyan);
        g.fillPolygon(polygon);

    }

    //draws a white dashed line in middle of runway
    private void drawCenterLine(Graphics2D g2){

        //create a dashed format for line
        Stroke dashed = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{15, 5}, 0);

        //set format of line and draw it
        g2.setStroke(dashed);
        g2.setColor(Color.WHITE);
        g2.drawLine(START + 50, DASHED_HEIGHT, START + this.scaling(this.getRunwayLength() - 50), DASHED_HEIGHT);
    }

    //draws left and right runway numbers
    private void drawRunwayNumbers(Graphics2D g2){
        g2.setColor(Color.WHITE);
        //increase size of font
        g2.setFont(new Font("Arial", Font.PLAIN, 10));
        //used to get string length so it can be centred
        FontMetrics metrics = g2.getFontMetrics();

        //RIGHT NUMBER
        //makes text vertical facing towards right edge of runway
        g2.rotate(-Math.PI/2);

        //second parameter centers text on runway
        //due to rotation axes flip, x' = -y, y' = x
        //gets runway end shifts by 10 left (scales
        //shifts left by START
        g2.drawString(runwayNumber,  - RUNWAY_Y() - this.scalingHeight(runwayHeight/2) - metrics.stringWidth(runwayNumber)/2, this.scaling(this.getRunwayLength()) + START - 10);

        //LEFT NUMBER
        //makes text vertical facing towards left edge of runway
        g2.rotate(-Math.PI);
        int runwayNum = Integer.parseInt(runwayNumber.substring(0,2));

        int reverseIntNumber = 0;

        if(runwayNum > 18)
            reverseIntNumber = runwayNum - 18;
        else
            reverseIntNumber = runwayNum + 18;

        String reverseNumber;

        //adds a 0 to the front of numbers less than 10
        if(reverseIntNumber < 10) {
            //calculates number on otherside of runway
            reverseNumber = "0" + String.valueOf(reverseIntNumber);
        }
        else {
            reverseNumber = String.valueOf(reverseIntNumber);
        }

        //gets runway letter
        String runwayLetter;
        if(runwayNumber.substring(2).equals("L")){
            runwayLetter = "R";
        } else {
            runwayLetter = "L";
        }

        //gets the runway number and appends side letter (subtract from 36 to get opposite direction
        String runwayString = reverseNumber + runwayLetter;

        //second parameter centers text on runway
        //due to rotation axes flip,  x' = y, y' = -x
        //shifts right by START and 10
        //shifts right by 10
        g2.drawString(runwayString,  RUNWAY_Y() + this.scalingHeight(runwayHeight/2) - metrics.stringWidth(runwayString)/2, -START - 10);

        //reset rotation to normal
        g2.rotate(-Math.PI/2);
    }

    @Override
    public int MeterX() {
        return this.scaling(50);
    }

    @Override
    public int MeterY() {
        return this.scalingHeight(50);
    }

    @Override
    public void setRunway(Runway runway) {
        super.setRunway(runway);

        runwayHeight = runway.getRunwayWidth();
        runwayNumber = runway.getDesignator();
    }
}
