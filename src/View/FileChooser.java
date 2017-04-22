package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser implements ActionListener {
    static private final String newline = "\n";
    JFileChooser fc;
    String fileName;
    JFrame frame;
    String st;
    JTextField field;
    public FileChooser(JFrame frame,String st,JTextField field) {
        this.frame=frame;
        this.st=st;
        this.field=field;
    }

    public void actionPerformed(ActionEvent e) {
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileFilter filter = new FileNameExtensionFilter("XML file", new String[] {"xml"});
        fc.removeChoosableFileFilter(fc.getFileFilter());
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);
        if (st =="Open") {
            int returnVal = fc.showOpenDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                field.setText(file.getPath());
                field.updateUI();
            }
            //Handle save button action.
        } else if (st == "Save") {
            int returnVal = fc.showSaveDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                field.setText(file.getPath());
                field.updateUI();
            }
        }
    }


}
