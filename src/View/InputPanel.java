package View;

import Controller.GraphicsPanel;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Anish on 4/20/17.
 */
public class InputPanel extends JPanel {
    XMLImporter importer;
    private JTextField obsDisCL;
    private JTextField obsDistTh;
    ArrayList<ObstacleBack> obsList;
    ArrayList<Runway>runWayList;
    GraphicsPanel side;
    GraphicsPanel top;
    JPanel calculationPanel;
    Plane plane;
    int tol;
    JComboBox<String> runWayComboBox;
    JComboBox<String> obsComboBox;
    Font font;
    Font Bold;
    Airport airport;
    OutputPanel outputPanel;
    NotificationPanel notificationPanel;
    int RESA;
    int engineBlastAllowance;
    public InputPanel(Airport airport,OutputPanel outputPanel,NotificationPanel notificationPanel){
        this.airport=airport;
        this.setPreferredSize(new Dimension(390,300));
        this.setBorder(BorderFactory.createTitledBorder("Input Panel"));
        this.setLocation(0,0);
        this.setLayout(new GridBagLayout());
        this.initialize();
        this.outputPanel=outputPanel;
        this.notificationPanel=notificationPanel;
    }
    public void initialize(){
        RESA = 240;
        engineBlastAllowance=300;
        JLabel label = new JLabel("HI");
        font = label.getFont();
        Bold = new Font(font.getName(),Font.BOLD, font.getSize());
        JLabel lblAllDistancesIn = new JLabel("All Distances in Meters");
        lblAllDistancesIn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        GridBagConstraints gbc_lblAllDistancesIn = new GridBagConstraints();
        gbc_lblAllDistancesIn.insets = new Insets(0, 0, 5, 5);
        gbc_lblAllDistancesIn.gridx = 2;
        gbc_lblAllDistancesIn.gridy = 1;
        this.add(lblAllDistancesIn, gbc_lblAllDistancesIn);
        importer = new XMLImporter();
        obsList= importer.importObstacles();
        runWayList = airport.getRunways();
        //Adding items to the runway combobox
        runWayComboBox = new JComboBox<String>();
        for (Runway runways : runWayList){
            runWayComboBox.addItem(runways.getDesignator());
        }
        runWayComboBox.setSelectedIndex(-1);
        JLabel lblChooseRunway = new JLabel("Choose Runway");
        lblChooseRunway.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints gbc_lblChooseRunway = new GridBagConstraints();
        gbc_lblChooseRunway.insets = new Insets(0, 0, 5, 5);
        gbc_lblChooseRunway.gridx = 1;
        gbc_lblChooseRunway.gridy = 2;
        this.add(lblChooseRunway, gbc_lblChooseRunway);
        GridBagConstraints gbc_runWayComboBox = new GridBagConstraints();
        gbc_runWayComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_runWayComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_runWayComboBox.gridx = 2;
        gbc_runWayComboBox.gridy = 2;
        this.add(runWayComboBox, gbc_runWayComboBox);

        //Adding obsticles to the obsticle combobox
        obsComboBox = new JComboBox<String>();
        for(ObstacleBack obs: obsList){
            obsComboBox.addItem(obs.getName());
        }
        obsComboBox.setSelectedIndex(-1);
        JLabel lblChooseObsticle = new JLabel("Choose Obsticle");
        lblChooseObsticle.setHorizontalAlignment(SwingConstants.LEFT);
        lblChooseObsticle.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints gbc_lblChooseObsticle = new GridBagConstraints();
        gbc_lblChooseObsticle.insets = new Insets(0, 0, 5, 5);
        gbc_lblChooseObsticle.gridx = 1;
        gbc_lblChooseObsticle.gridy = 3;
        this.add(lblChooseObsticle, gbc_lblChooseObsticle);
        GridBagConstraints gbc_obsComboBox = new GridBagConstraints();
        gbc_obsComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_obsComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_obsComboBox.gridx = 2;
        gbc_obsComboBox.gridy = 3;
        this.add(obsComboBox, gbc_obsComboBox);

        JLabel lblEnterObsticleLocation = new JLabel("Distance from CentreLine");
        lblEnterObsticleLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints gbc_lblEnterObsticleLocation = new GridBagConstraints();
        gbc_lblEnterObsticleLocation.insets = new Insets(0, 0, 5, 5);
        gbc_lblEnterObsticleLocation.gridx = 1;
        gbc_lblEnterObsticleLocation.gridy = 4;
        this.add(lblEnterObsticleLocation, gbc_lblEnterObsticleLocation);
        //Declaring text fields
        obsDisCL = new JTextField("0");
        GridBagConstraints gbc_obsDisCL = new GridBagConstraints();
        gbc_obsDisCL.insets = new Insets(0, 0, 5, 5);
        gbc_obsDisCL.fill = GridBagConstraints.HORIZONTAL;
        gbc_obsDisCL.gridx = 2;
        gbc_obsDisCL.gridy = 4;
        this.add(obsDisCL, gbc_obsDisCL);
        obsDisCL.setColumns(10);

        JLabel lblDistanceFromThreshold = new JLabel("Distance from Threshold");
        lblDistanceFromThreshold.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints gbc_lblDistanceFromThreshold = new GridBagConstraints();
        gbc_lblDistanceFromThreshold.insets = new Insets(0, 0, 5, 5);
        gbc_lblDistanceFromThreshold.gridx = 1;
        gbc_lblDistanceFromThreshold.gridy = 5;
        this.add(lblDistanceFromThreshold, gbc_lblDistanceFromThreshold);

        obsDistTh = new JTextField("0");
        GridBagConstraints gbc_obsDistTh = new GridBagConstraints();
        gbc_obsDistTh.insets = new Insets(0, 0, 5, 5);
        gbc_obsDistTh.fill = GridBagConstraints.HORIZONTAL;
        gbc_obsDistTh.gridx = 2;
        gbc_obsDistTh.gridy = 5;
        this.add(obsDistTh, gbc_obsDistTh);
        obsDistTh.setColumns(10);

        JLabel lblSelectAction = new JLabel("Select Action");
        font = lblSelectAction.getFont();
        lblSelectAction.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints gbc_lblSelectAction = new GridBagConstraints();
        gbc_lblSelectAction.insets = new Insets(0, 0, 5, 5);
        gbc_lblSelectAction.gridx = 1;
        gbc_lblSelectAction.gridy = 6;
        this.add(lblSelectAction, gbc_lblSelectAction);
        Bold = new Font(font.getName(),Font.BOLD, font.getSize());

        JComboBox<String> actionComboBox = new JComboBox<String>();
        actionComboBox.addItem("Landing");
        actionComboBox.addItem("Taking off");
        actionComboBox.setSelectedIndex(-1);
        GridBagConstraints gbc_actionComboBox = new GridBagConstraints();
        gbc_actionComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_actionComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_actionComboBox.gridx = 2;
        gbc_actionComboBox.gridy = 6;
        this.add(actionComboBox, gbc_actionComboBox);

        JLabel lblSelectDirection = new JLabel("Select Direction from Centerline");
        lblSelectDirection.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints selectDirection = new GridBagConstraints();
        selectDirection.insets = new Insets(0, 0, 5, 5);
        selectDirection.gridx = 1;
        selectDirection.gridy = 7;
        this.add(lblSelectDirection, selectDirection);
        //Adding stuff ti the direction combobox
        JComboBox<String> leftRight = new JComboBox<String>();
        leftRight.addItem("Left");
        leftRight.addItem("Right");
        leftRight.setSelectedIndex(-1);
        GridBagConstraints gbc_leftRight = new GridBagConstraints();
        gbc_leftRight.insets = new Insets(0, 0, 5, 5);
        gbc_leftRight.fill = GridBagConstraints.HORIZONTAL;
        gbc_leftRight.gridx = 2;
        gbc_leftRight.gridy = 7;
        this.add(leftRight, gbc_leftRight);

        JLabel lblSelectDirectionOf = new JLabel("Select Direction of Action");
        lblSelectDirectionOf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        GridBagConstraints gbc_lblSelectDirectionOf = new GridBagConstraints();
        gbc_lblSelectDirectionOf.insets = new Insets(0, 0, 5, 5);
        gbc_lblSelectDirectionOf.gridx = 1;
        gbc_lblSelectDirectionOf.gridy = 8;
        this.add(lblSelectDirectionOf, gbc_lblSelectDirectionOf);
        //Adding directions
        JComboBox<String> towardsAway = new JComboBox<String>();
        towardsAway.addItem("Towards");
        towardsAway.addItem("Away");
        towardsAway.setSelectedIndex(-1);
        GridBagConstraints gbc_towardsAway = new GridBagConstraints();
        gbc_towardsAway.insets = new Insets(0, 0, 5, 5);
        gbc_towardsAway.fill = GridBagConstraints.HORIZONTAL;
        gbc_towardsAway.gridx = 2;
        gbc_towardsAway.gridy = 8;
        this.add(towardsAway, gbc_towardsAway);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 4;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 9;
        this.add(panel, gbc_panel);
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JLabel());
        JButton btnCalculate = new JButton("Calculate");
        //Listener for calculate button
        btnCalculate.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
        panel.add(btnCalculate);
        btnCalculate.addActionListener(new CalculateListener(airport,runWayComboBox,importer,obsComboBox,obsDistTh,obsDisCL,leftRight,actionComboBox,towardsAway,plane));
    }

    public void setRESA(int RESA) {
        this.RESA = RESA;
    }

    public int getRESA() {
        return RESA;
    }

    public void setEngineBlastAllowance(int engineBlastAllowance) {
        this.engineBlastAllowance = engineBlastAllowance;
    }

    public int getEngineBlastAllowance() {
        return engineBlastAllowance;
    }

    public void updateObsList(){
        obsComboBox.removeAllItems();
        for(ObstacleBack obs : obsList){
            obsComboBox.addItem(obs.getName());
        }
        obsComboBox.setSelectedIndex(-1);
        obsComboBox.updateUI();
        this.updateUI();
    }
    //Update runway list
    public void updateRunwayList(){
        runWayComboBox.removeAllItems();
        for(Runway runway : runWayList){
            runWayComboBox.addItem(runway.getDesignator());
        }
        runWayComboBox.setSelectedIndex(-1);
        runWayComboBox.updateUI();
        this.updateUI();
    }

    class CalculateListener implements ActionListener {
        Runway runway;
        Airport airport;
        XMLImporter importer;
        ObstacleBack obs;
        int obsHeight;
        int obsLocThreshold;
        int obsLocCenteLine;
        String Side;
        String Action;
        JTextField obsLocCL;
        JTextField obsLocTH;
        String Direction;
        String Obsticle;
        JComboBox<String> rw;
        JComboBox<String>sd;
        JComboBox<String>ac;
        JComboBox<String>dr;
        JComboBox<String>ob;
        Plane plane;
//      GraphicsPanel top; //ted
//      GraphicsPanel side; //ted
//		public CalculateListener(, /*ted*/ GraphicsPanel top, GraphicsPanel side /*ted*/){


//			this.top = top; /*ted*/
//          this.side = side /*ted*/
//		}

        public CalculateListener(Airport airport,JComboBox<String>runway,XMLImporter importer,JComboBox<String>obs,JTextField obsLocTH,
                                 JTextField obsLocCL,JComboBox<String> Side,JComboBox<String>  Action,JComboBox<String>  Direction,Plane plane){
            this.airport=airport;
            this.rw = runway;
            this.importer = importer;
            this.obsLocTH=obsLocTH;
            this.obsLocCL=obsDisCL;
            this.sd=Side;
            this.ac= Action;
            this.dr=Direction;
            ob = obs;
            this.plane=plane;

        }
        public void actionPerformed(ActionEvent e) {
            outputPanel.removeAll();
            outputPanel.setBackground(UIManager.getColor("Panel.background"));
            outputPanel.updateUI();
            notificationPanel.removeAll();
            notificationPanel.updateUI();
//		    TODO Auto-generated method stub
            Side=sd.getItemAt(sd.getSelectedIndex());

            runway=airport.getRunwayByDesignator(rw.getItemAt(rw.getSelectedIndex()));
            Action=ac.getItemAt(ac.getSelectedIndex());
            Direction=dr.getItemAt(dr.getSelectedIndex());
            Obsticle = ob.getItemAt(ob.getSelectedIndex());
            try{
                obsLocCenteLine=Integer.parseInt(obsLocCL.getText());
            }catch (NumberFormatException er){
                notificationPanel.initialize("Invalid");
                return;
            }
            try{
                obsLocThreshold = Integer.parseInt(obsLocTH.getText());
            }catch (NumberFormatException er) {
                notificationPanel.initialize("Invalid");
                return;
            }
            obs=importer.getObsticalByName(Obsticle);

            if(sd!=null&&obsLocCenteLine>=0 &&obsLocThreshold>=0&&Obsticle!=null&&Action!=null&&Direction!=null&&Obsticle!=null&&runway!=null){


                obsHeight=obs.getHeight();

                Calculations calc = new Calculations(runway, obs.getHeight(), obsLocThreshold,RESA,engineBlastAllowance);
                if(obsLocCenteLine>runway.getRunwayWidth()/2){

                    if (Action=="Landing")
                        outputPanel.printObsOutOfRunway(calc,"Landing");//Calling landing case
                    else
                        outputPanel.printObsOutOfRunway(calc,"Taking");//Calling taking off case
                }
                else{
                    if(Action=="Landing"){//Cases of landing
                        if(Direction=="Towards"){
                            calc.calculateLda("Towards");//When landing towards
                            outputPanel.printCalcLandTowards(calc);

                        }else{
                            calc.calculateLda("Away");	//When landing over
                            outputPanel.printCalcLandOver(calc);
                        }
                    }
                    else{//Cases of taking off
                        if(Direction=="Towards"){
                            calc.calculateTORA("Towards");//When taking off towards the object
                            outputPanel.printCalcTakeOffTowards(calc);
                        }
                        else{
                            calc.calculateTORA("Away");//When taking off after the object
                            outputPanel.printTakeOffAfter(calc);
                        }
                    }

                }
                 notificationPanel.initialize("Valid");


                return;
            }
            notificationPanel.initialize("Invalid");
        }
//
    }

}
