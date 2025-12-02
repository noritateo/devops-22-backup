# SET09803: DevOps Coursework (Group 22)

## CI/CD & Project Badges

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

| ID | Requirement | Met | Screenshot |
|----|------------|-----|------------|
| 1 | All countries in the world by population | Yes | |
| 2 | All countries in a continent by population | Yes | |
| 3 | All countries in a region by population | Yes | |
| 4 | Top N countries in the world | Yes | |
| 5 | Top N countries by continent | Yes | |
| 6 | Top N countries by region | Yes | |
| 7 | All cities in the world | Yes | |
| 8 | All cities in a continent | Yes | |
| 9 | All cities in a region | Yes | |
| 10 | All cities in a country | Yes | |
| 11 | All cities in a district | Yes | |
| 12 | Top N cities in the world | Yes | |
| 13 | Top N cities in a continent | Yes | |
| 14 | Top N cities in a region | Yes | |
| 15 | Top N cities in a country | Yes | |
| 16 | Top N cities in a district | Yes | |
| 17 | All capital cities | Yes | |
| 18 | All capital cities in a continent | Yes | |
| 19 | All capital cities in a region | Yes | |
| 20 | Top N capital cities in the world | Yes | |
| 21 | Top N capital cities in a continent | Yes | |
| 22 | Top N capital cities in a region | Yes | |
| 23 | Population of each continent (city vs non-city) | Yes | |
| 24 | Population of each region | Yes | |
| 25 | Population of each country | Yes | |
| 26 | Population of the world | Yes | |
| 27 | Population of a continent | Yes | |
| 28 | Population of a region | Yes | |
| 29 | Population of a country | Yes | |
| 30 | Population of a district | Yes | |
| 31 | Population of a city | Yes | |
| 32 | Language speakers report | Yes | |

---

# Final Delivery Checklist

- [x] Repository link submitted
- [x] Badges correctly displayed
- [x] CI running successfully
- [x] Code coverage integrated
- [x] All 32 requirements completed
- [x] Requirements table with screenshots (to be inserted)

