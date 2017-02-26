import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public abstract class RunwayView {

    //Stores different components of runway and where they end
    private HashMap<String, Integer> runwayEnds;
    private int runwayLength;

    //start of runway
    final int START = 100;

    //start of displace LDA
    private int LDAStart;

    //for scaling
    private int jpanelWidth;
    private int jpanelHeight;

    //start of parts of runway
    private int start;


    //initial y position and height of runway
    protected abstract int RUNWAY_Y();
    protected abstract int RUNWAY_HEIGHT();
    
    private final int SEPARATOR_HEIGHT = 10;

    RunwayView(int LDAStart, int start, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength, int jpanelWidth, int jpanelHeight) {
        this.LDAStart = LDAStart;
        this.runwayLength = runwayLength;
        this.start = start;
        this.jpanelWidth = jpanelWidth;
        this.jpanelHeight = jpanelHeight;

        //stores all ends in hashmap
        runwayEnds = new HashMap<>();
        runwayEnds.put("TODA", TODALength);
        runwayEnds.put("TORA", TORALength);
        runwayEnds.put("ASDA", ASDALength);
        runwayEnds.put("LDA", LDALength);
        this.runwayLength = runwayLength;
    }

    //for updating parts of runway
    //must call drawAll after to display updates
    public void updateView( int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength){
        runwayEnds.put("TODA", START + TODALength);
        runwayEnds.put("TORA", START + TORALength);
        runwayEnds.put("ASDA", START + ASDALength);
        runwayEnds.put("LDA", LDAStart + LDALength);
    }

    //draws runway, seperators and labels
    protected void drawAll(Graphics g){
        this.drawRunway(g);
        this.drawAllSeparators(g);
        //draws separator labels
        this.drawLabels(g);
    }

    //draws runway
    void drawRunway(Graphics g){
        g.setColor(Color.black);
        g.fillRect(START, RUNWAY_Y(), this.scaling(runwayLength), this.scalingHeight(RUNWAY_HEIGHT()));
    }

    //draws a seperator to see ends of different strip components
    private void drawSeparator(Graphics g, int x){
        //x is where the runway component ends
        //height is altered so separator is visible
        g.setColor(Color.RED);
        g.fillRect(this.scaling(x) + start, RUNWAY_Y(), 2, RUNWAY_HEIGHT() + SEPARATOR_HEIGHT);
    }

    //loops through hashmap and displays seperators
    void drawAllSeparators(Graphics g){

        //draws end separators
        for(String key : runwayEnds.keySet()){
            this.drawSeparator(g, runwayEnds.get(key));
        }

        //draw START separators
        this.drawSeparator(g, START);
        this.drawSeparator(g, LDAStart);
    }

    //draws labels and handles overlapping
    void drawLabels(Graphics g){

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

        int stringY = RUNWAY_Y() + RUNWAY_HEIGHT() + 20; //START of string (y)

        for(String key : keys){
            int stringX = this.scaling(runwayEnds.get(key)) + start; //START of string (x)

            //creates relations between string and its dimensions
            stringData.put(key, new Point(stringX, stringY));
        }

        stringData.put("Start", new Point(START, stringY));
        stringData.put("LDAStart", new Point(LDAStart, stringY));
        return stringData;
    }

    int getRunwayLength() {
        return runwayLength;
    }

    //scales objects for JPanel
    int scaling(int x){
        return (int)((double)x/(double)runwayLength * (double)jpanelWidth) - 2 * START;
    }

    //scales objects for JPanel
    int scalingHeight(int y){
        return (int)((double)y/(double)RUNWAY_HEIGHT() * (double)jpanelHeight) - 2 * START;
    }
}
