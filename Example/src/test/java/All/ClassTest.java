package All;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClassTest {

    private AllClass cl;

    @Before
    public void setup(){
        cl = new AllClass();
    }

    @Test
    public void testABSInt() {
        assertEquals("ABS Method Test: 1",1,cl.ABSMethodInt(1));
        assertEquals("ABS Method Test: 1.0",1.0,cl.ABSMethodDouble(1.0), 0.0);
    }

    @Test
    public void testAOD(){
        assertEquals("AOD Method Test: 0+1",1,cl.AODMethod1(1));
        assertEquals("AOD Method Test: 1+1", 2, cl.AODMethod2(1));
        assertEquals("AOD Method Test: 0+1",1,cl.AODMethod1Long(1));
        assertEquals("AOD Method Test: 1+1", 2, cl.AODMethod2Long(1));
    }

    @Test
    public void testAOR(){
        assertEquals("AOR Method Test: 1+2-1*10/5%100000", 4, cl.AORMethod(1,2,1,10,5,100000));
        assertEquals("AOR Method Test: 1+0-0*1",1,cl.AORMethod2(1));
    }

    @Test
    public void testOBBN(){
        assertEquals("OBBN Method Test: 1&0",0,cl.OBBNMethod(1));
        assertTrue("OBBN Method Test: 1|0",cl.OBBNMethod2(1));
    }

    @Test
    public void testROR(){
        assertEquals(1,cl.RORMethod(1));
        assertEquals(2,cl.RORMethod(2));
        assertEquals(3,cl.RORMethod(3));
        assertEquals(-1,cl.RORMethod(-1));
    }

    @Test
    public void testUOI() {
        assertEquals("UOI Method Test: 1", 1, cl.UOIMethod(1));
    }
}
