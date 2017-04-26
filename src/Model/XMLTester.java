package Model;
import java.util.*;
import java.io.*;

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

      //  ArrayList<ObstacleBack> obstacleArrayList = importer.importObstacles();

       // System.out.println(obstacleArrayList.get(0).getName() + " " + obstacleArrayList.get(0).getHeight());


      //  ArrayList<Plane> planes = importer.importPlanes();





       XMLExporter exporter = new XMLExporter();

       // exporter.exportAiports("outputTest", airportArrayList);

       // exporter.backupFile("outputTest");

     //   exporter.exportObstacles("outputTest", obstacleArrayList);

        //System.out.println(importer.importPlanes().size());



        exporter.encrypt(new File("Data/log.csv"), new File("Data/log.csv"));

        //importer.decrypt(new File("Data/test2.xml"), new File("Data/test3.xml"));
    }

}
