import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RunwaySideView extends JPanel{

    //Stores different components of runway and their lengths
    private HashMap<String, Integer> runways;
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

        //stores all lengths in hashmap
        runways = new HashMap<String, Integer>();
        runways.put("TODA", TODALength);
        runways.put("TORA", TORALength);
        runways.put("ASDA", ASDALength);
        runways.put("LDA", LDALength);
    }

    //for testing only
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setSize(1000, 400);

        jframe.add(new RunwaySideView(100, 100,500, 300, 400, 300,500 ));
        jframe.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.drawRunway(g, runwayLength);

    }

    //draws runway
    private void drawRunway(Graphics g, int length){
        g.setColor(Color.black);
        g.fillRect(start, Y, length, HEIGHT);
    }







}
