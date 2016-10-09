package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation(){
        double[] temperatureSeries = {34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001d);
    }

    @Test
    public void testMin()
    {
        double[] temperatureSeries = {34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 34.0d;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin()
    {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.min();
    }

    @Test
    public void  testMax()
    {
        double[] temperatureSeries = {34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 40.0d;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax()
    {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.max();
    }

    @Test
    public void  testFindTempClosestToZero()
    {
        double[] temperatureSeries = {-2, 2, 34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 2.0d;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFindTempClosestToZero()
    {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void  testFindTempClosestToValue()
    {
        double[] temperatureSeries = {-2, 2, 34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 36d;

        double actualResult = seriesAnalysis.findTempClosestToValue(35.5d);

        assertEquals(expResult, actualResult, 0.00001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFindTempClosestToValue()
    {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.findTempClosestToValue(23);
    }

    @Test
    public void  testFindTempsLessThen()
    {
        double[] temperatureSeries = {-2, 2, 34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-2, 2, 34};

        double[] actualResult = seriesAnalysis.findTempsLessThen(35d);

        assertArrayEquals(expResult, actualResult, 0.00001d);
    }

    @Test
    public void  testFindTempsGreaterThen()
    {
        double[] temperatureSeries = {-2, 2, 34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {35, 36, 37, 38, 39, 40};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(35d);

        assertArrayEquals(expResult, actualResult, 0.00001d);
    }

    @Test
    public void testAddTemps()
    {
        double[] temperatureSeries = {-2, 2, 34, 35, 36, 37, 38, 39, 40};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] expResult = {-2, 2, 34, 35, 36, 37, 38, 39, 40, -7, 30};
        int expReturnValue = 11;

        int actualReturnValue = seriesAnalysis.addTemps(-7, 30);
        assertEquals(expReturnValue, actualReturnValue);

        double[] actualResult = seriesAnalysis.GetTemperatureSeries();
        assertArrayEquals(expResult, actualResult, 0.00001d);
    }

}
