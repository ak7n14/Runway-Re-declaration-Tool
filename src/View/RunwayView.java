package View;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import Model.Calculations;
import Model.ColourPalette;
import Model.Runway;

public abstract class RunwayView {

    //Stores different components of runway and where they end
    private HashMap<String, Integer> runwayEnds;
    int runwayLength;
    private int runwaydraw = 0;
    Runway runway;

    //start of runway
    int START = 0;

    //start of displace LDA
    private int LDAStart;
    private int RESAStart;

    //for scaling
    int jpanelWidth;
    int jpanelHeight;

    private String direction;
    private String takeOffOrLand;

    //start of parts of runway
    private int start;

    private ObstacleView ov;
    private Calculations calc;

    private boolean updated;

    Rectangle runwayRect;

    //initial y position and height of runway
    protected abstract int RUNWAY_Y();
    protected int runwayHeight;
    private final int SEPARATOR_HEIGHT = 10;


    RunwayView(Runway runway, int jpanelWidth, int jpanelHeight, int runwayHeight) {
        this.runwayLength = runway.getRunwayLenght();
        this.jpanelWidth = jpanelWidth;
        this.jpanelHeight = jpanelHeight;
        this.runwayHeight = runwayHeight;

        updated = false;

        this.runway = runway;
        //stores all ends in hashmap
        runwayEnds = new HashMap<>();

        runwayEnds.put("TODA", runway.getTODA());
        runwayEnds.put("TORA", runway.getTORA());
        runwayEnds.put("ASDA", runway.getASDA());
        runwayEnds.put("LDA", runway.getLDA());

        this.LDAStart = runway.getThreasholdDisplacement();
        this.start = 0;
        START = 0;

    }

    //for updating parts of runway
    //must call drawAll after to display updates
    public void updateView(int start, int LDAStart, Calculations calc, String direction, String takeOfforLand){
        this.LDAStart = LDAStart;
        this.RESAStart = ov.getOriginalOffsetX() + ov.getOb().getLength();

        this.calc = calc;
        //so calc doesnt get used before updated
        updated = true;

        runwayEnds = new HashMap<>();
        if(takeOfforLand.equals("Taking off")) {
            runwayEnds.put("TODA", calc.getReTODA());
            runwayEnds.put("TORA", calc.getReTORA());
            runwayEnds.put("ASDA", calc.getReASDA());

            if(direction.equals("Away"))
                runwayEnds.put("Blast Allowance", calc.getEngineBlastAllowance());

            START =  scaling((runway.getStripLength() - calc.getReTORA())/2);
        }
        else {
            runwayEnds.put("LDA", calc.getReLda());
            START =  scaling((runway.getStripLength() - calc.getReLda())/2);
        }

        //only add resa if traveling away
        if(direction == "Away") {
            runwayEnds.put("RESA", calc.getRESA());
        }

        this.start = start;
        this.direction = direction;
        this.takeOffOrLand = takeOfforLand;
    }

    //draws runway, seperators and labels
    public void drawAll(Graphics2D g){
        this.drawRunway(g);
        if(takeOffOrLand == "Taking off") {
            this.drawClearWay(g);
            this.drawStopWay(g);
        }
        //draws separator labels
        this.drawLabels(g);
        this.drawScaleX(g);
    }

    //draw stop and clear way, stop in front of clear as clear is normally larger
    public void drawStopWay(Graphics2D g){
        g.setColor(ColourPalette.darkPuple);
        g.fillRect(START + scaling(runwayLength), RUNWAY_Y(), scaling(runwayEnds.get("ASDA")) - scaling(runwayLength), scalingHeight(runwayHeight));
    }

    public void drawClearWay(Graphics2D g){
        g.setColor(ColourPalette.green);
        g.fillRect(START + this.scaling(runwayLength), RUNWAY_Y(), scaling(runwayEnds.get("TODA")-runwayLength), scalingHeight(runwayHeight));
    }

    //draws runway
    public void drawRunway(Graphics2D g){
        g.setColor(ColourPalette.black);
        if(takeOffOrLand.equals("Taking off")){
            runwaydraw = runwayEnds.get("TORA");
        }
        else {
            runwaydraw = runwayEnds.get("LDA");
        }
        runwayRect = new Rectangle(START, RUNWAY_Y(), this.scaling(runwaydraw) + this.scaling(start), this.scalingHeight(runwayHeight));
        g.fill(runwayRect);

    }

    //draws a seperator to see ends of different strip components
    public void drawSeparator(Graphics2D g, int x, int start){
        //x is where the runway component ends
        //height is altered so separator is visible
        g.setColor(ColourPalette.darkPink);
        g.fillRect(this.scaling(x) + START + this.scaling(start), RUNWAY_Y(), 2, this.scalingHeight(runwayHeight) + SEPARATOR_HEIGHT);
    }

    //loops through hashmap and displays seperators
    public void drawAllSeparators(Graphics2D g){
        //draws end separators
        for(String key : runwayEnds.keySet()){
            switch (key)
            {
                case "LDA":
                    //displaced start
                    this.drawSeparator(g, runwayEnds.get(key), LDAStart);
                    break;
                case "RESA":
                    //if flying away from obstacle include resa
                    if(direction == "Away") {
                        this.drawSeparator(g, RESAStart, 0);
                        this.drawSeparator(g, runwayEnds.get(key), RESAStart);
                        System.out.println(runwayEnds.get(key));
                    }
                    break;
                case "Blast Allowance":
                    if (direction.equals("Away") && takeOffOrLand.equals("Taking off")) {
                        this.drawSeparator(g, runwayEnds.get(key), RESAStart);
                    }
                    break;
                default:
                    this.drawSeparator(g, runwayEnds.get(key), start);
            }
        }

        //draw START separators
        this.drawSeparator(g, start, 0);
        this.drawSeparator(g, LDAStart, 0);
    }

    //draws labels and handles overlapping
    public void drawLabels(Graphics2D g){
        g.setColor(ColourPalette.darkPink);
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
    private void removeOverlap(HashMap<String, Point> stringData, Graphics2D g){

        for(String currentKey : stringData.keySet()){

            //point info of current key
            Point currentPoint = stringData.get(currentKey);
            int currentY = currentPoint.y;
            int currentX = currentPoint.x;

            int currentStringWidth = g.getFontMetrics().stringWidth(currentKey);

            //loop through all strings
            for(int i = 0; i < stringData.size(); i++) {

                //gets respective key value
                String key = (String)stringData.keySet().toArray()[i];
                //doesn't move out of the way of itself
                if (!key.equals(currentKey)){

                    int stringWidth = g.getFontMetrics().stringWidth(key); //get width of string
                    Point otherPoint = stringData.get(key);

                    //if tail overlaps then move other object (makes it look nicer)
                    if(currentX + currentStringWidth >= otherPoint.x && currentX + currentStringWidth <= otherPoint.x + stringWidth && currentY == otherPoint.y){
                        stringData.put(key, new Point(otherPoint.x, otherPoint.y += 10));
                        i = -1;
                    }

                    //changes y position if overlap in x direction and only changes if they are both on the same y level
                    if (currentX >= otherPoint.x && currentX <= otherPoint.x + stringWidth && currentY == otherPoint.y) {
                        stringData.put(currentKey, new Point(currentX, currentY += 10)); //move item down
                        //reset and check all again
                        i = -1;
                    }
                }
            }
        }


    }

    //returns dimensions of strings in a set
    private HashMap<String, Point> calculateStringDimensions(Set<String> keys){
        HashMap<String, Point> stringData = new HashMap<>();

        int stringY = RUNWAY_Y() + this.scalingHeight(runwayHeight) + 20; //START of string (y)

        for(String key : keys){
            int stringX;
            switch (key) {
                case "LDA":
                    stringX = this.scaling(runwayEnds.get(key)) + START + this.scaling(LDAStart); //START of string (x)
                    break;
                case "RESA":
                    stringX = this.scaling(runwayEnds.get(key)) + START + this.scaling(RESAStart); //START of string (x)
                    break;
                case "Blast Allowance":
                    stringX = this.scaling(runwayEnds.get(key)) + START + this.scaling(RESAStart); //START of string (x)
                    break;
                default:
                    stringX = this.scaling(runwayEnds.get(key)) + START + this.scaling(start); //START of string (x)
            }
            //creates relations between string and its dimensions
            stringData.put(key, new Point(stringX, stringY));
        }

        stringData.put("Start", new Point(START + scaling(start), stringY));
        stringData.put("LDAStart", new Point(START + scaling(LDAStart), stringY));

        //only draws resa when traveling away
        if(direction == "Away") {
            stringData.put("RESAStart", new Point(START + scaling(ov.getOb().getLength() + ov.getOriginalOffsetX()), stringY));
        }
        return stringData;
    }

    public int getRunwayLength() {
        return runwayLength;
    }
    public int getRunwayHeight() { return runwayHeight; }

    //scales objects for JPanel in x direction proportional to runway length
    public int scaling(int x){
        return (int)((double)x/(double)runway.getStripLength() * ((double)(jpanelWidth)));
    }

    //scales objects for JPanel in y direction proportional to height of runway
    public int scalingHeight(int y){
        return (int)((double)y/(double)runway.getStripWidth() * ((double)jpanelHeight - 2 * RUNWAY_Y()));
    }

    //draw scales so user can get a feel of how big the runway and obstacles are
    public void drawScaleX(Graphics2D g){

        //distance of 50 meters
        g.setColor(ColourPalette.black);
        g.fillRect(23,20, MeterX(), 2);

        //make it pretty
        g.fillRect(23, 15, 2, 5);
        g.fillRect(23 + MeterX(), 15, 2, 5);

        //add label to show what scale is in
        g.setFont(new Font("Arial", Font.BOLD, 11));

        g.drawString("50 meters", (35 + MeterX()) / 2, 35);
        drawArrow(g);
    }


    //draws an arrow image
    public void drawArrow(Graphics2D g){
        BufferedImage img = null;

        try {
            img = ImageIO.read(this.getClass().getResource("arrow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int stringWidth = g.getFontMetrics().stringWidth("00 meters");
        g.drawImage(img, 20 + stringWidth, 45, null);
    }

    public void drawScaleY(Graphics2D g){
        //distance of 50 meters
        g.setColor(ColourPalette.black);
        g.fillRect(20, 23, 2, MeterY());

        //make it pretty
        g.fillRect(15, 23, 5, 2);
        g.fillRect(15, 23 + MeterY(), 5, 2);

        //meter labels change dynamically based on scale
        if(this instanceof RunwaySideView){
            if (ov.getOb().getHeight() < 5) {
                g.drawString("1 meters", 25, (50 + MeterY()) / 2);
            }
            else if(ov.getOb().getHeight() < 10) {
                g.drawString("5 meters", 25, (50 + MeterY()) / 2);
            }
            else if(ov.getOb().getHeight() < 50) {
                g.drawString("10 meters", 25, (50 + MeterY()) / 2);
            }
        }
    }

    public void setObstacle(ObstacleView ov){
        this.ov = ov;
    }

    //used for scale display
    public abstract int MeterX();
    public abstract int MeterY();

    public String getDirection() {
        return direction;
    }

    public String getTakeOffOrLand() {
        return takeOffOrLand;
    }

    public Runway getRunway() {
        return runway;
    }

    public boolean isUpdated() {
        return updated;
    }

    public ObstacleView getOv() {
        return ov;
    }

    public Calculations getCalc() {
        return calc;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
        runwayLength = runway.getRunwayLenght();
    }

    public int getSTART() {
        return START;
    }

    public HashMap<String, Integer> getRunwayEnds() {
        return runwayEnds;
    }

    public int getRunwaydraw() {
        return runwaydraw;
    }

    public Rectangle getRunwayRect() {
        return runwayRect;
    }

    public int getStart() {
        return start;
    }
}
