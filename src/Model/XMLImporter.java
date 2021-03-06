package Model;
import org.w3c.dom.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Files;
import java.security.Key;
import java.util.*;
import java.io.BufferedReader;
public class XMLImporter
{

    // method reads a default XML file containing airport data
    // returns an ArrayList of Airport type
    public ArrayList<Airport> importAirports()
    {
        return importCustomAirports("Data/Airports.xml", true);
    }

    // This method takes only the file name and doesn't decrypt the given file
    public ArrayList<Airport> importCustomAirports(String filename)
    {
        return importCustomAirports(filename, false);
    }

    // this method reads a given XML file
    // if decrypt is true, then the file is decrypted
    // otherwise decryption doesn't take place
    public ArrayList<Airport> importCustomAirports(String filename, boolean decrypt)
    {
        ArrayList<Airport> airports = new ArrayList<Airport>();
        try
        {
            File airportFile = new File(filename);
            File temp = new File("Data/temp.xml");
            if(decrypt)
                decrypt(airportFile,temp);
            else
                Files.copy(airportFile.toPath(), temp.toPath());


            decrypt(airportFile, temp);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(temp);
            document.getDocumentElement().normalize();

            Files.delete(temp.toPath());

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
        return importCustomObstacles("Data/Obstacles.xml", true);
    }

    // This method takes only the file name and doesn't decrypt the given file
    public ArrayList<ObstacleBack> importCustomObstacles(String filename)
    {
        return importCustomObstacles(filename, false);
    }

    // this method reads a given XML file
    // if decrypt is true, then the file is decrypted
    // otherwise decryption doesn't take place
    public ArrayList<ObstacleBack> importCustomObstacles(String filename, boolean decrypt)
    {
        ArrayList<ObstacleBack> obstacles = new ArrayList<ObstacleBack>();
        try
        {
            File obstacleFile = new File(filename);
            File temp = new File("Data/temp.xml");

            if(decrypt)
                decrypt(obstacleFile, temp);
            else
                Files.copy(obstacleFile.toPath(), temp.toPath());


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(temp);
            document.getDocumentElement().normalize();

            Files.delete(temp.toPath());

            NodeList obstacleList = document.getElementsByTagName("obstacle");

            for(int i = 0; i < obstacleList.getLength(); i++)
            {
                Node obstacleNode = obstacleList.item(i);
                String obstacleName = obstacleNode.getAttributes().getNamedItem("name").getTextContent();

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
        return importCustomPlanes("Data/Planes.xml", true);
    }

    // This method takes only the file name and doesn't decrypt the given file
    public ArrayList<Plane> importCustomPlanes(String filename)
    {
        return importCustomPlanes(filename, false);
    }

    // this method reads a given XML file
    // if decrypt is true, then the file is decrypted
    // otherwise decryption doesn't take place
    public ArrayList<Plane> importCustomPlanes(String filename, boolean decrypt)
    {
        ArrayList<Plane> planes = new ArrayList<>();

        try
        {
            File planeFile = new File(filename);
            File temp = new File("Data/temp.xml");

            if(decrypt)
                decrypt(planeFile, temp);
            else
                Files.copy(planeFile.toPath(), temp.toPath());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(temp);
            Files.delete(temp.toPath());
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
            if(airport.getName().equals(name))
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

    // This method is used to import logs from a CSV file
    public ArrayList<Log> importLogs(Airport airportn)throws IOException{
        ArrayList<Log> Logs = new ArrayList();
        BufferedReader br;
        String line;

        decrypt(new File("Data/log.csv"), new File("Data/temp.csv"));

        br = new BufferedReader(new FileReader("Data/temp.csv"));
        while ((line = br.readLine()) != null) {
            String[] log = line.split(",");
            String name = log[0];
            Airport airport = this.getAirportByName(log[1]);
            Runway runway = airport.getRunwayByDesignator(log[2]);
            ObstacleBack obs = this.getObsticalByName(log[3]);
            int DistLocCL = Integer.parseInt(log[4]);
            int DistLocTh = Integer.parseInt(log[5]);
            String action = log[6];
            String DirectionCL = log[7];
            String DirectionAC = log[8];
            int RESA = Integer.parseInt(log[9]);
            int eng = Integer.parseInt(log[10]);
            Plane plane = this.getPlaneByName(log[11]);
            if(airport.getName().equals(airportn.getName())) {
                Logs.add(new Log(name, airport, runway, obs, DistLocCL, DistLocTh, action, DirectionCL, DirectionAC, RESA, eng, plane));
            }
        }
        if (br != null) {
            br.close();
        }

        Files.delete(new File("Data/temp.csv").toPath());

        return Logs;
    }

    // This method uses the AES algorithm to decrypt the first file and save it to the second file
    public void decrypt(File input, File output)
    {
        try
        {
            Key key = new SecretKeySpec("agile12345678910".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);

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
