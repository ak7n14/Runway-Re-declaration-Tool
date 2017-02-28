import javax.swing.*;
import java.awt.*;

public class PaintTester extends JPanel {

   private RunwayView rsw;
   private ObstacleBack obs;
   private ObstacleView obsView;

    public PaintTester() {
        int[] x = {0, 100, 100, 0};
        int[] y = {0, 0, 100, 100};

        rsw = new RunwayTopView(100, 100, 500, 300, 400, 300, 500, "10R",1000, 400);
        obs = new ObstacleBack();

        obs.setSideX(x);
        obs.setSideY(y);
        obs.setTopX(x);
        obs.setTopY(y);

        obsView = new ObstacleView(obs, rsw);
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
