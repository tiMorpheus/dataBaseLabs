package JunitTest;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CustomMathTest {



    @Test
    public void testSum(){
        System.out.println("sun");
        int x = 0;
        int y = 0;
        int expResult = 0;
        int result = CustomMath.sum(x,y);

        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDivision(){
        System.out.println("division");
        int x = 0;
        int y = 0;
        int expResult = 0;
        try {
            expResult = CustomMath.division(x, y);
            fail("Exception expected");
        }catch (IllegalArgumentException e){
            System.out.println("pass");
        }
    }

}

class CustomMath{
    public static int sum(int x, int y){
        return x + y;
    }
    public static int division(int x, int y){
        if(y==0) {
            throw new IllegalArgumentException("divide is 0");

        }
        return x/y;
    }
}