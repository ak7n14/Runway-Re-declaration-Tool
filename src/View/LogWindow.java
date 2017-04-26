package View;

import Controller.GraphicsPanel;
import Model.Calculations;
import Model.Log;
import Model.Runway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anish on 4/24/17.
 */
public class LogWindow extends JFrame{
    public LogWindow(Log log){
        super("Log:"+log.getName());
        initialize(log);
    }

    public void initialize(Log log) {
        Boolean activate;
        Container container = this.getContentPane();
        JPanel panel = new JPanel();
        container.add(panel);
        panel.setLayout(new BorderLayout());
        JPanel nedsPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel InputsPanel = new JPanel();
        OutputPanel outputPanel = new OutputPanel(LogWindow.this, log.getPlane(), 100);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(nedsPanel,BorderLayout.CENTER);
        rightPanel.setPreferredSize(new Dimension(400, 837));
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Calculations"));
        InputsPanel.setLayout(new GridLayout(11,2));
        InputsPanel.setBorder(BorderFactory.createTitledBorder("Inputs Entered"));
        InputsPanel.setPreferredSize(new Dimension(390, 450));
        InputsPanel.add(new JLabel(""));
        InputsPanel.add(new JLabel("All Distances are in meters"));
        InputsPanel.add(new JLabel("Runway:"));
        InputsPanel.add(new JLabel(log.getRunway().getDesignator()));
        InputsPanel.add(new JLabel("Obstacle:"));
        InputsPanel.add(new JLabel(log.getObsticle().getName()));
        InputsPanel.add(new JLabel("Distance From CentreLine:"));
        InputsPanel.add(new JLabel(String.valueOf(log.getDistCL())));
        InputsPanel.add(new JLabel("Distance From Threshold:"));
        InputsPanel.add(new JLabel(String.valueOf(log.getDistTH())));
        InputsPanel.add(new JLabel("Action: "));
        InputsPanel.add(new JLabel(log.getAction()));
        InputsPanel.add(new JLabel("Direction from CentreLine:"));
        InputsPanel.add(new JLabel(log.getDirectionCL()));
        InputsPanel.add(new JLabel("Direction of Action:"));
        InputsPanel.add(new JLabel(String.valueOf(log.getDirectionAc())));
        InputsPanel.add(new JLabel("RESA: "));
        InputsPanel.add(new JLabel(String.valueOf(log.getRESA())));
        InputsPanel.add(new JLabel("Engine Blast Allowence: "));
        InputsPanel.add(new JLabel(String.valueOf(log.getEngineBlastAllowence())));
        JButton close = new JButton("Close");

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogWindow.this.dispose();
            }
        });
        InputsPanel.add(close);
        JScrollPane scrollFrameInput = new JScrollPane(InputsPanel);
        InputsPanel.setAutoscrolls(true);
        scrollFrameInput.setPreferredSize(new Dimension(390, 270));
        rightPanel.add(scrollFrameInput);
        Calculations calc = new Calculations(log.getRunway(), log.getObsticle().getHeight(), log.getDistTH(), log.getRESA(), log.getEngineBlastAllowence());
        if (log.getDistCL() > log.getRunway().getRunwayWidth() / 2) {
            activate=true;
            if (log.getAction().equals("Landing")) {

                outputPanel.printObsOutOfRunway(calc, "Landing");//Calling landing case

            } else outputPanel.printObsOutOfRunway(calc, "Taking");//Calling taking off case
        } else {
            if (log.getAction().equals("Landing")) {//Cases of landing
                if (log.getDirectionAc().equals("Towards")) {
                    calc.calculateLda("Towards");//When landing towards
                    if(log.getPlane().getMinLandingDis()>calc.getReLda()) {
                        activate = false;
                    }else{
                        activate=true;
                    }
                    outputPanel.printCalcLandTowards(calc);

                } else {
                    calc.calculateLda("Away");    //When landing over
                    if(log.getPlane().getMinLandingDis()>calc.getReLda()) {
                        activate = false;
                    }else{
                        activate=true;
                    }
                    outputPanel.printCalcLandOver(calc);
                }
            } else {//Cases of taking off
                if (log.getDirectionAc().equals("Towards")) {
                    calc.calculateTORA("Towards");//When taking off towards the object
                    if(calc.getReTORA()<log.getPlane().getMinLandingDis() || calc.getReTODA()<log.getPlane().getMinLandingDis()
                            ||calc.getReASDA()<log.getPlane().getMinLandingDis()){
                        activate=false;
                    }
                    else{
                        activate=true;
                    }
                    outputPanel.printCalcTakeOffTowards(calc);

                } else {
                    calc.calculateTORA("Away");//When taking off after the object
                    if(calc.getReTORA()<log.getPlane().getMinLandingDis() || calc.getReTODA()<log.getPlane().getMinLandingDis()
                            ||calc.getReASDA()<log.getPlane().getMinLandingDis()){
                        activate=false;
                    }
                    else{
                        activate=true;
                    }
                    outputPanel.printTakeOffAfter(calc);
                }
            }

        }

        nedsPanel.setLayout(new BorderLayout());

        GraphicsPanel ptSide = new GraphicsPanel(new Runway("X", 1000, 1700, 1500, 300, 0, 1000, 100, 2000, 500), "side", 1000, 500);
        GraphicsPanel ptTop = new GraphicsPanel(new Runway("X", 1000, 1700, 1500, 300, 0, 1000, 100, 2000, 500), "top", 1000, 500);
        ptSide.setPreferredSize(new Dimension(2000,1000));
        ptTop.setPreferredSize(new Dimension(2000,1000));

        JTabbedPane jtp = new JTabbedPane();
        JScrollPane side = new JScrollPane(ptSide);
        JScrollPane top = new JScrollPane(ptTop);
        jtp.addTab("Side", side);
        jtp.addTab("Top", top);

        nedsPanel.add(jtp);

        if(activate) {
            int offsetX = log.getDistCL();
            if(log.getDirectionCL() == "left"){
                offsetX *= -1;
            }
            ptSide.setRunway(log.getRunway());
            ptTop.setRunway(log.getRunway());
            ptSide.updatePaint(calc, offsetX, log.getObsticle(), log.getDirectionAc(),log.getAction());
            ptTop.updatePaint(calc, offsetX, log.getObsticle(), log.getDirectionAc(),log.getAction());

        }

        JScrollPane scrollFrameOutput = new JScrollPane(outputPanel);
        outputPanel.setAutoscrolls(true);
        scrollFrameOutput.setPreferredSize(new Dimension(390, 270));
        rightPanel.add(scrollFrameOutput);
        this.setSize(1000,700);
        this.setVisible(true);
    }
}
