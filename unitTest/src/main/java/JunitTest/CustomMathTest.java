package JunitTest;

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