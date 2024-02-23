import org.example.Student;
import org.example.StudentDAO;
import org.example.StudentDAOI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockTest {
    Student studentBeforeDB;
    StudentDAO studentDAO;

    // Mockito bibliotekos pagalba sukuriamas objektas imituojantis duomenu bazes objekta
    @Mock
    StudentDAOI studentDAOI; //imitacinis objektas

    // Apjungiamas Mockito su JUnit
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp(){
        // Duomenu bazes klases konstruktoriui perduodamas imituojamas objektas
        studentDAO = new StudentDAO(studentDAOI);
        //int id, String vardas, String grupe, double vidurkis, String mokykla, String data
        studentBeforeDB = new Student(1, "Jonas", "T23",8.5, "KITM", "2024-02-22");
    }

    @Test
    public void searchByIDTest(){
        Student studentAfterDB;
        // studentas paimtas is DB
        studentAfterDB = new Student(1, "Jonas", "T23",8.5, "KITM", "2024-02-22");
        // Kai kreipsimes i imituojanti objekta (tai bus daroma duomenu bazes klases viduje - searchById metode)
        // Grazink sio objekto imitacija
        when(studentDAOI.searchById(1)).thenReturn(studentAfterDB);
        // Palyginame ar studentu objektai lygus (! NElyginti objekto su objektu - lyginti objekto pozymius)
        // Kreipiames i duomenu bazes objekta (ne imitacija)
        assertStudents(studentBeforeDB, studentDAO.searchById(1));

        // Nera privaloma, bet galima patikrinti ar buvo kreiptasi i imituojanti duomenu baze metoda
        // Gerai tuo atveju, jeigu uzmirsime kreiptis i imitacija duomenu bazes viduje
        verify(studentDAOI).searchById(1);
    }
    private void assertStudents(Student expected, Student actual){
        Assert.assertEquals(expected.getVardas(), actual.getVardas());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getVidurkis(), actual.getVidurkis(), 0); //double + delta parametras 0
    }
}
