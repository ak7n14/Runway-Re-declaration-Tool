import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

public class XMLExporter
{

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


    private Node getAirport(Document document, String name, ArrayList<Runway> runwayList)
    {
        Element airportNode = document.createElement("airport");
        airportNode.setAttribute("name", name);

        for(Runway runway : runwayList)
        {
            airportNode.appendChild(getRunway(document, runway.getDesignator(), runway.getTORA(), runway.getTODA(),
                    runway.getASDA(), runway.getLDA(), runway.getThreasholdDisplacement()));
        }

        return airportNode;
    }

    private Node getRunway(Document document, String designator, int TORA, int TODA, int ASDA, int LDA, int threasholdDisplacement)
    {
        Element runway = document.createElement("runway");

        runway.appendChild(getRunwayFields(document, runway, "designator", designator));
        runway.appendChild(getRunwayFields(document, runway, "TORA", ""+TORA));
        runway.appendChild(getRunwayFields(document, runway, "TODA", ""+TODA));
        runway.appendChild(getRunwayFields(document, runway, "ASDA", ""+ASDA));
        runway.appendChild(getRunwayFields(document, runway, "LDA", ""+LDA));
        runway.appendChild(getRunwayFields(document, runway, "displacement", ""+threasholdDisplacement));

        return runway;
    }


    private Node getRunwayFields(Document document, Element element, String name, String value)
    {
        Element field = document.createElement(name);
        field.appendChild(document.createTextNode(value));
        return field;
    }
}
