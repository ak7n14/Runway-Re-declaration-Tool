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
            File airportFile = new File("Airports.xml");
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

                        runways.add(new Runway(designator, TODA, TODA, ASDA, LDA));
                    }
                }

                airports.add(new Airport(airportName, runways));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return airports;
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

}
