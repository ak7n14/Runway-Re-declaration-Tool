//stub class
public class Runway
{

    private String designator;
    private int TORA;
    private int TODA;
    private int ASDA;
    private int LDA;
    private int threasholdDisplacement;


    public Runway(String designator, int TORA, int TODA, int ASDA, int LDA, int threasholdDisplacement)
    {
        this.designator = designator;
        this.TORA = TORA;
        this.TODA = TODA;
        this.ASDA = ASDA;
        this.LDA = LDA;
        this.threasholdDisplacement = threasholdDisplacement;
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
}
