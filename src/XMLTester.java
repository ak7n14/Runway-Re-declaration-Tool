import java.util.*;
/**
 * Created by Mikolaj on 25/02/2017.
 */
public class XMLTester
{

    public static void main(String[] args)
    {
        XMLImporter importer = new XMLImporter();

        ArrayList<Airport> airportArrayList = importer.importAirports();

        System.out.println(airportArrayList.get(0).toString());
    }

}
