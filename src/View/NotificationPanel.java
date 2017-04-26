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
        this.setPreferredSize(new Dimension(390,120));


    }
    public void initialize(String st){
        if(st=="Valid") {

            JPanel panel = new JPanel();


           // panel.setLayout();
            //Initializing the panel
            panel.setPreferredSize(new Dimension(360,110));
            JScrollPane scrollFrame = new JScrollPane(panel);
            this.add(new JLabel ("Values Updated!"));
            panel.add(new JLabel("Key:"));
            panel.add(new JLabel(""));
            panel.add(new JLabel("OLT = Obstacle location from threshold"));
            panel.add(new JLabel(""));
            panel.add(new JLabel("THD = Threshold Displacement"));
            panel.add(new JLabel("EBA = Engine Blast Allowance"));
            panel.add(new JLabel("ALS = Approach Landing Surface"));
            panel.setAutoscrolls(true);
            scrollFrame.setPreferredSize(new Dimension( 360,70));
            this.add(scrollFrame);
            this.updateUI();
        }
        else{
            //If wrong input entered
            JPanel panel = new JPanel();
            panel.add(new JLabel("Invalid inputs!"));
            this.add(panel);
            this.updateUI();
        }
    }
}
