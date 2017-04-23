package Model;

/**
 * Created by Anish on 4/22/17.
 */
public class Log {
    String name;
    Runway runway;
    ObstacleBack obsticle;
    int DistCL;
    int DistTH;
    String action;
    String DirectionCL;
    String DirectionAc;
    int RESA;
    int engineBlastAllowence;
    Plane plane;
    Airport airport;
    public Log(String name,Airport airport,Runway runway,ObstacleBack obsticle,int DistCL, int DistTH,String action, String DirectionCL, String DirectionAc,int RESA,int eng,Plane plane){
        this.name=name;
        this.runway=runway;
        this.obsticle=obsticle;
        this.DistCL=DistCL;
        this.action=action;
        this.DirectionCL=DirectionCL;
        this.DirectionAc=DirectionAc;
        this.RESA=RESA;
        this.engineBlastAllowence=eng;
        this.plane = plane;
        this.airport=airport;
    }

    public int getDistCL() {
        return DistCL;
    }

    public int getDistTH() {
        return DistTH;
    }

    public ObstacleBack getObsticle() {
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

    public Plane getPlane() {
        return plane;
    }

    public Airport getAirport() {
        return airport;
    }
}
