package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Maven + JDBC");
        Airport airportKaunas = new Airport("Vilniaus oro uostas", "Naujininkai g. 15", "Vilnius");
        AirportDAO.insert(airportKaunas);
    }
}