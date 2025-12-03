package com.napier.devops;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App
{
    private Connection con = null;

    /**
     * DB CONNECTION
     **/
    public void connect(String location, int delay)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(delay);
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect()
    {
        if (con != null)
        {
            try { con.close(); }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }



    /* ============================================================
                 COUNTRY QUERY METHODS
       ============================================================ */

    // Base: All countries
    public List<Country> getAllCountries()
    {
        List<Country> countries = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "ci.Name AS CapitalCity " +
                            "FROM country c LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                Country c = new Country();
                c.code = r.getString("Code");
                c.name = r.getString("Name");
                c.continent = r.getString("Continent");
                c.region = r.getString("Region");
                c.population = r.getInt("Population");
                c.capitalCity = r.getString("CapitalCity");
                countries.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Country query failed: " + e.getMessage());
        }

        return countries;
    }

    public List<Country> getCountriesByContinent(String continent)
    {
        List<Country> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "ci.Name AS CapitalCity " +
                            "FROM country c LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Continent = ? " +
                            "ORDER BY c.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);

            ResultSet r = stmt.executeQuery();
            while (r.next())
            {
                Country c = new Country();
                c.code = r.getString("Code");
                c.name = r.getString("Name");
                c.continent = r.getString("Continent");
                c.region = r.getString("Region");
                c.population = r.getInt("Population");
                c.capitalCity = r.getString("CapitalCity");
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCountriesByContinent: " + e.getMessage());
        }

        return list;
    }

    public List<Country> getCountriesByRegion(String region)
    {
        List<Country> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "ci.Name AS CapitalCity " +
                            "FROM country c LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Region = ? " +
                            "ORDER BY c.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);

            ResultSet r = stmt.executeQuery();
            while (r.next())
            {
                Country c = new Country();
                c.code = r.getString("Code");
                c.name = r.getString("Name");
                c.continent = r.getString("Continent");
                c.region = r.getString("Region");
                c.population = r.getInt("Population");
                c.capitalCity = r.getString("CapitalCity");
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCountriesByRegion: " + e.getMessage());
        }

        return list;
    }

    public List<Country> getTopNCountriesWorld(int n)
    {
        List<Country> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "ci.Name AS CapitalCity " +
                            "FROM country c LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n);

            ResultSet r = stmt.executeQuery();
            while (r.next())
            {
                Country c = new Country();
                c.code = r.getString("Code");
                c.name = r.getString("Name");
                c.continent = r.getString("Continent");
                c.region = r.getString("Region");
                c.population = r.getInt("Population");
                c.capitalCity = r.getString("CapitalCity");
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCountriesWorld: " + e.getMessage());
        }

        return list;
    }

    public List<Country> getTopNCountriesByContinent(String continent, int n)
    {
        List<Country> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "ci.Name AS CapitalCity " +
                            "FROM country c LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Continent = ? " +
                            "ORDER BY c.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();
            while (r.next())
            {
                Country c = new Country();
                c.code = r.getString("Code");
                c.name = r.getString("Name");
                c.continent = r.getString("Continent");
                c.region = r.getString("Region");
                c.population = r.getInt("Population");
                c.capitalCity = r.getString("CapitalCity");
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCountriesByContinent: " + e.getMessage());
        }

        return list;
    }

    public List<Country> getTopNCountriesByRegion(String region, int n)
    {
        List<Country> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "ci.Name AS CapitalCity " +
                            "FROM country c LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Region = ? " +
                            "ORDER BY c.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();
            while (r.next())
            {
                Country c = new Country();
                c.code = r.getString("Code");
                c.name = r.getString("Name");
                c.continent = r.getString("Continent");
                c.region = r.getString("Region");
                c.population = r.getInt("Population");
                c.capitalCity = r.getString("CapitalCity");
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCountriesByRegion: " + e.getMessage());
        }

        return list;
    }



    /* ============================================================
                        CITY QUERY METHODS
       ============================================================ */

    public List<City> getAllCities()
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District, " +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "ORDER BY ci.Population DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getAllCities: " + e.getMessage());
        }

        return list;
    }

    public List<City> getCitiesByContinent(String continent)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE co.Continent = ? ORDER BY ci.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCitiesByContinent: " + e.getMessage());
        }

        return list;
    }

    public List<City> getCitiesByRegion(String region)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE co.Region = ? ORDER BY ci.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCitiesByRegion: " + e.getMessage());
        }

        return list;
    }

    public List<City> getCitiesByCountry(String country)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE co.Name = ? ORDER BY ci.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, country);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCitiesByCountry: " + e.getMessage());
        }

        return list;
    }

    public List<City> getCitiesByDistrict(String district)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE ci.District = ? ORDER BY ci.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, district);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCitiesByDistrict: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCitiesWorld(int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCitiesWorld: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCitiesByContinent(String continent, int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE co.Continent = ? ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCitiesByContinent: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCitiesByRegion(String region, int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE co.Region = ? ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCitiesByRegion: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCitiesByCountry(String country, int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE co.Name = ? ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, country);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCitiesByCountry: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCitiesByDistrict(String district, int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.District," +
                            "co.Region, co.Continent, ci.Population " +
                            "FROM city ci JOIN country co ON ci.CountryCode = co.Code " +
                            "WHERE ci.District = ? ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, district);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCitiesByDistrict: " + e.getMessage());
        }

        return list;
    }



    /* ============================================================
                CAPITAL CITY QUERY METHODS
       ============================================================ */

    public List<City> getAllCapitalCities()
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.Population, " +
                            "co.Region, co.Continent, ci.District " +
                            "FROM city ci JOIN country co ON co.Capital = ci.ID " +
                            "ORDER BY ci.Population DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getAllCapitalCities: " + e.getMessage());
        }

        return list;
    }

    public List<City> getCapitalCitiesByContinent(String continent)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.Population, " +
                            "co.Region, co.Continent, ci.District " +
                            "FROM city ci JOIN country co ON co.Capital = ci.ID " +
                            "WHERE co.Continent = ? ORDER BY ci.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCapitalCitiesByContinent: " + e.getMessage());
        }

        return list;
    }

    public List<City> getCapitalCitiesByRegion(String region)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.Population, " +
                            "co.Region, co.Continent, ci.District " +
                            "FROM city ci JOIN country co ON co.Capital = ci.ID " +
                            "WHERE co.Region = ? ORDER BY ci.Population DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getCapitalCitiesByRegion: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCapitalCitiesWorld(int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.Population, " +
                            "co.Region, co.Continent, ci.District " +
                            "FROM city ci JOIN country co ON co.Capital = ci.ID " +
                            "ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCapitalCitiesWorld: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCapitalCitiesByContinent(String continent, int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.Population, " +
                            "co.Region, co.Continent, ci.District " +
                            "FROM city ci JOIN country co ON co.Capital = ci.ID " +
                            "WHERE co.Continent = ? ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCapitalCitiesByContinent: " + e.getMessage());
        }

        return list;
    }

    public List<City> getTopNCapitalCitiesByRegion(String region, int n)
    {
        List<City> list = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT ci.Name AS CityName, co.Name AS CountryName, ci.Population, " +
                            "co.Region, co.Continent, ci.District " +
                            "FROM city ci JOIN country co ON co.Capital = ci.ID " +
                            "WHERE co.Region = ? ORDER BY ci.Population DESC LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            stmt.setInt(2, n);

            ResultSet r = stmt.executeQuery();

            while (r.next())
            {
                list.add(new City(
                        r.getString("CityName"),
                        r.getString("CountryName"),
                        r.getString("District"),
                        r.getString("Region"),
                        r.getString("Continent"),
                        r.getInt("Population")
                ));
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getTopNCapitalCitiesByRegion: " + e.getMessage());
        }

        return list;
    }

    /* ============================================================
                POPULATION QUERY METHODS
       ============================================================ */

    public List<PopulationReport> getPopulationByContinent()
    {
        List<PopulationReport> list = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT co.Continent AS Name, " +
                            "       SUM(co.Population) AS TotalPopulation, " +
                            "       SUM(ci.Population) AS CityPopulation " +
                            "FROM country co " +
                            "LEFT JOIN city ci ON ci.CountryCode = co.Code " +
                            "GROUP BY co.Continent " +
                            "ORDER BY TotalPopulation DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                PopulationReport p = new PopulationReport();
                p.name = r.getString("Name");
                p.totalPopulation = r.getLong("TotalPopulation");
                long cityPop = r.getLong("CityPopulation");
                if (r.wasNull())
                    cityPop = 0;
                p.cityPopulation = cityPop;
                p.nonCityPopulation = p.totalPopulation - p.cityPopulation;

                if (p.totalPopulation > 0)
                {
                    p.cityPercentage = (p.cityPopulation * 100.0) / p.totalPopulation;
                    p.nonCityPercentage = (p.nonCityPopulation * 100.0) / p.totalPopulation;
                }

                list.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getPopulationByContinent: " + e.getMessage());
        }
        return list;
    }

    public List<PopulationReport> getPopulationByRegion()
    {
        List<PopulationReport> list = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT co.Region AS Name, " +
                            "       SUM(co.Population) AS TotalPopulation, " +
                            "       SUM(ci.Population) AS CityPopulation " +
                            "FROM country co " +
                            "LEFT JOIN city ci ON ci.CountryCode = co.Code " +
                            "GROUP BY co.Region " +
                            "ORDER BY TotalPopulation DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                PopulationReport p = new PopulationReport();
                p.name = r.getString("Name");
                p.totalPopulation = r.getLong("TotalPopulation");
                long cityPop = r.getLong("CityPopulation");
                if (r.wasNull())
                    cityPop = 0;
                p.cityPopulation = cityPop;
                p.nonCityPopulation = p.totalPopulation - p.cityPopulation;

                if (p.totalPopulation > 0)
                {
                    p.cityPercentage = (p.cityPopulation * 100.0) / p.totalPopulation;
                    p.nonCityPercentage = (p.nonCityPopulation * 100.0) / p.totalPopulation;
                }

                list.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getPopulationByRegion: " + e.getMessage());
        }
        return list;
    }

    public List<PopulationReport> getPopulationByCountry()
    {
        List<PopulationReport> list = new ArrayList<>();
        try
        {
            String sql =
                    "SELECT co.Name AS Name, " +
                            "       co.Population AS TotalPopulation, " +
                            "       SUM(ci.Population) AS CityPopulation " +
                            "FROM country co " +
                            "LEFT JOIN city ci ON ci.CountryCode = co.Code " +
                            "GROUP BY co.Code, co.Name, co.Population " +
                            "ORDER BY TotalPopulation DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                PopulationReport p = new PopulationReport();
                p.name = r.getString("Name");
                p.totalPopulation = r.getLong("TotalPopulation");
                long cityPop = r.getLong("CityPopulation");
                if (r.wasNull())
                    cityPop = 0;
                p.cityPopulation = cityPop;
                p.nonCityPopulation = p.totalPopulation - p.cityPopulation;

                if (p.totalPopulation > 0)
                {
                    p.cityPercentage = (p.cityPopulation * 100.0) / p.totalPopulation;
                    p.nonCityPercentage = (p.nonCityPopulation * 100.0) / p.totalPopulation;
                }

                list.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getPopulationByCountry: " + e.getMessage());
        }
        return list;
    }

    /* ============================================================
                    SINGLE POPULATION QUERIES
       ============================================================ */

    public long getWorldPopulation()
    {
        long pop = 0;
        try
        {
            String sql = "SELECT SUM(Population) AS Pop FROM country";
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);
            if (r.next())
                pop = r.getLong("Pop");
        }
        catch (Exception e)
        {
            System.out.println("Failed getWorldPopulation: " + e.getMessage());
        }
        return pop;
    }

    public long getContinentPopulation(String continent)
    {
        long pop = 0;
        try
        {
            String sql = "SELECT SUM(Population) AS Pop FROM country WHERE Continent = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, continent);
            ResultSet r = stmt.executeQuery();
            if (r.next())
                pop = r.getLong("Pop");
        }
        catch (Exception e)
        {
            System.out.println("Failed getContinentPopulation: " + e.getMessage());
        }
        return pop;
    }

    public long getRegionPopulation(String region)
    {
        long pop = 0;
        try
        {
            String sql = "SELECT SUM(Population) AS Pop FROM country WHERE Region = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, region);
            ResultSet r = stmt.executeQuery();
            if (r.next())
                pop = r.getLong("Pop");
        }
        catch (Exception e)
        {
            System.out.println("Failed getRegionPopulation: " + e.getMessage());
        }
        return pop;
    }

    public long getCountryPopulation(String country)
    {
        long pop = 0;
        try
        {
            String sql = "SELECT Population AS Pop FROM country WHERE Name = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, country);
            ResultSet r = stmt.executeQuery();
            if (r.next())
                pop = r.getLong("Pop");
        }
        catch (Exception e)
        {
            System.out.println("Failed getCountryPopulation: " + e.getMessage());
        }
        return pop;
    }

    public long getDistrictPopulation(String district)
    {
        long pop = 0;
        try
        {
            String sql = "SELECT SUM(Population) AS Pop FROM city WHERE District = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, district);
            ResultSet r = stmt.executeQuery();
            if (r.next())
                pop = r.getLong("Pop");
        }
        catch (Exception e)
        {
            System.out.println("Failed getDistrictPopulation: " + e.getMessage());
        }
        return pop;
    }

    public long getCityPopulation(String city)
    {
        long pop = 0;
        try
        {
            String sql = "SELECT SUM(Population) AS Pop FROM city WHERE Name = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, city);
            ResultSet r = stmt.executeQuery();
            if (r.next())
                pop = r.getLong("Pop");
        }
        catch (Exception e)
        {
            System.out.println("Failed getCityPopulation: " + e.getMessage());
        }
        return pop;
    }

    /* ============================================================
                        LANGUAGE REPORT
       ============================================================ */

    public List<LanguageReport> getLanguageReports()
    {
        List<LanguageReport> list = new ArrayList<>();

        long worldPop = getWorldPopulation();
        if (worldPop == 0)
        {
            System.out.println("World population is zero or could not be retrieved.");
            return list;
        }

        try
        {
            String sql =
                    "SELECT cl.Language, " +
                            "       SUM(co.Population * cl.Percentage / 100) AS Speakers " +
                            "FROM countrylanguage cl " +
                            "JOIN country co ON co.Code = cl.CountryCode " +
                            "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                            "GROUP BY cl.Language " +
                            "ORDER BY Speakers DESC";

            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery(sql);

            while (r.next())
            {
                LanguageReport lr = new LanguageReport();
                lr.language = r.getString("Language");
                lr.speakers = r.getLong("Speakers");
                if (worldPop > 0)
                    lr.percentageOfWorld = (lr.speakers * 100.0) / worldPop;
                list.add(lr);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed getLanguageReports: " + e.getMessage());
        }

        return list;
    }


    /* ============================================================
                   MARKDOWN OUTPUT HELPERS
       ============================================================ */

    public void outputCountries(List<Country> list, String filename)
    {
        try
        {
            if (list == null)
                list = new ArrayList<>();

            File dir = new File("./reports/");
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("Warning: Could not create reports directory.");
            }

            BufferedWriter w = getBufferedWriter(list, filename);

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }


    private static BufferedWriter getBufferedWriter(List<Country> list, String filename) throws IOException {
        BufferedWriter w = new BufferedWriter(
                new FileWriter("./reports/" + filename));

        w.write("| Code | Name | Continent | Region | Population | Capital City |\n");
        w.write("| --- | --- | --- | --- | --- | --- |\n");

        for (Country c : list)
        {
            if (c == null) continue;
            w.write("| " + c.code + " | " + c.name + " | " + c.continent + " | " +
                    c.region + " | " + c.population + " | " +
                    (c.capitalCity == null ? "" : c.capitalCity) + " |\n");
        }
        return w;
    }

    public void outputCities(List<City> list, String filename)
    {
        try
        {
            if (list == null)
                list = new ArrayList<>();

            File dir = new File("./reports/");
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("Warning: Could not create reports directory.");
            }

            BufferedWriter w = new BufferedWriter(
                    new FileWriter("./reports/" + filename));

            w.write("| Name | Country | District | Region | Continent | Population |\n");
            w.write("| --- | --- | --- | --- | --- | --- |\n");

            for (City c : list)
            {
                w.write("| " + c.name + " | " + c.countryName + " | " + c.district + " | " +
                        c.region + " | " + c.continent + " | " + c.population + " |\n");
            }

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }

    public void outputCapitalCities(List<City> list, String filename)
    {
        try
        {
            if (list == null)
                list = new ArrayList<>();

            File dir = new File("./reports/");
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("Warning: Could not create reports directory.");
            }

            BufferedWriter w = new BufferedWriter(
                    new FileWriter("./reports/" + filename));

            w.write("| Name | Country | Population |\n");
            w.write("| --- | --- | --- |\n");

            for (City c : list)
            {
                w.write("| " + c.name + " | " + c.countryName + " | " + c.population + " |\n");
            }

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }

    public void outputPopulationReports(List<PopulationReport> list, String filename)
    {
        try
        {
            if (list == null)
                list = new ArrayList<>();

            File dir = new File("./reports/");
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("Warning: Could not create reports directory.");
            }

            BufferedWriter w = getWriter(list, filename);

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }

    private static BufferedWriter getWriter(List<PopulationReport> list, String filename) throws IOException {
        BufferedWriter w = new BufferedWriter(
                new FileWriter("./reports/" + filename));

        w.write("| Name | Total Population | City Population | Non-City Population | % In Cities | % Not In Cities |\n");
        w.write("| --- | --- | --- | --- | --- | --- |\n");

        for (PopulationReport p : list)
        {
            if (p == null) continue;
            w.write("| " + p.name + " | " + p.totalPopulation + " | " +
                    p.cityPopulation + " | " + p.nonCityPopulation + " | " +
                    String.format("%.2f", p.cityPercentage) + " | " +
                    String.format("%.2f", p.nonCityPercentage) + " |\n");
        }
        return w;
    }

    public void outputLanguageReport(List<LanguageReport> list, String filename)
    {
        try
        {
            if (list == null)
                list = new ArrayList<>();

            File dir = new File("./reports/");
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("Warning: Could not create reports directory.");
            }

            BufferedWriter w = new BufferedWriter(
                    new FileWriter("./reports/" + filename));

            w.write("| Language | Speakers | % of World Population |\n");
            w.write("| --- | --- | --- |\n");

            for (LanguageReport lr : list)
            {
                if (lr == null) continue;
                w.write("| " + lr.language + " | " + lr.speakers + " | " +
                        String.format("%.2f", lr.percentageOfWorld) + " |\n");
            }

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }

    public void outputSingleValueReport(String description, long value, String filename)
    {
        try
        {
            File dir = new File("./reports/");
            if (!dir.exists() && !dir.mkdirs()) {
                System.out.println("Warning: Could not create reports directory.");
            }

            BufferedWriter w = new BufferedWriter(
                    new FileWriter("./reports/" + filename));

            w.write("| Description | Value |\n");
            w.write("| --- | --- |\n");
            w.write("| " + description + " | " + value + " |\n");

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }


    /* ============================================================
                             MAIN
       ============================================================ */

    public static void main(String[] args)
    {
        App a = new App();

        if (args.length < 2)
            a.connect("localhost:33060", 30000);
        else
            a.connect(args[0], Integer.parseInt(args[1]));

        /* Parameters for testing */
        String continent = "Europe";
        String region = "Western Europe";
        String country = "United States";
        String district = "California";
        int n = 10;

/* ------------------------------------------------------------
                  COUNTRY REPORTS (6)
   ------------------------------------------------------------ */

        a.outputCountries(a.getAllCountries(),
                "req01_countries_world_all.md");

        a.outputCountries(a.getCountriesByContinent(continent),
                "req02_countries_continent_europe_all.md");

        a.outputCountries(a.getCountriesByRegion(region),
                "req03_countries_region_western_europe_all.md");

        a.outputCountries(a.getTopNCountriesWorld(n),
                "req04_countries_world_top10.md");

        a.outputCountries(a.getTopNCountriesByContinent(continent, n),
                "req05_countries_continent_europe_top10.md");

        a.outputCountries(a.getTopNCountriesByRegion(region, n),
                "req06_countries_region_western_europe_top10.md");


/* ------------------------------------------------------------
                  CITY REPORTS (10)
   ------------------------------------------------------------ */

        a.outputCities(a.getAllCities(),
                "req07_cities_world_all.md");

        a.outputCities(a.getCitiesByContinent(continent),
                "req08_cities_continent_europe_all.md");

        a.outputCities(a.getCitiesByRegion(region),
                "req09_cities_region_western_europe_all.md");

        a.outputCities(a.getCitiesByCountry(country),
                "req10_cities_country_united_states_all.md");

        a.outputCities(a.getCitiesByDistrict(district),
                "req11_cities_district_california_all.md");

        a.outputCities(a.getTopNCitiesWorld(n),
                "req12_cities_world_top10.md");

        a.outputCities(a.getTopNCitiesByContinent(continent, n),
                "req13_cities_continent_europe_top10.md");

        a.outputCities(a.getTopNCitiesByRegion(region, n),
                "req14_cities_region_western_europe_top10.md");

        a.outputCities(a.getTopNCitiesByCountry(country, n),
                "req15_cities_country_united_states_top10.md");

        a.outputCities(a.getTopNCitiesByDistrict(district, n),
                "req16_cities_district_california_top10.md");


/* ------------------------------------------------------------
                CAPITAL CITY REPORTS (6)
   ------------------------------------------------------------ */

        a.outputCapitalCities(a.getAllCapitalCities(),
                "req17_capitals_world_all.md");

        a.outputCapitalCities(a.getCapitalCitiesByContinent(continent),
                "req18_capitals_continent_europe_all.md");

        a.outputCapitalCities(a.getCapitalCitiesByRegion(region),
                "req19_capitals_region_western_europe_all.md");

        a.outputCapitalCities(a.getTopNCapitalCitiesWorld(n),
                "req20_capitals_world_top10.md");

        a.outputCapitalCities(a.getTopNCapitalCitiesByContinent(continent, n),
                "req21_capitals_continent_europe_top10.md");

        a.outputCapitalCities(a.getTopNCapitalCitiesByRegion(region, n),
                "req22_capitals_region_western_europe_top10.md");


/* ------------------------------------------------------------
            POPULATION BREAKDOWN REPORTS (3)
   ------------------------------------------------------------ */

        a.outputPopulationReports(a.getPopulationByContinent(),
                "req23_population_continent_all.md");

        a.outputPopulationReports(a.getPopulationByRegion(),
                "req24_population_region_all.md");

        a.outputPopulationReports(a.getPopulationByCountry(),
                "req25_population_country_all.md");


/* ------------------------------------------------------------
                  LANGUAGE REPORT (1)
   ------------------------------------------------------------ */

        a.outputLanguageReport(a.getLanguageReports(),
                "req26_language_report.md");


/* ------------------------------------------------------------
                    SINGLE POPULATION REPORTS (6)
   ------------------------------------------------------------ */

        a.outputSingleValueReport(
                "World population",
                a.getWorldPopulation(),
                "req27_population_world.md"
        );

        a.outputSingleValueReport(
                "Population of continent: " + continent,
                a.getContinentPopulation(continent),
                "req28_population_continent.md"
        );

        a.outputSingleValueReport(
                "Population of region: " + region,
                a.getRegionPopulation(region),
                "req29_population_region.md"
        );

        a.outputSingleValueReport(
                "Population of country: " + country,
                a.getCountryPopulation(country),
                "req30_population_country.md"
        );

        a.outputSingleValueReport(
                "Population of district: " + district,
                a.getDistrictPopulation(district),
                "req31_population_district.md"
        );

        a.outputSingleValueReport(
                "Population of city: New York",
                a.getCityPopulation("New York"),
                "req32_population_city.md"
        );

        /* END */


        a.disconnect();
    }
}
