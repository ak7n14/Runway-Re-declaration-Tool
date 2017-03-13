package Model;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMLImporter
{



    // method reads a default XML file containing airport data
    // returns an ArrayList of Airport type
    public ArrayList<Airport> importAirports()
    {
        ArrayList<Airport> airports = new ArrayList<Airport>();
        try
        {
            File airportFile = new File("Data/Airports.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(airportFile);
            document.getDocumentElement().normalize();

            NodeList airportList = document.getElementsByTagName("airport");



            for(int i = 0; i < airportList.getLength(); i++)
            {

                ArrayList<Runway> runways = new ArrayList<Runway>();
                Node airportNode = airportList.item(i);
                String airportName = airportNode.getAttributes().item(0).getTextContent();
                NodeList runwayList = airportNode.getChildNodes();

                for(int j = 0; j < runwayList.getLength(); j++)
                {
                    String designator;
                    Node runwayNode = runwayList.item(j);
                    if(runwayNode.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element element = (Element) runwayNode;

                        designator = element.getElementsByTagName("designator").item(0).getTextContent();
                        int TORA = Integer.parseInt(element.getElementsByTagName("TORA").item(0).getTextContent());
                        int TODA = Integer.parseInt(element.getElementsByTagName("TODA").item(0).getTextContent());
                        int ASDA = Integer.parseInt(element.getElementsByTagName("ASDA").item(0).getTextContent());
                        int LDA = Integer.parseInt(element.getElementsByTagName("LDA").item(0).getTextContent());
                        int displacement = Integer.parseInt(element.getElementsByTagName("displacement").item(0).getTextContent());
                        int runwayLength = Integer.parseInt(element.getElementsByTagName("runwayLength").item(0).getTextContent());
                        int runwayWidth = Integer.parseInt(element.getElementsByTagName("runwayWidth").item(0).getTextContent());
                        int stripLength = Integer.parseInt(element.getElementsByTagName("stripLength").item(0).getTextContent());
                        int stripWidth = Integer.parseInt(element.getElementsByTagName("stripWidth").item(0).getTextContent());

                        runways.add(new Runway(designator, TORA, TODA, ASDA, LDA, displacement, runwayLength, runwayWidth, stripLength, stripWidth));
                    }
                }

                airports.add(new Airport(airportName, runways));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return airports;
    }

    // method reads a default XML file containing obstacle data
    // returns an ArrayList of ObstacleBack type
    public ArrayList<ObstacleBack> importObstacles()
    {
        ArrayList<ObstacleBack> obstacles = new ArrayList<ObstacleBack>();
        try
        {
            File obstacleFile = new File("Data/Obstacles.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(obstacleFile);
            document.getDocumentElement().normalize();

            NodeList obstacleList = document.getElementsByTagName("obstacle");

            for(int i = 0; i < obstacleList.getLength(); i++)
            {
                Node obstacleNode = obstacleList.item(i);
                String obstacleName = obstacleNode.getAttributes().getNamedItem("name").getTextContent();
                System.out.println(obstacleName);

                if(obstacleNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) obstacleNode;

                    int height = Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent());
                    int length = Integer.parseInt(element.getElementsByTagName("length").item(0).getTextContent());
                    int depth = Integer.parseInt(element.getElementsByTagName("depth").item(0).getTextContent());

                    obstacles.add(new ObstacleBack(obstacleName, height, length, depth));
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return obstacles;
    }


    // method reads a default XML file containing plane data
    // returns an ArrayList of Plane type
    public ArrayList<Plane> importPlanes()
    {
        ArrayList<Plane> planes = new ArrayList<>();

        try
        {
            File planeFile = new File("Data/Planes.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(planeFile);
            document.getDocumentElement().normalize();

            NodeList planeList = document.getElementsByTagName("plane");

            for(int i = 0; i < planeList.getLength(); i++)
            {
                Node planeNode = planeList.item(i);
                String planeName = planeNode.getAttributes().getNamedItem("name").getTextContent();

                if(planeNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) planeNode;

                    planes.add(new Plane(planeName, Integer.parseInt(element.getElementsByTagName("landingLength").item(0).getTextContent()),
                                Integer.parseInt(element.getElementsByTagName("takeoffLength").item(0).getTextContent())));

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return planes;
    }

    // Lazy way of getting an airport by passing a name to the method
    // It has to read the whole file to return one airport
    // (depending on performance this will be changed)
    public Airport getAirportByName(String name)
    {
        ArrayList<Airport> airports = importAirports();

        for(Airport airport : airports)
        {
            if(airport.getName().equals(name.toUpperCase()))
                return airport;
        }

        return null;
    }

    
    public Plane getPlaneByName(String name){
    	ArrayList<Plane> planes = importPlanes();

        for(Plane plane : planes)
        {
            if(plane.getName().equals(name))
                return plane;
        }

        return null;
    }
    public ObstacleBack getObsticalByName(String name){
    	ArrayList<ObstacleBack> obstacle = importObstacles();

        for(ObstacleBack obs : obstacle)
        {
            if(obs.getName().equals(name))
                return obs;
        }

        return null;
    }
}
