import java.io.StringWriter;
import java.util.*;
/**
 * Created by Mikolaj on 25/02/2017.
 */
public class XMLTester
{

    public static void main(String[] args)
    {
        XMLImporter importer = new XMLImporter();

       // ArrayList<Airport> airportArrayList = importer.importAirports();

       // System.out.println(airportArrayList.get(2).toString());
       // System.out.println(airportArrayList.get(1).toString());

       // System.out.print(importer.getAirportByName("LONDON GATWICK").toString());

        ArrayList<ObstacleBack> obstacleArrayList = importer.importObstacles();

        System.out.println(obstacleArrayList.get(0).getName() + " " + obstacleArrayList.get(0).getHeight());


      //  ArrayList<Plane> planes = importer.importPlanes();





        XMLExporter exporter = new XMLExporter();

       // exporter.exportAiports("outputTest", airportArrayList);

       // exporter.backupFile("outputTest");

        exporter.exportObstacles("outputTest", obstacleArrayList);

        //System.out.println(importer.importPlanes().size());

    }

}
