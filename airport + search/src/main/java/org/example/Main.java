package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("mawen + jdbc");
        Airport airportVilnius = new Airport("Vilniaus oro uostas", "Naujininkai g. 15", "Vilnius");
        Airport airportKaunas = new Airport(2, "Palangos oro uostas", "Juros g. 30", "Palanga");
//        AirportDAO.insert(airportVilnius);
//        AirportDAO.insert(airportKaunas);
   //     AirportDAO.updateById(airportKaunas);
        ArrayList<Airport> airports= AirportDAO.searchById(33);
        if (airports.isEmpty()){
            System.out.println("Nerastas irasas");
        } else {
            System.out.println(airports);
        }
     //   AirportDAO.deleteById(2);

    }
}