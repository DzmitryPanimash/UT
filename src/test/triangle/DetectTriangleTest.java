package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


    public class DetectTriangleTest {

    @DataProvider(name = "positiveTriangles")

    public Object[][] createSidesOfTriangles(){
    return new Object[][]{

            {1, 3.5, 3.5, 3.5},  //"Equilateral"
            {1, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE}, //"Equilateral"
            {2, 3.0, 3.0, 2.0},//"Isosceles"
            {2, 3.0, 2.0, 3.0},
            {8, 3.0, 4.0, 5.0},//"Rectangular"
            {4, 3.0, 4.0, 2.0},//"Ordinary"
            {10, 3.3, 3.3, Math.sqrt(3.3*3.3 + 3.3*3.3)},//"Combination of features"
            {10, 15.987654321, 15.987654321, Math.sqrt(15.987654321*15.987654321 + 15.987654321*15.987654321)},
            {1, 0b11001, 0b11001, 0b11001},   //"Equilateral" in bin
            {1, 0x902E, 0x902E, 0x902E}   //"Equilateral" in hex
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


    @Test(dataProvider = "positiveTriangles")
    public void positiveTest (int expected,double a, double b, double c) {
    Triangle tr = new Triangle(a, b, c);
    Assert.assertEquals(tr.detectTriangle(), expected);

}


    @Test (dataProvider = "exceptionExpectedData", expectedExceptions = Exception.class)
    public void exceptionTest (double a, double b, double c) {
    Triangle tr = new Triangle(a,b,c);
    tr.detectTriangle();
}
    }