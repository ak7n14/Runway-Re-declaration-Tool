import javax.swing.*;
import java.awt.*;
import java.util.*;

//Initiate with runway values
//call drawAll to display
//call updateSideView + drawAll to display update
public class RunwaySideView {

    //Stores different components of runway and where they end
    private HashMap<String, Integer> runwayEnds;
    private int runwayLength;

    //start of TODA, TORA, ASDA and LDA(with no displaced threshold)
    private int start;

    //start of displace LDA
    private int LDAStart;

    //initial y position and height of runway
    private final int RUNWAY_Y = 300;
    private final int RUNWAY_HEIGHT = 10;

    //initial y position and height are constants for

    //defines size of runway
    public RunwaySideView(int start, int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength) {
        this.start = start;
        this.LDAStart = LDAStart;
        this.runwayLength = runwayLength;

        //stores all ends in hashmap
        runwayEnds = new HashMap<>();
        runwayEnds.put("TODA", start + TODALength);
        runwayEnds.put("TORA", start + TORALength);
        runwayEnds.put("ASDA", start + ASDALength);
        runwayEnds.put("LDA", LDAStart + LDALength);
    }

    //for updating parts of runway
    //must call drawAll after to display updates
    public void updateSideView( int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength){
        runwayEnds.put("TODA", start + TODALength);
        runwayEnds.put("TORA", start + TORALength);
        runwayEnds.put("ASDA", start + ASDALength);
        runwayEnds.put("LDA", LDAStart + LDALength);
    }

    //draws runway, seperators and labels
    public void drawAll(Graphics g){
        this.drawRunway(g);
        this.drawAllSeparators(g);
        //draws separator labels
        this.drawLabels(g);
    }

    //draws runway
    private void drawRunway(Graphics g){
        g.setColor(Color.black);
        g.fillRect(start, RUNWAY_Y, runwayLength, RUNWAY_HEIGHT);
    }

    //draws a seperator to see ends of different strip components with displacedStart (used by LDA)
    private void drawSeparator(Graphics g, int x){
        //x is where the runway component ends
        //height is altered so separator is visible
        g.setColor(Color.RED);
        g.fillRect(x, RUNWAY_Y, 2, RUNWAY_HEIGHT + 10);
    }

    //loops through hashmap and displays seperators
    private void drawAllSeparators(Graphics g){

        //draws end separators
        for(String key : runwayEnds.keySet()){
            this.drawSeparator(g, runwayEnds.get(key));
        }

        //draw start separators
        this.drawSeparator(g, start);
        this.drawSeparator(g, LDAStart);
    }

    //draws labels and handles overlapping
    private void drawLabels(Graphics g){

        //string data of runwayEnd labels
        HashMap<String, Point> stringData = this.calculateStringDimensions(runwayEnds.keySet());

        //alters overlapping labels
       this.removeOverlap(stringData, g);

        //draws labels
        for(String key : stringData.keySet()) {
            Point currentPoint = stringData.get(key);
            g.drawString(key, currentPoint.x, currentPoint.y);
        }
    }

    //stops labels overlapping
    private void removeOverlap(HashMap<String, Point> stringData, Graphics g){

        for(String currentKey : stringData.keySet()){

            //point info of current key
            Point currentPoint = stringData.get(currentKey);
            int currentY = currentPoint.y;
            int currentX = currentPoint.x;

            //loop through all strings
            for(String key : stringData.keySet()) {

                //doesn't move out of the way of itself
                if (!key.equals(currentKey)){

                    int stringWidth = g.getFontMetrics().stringWidth(key); //get width of string
                    Point otherPoint = stringData.get(key);

                    //changes y position if overlap in x direction and only changes if they are both on the same y level
                    if (currentX >= otherPoint.x && currentX <= otherPoint.x + stringWidth && currentY == otherPoint.y) {
                        stringData.put(currentKey, new Point(currentX, currentY + 10)); //move item down
                    }
                }
            }
        }


    }

    //returns dimensions of strings in a set
    private HashMap<String, Point> calculateStringDimensions(Set<String> keys){
        HashMap<String, Point> stringData = new HashMap<>();

        int stringY = RUNWAY_Y + RUNWAY_HEIGHT + 20; //start of string (y)

        for(String key : keys){
            int stringX = runwayEnds.get(key); //start of string (x)

            //creates relations between string and its dimensions
            stringData.put(key, new Point(stringX, stringY));
        }

        stringData.put("Start", new Point(start, stringY));
        stringData.put("LDAStart", new Point(LDAStart, stringY));
        return stringData;
    }

}
