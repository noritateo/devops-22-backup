package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App
{
    private Connection con = null;

    // -------------------------------------------------------------------------
    // DB CONNECTION
    // -------------------------------------------------------------------------
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
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // -------------------------------------------------------------------------
    // QUERIES
    // -------------------------------------------------------------------------

    /** All countries ordered by population (desc). */
    public List<Country> getAllCountries()
    {
        List<Country> countries = new ArrayList<>();

        if (con == null)
        {
            System.out.println("No database connection. Returning empty country list.");
            return countries;
        }

        try
        {
            Statement stmt = con.createStatement();
            String query =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, " +
                            "       ci.Name AS CapitalCity " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC";

            ResultSet rset = stmt.executeQuery(query);

            while (rset.next())
            {
                Country c = new Country();
                c.code       = rset.getString("Code");
                c.name       = rset.getString("Name");
                c.continent  = rset.getString("Continent");
                c.region     = rset.getString("Region");
                c.population = rset.getInt("Population");
                c.capitalCity = rset.getString("CapitalCity");
                countries.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed to get country details");
            System.out.println(e.getMessage());
        }

        return countries;
    }

    /** All cities in the world ordered by population (desc). */
    public List<City> getAllCities()
    {
        List<City> cities = new ArrayList<>();

        if (con == null)
        {
            System.out.println("No database connection. Returning empty city list.");
            return cities;
        }

        try
        {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, " +
                            "       co.Name AS CountryName, " +
                            "       ci.District, " +
                            "       co.Region, " +
                            "       co.Continent, " +
                            "       ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.CountryCode = co.Code " +
                            "ORDER BY ci.Population DESC";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next())
            {
                City city = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                cities.add(city);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to get city details");
            System.out.println(e.getMessage());
        }

        return cities;
    }

    /** All capital cities ordered by population (desc). */
    public List<City> getAllCapitalCities()
    {
        List<City> capitals = new ArrayList<>();

        if (con == null)
        {
            System.out.println("No database connection. Returning empty capital city list.");
            return capitals;
        }

        try
        {
            Statement stmt = con.createStatement();
            String sql =
                    "SELECT ci.Name AS CityName, " +
                            "       co.Name AS CountryName, " +
                            "       ci.District, " +
                            "       co.Region, " +
                            "       co.Continent, " +
                            "       ci.Population " +
                            "FROM city ci " +
                            "JOIN country co ON ci.ID = co.Capital " +
                            "ORDER BY ci.Population DESC";

            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next())
            {
                City capitalCity = new City(
                        resultSet.getString("CityName"),
                        resultSet.getString("CountryName"),
                        resultSet.getString("District"),
                        resultSet.getString("Region"),
                        resultSet.getString("Continent"),
                        resultSet.getInt("Population")
                );
                capitals.add(capitalCity);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to get capital city details");
            System.out.println(e.getMessage());
        }

        return capitals;
    }

    // -------------------------------------------------------------------------
    // DISPLAY HELPERS (replace old Display class)
    // -------------------------------------------------------------------------

    public void displayAllCountries(List<Country> countries)
    {
        if (countries == null || countries.isEmpty())
        {
            System.out.println("No countries found.");
            return;
        }

        System.out.printf(
                "%-5s %-55s %-20s %-25s %-15s %-25s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital City");

        for (Country c : countries)
        {
            System.out.printf(
                    "%-5s %-55s %-20s %-25s %-15d %-25s%n",
                    c.code, c.name, c.continent, c.region, c.population, c.capitalCity);
        }
    }

    public void displayAllCities(List<City> cities)
    {
        if (cities == null || cities.isEmpty())
        {
            System.out.println("No cities found.");
            return;
        }

        System.out.printf(
                "%-35s %-40s %-25s %-25s %-15s %-15s%n",
                "City", "Country", "District", "Region", "Continent", "Population");

        for (City ci : cities)
        {
            System.out.printf(
                    "%-35s %-40s %-25s %-25s %-15s %-15d%n",
                    ci.name, ci.countryName, ci.district, ci.region, ci.continent, ci.population);
        }
    }

    // -------------------------------------------------------------------------
    // MAIN
    // -------------------------------------------------------------------------

    public static void main(String[] args)
    {
        App a = new App();

        // Default for local debugging (Docker mapping to 33060)
        if (args.length < 2)
        {
            a.connect("localhost:33060", 30000);
        }
        else
        {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // 1. All countries
        List<Country> countries = a.getAllCountries();
        a.displayAllCountries(countries);
        System.out.println();

        // 2. All cities
        List<City> cities = a.getAllCities();
        a.displayAllCities(cities);
        System.out.println();

        // 3. All capital cities
        List<City> capitalCities = a.getAllCapitalCities();
        a.displayAllCities(capitalCities);
        System.out.println();

        a.disconnect();
    }
}
