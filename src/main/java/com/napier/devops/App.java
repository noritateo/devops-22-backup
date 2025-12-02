package com.napier.devops;

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
                   MARKDOWN OUTPUT HELPERS
       ============================================================ */

    public void outputCountries(List<Country> list, String filename)
    {
        try
        {
            new File("./reports/").mkdirs();

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

            w.close();
            System.out.println("Created report: " + filename);
        }
        catch (Exception e)
        {
            System.out.println("Failed writing " + filename);
        }
    }

    public void outputCities(List<City> list, String filename)
    {
        try
        {
            new File("./reports/").mkdirs();

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
            new File("./reports/").mkdirs();

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
        String continent = "Asia";
        String region = "Eastern Asia";
        String country = "China";
        String district = "New York";
        int n = 10;

        /* ------------------------------------------------------------
                          COUNTRY REPORTS (6)
           ------------------------------------------------------------ */

        a.outputCountries(a.getAllCountries(),
                "Countries_World_All.md");

        a.outputCountries(a.getCountriesByContinent(continent),
                "Countries_Continent_" + continent + "_All.md");

        a.outputCountries(a.getCountriesByRegion(region),
                "Countries_Region_" + region + "_All.md");

        a.outputCountries(a.getTopNCountriesWorld(n),
                "Countries_World_Top_" + n + ".md");

        a.outputCountries(a.getTopNCountriesByContinent(continent, n),
                "Countries_Continent_" + continent + "_Top_" + n + ".md");

        a.outputCountries(a.getTopNCountriesByRegion(region, n),
                "Countries_Region_" + region + "_Top_" + n + ".md");


        /* ------------------------------------------------------------
                          CITY REPORTS (10)
           ------------------------------------------------------------ */

        a.outputCities(a.getAllCities(),
                "Cities_World_All.md");

        a.outputCities(a.getCitiesByContinent(continent),
                "Cities_Continent_" + continent + "_All.md");

        a.outputCities(a.getCitiesByRegion(region),
                "Cities_Region_" + region + "_All.md");

        a.outputCities(a.getCitiesByCountry(country),
                "Cities_Country_" + country + "_All.md");

        a.outputCities(a.getCitiesByDistrict(district),
                "Cities_District_" + district + "_All.md");

        a.outputCities(a.getTopNCitiesWorld(n),
                "Cities_World_Top_" + n + ".md");

        a.outputCities(a.getTopNCitiesByContinent(continent, n),
                "Cities_Continent_" + continent + "_Top_" + n + ".md");

        a.outputCities(a.getTopNCitiesByRegion(region, n),
                "Cities_Region_" + region + "_Top_" + n + ".md");

        a.outputCities(a.getTopNCitiesByCountry(country, n),
                "Cities_Country_" + country + "_Top_" + n + ".md");

        a.outputCities(a.getTopNCitiesByDistrict(district, n),
                "Cities_District_" + district + "_Top_" + n + ".md");


        /* ------------------------------------------------------------
                        CAPITAL CITY REPORTS (6)
           ------------------------------------------------------------ */

        a.outputCapitalCities(a.getAllCapitalCities(),
                "CapitalCities_World_All.md");

        a.outputCapitalCities(a.getCapitalCitiesByContinent(continent),
                "CapitalCities_Continent_" + continent + "_All.md");

        a.outputCapitalCities(a.getCapitalCitiesByRegion(region),
                "CapitalCities_Region_" + region + "_All.md");

        a.outputCapitalCities(a.getTopNCapitalCitiesWorld(n),
                "CapitalCities_World_Top_" + n + ".md");

        a.outputCapitalCities(a.getTopNCapitalCitiesByContinent(continent, n),
                "CapitalCities_Continent_" + continent + "_Top_" + n + ".md");

        a.outputCapitalCities(a.getTopNCapitalCitiesByRegion(region, n),
                "CapitalCities_Region_" + region + "_Top_" + n + ".md");


        /* END */
        a.disconnect();
    }
}
