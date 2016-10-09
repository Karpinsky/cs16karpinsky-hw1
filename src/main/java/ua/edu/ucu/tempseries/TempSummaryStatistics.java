package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {

    final private double avgTemp;
    final private double devTemp;
    final private double minTemp;
    final private double maxTemp;

    public TempSummaryStatistics(double avgTempC, double devTempC, double minTempC, double maxTempC)
    {
        this.avgTemp = avgTempC;
        this.devTemp = devTempC;
        this.minTemp = minTempC;
        this.maxTemp = maxTempC;
    }

    public double getAverageTemperature() { return avgTemp; }
    public double getDeviationTemperature() { return devTemp; }
    public double getMinTemperature() { return minTemp; }
    public double getMaxTemperature() { return maxTemp; }
}
