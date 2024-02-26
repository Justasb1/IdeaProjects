package org.example;

import java.sql.*;
import java.util.ArrayList;

public class AirportDAO {
    //jdbc -java data base connection                             //airport-duomenu bazes pavadinimas
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

    public static void updateById(Airport airport){
        query = "UPDATE sb_airports SET biz_name = ?, address = ?, city = ? WHERE biz_id = ?;";
        try{
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getBizName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.setInt(4, airport.getBizId());

            preparedStatement.executeUpdate();
            System.out.println("Pavyko atnaujinti irasa, kurio biz_id = " + airport.getBizId());
            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            System.out.println("Ivyko klaida atnaujinant irasa. Error message: " +  e.getMessage());
        }
    }

    public static void deleteById(int bizId){
        query = "DELETE FROM sb_airports WHERE biz_id = ?;";
        try{
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bizId);

            preparedStatement.executeUpdate();
            System.out.println("Pavyko istrinti irasa, kurio biz_id = " + bizId);
            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            System.out.println("Ivyko klaida trinant irasa. Error message: " +  e.getMessage());
        }
    }

    public static ArrayList<Airport> searchById(int bizId){
        query = "SELECT * FROM sb_airports WHERE biz_id = ?;";
        ArrayList<Airport> airportsArray = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bizId);

            //resultset yra tarpinis sarasas tarp kodo ir duomenu bazes
            //atitiktu javoje map kolekcija(raktas-reiksme), galetu atitikti json(raktas-reiksme)
            //raktas resultsete atitinka db lenteles stulpelio pavadinima, o reiksme-irasa stulpelyje
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Pavyko rasti irasa, kurio biz_id = " + bizId);

            //ArrayList guminis masyvas
            //saraso klases pavadinimas <klases pavadinimas kurios objektus desime i sarasa> pavadinimas saraso = new ArrayList<>()

            while (resultSet.next()){
                Airport airport = new Airport(
                        //pagal rakta(stulpelio pav) istraukiama reiksme
                        resultSet.getInt("biz_id"),
                        resultSet.getString("biz_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city")
                );
                //pridedam i sarasa su add
                airportsArray.add(airport);
//                System.out.println("Rastas irasas: " + airport);
            }
//            System.out.println(airportsArray.get(0));
//            System.out.println(airportsArray);


            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            System.out.println("Ivyko klaida randant irasa. Error message: " +  e.getMessage());
        }
        return airportsArray;
    }

}
