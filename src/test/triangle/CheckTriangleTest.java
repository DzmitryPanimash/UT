package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckTriangleTest {

    @DataProvider(name = "negativeExpectedData")
    public Object[][] createWrongSidesOfTriangles(){
       return new Object[][] {

               {"a<=0", -1, 3, 4},
               {"b<=0", 2.0, 0, 4.0},
               {"c<=0", 2, 3, -8.45},
               {"a+b<=c", Double.MIN_VALUE, 3, 4},
               {"a+b<=c", 1, 2, 3},
               {"a+c<=b", 1, 4.66, 2},
               {"b+c<=a", 93.245, 34.8, 45}

       };
    }



    @DataProvider (name = "positiveExpectedData")
    public Object[][] createRightSidesOfTriangles(){
        return new Object[][] {

                {true, 2,3,4},
                {true, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE}

        };
    }


    @DataProvider(name = "exceptionExpectedData")
    public Object[][] createExceptionSidesOfTriangles(){
        return new Object[][] {

                {2, Double.MAX_VALUE, 4},
                {Double.NaN, 3.0, 4.0},
                {3.5, 3.5, Double.NaN},
                {2, 3, Double.POSITIVE_INFINITY},
                {2,Double.NEGATIVE_INFINITY, 4}

        };
    }


    @Test(dataProvider = "negativeExpectedData")
    public void negativeTest(String expected, double a, double b, double c) {
        Triangle tr = new Triangle(a,b,c);
        Assert.assertFalse(tr.checkTriangle());
        Assert.assertEquals(tr.getMessage(), expected);
    }


    @Test (dataProvider = "positiveExpectedData")
    public void positiveTest(boolean expected, double a, double b, double c) {
        Triangle tr = new Triangle(a,b,c);
        Assert.assertEquals(tr.checkTriangle(), expected);
    }


    @Test (dataProvider = "exceptionExpectedData", expectedExceptions = Exception.class)
    public void exceptionTest (double a, double b, double c) {
        Triangle tr = new Triangle(a,b,c);
       tr.checkTriangle();
    }
}
