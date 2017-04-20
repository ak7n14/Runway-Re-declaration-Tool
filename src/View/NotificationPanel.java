package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Anish on 4/20/17.
 */
public class NotificationPanel extends JPanel {

    public NotificationPanel(){
        this.setLocation(0,350);
        this.setBorder(BorderFactory.createTitledBorder("Notification Panel"));
        this.setPreferredSize(new Dimension(390,110));


    }
    public void initialize(String st){
        if(st=="Valid") {

            JPanel panel = new JPanel();
            JScrollPane scroll = new JScrollPane(panel);
            this.add(scroll);
           // panel.setLayout();
            panel.setPreferredSize(new Dimension(360,105));
            this.add(new JLabel ("Values Updated"));
            panel.add(new JLabel("Key:"));
            panel.add(new JLabel(""));
            panel.add(new JLabel("OLT = Obsticle location from threshold"));
            panel.add(new JLabel(""));
            panel.add(new JLabel("THD = Threshold Displacement"));
            panel.add(new JLabel("EBA = Engine Blast Allowence"));
            panel.add(new JLabel("ALS = Approach Landing Surface"));
        }
        else{
            this.add(new JLabel("Invalid inputs!"));
        }
    }
}
