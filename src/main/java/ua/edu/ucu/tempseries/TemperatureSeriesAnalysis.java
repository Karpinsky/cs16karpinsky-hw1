package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    public double[] temperatureSeries;
    private int currentFreeElementIndex;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[1];
        this.currentFreeElementIndex = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries_) {

        for (int i = 0; i < temperatureSeries_.length; ++i)
        {
            if (temperatureSeries_[i] < -273)
            {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries = temperatureSeries_;
        this.currentFreeElementIndex = this.temperatureSeries.length;
    }

    public double average() {

        if (this.temperatureSeries.length < 1)
        {
            throw new IllegalArgumentException();
        }

        double sum = 0;
        for (int i = 0; i < this.temperatureSeries.length; ++i)
        {
            sum += this.temperatureSeries[i];
        }

        return sum / this.temperatureSeries.length;
    }

    public double deviation() {
        return 0;
    }

    public double min() {

        if (this.temperatureSeries.length < 1)
        {
            throw new IllegalArgumentException();
        }

        double currentMin = this.temperatureSeries[0];
        for (int i = 1; i < this.temperatureSeries.length; ++i)
        {
            if (this.temperatureSeries[i] < currentMin)
            {
                currentMin = this.temperatureSeries[i];
            }
        }

        return currentMin;
    }

    public double max() {
        if (this.temperatureSeries.length < 1)
        {
            throw new IllegalArgumentException();
        }

        double currentMax = this.temperatureSeries[0];
        for (int i = 1; i < this.temperatureSeries.length; ++i)
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

        if (this.temperatureSeries.length < 1)
        {
            throw new IllegalArgumentException();
        }

        double currentClosest = this.temperatureSeries[0];
        double closestDistance = Math.abs(tempValue - this.temperatureSeries[0]);
        for (int i = 1; i < this.temperatureSeries.length; ++i)
        {
            double currentDistance = Math.abs(tempValue - this.temperatureSeries[i]);
            if (currentDistance < closestDistance)
            {
                currentClosest = this.temperatureSeries[i];
                closestDistance = currentDistance;
            }
            else if (currentDistance == closestDistance && this.temperatureSeries[i] > 0)
            {
                currentClosest = this.temperatureSeries[i];
            }
        }

        return currentClosest;
    }

    public double[] findTempsLessThen(double tempValue) {

        double[] temperaturesLessThanTempValue = new double[1];
        int temporaryFreeElementIndex = 0;

        for (int i = 0; i < this.temperatureSeries.length; ++i)
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

        for (int i = 0; i < this.temperatureSeries.length; ++i)
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
        return null;
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

        return (int)this.average();
    }
}
