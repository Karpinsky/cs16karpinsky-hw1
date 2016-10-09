package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final double MIN_POSSIBLE_TEMPERATURE = -273.0;
    private static final double DIFFERENCE_BIAS = 0.00001;

    private double[] temperatureSeries;
    private int currentFreeElementIndex;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[1];
        this.currentFreeElementIndex = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeriesC) {

        for (int i = 0; i < temperatureSeriesC.length; ++i)
        {
            if (Math.abs(this.MIN_POSSIBLE_TEMPERATURE - temperatureSeriesC[i]) < this.DIFFERENCE_BIAS)
            {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries = new double[temperatureSeriesC.length];
        System.arraycopy(temperatureSeriesC, 0, this.temperatureSeries, 0, temperatureSeriesC.length);
        this.currentFreeElementIndex = this.temperatureSeries.length;
    }

    public double[] getTemperatureSeries()
    {
        double[] newArray = new double[this.currentFreeElementIndex];
        System.arraycopy(this.temperatureSeries, 0, newArray, 0, this.currentFreeElementIndex);
        return newArray;
    }

    public double average() {

        if (this.currentFreeElementIndex < 1)
        {
            throw new IllegalArgumentException();
        }

        return sum() / this.currentFreeElementIndex;
    }

    public double sum()
    {
        return sum(this.temperatureSeries, this.currentFreeElementIndex);
    }

    private double sum(double[] arr, int lengthToCount)
    {
        if (arr.length == 1)
        {
            return arr[0];
        }

        double temperatureSum = 0;
        for (int i = 0; i < lengthToCount; ++i)
        {
            temperatureSum += arr[i];
        }

        return temperatureSum;
    }

    public double deviation() {

        if (this.currentFreeElementIndex < 1)
        {
            throw new IllegalArgumentException();
        }

        double averageValue = this.average();
        double[] deviationsFromAverageSquared = new double[this.currentFreeElementIndex];

        // Less memory usage and less operations:
        // double sum  = 0;
        // for this.currentFreeElementIndex = sum += (this.temperatureSeries[i] - averageValue) * (this.temperatureSeries[i] - averageValue);
        // return Math.sqrt(sum / this.currentFreeElementIndex)

        for (int i = 0; i < this.currentFreeElementIndex; ++i)
        {
            deviationsFromAverageSquared[i] = (this.temperatureSeries[i] - averageValue) * (this.temperatureSeries[i] - averageValue);
        }

        return Math.sqrt(sum(deviationsFromAverageSquared, deviationsFromAverageSquared.length) / this.currentFreeElementIndex);

    }

    public double min() {

        if (this.currentFreeElementIndex < 1)
        {
            throw new IllegalArgumentException();
        }

        double currentMin = this.temperatureSeries[0];
        for (int i = 1; i < this.currentFreeElementIndex; ++i)
        {
            if (this.temperatureSeries[i] < currentMin)
            {
                currentMin = this.temperatureSeries[i];
            }
        }

        return currentMin;
    }

    public double max() {
        if (this.currentFreeElementIndex < 1)
        {
            throw new IllegalArgumentException();
        }

        double currentMax = this.temperatureSeries[0];
        for (int i = 1; i < this.currentFreeElementIndex; ++i)
        {
            if (this.temperatureSeries[i] > currentMax)
            {
                currentMax = this.temperatureSeries[i];
            }
        }

        return currentMax;
    }

    public double findTempClosestToZero() { return findTempClosestToValue(0); }

    public double findTempClosestToValue(double tempValue) {

        if (this.currentFreeElementIndex < 1)
        {
            throw new IllegalArgumentException();
        }

        double currentClosest = this.temperatureSeries[0];
        double closestDistance = Math.abs(tempValue - this.temperatureSeries[0]);
        for (int i = 1; i < this.currentFreeElementIndex; ++i)
        {
            double currentDistance = Math.abs(tempValue - this.temperatureSeries[i]);
            if (currentDistance < closestDistance)
            {
                currentClosest = this.temperatureSeries[i];
                closestDistance = currentDistance;
            }
            else if (Math.abs(currentDistance - closestDistance) < this.DIFFERENCE_BIAS && this.temperatureSeries[i] > 0)
            {
                currentClosest = this.temperatureSeries[i];
            }
        }

        return currentClosest;
    }

    public double[] findTempsLessThen(double tempValue) {

        double[] temperaturesLessThanTempValue = new double[1];
        int temporaryFreeElementIndex = 0;

        for (int i = 0; i < this.currentFreeElementIndex; ++i)
        {
            if (this.temperatureSeries[i] < tempValue)
            {
                temperaturesLessThanTempValue[temporaryFreeElementIndex] = this.temperatureSeries[i];
                temporaryFreeElementIndex++;

                if (temporaryFreeElementIndex >= temperaturesLessThanTempValue.length)
                {
                    double[] newArray = new double[temperaturesLessThanTempValue.length * 2];
                    System.arraycopy(temperaturesLessThanTempValue, 0, newArray, 0, temperaturesLessThanTempValue.length);
                    temperaturesLessThanTempValue = newArray;
                }
            }
        }

        double[] finalArray = new double[temporaryFreeElementIndex];
        System.arraycopy(temperaturesLessThanTempValue, 0, finalArray, 0, temporaryFreeElementIndex);

        return finalArray;
    }

    public double[] findTempsGreaterThen(double tempValue) {

        double[] temperaturesLessThanTempValue = new double[1];
        int currentFreeElementIndex = 0;

        for (int i = 0; i < this.currentFreeElementIndex; ++i)
        {
            if (this.temperatureSeries[i] >= tempValue)
            {
                temperaturesLessThanTempValue[currentFreeElementIndex] = this.temperatureSeries[i];
                currentFreeElementIndex++;

                if (currentFreeElementIndex >= temperaturesLessThanTempValue.length)
                {
                    double[] newArray = new double[temperaturesLessThanTempValue.length * 2];
                    System.arraycopy(temperaturesLessThanTempValue, 0, newArray, 0, temperaturesLessThanTempValue.length);
                    temperaturesLessThanTempValue = newArray;
                }
            }
        }

        double[] finalArray = new double[currentFreeElementIndex];
        System.arraycopy(temperaturesLessThanTempValue, 0, finalArray, 0, currentFreeElementIndex);

        return finalArray;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
    }

    public int addTemps(double... temps) {

        for (int i = 0; i < temps.length; ++i)
        {
            if (this.currentFreeElementIndex >= this.temperatureSeries.length)
            {
                double[] newArray = new double[this.temperatureSeries.length * 2];
                System.arraycopy(this.temperatureSeries, 0, newArray, 0, this.temperatureSeries.length);
                this.temperatureSeries = newArray;
            }

            this.temperatureSeries[this.currentFreeElementIndex] = temps[i];
            this.currentFreeElementIndex++;
        }

        return this.currentFreeElementIndex;
    }
}
