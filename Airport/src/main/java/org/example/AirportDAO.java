package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirportDAO {
    //jdbc = java data base connection
    private static final String URL = "jdbc:mysql://localhost:3306/db"; //3306 mysql port
    private static String query;

    public static void insert(Airport airport) {
        //INSERT INTO sb_airports VALUES(0, "Karmelavos oro uostas", "karmelavos g.21", "Karmelava");
        query = "INSERT INTO sb_airports (biz_name, address, city) VALUES (?,?,?);";
        try {
            //1.Sukuriamas prisijungimas prie duomenu bazes
            Connection connection = DriverManager.getConnection(URL, "root", "");
            //2. Prisjungimui perduodama uzklausa
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //klaustukai ? naudojami vengiant isvengiant sql injekciju
            preparedStatement.setString(1, airport.getBizName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            //3. Ivykdoma uzklausa. executeUpdate naudojamas sukuriant nauja irasa duomenu bazeje. Redaguojant ir trinant esama irasa db.
            // executeQuery metodas naudojamas gaunant irasa(us) is db. (Irasu paieskai)
            preparedStatement.executeUpdate();
            System.out.println("Pavyko sukurti nauja irasa");

            // Geroji praktika atlikus uzklausas uzdaryti prisijungimus prie duomenu bazes.
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ivyko klaida kuriant nauja iraso. Placiau:" + e.getMessage());
        }
    }
}