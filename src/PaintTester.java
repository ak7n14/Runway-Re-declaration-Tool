import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintTester extends JPanel {

   private RunwayView rsw;
   private ObstacleBack obs;
   private ObstacleView obsView;

    private int LDAStart = 0;
    private int start = 0;
    private int TODALength = 0;
    private int TORALength = 0;
    private int ASDALength = 0;
    private int LDALength = 0;
    private int runwayLength = 0;
    private String runwayNumber = "";
    private int runwayHeight = 0;
    private int offsetX = 0;
    private int offsetY = 0;
    private int offsetZ = 0;
    private int obstacleHeight = 0;

    private int[] obX;
    private int[] obY;

    ArrayList<JLabel> jLabels;
    ArrayList<JTextField> jTextFields;

    public PaintTester(String type) {

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
            obs.setSideX(obX);
            obs.setSideY(obY);
            obs.setTopX(obX);
            obs.setTopX(obX);

            obsView = new ObstacleView(obs, rsw, type, offsetX, offsetY, offsetZ);
            obsView.createShapes();
        }
    }

    //for testing only
    //----------------------
    public static void main(String[] args) {
        PaintTester pt = new PaintTester("null");
        JFrame jframe = new JFrame();
        jframe.setSize(1000, 400);

        pt.GUI();

        jframe.add(pt);
        jframe.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        rsw.drawAll(g);
        obsView.drawShape(g);
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
        jLabels.add(new JLabel("Coord 1 x,y"));
        jLabels.add(new JLabel("Coord 2 x,y"));
        jLabels.add(new JLabel("Coord 3 x,y"));
        jLabels.add(new JLabel("Coord 4 x,y"));
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

            LDAStart = getIntField(0);
            start = getIntField(1);
            TODALength = getIntField(2);
            TORALength = getIntField(3);
            ASDALength = getIntField(4);
            LDALength = getIntField(5);
            runwayLength = getIntField(6);
            runwayHeight = getIntField(7);
            runwayNumber = getField(8);

            obX[0] = getCoord(9, "x");
            obX[1] = getCoord(10, "x");
            obX[2] = getCoord(11, "x");
            obX[3] = getCoord(12, "x");

            obY[0] = getCoord(9, "y");
            obY[1] = getCoord(10, "y");
            obY[2] = getCoord(11, "y");
            obY[3] = getCoord(12, "y");

            obstacleHeight = getIntField(13);

            jFrame2.setSize(2000, 400);
            jFrame2.add(new PaintTester("side"));
            jFrame2.add(new PaintTester("top"));
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
    public int getCoord(int loc, String xOrY){
        if(xOrY.equals("x")){
            return Integer.parseInt(getField(loc).substring(0,1));
        } else{
            return Integer.parseInt(getField(loc).substring(2));
        }
    }
}
