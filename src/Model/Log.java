package Model;

/**
 * Created by Anish on 4/22/17.
 */
public class Log {
    String name;
    Runway runway;
    Obstacle obsticle;
    int DistCL;
    int DistTH;
    String action;
    String DirectionCL;
    String DirectionAc;
    int RESA;
    int engineBlastAllowence;

    public Log(String name,Runway runway,Obstacle obsticle,int DistCL, int DistTH,String action, String DirectionCL, String DirectionAc,int RESA,int eng){
        this.name=name;
        this.runway=runway;
        this.obsticle=obsticle;
        this.DistCL=DistCL;
        this.action=action;
        this.DirectionCL=DirectionCL;
        this.DirectionAc=DirectionAc;
        this.RESA=RESA;
        this.engineBlastAllowence=eng;

    }

    public int getDistCL() {
        return DistCL;
    }

    public int getDistTH() {
        return DistTH;
    }

    public Obstacle getObsticle() {
        return obsticle;
    }

    public Runway getRunway() {
        return runway;
    }

    public String getAction() {
        return action;
    }

    public String getDirectionAc() {
        return DirectionAc;
    }

    public String getDirectionCL() {
        return DirectionCL;
    }

    public String getName() {
        return name;
    }

    public int getRESA() {
        return RESA;
    }

    public int getEngineBlastAllowence() {
        return engineBlastAllowence;
    }
}
