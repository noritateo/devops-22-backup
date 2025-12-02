package com.napier.devops;

public class City
{
    public String name;
    public String countryName;
    public String district;
    public String region;
    public String continent;
    public int population;

    public City() {}

    public City(String name,
                String countryName,
                String district,
                String region,
                String continent,
                int population)
    {
        this.name = name;
        this.countryName = countryName;
        this.district = district;
        this.region = region;
        this.continent = continent;
        this.population = population;
    }
}
