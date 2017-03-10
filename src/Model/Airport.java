package Model;
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


    public String toString()
    {
        String output = "Name:\t" + name;

        for(int i = 0; i < runways.size(); i++)
        {
            output = output + "\n\t Runway " + runways.get(i).getDesignator()
                        + "\n\t\t TORA: " + runways.get(i).getTORA()
                        + "\n\t\t TODA: " + runways.get(i).getTODA()
                        + "\n\t\t ASDA: " + runways.get(i).getASDA()
                        + "\n\t\t LDA: " + runways.get(i).getLDA()
                        + "\n\t\t Threashold displacement: " + runways.get(i).getThreasholdDisplacement()
                        + "\n\t\t Runway Length: " + runways.get(i).getRunwayLenght()
                        + "\n\t\t Runway Width: " + runways.get(i).getRunwayWidth()
                        + "\n\t\t Strip Length: " + runways.get(i).getStripLength()
                        + "\n\t\t Strip Width: " + runways.get(i).getRunwayWidth();
        }

        return output;
    }
    
    public Runway getRunwayByDesignator(String desig){
    	for(int i=0;i<runways.size();i++){
    		if(runways.get(i).getDesignator()==desig){
    			return runways.get(i);
    		}
    	}
    	System.err.println("RunwayNotFound");
    	return null;
    }


}
