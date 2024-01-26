# LA City Database System

## Overview

This project implements a City Database System in Java, providing functionality to manage information about cities, including their names, latitude, longitude, and population. The system supports operations such as displaying city information, searching for cities, inserting new cities, deleting cities, updating population, calculating the distance between two cities, and finding nearby cities. The data is stored in a Binary Search Tree (BST), with separate trees for normal ordering and ordering based on population.

## Usage

### Compilation and Execution

To compile and execute the program, follow these steps:

```bash
javac csc5.java
java csc5
```

Make sure the "LA city.txt" file is present in the same directory, containing information about various cities.

### Menu Options

The program presents a menu with the following options:

1. Display the information of all the cities
   - Preorder, Inorder, and postorder of the cities in terms of their names
   - Preorder, Inorder, and postorder of the cities in terms of their populations

2. Search a city
   - Search by Name
   - Search by Population range

3. Insert a city

4. Delete a city

5. Update population of a city

6. Distance between 2 cities

7. Find nearby cities

8. Exit the City Database

## Data Structure

The city information is organized using a Binary Search Tree (BST), providing efficient search, insertion, and deletion operations. Two separate BSTs are used for normal ordering and ordering based on population.

## Additional Notes

- The program reads city information from the "LA city.txt" file.
- The distance between two cities is calculated using the Haversine formula.
- The menu-driven interface allows users to interact with the database efficiently.