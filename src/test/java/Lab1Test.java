import org.junit.Assert;
import org.junit.Test;

public class Lab1Test {

    @Test
    public void powModTest() {
        Lab1 lab1 = new Lab1();
        long result = lab1.powMod(175, 235, 257);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void powModTest2() {
        Lab1 lab1 = new Lab1();
        long result = lab1.powMod(595, 703, 991);
        Assert.assertEquals(result, 342);
    }

    @Test
    public void gcdTest() {
        Lab1 lab1 = new Lab1();
        long result[] = lab1.gcd(28, 19);
        long out[] = {1, -2, 3};

        Assert.assertArrayEquals(result, out);
    }

    @Test
    public void BSGSTest() {
        Lab1 lab1 = new Lab1();
        long result = lab1.bsgs(6, 12, 229);
        Assert.assertEquals(result, 22);
    }

    @Test(expected = RuntimeException.class)
    public void BSGSTestError() {
        Lab1 lab1 = new Lab1();
        lab1.bsgs(9, 12, 229);
    }
}
