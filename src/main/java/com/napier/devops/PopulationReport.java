package com.napier.devops;

public class PopulationReport
{
    public String name;            // Name of continent/region/country
    public long totalPopulation;   // Total population
    public long cityPopulation;    // People living in cities
    public long nonCityPopulation; // People NOT living in cities
    public double cityPercent;     // % living in cities
    public double nonCityPercent;  // % not living in cities

    public PopulationReport(String name,
                            long totalPopulation,
                            long cityPopulation)
    {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;

        this.nonCityPopulation = totalPopulation - cityPopulation;

        if (totalPopulation > 0)
        {
            this.cityPercent = (cityPopulation * 100.0) / totalPopulation;
            this.nonCityPercent = (nonCityPopulation * 100.0) / totalPopulation;
        }
        else
        {
            this.cityPercent = 0;
            this.nonCityPercent = 0;
        }
    }
}
