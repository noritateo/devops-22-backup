package com.napier.devops;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest
{
    static App app;

    @BeforeClass
    public static void init()
    {
        app = new App();
    }

    /* ------------------------------------------------------------
       outputCountries() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputCountries_nullList()
    {
        try
        {
            app.outputCountries(null, "Test_Countries_Null.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCountries_emptyList()
    {
        try
        {
            List<Country> empty = new ArrayList<>();
            app.outputCountries(empty, "Test_Countries_Empty.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCountries_singleCountry()
    {
        List<Country> list = new ArrayList<>();

        Country c = new Country();
        c.code = "SGP";
        c.name = "Singapore";
        c.continent = "Asia";
        c.region = "Southeast Asia";
        c.population = 5703600;
        c.capitalCity = "Singapore";

        list.add(c);

        String filename = "Test_Countries_Single.md";
        app.outputCountries(list, filename);

        File file = new File("./reports/" + filename);
        assertTrue("Country report file should exist", file.exists());
    }

    /* ------------------------------------------------------------
       outputCities() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputCities_nullList()
    {
        try
        {
            app.outputCities(null, "Test_Cities_Null.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCities_emptyList()
    {
        try
        {
            List<City> empty = new ArrayList<>();
            app.outputCities(empty, "Test_Cities_Empty.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCities_singleCity()
    {
        List<City> list = new ArrayList<>();

        City city = new City(
                "Singapore",
                "Singapore",
                "Central",
                "Southeast Asia",
                "Asia",
                5703600
        );
        list.add(city);

        String filename = "Test_Cities_Single.md";
        app.outputCities(list, filename);

        File f = new File("./reports/" + filename);
        assertTrue("City report file should exist", f.exists());
    }

    /* ------------------------------------------------------------
       outputCapitalCities() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputCapitalCities_nullList()
    {
        try
        {
            app.outputCapitalCities(null, "Test_Capitals_Null.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCapitalCities_emptyList()
    {
        try
        {
            List<City> empty = new ArrayList<>();
            app.outputCapitalCities(empty, "Test_Capitals_Empty.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCapitalCities_singleCity()
    {
        List<City> list = new ArrayList<>();

        City capital = new City(
                "Singapore",
                "Singapore",
                "Central",
                "Southeast Asia",
                "Asia",
                5703600
        );
        list.add(capital);

        String filename = "Test_Capitals_Single.md";
        app.outputCapitalCities(list, filename);

        File f = new File("./reports/" + filename);
        assertTrue("Capital city report file should exist", f.exists());
    }

    /* ------------------------------------------------------------
       outputPopulationReports() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputPopulationReports_nullList()
    {
        try
        {
            app.outputPopulationReports(null, "Test_Population_Null.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputPopulationReports_singleEntry()
    {
        List<PopulationReport> list = new ArrayList<>();

        PopulationReport p = new PopulationReport();
        p.name = "Asia";
        p.totalPopulation = 1000;
        p.cityPopulation = 600;
        p.nonCityPopulation = 400;
        p.cityPercentage = 60.0;
        p.nonCityPercentage = 40.0;
        list.add(p);

        String filename = "Test_Population_Single.md";
        app.outputPopulationReports(list, filename);

        File f = new File("./reports/" + filename);
        assertTrue("Population report file should exist", f.exists());
    }

    /* ------------------------------------------------------------
       outputLanguageReport() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputLanguageReport_nullList()
    {
        try
        {
            app.outputLanguageReport(null, "Test_Language_Null.md");
        }
        catch (Exception e)
        {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputLanguageReport_singleLanguage()
    {
        List<LanguageReport> list = new ArrayList<>();

        LanguageReport lr = new LanguageReport();
        lr.language = "English";
        lr.speakers = 500000000;
        lr.percentageOfWorld = 8.5;
        list.add(lr);

        String filename = "Test_Language_Single.md";
        app.outputLanguageReport(list, filename);

        File f = new File("./reports/" + filename);
        assertTrue("Language report file should exist", f.exists());
    }
}
