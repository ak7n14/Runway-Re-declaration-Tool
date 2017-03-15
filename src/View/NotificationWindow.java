package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.SystemColor.window;

public class NotificationWindow {

    public NotificationWindow(){
        init();
    }
    public void init(){
        JFrame window = new JFrame("Notification");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container cont = window.getContentPane();
        window.setSize(200,100);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Values Updated!"));
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
