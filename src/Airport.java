import java.util.ArrayList;

//stub class for now
public class Airport
{
    private String name;
    private ArrayList<Runway> runways;


    public Airport(String name, ArrayList<Runway> runways)
    {
        this.name = name;
        this.runways = runways;
    }


    //Accessor methods - not sure if we need mutator methods for airports.
    public String getName()
    {
        return name;
    }

    public ArrayList<Runway> getRunways()
    {
        return runways;
    }


}
