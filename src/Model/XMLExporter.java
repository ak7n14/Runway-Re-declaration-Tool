package Model;
import org.w3c.dom.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.print.Doc;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class XMLExporter
{

    XMLImporter importer = new XMLImporter();

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

            encrypt(new File("Data/"+filename+".xml"), new File("Data/"+filename+".xml"));

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

            encrypt(new File("Data/"+filename+".xml"), new File("Data/"+filename+".xml"));
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

            encrypt(new File("Data/"+filename+".xml"), new File("Data/"+filename+".xml"));

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

    // This method is used to export the logs to an encrypted csv file and unencrypted xml
    public void exportLog(Log log) throws IOException {


        FileWriter pw = new FileWriter("Data/temp.csv",true);
        pw.append(log.getName());
        pw.append(",");
        pw.append(log.getAirport().getName());
        pw.append(",");
        pw.append(log.getRunway().getDesignator());
        pw.append(",");
        pw.append(log.getObsticle().getName());
        pw.append(",");
        pw.append(String.valueOf(log.getDistCL()));
        pw.append(",");
        pw.append(String.valueOf(log.getDistTH()));
        pw.append(",");
        pw.append(log.getAction());
        pw.append(",");
        pw.append(log.getDirectionCL());
        pw.append(",");
        pw.append(log.getDirectionAc());
        pw.append(",");
        pw.append(String.valueOf(log.getRESA()));
        pw.append(",");
        pw.append(String.valueOf(log.getEngineBlastAllowence()));
        pw.append(",");
        pw.append(log.getPlane().getName());
        pw.append("\n");
        pw.flush();
        pw.close();

        encrypt(new File("Data/temp.csv"), new File("Data/log.csv"));
        Files.delete(new File("Data/temp.csv").toPath());

        ArrayList<Log> tempList = new XMLImporter().importLogs(log.getAirport());
        tempList.add(log);
        logsXML("Data/log.xml",tempList);

    }

    // This method saves a given list of logs to a specified xml file
    public void logsXML(String filename, ArrayList<Log> logs)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("logs");

            document.appendChild(root);

            for(Log log : logs)
            {
                root.appendChild(getLog(document, log));
            }

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File (filename));
            transformer.transform(source, file);


        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    // This method creates a Node object containing information about a given Log object
    public Node getLog(Document doc, Log log)
    {
        Element logNode = doc.createElement("log");

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(log.getName()));
        logNode.appendChild(name);

        Element runway = doc.createElement("runway");
        runway.appendChild(doc.createTextNode(log.getRunway().getDesignator()));
        logNode.appendChild(runway);

        Element obstacle = doc.createElement("obstacle");
        obstacle.appendChild(doc.createTextNode(log.getObsticle().getName()));
        logNode.appendChild(obstacle);

        Element distCL = doc.createElement("DistCL");
        distCL.appendChild(doc.createTextNode(log.getDistCL()+""));
        logNode.appendChild(distCL);

        Element distTH = doc.createElement("DistTH");
        distTH.appendChild(doc.createTextNode(log.getDistTH()+""));
        logNode.appendChild(distTH);

        Element action = doc.createElement("action");
        action.appendChild(doc.createTextNode(log.getAction()));
        logNode.appendChild(action);

        Element directionCL = doc.createElement("DirectionCL");
        directionCL.appendChild(doc.createTextNode(log.getDirectionCL()));
        logNode.appendChild(directionCL);

        Element directionAc = doc.createElement("DirectionAc");
        directionAc.appendChild(doc.createTextNode(log.getDirectionAc()));
        logNode.appendChild(directionAc);

        Element RESA = doc.createElement("RESA");
        RESA.appendChild(doc.createTextNode(log.getRESA()+""));
        logNode.appendChild(RESA);

        Element engineBlastAllowence = doc.createElement("engineBlastAllowence");
        engineBlastAllowence.appendChild(doc.createTextNode(log.getEngineBlastAllowence()+""));
        logNode.appendChild(engineBlastAllowence);

        Element plane = doc.createElement("plane");
        plane.appendChild(doc.createTextNode(log.getPlane().getName()));
        logNode.appendChild(plane);

        Element airport = doc.createElement("airport");
        airport.appendChild(doc.createTextNode(log.getAirport().getName()));
        logNode.appendChild(airport);

        return logNode;
    }


    // This method is used to add a single airport to a given xml file
    public void addAirport(String filename, Airport airport)
    {
        ArrayList<Airport> airports = importer.importCustomAirports(filename);
        airports.add(airport);
        exportAiports(filename, airports);
    }

    // This method is used to add a single plane to a given xml file
    public void addPlane(String filename, Plane plane)
    {
        ArrayList<Plane> planes = importer.importCustomPlanes(filename);
        planes.add(plane);
        exportPlanes(filename, planes);
    }

    // This method is used to add a single obstacle to a given xml file
    public void addObstacle(String filename, ObstacleBack obstacle)
    {
        ArrayList<ObstacleBack> obstacles = importer.importCustomObstacles(filename);
        obstacles.add(obstacle);
        exportObstacles(filename, obstacles);
    }

    // This method uses the AES algorithm to encrypt a given file and output to the second given file
    public void encrypt (File input, File output)
    {
        try
        {
            Key key = new SecretKeySpec("agile12345678910".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            FileInputStream inputStream = new FileInputStream(input);
            byte[] inputBytes = new byte[(int) input.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(output);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        } catch (Exception e){
            System.err.println(e);
        }
    }




}
