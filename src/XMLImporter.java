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

                        runways.add(new Runway(designator, TODA, TODA, ASDA, LDA, displacement, runwayLength, runwayWidth, stripLength, stripWidth));
                    }
                }

                airports.add(new Airport(airportName, runways));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return airports;
    }

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
                int height = Integer.parseInt(obstacleNode.getAttributes().getNamedItem("height").getTextContent());
                int noOfTop = Integer.parseInt(obstacleNode.getAttributes().getNamedItem("noOfTop").getTextContent());
                int noOfSide = Integer.parseInt(obstacleNode.getAttributes().getNamedItem("noOfSide").getTextContent());

                int[] topX = new int[noOfTop];
                int[] topY = new int[noOfTop];
                int[] sideX = new int[noOfSide];
                int[] sideY = new int[noOfSide];

                if(obstacleNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element obstacleElement = (Element) obstacleNode;

                    Element topXElement = (Element) obstacleElement.getElementsByTagName("topX").item(0);
                    Element topYElement = (Element) obstacleElement.getElementsByTagName("topY").item(0);
                    Element sideXElement = (Element) obstacleElement.getElementsByTagName("sideX").item(0);
                    Element sideYElement = (Element) obstacleElement.getElementsByTagName("sideY").item(0);

                    for(int j = 0; j < noOfTop; j++)
                    {
                        topX[j] = Integer.parseInt(topXElement.getElementsByTagName("topX"+j).item(0).getTextContent());
                        topY[j] = Integer.parseInt(topYElement.getElementsByTagName("topY"+j).item(0).getTextContent());
                    }

                    for(int j = 0; j < noOfSide; j++)
                    {
                        sideX[j] = Integer.parseInt(sideXElement.getElementsByTagName("sideX"+j).item(0).getTextContent());
                        sideY[j] = Integer.parseInt(sideYElement.getElementsByTagName("sideY"+j).item(0).getTextContent());
                    }

                    ObstacleBack obstacle = new ObstacleBack(obstacleName, height);
                    obstacle.setSideX(sideX);
                    obstacle.setSideY(sideY);
                    obstacle.setTopX(topX);
                    obstacle.setTopY(topY);

                    obstacles.add(obstacle);
                }


            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return obstacles;
    }


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

    
    public Plane getPlaneByName(String Name){
    	ArrayList<Plane> planes = importPlanes();

        for(Plane plane : planes)
        {
            if(plane.getName().equals(Name))
                return plane;
        }

        return null;
    }
}
