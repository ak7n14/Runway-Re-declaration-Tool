//stub class
public class Runway
{

    private String designator;
    private int TORA;
    private int TODA;
    private int ASDA;
    private int LDA;
    private int threasholdDisplacement;
    private int runwayLenght;
    private int runwayWidth;
    private int stripLength;
    private int stripWidth;




    public Runway(String designator, int TORA, int TODA, int ASDA, int LDA, int threasholdDisplacement, int runwayLenght, int runwayWidth, int stripLength, int stripWidth)
    {
        this.designator = designator;
        this.TORA = TORA;
        this.TODA = TODA;
        this.ASDA = ASDA;
        this.LDA = LDA;
        this.threasholdDisplacement = threasholdDisplacement;
        this.runwayLenght = runwayLenght;
        this.runwayWidth = runwayWidth;
        this.stripLength = stripLength;
        this.stripWidth = stripWidth;

    }

    public String getDesignator()
    {
        return designator;
    }

    public int getTORA()
    {
        return TORA;
    }

    public int getTODA()
    {
        return TODA;
    }

    public int getASDA()
    {
        return ASDA;
    }

    public int getLDA()
    {
        return LDA;
    }

    public int getThreasholdDisplacement() {
        return threasholdDisplacement;
    }

    public int getRunwayLenght() {
        return runwayLenght;
    }

    public int getRunwayWidth() {
        return runwayWidth;
    }

    public int getStripLength() {
        return stripLength;
    }

    public int getStripWidth() {
        return stripWidth;
    }
}
