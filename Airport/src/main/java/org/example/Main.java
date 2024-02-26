package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Maven + JDBC");
        Airport airportVilniius = new Airport("Vilniaus oro uostas", "Naujininkai g. 15", "Vilnius");
        Airport airportKaunas = new Airport(2,"Palangos oro uostas", "Juros g. 30", "Palanga");
        AirportDAO.insert(airportKaunas);
        AirportDAO.insert(airportVilniius);
        AirportDAO.updateById(airportKaunas);
        ArrayList<Airport> airports = AirportDAO.searchById
    }
}