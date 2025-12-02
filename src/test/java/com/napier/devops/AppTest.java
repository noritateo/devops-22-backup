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
        try {
            app.outputCountries(null, "Test_Null.md");
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCountries_emptyList()
    {
        try {
            List<Country> empty = new ArrayList<>();
            app.outputCountries(empty, "Test_Empty.md");
        } catch (Exception e) {
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

        String filename = "Test_Single.md";
        app.outputCountries(list, filename);

        File file = new File("./reports/" + filename);
        assertTrue("Report file should exist", file.exists());
    }

    /* ------------------------------------------------------------
       getAllCountries() Basic Test
       ------------------------------------------------------------ */

    @Test
    public void getAllCountries_returnsList()
    {
        List<Country> list = app.getAllCountries();
        assertNotNull("List should never be null", list);
    }

    /* ------------------------------------------------------------
       outputCities() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputCities_nullList()
    {
        try {
            app.outputCities(null, "Cities_Null.md");
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCities_emptyList()
    {
        try {
            List<City> empty = new ArrayList<>();
            app.outputCities(empty, "Cities_Empty.md");
        } catch (Exception e) {
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

        String filename = "Cities_Single.md";
        app.outputCities(list, filename);

        File f = new File("./reports/" + filename);
        assertTrue("City report should exist", f.exists());
    }

    /* ------------------------------------------------------------
       outputCapitalCities() Tests
       ------------------------------------------------------------ */

    @Test
    public void outputCapitalCities_nullList()
    {
        try {
            app.outputCapitalCities(null, "Capitals_Null.md");
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void outputCapitalCities_emptyList()
    {
        try {
            List<City> empty = new ArrayList<>();
            app.outputCapitalCities(empty, "Capitals_Empty.md");
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }
}
