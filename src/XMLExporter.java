import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

public class XMLExporter
{

    // save methods allow the user to save the data about Airports, Obstacles and Planes
    // files are always backed up before being overwritten
    public void saveAirportData(ArrayList<Airport> airports)
    {
        backupFile("Airports");
        exportAiports("Airports", airports);
    }

    public void saveObstacleData(ArrayList<ObstacleBack> obstacles)
    {
        backupFile("Obstacles");
        exportObstacles("Obstacles", obstacles);
    }

    public void savePlaneData(ArrayList<Plane> planes)
    {
        backupFile("Planes");
        exportPlanes("Planes", planes);
    }

    // This method allows the user to export a given ArrayList of Airports to an XML file
    // with a given name
    public void exportAiports(String filename, ArrayList<Airport> airports)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("airports");

            document.appendChild(root);

            for(Airport airport : airports)
            {
                root.appendChild(getAirport(document, airport.getName(), airport.getRunways()));
            }


            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);


            StreamResult file = new StreamResult(new File("Data/"+filename+".xml"));

            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //This method creates an airport node for XML
    private Node getAirport(Document document, String name, ArrayList<Runway> runwayList)
    {
        Element airportNode = document.createElement("airport");
        airportNode.setAttribute("name", name);

        for(Runway runway : runwayList)
        {
            airportNode.appendChild(getRunway(document, runway.getDesignator(), runway.getTORA(), runway.getTODA(),
                    runway.getASDA(), runway.getLDA(), runway.getThreasholdDisplacement(), runway.getRunwayLenght(),
                    runway.getRunwayWidth(), runway.getStripLength(), runway.getStripWidth()));
        }

        return airportNode;
    }

    // This method creates a runway node for XML
    private Node getRunway(Document document, String designator, int TORA, int TODA, int ASDA, int LDA, int thresholdDisplacement, int runwayLength, int runwayWidth, int stripLength, int stripWidth)
    {
        Element runway = document.createElement("runway");

        runway.appendChild(getRunwayFields(document, "designator", designator));
        runway.appendChild(getRunwayFields(document, "TORA", ""+TORA));
        runway.appendChild(getRunwayFields(document, "TODA", ""+TODA));
        runway.appendChild(getRunwayFields(document, "ASDA", ""+ASDA));
        runway.appendChild(getRunwayFields(document, "LDA", ""+LDA));
        runway.appendChild(getRunwayFields(document, "displacement", ""+thresholdDisplacement));
        runway.appendChild(getRunwayFields(document, "runwayLength", ""+runwayLength));
        runway.appendChild(getRunwayFields(document, "runwayWidth", ""+runwayWidth));
        runway.appendChild(getRunwayFields(document, "stripLength", ""+stripLength));
        runway.appendChild(getRunwayFields(document, "stripWidth", ""+stripWidth));

        return runway;
    }


    // This method returns Nodes with values using given node name and value
    private Node getRunwayFields(Document document, String name, String value)
    {
        Element field = document.createElement(name);
        field.appendChild(document.createTextNode(value));
        return field;
    }

    // This method allows the user to export a given ArrayList of ObstacleBack to an XML file
    // with a given name
    public void exportObstacles(String filename, ArrayList<ObstacleBack> obstacles)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("obstacles");

            document.appendChild(root);

            for(ObstacleBack obstacle : obstacles)
            {
                root.appendChild(getObstacle(document, obstacle));
            }


            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);


            StreamResult file = new StreamResult(new File("Data/"+filename+".xml"));

            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method creates a node for Obstacle containing details of the given ObstacleBack
    private Node getObstacle(Document document, ObstacleBack obstacle)
    {
        Element obstacleNode = document.createElement("obstacle");

        obstacleNode.setAttribute("name", obstacle.getName());

        Element height = document.createElement("height");
        height.appendChild(document.createTextNode(obstacle.getHeight()+""));
        obstacleNode.appendChild(height);

        Element length = document.createElement("length");
        length.appendChild(document.createTextNode(obstacle.getLength()+""));
        obstacleNode.appendChild(length);

        Element depth = document.createElement("depth");
        depth.appendChild(document.createTextNode(obstacle.getDepth()+""));
        obstacleNode.appendChild(depth);

        return obstacleNode;
    }

    // This method allows the user to export a given ArrayList of Planes to an XML file
    // with a given name
    public void exportPlanes(String filename, ArrayList<Plane> planes)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("planes");

            document.appendChild(root);

            for(Plane plane : planes)
            {
                root.appendChild(getPlane(document, plane));
            }


            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);


            StreamResult file = new StreamResult(new File("Data/"+filename+".xml"));

            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method creates a node for Plane containing details of the given Plane
    private Node getPlane(Document document, Plane plane)
    {
        Element planeNode = document.createElement("plane");

        planeNode.setAttribute("name", plane.getName());

        Element minTakeoff = document.createElement("takeoffLength");
        minTakeoff.appendChild(document.createTextNode(plane.getMinTakeoffDis()+""));
        planeNode.appendChild(minTakeoff);

        Element minLanding = document.createElement("landingLength");
        minLanding.appendChild(document.createTextNode(plane.getMinLandingDis()+""));
        planeNode.appendChild(minLanding);

        return planeNode;
    }

    // This method is used to back-up a named file by copying the file to a new file
    // Back-up file is name in format "<filename>_backup.xml"
    public void backupFile(String filename)
    {
        try
        {
            FileInputStream inputStream = new FileInputStream(new File("Data/"+filename+".xml"));
            FileOutputStream outputStream = new FileOutputStream(new File("Data/"+filename+"_backup.xml"));

            byte[] buffer = new byte[1024];
            int length;

            while((length = inputStream.read(buffer)) > 0)
                outputStream.write(buffer, 0, length);

            inputStream.close();
            outputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
