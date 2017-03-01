import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintTester extends JPanel {

    private RunwayView rsw;
    private ObstacleBack obs;
    private ObstacleView obsView;

    ArrayList<JLabel> jLabels;
    ArrayList<JTextField> jTextFields;

    public PaintTester(String type, int LDAStart, int start, int TODALength, int TORALength, int ASDALength, int LDALength, int runwayLength, int runwayHeight, String runwayNumber, int offsetX, int offsetY, int offsetZ, int[] obX, int[] obY, int[] obSideX, int[] obSideY, int obstacleHeight) {

        jLabels = new ArrayList<>();
        jTextFields = new ArrayList<>();

//        int[] x = {0, 100, 300, 100, 0};
//        int[] y = {0, 0, 100, 200, 200};
//
//        rsw = new RunwayTopView(100, 0, 900, 300, 400, 300, 1000,500, "10R", 1000, 400);
//        obs = new ObstacleBack("nuclear bomb mk2 v1.2.3 heavy armory edition", 200);
//
//        obs.setSideX(x);
//        obs.setSideY(y);
//        obs.setTopX(x);
//        obs.setTopY(y);
//
//        obsView = new ObstacleView(obs, rsw, "top", 100, 0, 300);
//        obsView.createShapes();

        //creates runway
        if(type.equals("top")) {
            rsw = new RunwayTopView(LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, runwayHeight, runwayNumber, 1000, 400);
        }
        else if(type.equals("side")){
            rsw = new RunwaySideView(LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, 1000, 400);
        }


        //creates obstacle
        if(type.equals("side")||type.equals("top")){
            obs = new ObstacleBack("nuclear bomb", obstacleHeight);

            obs.setSideX(obSideX);
            obs.setSideY(obSideY);
            obs.setTopX(obX);
            obs.setTopY(obY);

            obsView = new ObstacleView(obs, rsw, type, offsetX, offsetY, offsetZ);
            obsView.createShapes();
        }
    }

    //for testing only
    //----------------------
    public static void main(String[] args) {
        PaintTester pt = new PaintTester("null", 0, 0,0, 0,0,0,0,0,"",0,0,0,null,null,null, null, 0);

        pt.GUI();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(rsw != null) {
            rsw.drawAll(g);
            obsView.drawShape(g);
        }
    }
    //--------------------------

    public void GUI(){
        JFrame jFrame = new JFrame();
        jLabels.add(new JLabel("LDAStart"));
        jLabels.add(new JLabel("Start"));
        jLabels.add(new JLabel("TODA Length"));
        jLabels.add(new JLabel("TORA Length"));
        jLabels.add(new JLabel("ASDA Length"));
        jLabels.add(new JLabel("LDA Length"));
        jLabels.add(new JLabel("Runway Length"));
        jLabels.add(new JLabel("Runway Height"));
        jLabels.add(new JLabel("Runway Number"));
        jLabels.add(new JLabel("Offset X"));
        jLabels.add(new JLabel("Offset Y"));
        jLabels.add(new JLabel("Offset Z"));
        jLabels.add(new JLabel("Coord top x,z;x2,z2;...;xn,zn"));
        jLabels.add(new JLabel("Coord side x,y;x2,y2;...;xn,yn"));
        jLabels.add(new JLabel("Obstacle Height"));

        JButton jButton = new JButton("OK");

        //create input boxes
        for (int i = 0; i < jLabels.size(); i++){
            jFrame.add(jLabels.get(i));

            jTextFields.add(new JTextField(5));

            jFrame.add(jTextFields.get(i));
            jFrame.add(jButton);
        }

        //set data on submit
        jButton.addActionListener(e -> {
            JFrame jFrame2 = new JFrame();
            jFrame2.setLayout(new GridLayout(1,2));

            int LDAStart = getIntField(0);
            int start = getIntField(1);
            int TODALength = getIntField(2);
            int TORALength = getIntField(3);
            int ASDALength = getIntField(4);
            int LDALength = getIntField(5);
            int runwayLength = getIntField(6);
            int runwayHeight = getIntField(7);
            String runwayNumber = getField(8);

            int offsetX = getIntField(9);
            int offsetY = getIntField(10);
            int offsetZ = getIntField(11);

            int[] obX = new int[4];
            int[] obY = new int[4];
            int[] obSideX = new int[4];
            int[] obSideY = new int[4];


            obX = getCoord(12, "x");

            obY = getCoord(12, "y");


            obSideX = getCoord(13, "x");

            obSideY = getCoord(13, "y");

            int obstacleHeight = getIntField(14);

            jFrame2.setSize(2000, 400);
            jFrame2.add(new PaintTester("side", LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, runwayHeight, runwayNumber, offsetX, offsetY, offsetZ, obX, obY, obSideX, obSideY, obstacleHeight));
            jFrame2.add(new PaintTester("top", LDAStart, start, TODALength, TORALength, ASDALength, LDALength, runwayLength, runwayHeight, runwayNumber, offsetX, offsetY, offsetZ, obX, obY, obSideX, obSideY, obstacleHeight));
            jFrame2.setVisible(true);
        });

        jFrame.setSize(1000, 400);
        jFrame.setLayout(new FlowLayout());
        jFrame.setVisible(true);
    }

    //get text from textbox
    public String getField(int loc){
        return jTextFields.get(loc).getText();
    }

    //get int value of field
    public int getIntField(int loc){
        return Integer.parseInt(getField(loc));
    }

    //get coords
    public int[] getCoord(int loc, String xOrY){
        //loops through all coords
        String[] split = getField(loc).split(";");
        int[] coords = new int[split.length];
        for(int i = 0; i < split.length; i++) {
            //gets x and y
            String[] xy = split[i].split(",");

            if (xOrY.equals("x")) {
                coords[i] = Integer.parseInt(xy[0]);
            } else {
                coords[i] = Integer.parseInt(xy[1]);
            }
        }

        return coords;
    }
}
