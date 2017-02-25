import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RunwaySideView extends JPanel{

    //Stores different components of runway and where they end
    private HashMap<String, Integer> runwayEnds;
    private int runwayLength;

    //start of TODA, TORA, ASDA and LDA(with no displaced threshold)
    private int start;

    //start of displace LDA
    private int LDAStart;

    //initial y position and height are constant in sideview
    private final int Y = 300;
    private final int HEIGHT = 10;

    //defines size of runway
    public RunwaySideView(int start, int LDAStart, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength) {
        this.start = start;
        this.LDAStart = LDAStart;
        this.runwayLength = runwayLength;

        //stores all ends in hashmap
        runwayEnds = new HashMap<String, Integer>();
        runwayEnds.put("TODA", start + TODALength);
        runwayEnds.put("TORA", start + TORALength);
        runwayEnds.put("ASDA", start + ASDALength);
        runwayEnds.put("LDA", LDAStart + LDALength);
    }

    //for testing only
    //----------------------
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setSize(1000, 400);

        jframe.add(new RunwaySideView(100, 100,500, 300, 400, 300,500 ));
        jframe.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.drawRunway(g);
        this.drawSeparatorEnd(g, runwayEnds.get("TODA"));
        this.drawSeparatorEnd(g, runwayEnds.get("TORA"));
        this.drawSeparatorEnd(g, runwayEnds.get("ASDA"));
        this.drawSeparatorEnd(g, runwayEnds.get("LDA"));
    }
    //--------------------------

    //draws runway
    private void drawRunway(Graphics g){
        g.setColor(Color.black);
        g.fillRect(start, Y, runwayLength, HEIGHT);
    }

    //draws a seperator to see ends of different strip components with displacedStart (used by LDA)
    private void drawSeparatorEnd(Graphics g, int x){
        //x is where the runway component ends
        //height is altered so separator is visible
        g.setColor(Color.RED);
        g.fillRect(x, Y, 2, HEIGHT + 10);
    }

    private void drawLabels(Graphics g){

    }

}
