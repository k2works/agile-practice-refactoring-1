import junit.framework.*;

/**
 * Created by k2works on 2016/02/15.
 * @version 0.0.1
 */
public class TestGeneratePrimes extends TestCase
{
    public void testPrimes()
    {
        int[] nullArray = GeneratePrimes.generatePrimes(0);
        assertEquals(nullArray.length,0);
        int[] minArray = GeneratePrimes.generatePrimes(2);
        assertEquals(minArray.length, 1);
        assertEquals(minArray[0], 2);
        int[] threeArray = GeneratePrimes.generatePrimes(3);
        assertEquals(threeArray.length,2);
        assertEquals(threeArray[0], 2);
        assertEquals(threeArray[1], 3);
        int[] centArray = GeneratePrimes.generatePrimes(100);
        assertEquals(centArray.length, 25);
        assertEquals(centArray[24], 97);
    }
}