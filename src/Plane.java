/**
 * Created by Mikolaj on 28/02/2017.
 */
public class Plane
{

    private String name;
    private int minLandingDis;
    private int minTakeoffDis;


    public Plane(String name, int minLandingDis, int minTakeoffDis)
    {
        this.name = name;
        this.minLandingDis = minLandingDis;
        this.minTakeoffDis = minTakeoffDis;
    }


    //Accessor methods

    public String getName()
    {
        return name;
    }

    public int getMinLandingDis()
    {
        return minLandingDis;
    }

    public int getMinTakeoffDis()
    {
        return minTakeoffDis;
    }

}
