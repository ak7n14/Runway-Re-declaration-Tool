package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.SystemColor.ACTIVE_CAPTION;
import static java.awt.SystemColor.window;

public class NotificationWindow {
    String Action;
    public NotificationWindow(String st){
        init(st);
    }
    public void init(String st){
        JFrame window = new JFrame("Notification");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container cont = window.getContentPane();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        if(st=="Valid") {
            window.setSize(500,200);
            panel.add(new JLabel("Values Updated!"));
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(5,1));
            panel2.add(new JLabel("Key:"));
            panel2.add(new JLabel(""));
            panel2.add(new JLabel("OLT = Obsticle location from threshold"));
            panel2.add(new JLabel(""));
            panel2.add(new JLabel("THD = Threshold Displacement"));
            panel2.add(new JLabel("EBA = Engine Blast Allowence"));
            panel2.add(new JLabel("ALS = Approach Landing Surface"));
            panel.add(panel2);
        }
        else{
            window.setSize(200,100);
            panel.add(new JLabel("Invalid inputs!"));
        }
        JButton close = new JButton("Close");
        close.addActionListener(new CloseListsner(window));
        panel.add(close);
        cont.add(panel);
        window.setVisible(true);
    }

    class CloseListsner implements ActionListener{
        JFrame window;
        public CloseListsner(JFrame window){
            this.window=window;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            window.setVisible(false);
        }
    }
}
