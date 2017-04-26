package View;

import Model.Calculations;
import Model.Plane;
import Model.ColourPalette;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Anish on 4/20/17.
 */
public class OutputPanel extends JPanel {
    Font font;
    Font Bold;
    JFrame frame;
    Plane plane;
    int tol;
    public OutputPanel(JFrame frame,Plane plane,int tol){
        this.setPreferredSize(new Dimension(380,270));
        this.setBorder(BorderFactory.createTitledBorder("Output Panel"));
        this.setLocation(0,0);
        this.setLocation(0,500);
        this.frame=frame;
        JLabel label = new JLabel("HI");
        font = label.getFont();
        this.plane=plane;
        this.tol=tol;
        Bold = new Font(font.getName(),Font.BOLD, font.getSize());
    }

    public void printObsOutOfRunway(Calculations calc, String Direction){
        this.removeAll();
        this.updateUI();
        this.setBackground(ColourPalette.green);
        this.setToolTipText("Safe!");
        this.setOpaque(true);
        JLabel label = new JLabel("Object out of graded Area");

        label.setBackground(ColourPalette.green);
        label.setOpaque(true);
        this.add(label);
        JLabel l = new JLabel();
        l.setBackground(ColourPalette.green);
        l.setOpaque(true);
        this.add(l);

        //Checking if plane is landin
        if(Direction =="Landing"){
            this.setLayout(new GridLayout(2,2));
            JLabel label1= new JLabel("LDA");
            label1.setOpaque(true);
            label1.setBackground(ColourPalette.green);
            label1.setFont(Bold);
            JLabel label2= new JLabel(String.format("= %d meters",calc.getRunway().getLDA()));
            label2.setBackground(ColourPalette.green);
            label2.setOpaque(true);
            label2.setFont(Bold);
            this.add(label1);
            this.add(label2);
        }

        //If plane is taking off
        else {
            this.setLayout(new GridLayout(3, 1));
            JLabel label2 = new JLabel(String.format("TORA = %d meters", calc.getRunway().getTORA()));
            label2.setFont(Bold);
            label2.setBackground(ColourPalette.green);
            label2.setOpaque(true);
            this.add(label2);
            JLabel label3 = new JLabel(String.format("TODA = %d meters", calc.getRunway().getTODA()));
            label3.setBackground(ColourPalette.green);
            label3.setOpaque(true);
            label3.setFont(Bold);
            this.add(label3);
            JLabel label4 = new JLabel(String.format("ASDA = %d meters", calc.getRunway().getASDA()));
            label4.setBackground(ColourPalette.green);
            label4.setOpaque(true);
            label4.setFont(Bold);
            JLabel l2 = new JLabel("");
            l2.setBackground(ColourPalette.green);
            l2.setOpaque(true);
            this.add(label4);
            this.add(l2);
        }
        this.setSize(300,300);
        this.updateUI();
        frame.validate();
    }
    //prints labels with details to screen when plane is landing over the object
    public void printCalcLandOver(Calculations calc){
        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(7,2));
        this.add(new JLabel("TORA = "));
        this.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
        this.add(new JLabel("OLTH = "));
        this.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
        this.add(new JLabel("ALS = "));
        this.add(new JLabel(String.format("%d meters", calc.getALS())));
        JLabel la1=new JLabel("Re-LDA = ");
        la1.setFont(Bold);
        this.add(la1);
        this.add(new JLabel("TORA - OLTH - ALS -60"));
        this.add(new JLabel(""));
        String equation = String.format("=%d - %d - %d - %d",calc.getRunway().getTORA(),calc.getObsLoc(),calc.getALS(),60);
        this.add(new JLabel (equation));
        this.add(new JLabel(""));
        JLabel la2=new JLabel(String.format("= %d meters",calc.getReLda()));
        la2.setFont(Bold);
        this.add(la2);

        //Updating color of the panel depending on if the runway distance is sufficient or not
        if(calc.getReLda()>plane.getMinLandingDis()+tol){
            this.setBackground(ColourPalette.green);
            this.setToolTipText("Safe!");
        }
        else if(calc.getReLda()<plane.getMinLandingDis()){
            this.setBackground(ColourPalette.red.brighter().brighter());
            this.setToolTipText("NOT SAFE!");
            JLabel la3 =new JLabel("Min Dist req");
            la3.setFont(Bold);
            this.add(la3);
            this.add(new JLabel(String.format("= %d meters", plane.getMinLandingDis())));
        }
        else{
            this.setBackground(ColourPalette.yellow);
            this.setToolTipText("Safe but close!");
        }
        this.setOpaque(true);
        frame.validate();
    }
    //prints details to screen when plane is landing to the object
    public void printCalcLandTowards(Calculations calc) {

        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(6, 2));
        this.add(new JLabel("OLTH = "));
        this.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
        this.add(new JLabel("RESA = "));
        this.add(new JLabel(String.format("%d meters", calc.getRESA())));
        JLabel la4 =new JLabel("Re-LDA = ");
        la4.setFont(Bold);
        this.add(la4);
        this.add(new JLabel("OLTH - RESA - 60"));
        this.add(new JLabel(""));
        String equation = String.format("=%d - %d - %d", calc.getObsLoc(), calc.getRESA(), 60);
        this.add(new JLabel(equation));
        this.add(new JLabel(""));
        JLabel la5 = new JLabel(String.format("= %d meters", calc.getReLda()));
        la5.setFont(Bold);
        this.add(la5);

        //Changing color of the panel according to the condition(If runway is sufficient)
        if (calc.getReLda() > plane.getMinLandingDis() + tol) {
            this.setBackground(ColourPalette.green);
            this.setToolTipText("Safe!");
        } else if (calc.getReLda() < plane.getMinLandingDis()) {
            this.setBackground(ColourPalette.red.brighter().brighter());
            this.setToolTipText("NOT SAFE!");
            JLabel la6= new JLabel("Min Dist req ");
            la6.setFont(Bold);
            this.add(la6);
            this.add(new JLabel(String.format("= %d meters", plane.getMinLandingDis())));
        } else {
            this.setBackground(ColourPalette.yellow);
            this.setToolTipText("Safe but close!");
        }
        this.setOpaque(true);
        frame.validate();
    }
    //prints details to screen when plane is taking off towards the object
    public void printCalcTakeOffTowards(Calculations calc) {
        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(10, 2));
        this.add(new JLabel("TORA = "));
        this.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
        this.add(new JLabel("THD = "));
        this.add(new JLabel(String.format("%d meters", calc.getRunway().getThreasholdDisplacement())));
        this.add(new JLabel("ALS = "));
        this.add(new JLabel(String.format("%d meters", calc.getALS())));
        JLabel la7 = new JLabel("Re-TORA = ");
        la7.setFont(Bold);
        this.add(la7);
        this.add(new JLabel("OLTH + THD - ALS - 60"));
        this.add(new JLabel(""));
        String equation = String.format("=%d + %d - %d -%d", calc.getObsLoc(), calc.getRunway().getThreasholdDisplacement(), calc.getALS(), 60);
        this.add(new JLabel(equation));
        this.add(new JLabel(""));
        JLabel la8 = new JLabel(String.format("= %d meters", calc.getReTORA()));
        la8.setFont(Bold);
        this.add(la8);
        JLabel la9=new JLabel("Re-TODA = Re-TORA");
        la9.setFont(Bold);
        this.add(la9);
        JLabel la10 = new JLabel(String.format("= %d meters", calc.getReTORA()));
        la10.setFont(Bold);
        this.add(la10);
        JLabel la11=new JLabel("Re-ASDA = Re-ORA");
        la11.setFont(Bold);
        this.add(la11);
        JLabel la12 =new JLabel(String.format("= %d meters", calc.getReTORA()));
        la12.setFont(Bold);
        this.add(la12);

        //Changing color of the panel according to the condition(If runway is sufficient)
        if (calc.getReTORA() > plane.getMinTakeoffDis() + tol
                && calc.getReTODA() > plane.getMinTakeoffDis() + tol
                && calc.getReASDA() > plane.getMinTakeoffDis() + tol) {
            this.setBackground(ColourPalette.green);
            this.setToolTipText("Safe!");
        } else if (calc.getReTORA() < plane.getMinTakeoffDis()
                && calc.getReTODA() < plane.getMinTakeoffDis()
                && calc.getReASDA() < plane.getMinTakeoffDis()) {
            this.setBackground(ColourPalette.red.brighter().brighter());
            this.setToolTipText("NOT SAFE!");
            JLabel la13=new JLabel("Min Dist req");
            la13.setFont(Bold);
            this.add(la13);
            JLabel la14 = new JLabel(String.format("= %d meters", plane.getMinTakeoffDis()));
            la14.setFont(Bold);
            this.add(la14);
        } else {
            this.setBackground(ColourPalette.yellow);
            this.setToolTipText("Safe but close!");
        }
        this.setOpaque(true);
        frame.validate();
    }
    //Prints details to screen
    public void printTakeOffAfter(Calculations calc) {

        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(15, 2));
        this.add(new JLabel("TORA = "));
        this.add(new JLabel(String.format("%d meters", calc.getRunway().getTORA())));
        this.add(new JLabel("TODA = "));
        this.add(new JLabel(String.format("%d meters", calc.getRunway().getTODA())));
        this.add(new JLabel("ASDA = "));
        this.add(new JLabel(String.format("%d meters", calc.getRunway().getASDA())));
        this.add(new JLabel("OLTH"));
        this.add(new JLabel(String.format("%d meters", calc.getObsLoc())));
        this.add(new JLabel("EBA"));
        this.add(new JLabel(String.format("%d meters", calc.getEngineBlastAllowance())));
        JLabel la15 = new JLabel("Re-TORA = ");
        la15.setFont(Bold);
        this.add(la15);
        this.add(new JLabel("TORA - OLTH - EBA"));
        this.add(new JLabel(""));
        String equation = String.format("=%d - %d - %d", calc.getRunway().getTORA(), calc.getObsLoc(), calc.getEngineBlastAllowance());
        this.add(new JLabel(equation));
        this.add(new JLabel(""));
        JLabel la16 = new JLabel(String.format("= %d meters", calc.getReTORA()));
        la16.setFont(Bold);
        this.add(la16);
        JLabel la17= new JLabel("Re-TODA = ");
        la17.setFont(Bold);
        this.add(la17);
        this.add(new JLabel("TODA - OBTH - EBA"));
        this.add(new JLabel(""));
        String equation1 = String.format("=%d - %d - %d", calc.getRunway().getTODA(), calc.getObsLoc(), calc.getEngineBlastAllowance());
        this.add(new JLabel(equation1));
        this.add(new JLabel(""));
        JLabel la18 = new JLabel(String.format("= %d meters", calc.getReTODA()));
        la18.setFont(Bold);
        this.add(la18);
        JLabel la19 = new JLabel("Re-ASDA = ");
        la19.setFont(Bold);
        this.add(la19);
        this.add(new JLabel("ASDA - OLTH - EBA"));
        this.add(new JLabel(""));
        String equation2 = String.format("=%d - %d - %d", calc.getRunway().getASDA(), calc.getObsLoc(), calc.getEngineBlastAllowance());
        this.add(new JLabel(equation2));
        this.add(new JLabel(""));
        JLabel la20 =new JLabel(String.format("= %d meters", calc.getReASDA()));
        la20.setFont(Bold);
        this.add(la20);


        //Changing color of the panel according to the condition(If runway is sufficient)
        if (calc.getReTORA() > plane.getMinTakeoffDis() + tol
                && calc.getReTODA() > plane.getMinTakeoffDis() + tol
                && calc.getReASDA() > plane.getMinTakeoffDis() + tol) {
            this.setBackground(ColourPalette.green);
            this.setToolTipText("Safe!");
        } else if (calc.getReTORA() < plane.getMinTakeoffDis()
                && calc.getReTODA() < plane.getMinTakeoffDis()
                && calc.getReASDA() < plane.getMinTakeoffDis()) {
            this.setBackground(ColourPalette.red.brighter().brighter());
            this.setToolTipText("NOT SAFE!");
            JLabel la21 = new JLabel("Min Dist req");
            this.add(la21);
            JLabel la22 = new JLabel(String.format("= %d meters", plane.getMinTakeoffDis()));
            this.add(la22);
        } else {
            this.setBackground(ColourPalette.yellow);
            this.setToolTipText("Safe but close!");
        }
        this.setOpaque(true);
        frame.validate();
    }

}
