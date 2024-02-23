public class Student {
    private String vardas;
    private String grupe;
    private double vidurkis;
    private String mokykla;
    private int id;
    private String data;

//Reikalingas naudojant search, edit, delete
    public Student(String vardas, String grupe, double vidurkis, String mokykla, int id, String data) {
        this.vardas = vardas;
        this.grupe = grupe;
        this.vidurkis = vidurkis;
        this.mokykla = mokykla;
        this.id = id;
        this.data = data;
    }
//Naujo iraso kurimui
    public Student(String vardas, String grupe, double vidurkis, String mokykla) {
        this.vardas = vardas;
        this.grupe = grupe;
        this.vidurkis = vidurkis;
        this.mokykla = mokykla;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getGrupe() {
        return grupe;
    }

    public void setGrupe(String grupe) {
        this.grupe = grupe;
    }

    public double getVidurkis() {
        return vidurkis;
    }

    public void setVidurkis(double vidurkis) {
        this.vidurkis = vidurkis;
    }

    public String getMokykla() {
        return mokykla;
    }

    public void setMokykla(String mokykla) {
        this.mokykla = mokykla;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Student{" +
                "vardas='" + vardas + '\'' +
                ", grupe='" + grupe + '\'' +
                ", vidurkis=" + vidurkis +
                ", mokykla='" + mokykla + '\'' +
                ", id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
