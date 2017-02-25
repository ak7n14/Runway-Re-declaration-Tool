import javax.swing.*;
import java.awt.*;

public class RunwaySideView extends JPanel{
    public RunwaySideView() {

    }

    //for testing only
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setSize(500, 500);

        jframe.add(new RunwaySideView());
        jframe.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
