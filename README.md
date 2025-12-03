# SET09803: DevOps Coursework (Group 22)

## Project Badges

### Main Workflow
![Main Workflow](https://github.com/noritateo/devops-22-backup/actions/workflows/main.yml/badge.svg)

### License
[![License](https://img.shields.io/github/license/noritateo/devops-22-backup.svg?style=flat-square)](LICENSE)

### Latest Release
[![Release](https://img.shields.io/github/release/noritateo/devops-22-backup/all.svg?style=flat-square)](https://github.com/noritateo/devops-22-backup/releases)

---

## Build Status by Branch

### master Branch
![Master Build](https://img.shields.io/github/actions/workflow/status/noritateo/devops-22-backup/main.yml?branch=master&style=flat-square)

### develop Branch
![Develop Build](https://img.shields.io/github/actions/workflow/status/noritateo/devops-22-backup/main.yml?branch=develop&style=flat-square)

---

## Code Coverage (Codecov)

### master Branch
[![codecov](https://codecov.io/github/noritateo/devops-22-backup/branch/master/graph/badge.svg?token=B1Z2U9KM6X)](https://codecov.io/github/noritateo/devops-22-backup)

### develop Branch
[![codecov](https://codecov.io/github/noritateo/devops-22-backup/branch/develop/graph/badge.svg?token=B1Z2U9KM6X)](https://codecov.io/github/noritateo/devops-22-backup)

---

# Project Overview

This project is a DevOps-based Java application that generates population and geographic reports using the MySQL World Database.

It includes full implementation of all 32 coursework requirements, generating Markdown reports for:

- Countries
- Cities
- Capital cities
- Top-N population lists
- Population breakdowns (continent, region, country)
- Single population queries
- Language population statistics

Continuous integration, unit tests, integration tests, and code coverage ensure reliability and correctness.

---

# Database Schema (World DB)

The application uses the standard `world.sql` dataset containing:

- `country`
- `city`
- `countrylanguage`

### ERD Diagram
![ERD Diagram](imgs/db_structure.png)

---

# Coursework Requirement Completion

There are 32 requirements in total.

**32 out of 32 requirements implemented (100%).**

---

## Requirements Table

| ID | Requirement                                                                                                      | Met | Screenshot                                           |
|----|------------------------------------------------------------------------------------------------------------------|-----|------------------------------------------------------|
| 1  | All the countries in the world organised by largest population to smallest.                                      | Yes | <img src="imgs/req01_countries_world_all.png" width="300"/> |
| 2  | All the countries in a continent organised by largest population to smallest.                                    | Yes | <img src="imgs/req02_countries_continent_europe_all.png" width="300"/> |
| 3  | All the countries in a region organised by largest population to smallest.                                       | Yes | <img src="imgs/req03_countries_region_western_europe_all.png" width="300"/> |
| 4  | The top N populated countries in the world where N is provided by the user.                                      | Yes | <img src="imgs/req04_countries_world_top10.png" width="300"/> |
| 5  | The top N populated countries in a continent where N is provided by the user.                                    | Yes | <img src="imgs/req05_countries_continent_europe_top10.png" width="300"/> |
| 6  | The top N populated countries in a region where N is provided by the user.                                       | Yes | <img src="imgs/req06_countries_region_western_europe_top10.png" width="300"/> |
| 7  | All the cities in the world organised by largest population to smallest.                                         | Yes | <img src="imgs/req07_cities_world_all.png" width="300"/> |
| 8  | All the cities in a continent organised by largest population to smallest.                                       | Yes | <img src="imgs/req08_cities_continent_europe_all.png" width="300"/> |
| 9  | All the cities in a region organised by largest population to smallest.                                          | Yes | <img src="imgs/req09_cities_region_western_europe_all.png" width="300"/> |
| 10 | All the cities in a country organised by largest population to smallest.                                         | Yes | <img src="imgs/req10_cities_country_united_states_all.png" width="300"/> |
| 11 | All the cities in a district organised by largest population to smallest.                                        | Yes | <img src="imgs/req11_cities_district_california_all.png" width="300"/> |
| 12 | The top N populated cities in the world where N is provided by the user.                                         | Yes | <img src="imgs/req12_cities_world_top10.png" width="300"/> |
| 13 | The top N populated cities in a continent where N is provided by the user.                                       | Yes | <img src="imgs/req13_cities_continent_europe_top10.png" width="300"/> |
| 14 | The top N populated cities in a region where N is provided by the user.                                          | Yes | <img src="imgs/req14_cities_region_western_europe_top10.png" width="300"/> |
| 15 | The top N populated cities in a country where N is provided by the user.                                         | Yes | <img src="imgs/req15_cities_country_united_states_top10.png" width="300"/> |
| 16 | The top N populated cities in a district where N is provided by the user.                                        | Yes | <img src="imgs/req16_cities_district_california_top10.png" width="300"/> |
| 17 | All the capital cities in the world organised by largest population to smallest.                                 | Yes | <img src="imgs/req17_capitals_world_all.png" width="300"/> |
| 18 | All the capital cities in a continent organised by largest population to smallest.                               | Yes | <img src="imgs/req18_capitals_continent_europe_all.png" width="300"/> |
| 19 | All the capital cities in a region organised by largest to smallest.                                             | Yes | <img src="imgs/req19_capitals_region_western_europe_all.png" width="300"/> |
| 20 | The top N populated capital cities in the world where N is provided by the user.                                 | Yes | <img src="imgs/req20_capitals_world_top10.png" width="300"/> |
| 21 | The top N populated capital cities in a continent where N is provided by the user.                               | Yes | <img src="imgs/req21_capitals_continent_europe_top10.png" width="300"/> |
| 22 | The top N populated capital cities in a region where N is provided by the user.                                  | Yes | <img src="imgs/req22_capitals_region_western_europe_top10.png" width="300"/> |
| 23 | Population, city population, and non-city population in each continent.                                          | Yes | <img src="imgs/req23_population_continent_all.png" width="300"/> |
| 24 | Population, city population, and non-city population in each region.                                             | Yes | <img src="imgs/req24_population_region_all.png" width="300"/> |
| 25 | Population, city population, and non-city population in each country.                                            | Yes | <img src="imgs/req25_population_country_all.png" width="300"/> |
| 26 | Speakers of Chinese, English, Hindi, Spanish, Arabic + % of world population.                                    | Yes | <img src="imgs/req26_language_report.png" width="300"/> |
| 27 | The population of the world.                                                                                      | Yes | <img src="imgs/req27_population_world.png" width="300"/> |
| 28 | The population of a continent.                                                                                    | Yes | <img src="imgs/req28_population_continent.png" width="300"/> |
| 29 | The population of a region.                                                                                       | Yes | <img src="imgs/req29_population_region.png" width="300"/> |
| 30 | The population of a country.                                                                                      | Yes | <img src="imgs/req30_population_country.png" width="300"/> |
| 31 | The population of a district.                                                                                     | Yes | <img src="imgs/req31_population_district.png" width="300"/> |
| 32 | The population of a city.                                                                                         | Yes | <img src="imgs/req32_population_city.png" width="300"/> |

---
