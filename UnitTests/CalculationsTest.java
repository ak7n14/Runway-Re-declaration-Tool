import Model.Calculations;
import Model.Runway;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationsTest {
    Runway runway = new Runway("09R",3658,3658,3658,3350,308,3660,50,4022,300);
    Calculations calc = new Calculations(runway,5,350);


    @Test
    public void getReLda() throws Exception {
        calc.calculateLda("Towards");
        assertEquals(50,calc.getReLda());
    }

    @Test
    public void getReASDA() throws Exception {
        calc.calculateTORA("Towards");
        assertEquals(348,calc.getReASDA());
    }

    @Test
    public void getStopWay() throws Exception {
        assertEquals(0,calc.getStopWay());
    }

    @Test
    public void getClearWay() throws Exception {
        assertEquals(0,calc.getClearWay());
    }

    @Test
    public void getReTODA() throws Exception {
        calc.calculateTORA("Towards");
        assertEquals(348,calc.getReTODA());
    }

    @Test
    public void getReTORA() throws Exception {
        calc.calculateTORA("Towards");
        assertEquals(348,calc.getReTORA());
    }

}