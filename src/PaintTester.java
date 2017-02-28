import javax.swing.*;
import java.awt.*;

public class PaintTester extends JPanel {

   private RunwayView rsw;
   private ObstacleBack obs;
   private ObstacleView obsView;

    public PaintTester() {
        int[] x = {0, 100, 300, 100, 0};
        int[] y = {0, 0, 100, 200, 200};

        rsw = new RunwayTopView(100, 0, 900, 300, 400, 300, 1000, 500, "22R", 1000, 400);
        obs = new ObstacleBack("nuclear bomb mk2 v1.2.3 heavy armory edition", 200);

        obs.setSideX(x);
        obs.setSideY(y);
        obs.setTopX(x);
        obs.setTopY(y);

        obsView = new ObstacleView(obs, rsw, 100, 300);
        obsView.createShapes();
    }

    //for testing only
    //----------------------
    public static void main(String[] args) {
        PaintTester pt = new PaintTester();
        JFrame jframe = new JFrame();
        jframe.setSize(1000, 400);

        jframe.add(pt);
        jframe.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        rsw.drawAll(g);
        obsView.drawShape(g, "top");
    }
    //--------------------------


    public RunwayView getRsw() {
        return rsw;
    }
}
