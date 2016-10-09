package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {

    private final double avgTemp;
    public double GetAverageTemperature(){ return avgTemp; }
    private final double devTemp;
    public double GetDeviationTemperature(){ return devTemp; }
    private final double minTemp;
    public double GetMinTemperature(){ return minTemp; }
    private final double maxTemp;
    public double GetMaxTemperature(){ return maxTemp; }

    public TempSummaryStatistics(double avgTemp_, double devTemp_, double minTemp_, double maxTemp_)
    {
        this.avgTemp = avgTemp_;
        this.devTemp = devTemp_;
        this.minTemp = minTemp_;
        this.maxTemp = maxTemp_;
    }

}
