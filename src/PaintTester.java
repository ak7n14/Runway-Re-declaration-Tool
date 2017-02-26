import javax.swing.*;
import java.awt.*;

public class PaintTester extends JPanel {

    RunwaySideView rsw;
    Obstacle obs;

    public PaintTester() {
        int[] x = {100, 100, 200, 200};
        int[] y = {100, 200, 100, 200};

        rsw = new RunwaySideView(100, 100, 500, 300, 400, 300, 500);
        obs = new Obstacle();


        obs.setSideX(x);
        obs.setSideY(y);
        obs.setTopX(x);
        obs.setTopY(y);

        obs.createSidePolygon();
        obs.createTopPolygon();
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
        obs.drawShape(g, obs.getShapeSide());
    }
    //--------------------------


    public RunwaySideView getRsw() {
        return rsw;
    }
}
