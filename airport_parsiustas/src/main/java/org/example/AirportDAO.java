package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirportDAO {
    //jdbc -java data base connection
    private static final String URL = "jdbc:mysql://localhost:3306/airport";
    private static String query;

    public static void insert(Airport airport){
        query = "INSERT INTO sb_airports (biz_name, address, city) VALUES (?,?,?);";
        try {
            //1.sukuriamas prisijungimas prie duomenu bazes
            Connection connection = DriverManager.getConnection(URL, "root", "");
            //2.prisijungimui perduodama uzklausa
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //klaustukai ? naudojami isvengiant sql injekciju
            preparedStatement.setString(1, airport.getBizName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            //3.ivikgdoma uzklausa. executeUpdate metodas naudojamas sukuriant nauja irasa duomenu bazeje, redaguojant/trinant esama db irasa.
            //executeQuery metodas naudojamas gaunant irasa(us) is db. Irasu paieskai.
            preparedStatement.executeUpdate();
            System.out.println("Pavyko sukurti nauja irasa");

            //geroji praktika, atlikus uzklausas uzdaryti prisijungimus prie duomenu bazes
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ivyko klaida kuriant nauja irasa. Placiau: " + e.getMessage());
        }
    }

}
