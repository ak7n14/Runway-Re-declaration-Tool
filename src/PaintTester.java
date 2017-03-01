import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintTester extends JPanel {

   private RunwayView rsw;
   private ObstacleBack obs;
   private ObstacleView obsView;

    private int LDAStart;
    private int start;
    private int TODALength;
    private int TORALength;
    private int ASDALength;
    private int LDALength;
    private int runwayLength;
    private int runwayNumber;
    private int runwayHeight;
    private int offsetX;
    private int offsetY;
    private int offsetZ;

    ArrayList<JLabel> jLabels;
    ArrayList<JTextField> jTextAreas;

    public PaintTester() {

        jLabels = new ArrayList<>();
        jTextAreas = new ArrayList<>();

        int[] x = {0, 10, 30, 10, 0};
        int[] y = {0, 0, 10, 20, 20};

        rsw = new RunwaySideView(100, 0, 900, 300, 400, 300, 1000,1000, 400);
        obs = new ObstacleBack("nuclear bomb mk2 v1.2.3 heavy armory edition", 20);

        obs.setSideX(x);
        obs.setSideY(y);
        obs.setTopX(x);
        obs.setTopY(y);

        obsView = new ObstacleView(obs, rsw, "side", 100, 0, 300);
        obsView.createShapes();
    }

    //for testing only
    //----------------------
    public static void main(String[] args) {
        PaintTester pt = new PaintTester();
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


    public RunwayView getRsw() {
        return rsw;
    }

    public void GUI(){
        JFrame jFrame = new JFrame();
        jLabels.add(new JLabel("LDAStart"));
        jLabels.add(new JLabel("Start"));
        jLabels.add(new JLabel("TODA Length"));
        jLabels.add(new JLabel("TORA Length"));
        jLabels.add(new JLabel("ASDA Length"));
        jLabels.add(new JLabel("LDA Length"));
        jLabels.add(new JLabel("Runway Length"));
        jLabels.add(new JLabel("Runway Number"));
        jLabels.add(new JLabel("Runway Height"));
        jLabels.add(new JLabel("Offset X"));
        jLabels.add(new JLabel("Offset Y"));
        jLabels.add(new JLabel("Offset Z"));

        JButton jButton = new JButton("OK");

        for (int i = 0; i < jLabels.size(); i++){
            jFrame.add(jLabels.get(i));

            jFrame.add(new TextField(5));
            jFrame.add(jButton);
        }

        jFrame.setSize(1000, 400);
        jFrame.setLayout(new FlowLayout());
        jFrame.setVisible(true);
    }
}
