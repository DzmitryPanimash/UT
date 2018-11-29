package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetSquareTest {

    @DataProvider(name = "rightData")
    public Object[][] createRightData(){
        return new Object[][] {

                {13, 14, 15, 84},
                {13,14,15, Math.sqrt(21*(21-13)*(21-14)*(21-15))},
                {15.987654321, 15, 14.5432, Math.sqrt(((15.987654321 + 15 + 14.5432)/2)*(((15.987654321 + 15 + 14.5432)/2) - 15.987654321)*(((15.987654321 + 15 + 14.5432)/2) - 15)*(((15.987654321 + 15 + 14.5432)/2) - 14.5432))},
        };
    }

    @DataProvider(name = "exceptionExpectedData")
    public Object[][] createExceptionSidesOfTriangles(){
        return new Object[][] {

                {2, Double.MAX_VALUE, 4},
                {Double.NaN, 3.0, 4.0},
                {3.5, 3.5, Double.NaN},
                {2, 3, Double.POSITIVE_INFINITY},
                {2,Double.NEGATIVE_INFINITY, 4},
                { -1, 3, 4},            //"a<=0"
                {2.0, 0, 4.0},          //"b<=0"
                {2, 3, -8.45},          //"c<=0"
                {1, 2, 3},              //"a+b<=c"
                {1, 4.66, 2},           //"a+c<=b"
                { 93.245, 34.8, 45}     //"b+c<=a"

        };
    }

    @Test(dataProvider = "rightData")
    public void squareTest(double a, double b, double c, double square) {
        Triangle tr = new Triangle(a, b, c);
        Assert.assertEquals(tr.getSquare(), square);
    }


    @Test (dataProvider = "exceptionExpectedData", expectedExceptions = Exception.class)
    public void exceptionTest (double a, double b, double c) {
        Triangle tr = new Triangle(a,b,c);
        tr.getSquare();
    }
}
