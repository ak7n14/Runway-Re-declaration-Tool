//stub class
public class Runway
{

    private String designator;
    private int TORA;
    private int TODA;
    private int ASDA;
    private int LDA;


    public Runway(String designator, int TORA, int TODA, int ASDA, int LDA)
    {
        this.designator = designator;
        this.TORA = TORA;
        this.TODA = TODA;
        this.ASDA = ASDA;
        this.LDA = LDA;
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
}
