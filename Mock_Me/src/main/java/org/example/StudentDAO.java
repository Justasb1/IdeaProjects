import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    public static void insert(Student student) {
        String query = "INSERT INTO students (name,school,group2,average2) VALUES (?,?,?,?)";


        String url = "jdbc:mysql://localhost:3306/db";

        try {
            Connection jungtis = DriverManager.getConnection(url, "root", "");
            PreparedStatement st = jungtis.prepareStatement(query);
            st.setString(1, student.getVardas());
            st.setString(2, student.getMokykla());
            st.setString(3, student.getGrupe());
            st.setDouble(4, student.getVidurkis());

            st.executeUpdate();
            st.close();  // uzdaroma prisijungimas prie db


            System.out.println("Duomenys iterpti sekmingai");
        } catch (SQLException e) {
            System.out.println("Total FAIL");
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> searchById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";

        String url = "jdbc:mysql://localhost:3306/db";
        ArrayList<Student>studentai=new ArrayList<Student>();

        try {
            Connection jungtis = DriverManager.getConnection(url, "root", "");
            PreparedStatement st = jungtis.prepareStatement(query);
            st.setInt(1, id);

            ResultSet rezultatuRinkinys = st.executeQuery();
            while (rezultatuRinkinys.next()){
                //String vardas, String grupe, double vidurkis, String mokykla, int id, String data)
               studentai.add(new Student(
                       rezultatuRinkinys.getString("name"),
                       rezultatuRinkinys.getString("group2"),
                       rezultatuRinkinys.getDouble("average2"),
                       rezultatuRinkinys.getString("school"),
                       rezultatuRinkinys.getInt("id"),
                       rezultatuRinkinys.getString("data")
               ));
            }
            st.close();  // uzdaroma prisijungimas prie db

            System.out.println("Paieska pagal ID ivykdyta sekmingai");
        } catch (SQLException e) {
            System.out.println("Paieska nesekminga");
            e.printStackTrace();
        }
        return studentai;
    }

    public static void update(Student student) {

        String query = "Update students  SET name=?,school=?,group2=?,average2=? WHERE id=?";
        String url = "jdbc:mysql://localhost:3306/db";

        try {
            Connection jungtis = DriverManager.getConnection(url, "root", "");
            PreparedStatement st = jungtis.prepareStatement(query);
            st.setString(1, student.getVardas());
            st.setString(2, student.getMokykla());
            st.setString(3, student.getGrupe());
            st.setDouble(4, student.getVidurkis());
            st.setInt(5,student.getId());

            st.executeUpdate();
            st.close();  // uzdaroma prisijungimas prie db

            System.out.println("Duomenys readaguoti sekmingai");
        } catch (SQLException e) {
            System.out.println("Duomenu readagavimas nepavyko");
            e.printStackTrace();
        }
    }

    public static void delete(int id) {

        String query = "DELETE FROM students WHERE id=?";
        String url = "jdbc:mysql://localhost:3306/db";

        try {
            Connection jungtis = DriverManager.getConnection(url, "root", "");
            PreparedStatement st = jungtis.prepareStatement(query);
            st.setInt(1, id);

            st.executeUpdate();
            st.close();  // uzdaroma prisijungimas prie db

            System.out.println("Duomenys istrinti sekmingai");
        } catch (SQLException e) {
            System.out.println("Duomenu istrinti nepavyko");
            e.printStackTrace();
        }
    }
}
