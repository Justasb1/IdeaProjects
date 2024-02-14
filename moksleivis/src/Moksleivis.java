public class Moksleivis {
    // 1.0 Pirma dalis klases kintamieji
    private int id; // pasidarom private int moksleivio id
    private String vardas; // pasidarom private string vardui
    private String pavarde; // pasidarom private string pavardei
    private String grupe; // pasidarom private string grupei

    // 2. Konstruktorius/iai
    public Moksleivis(){ // konstruktorius pagal nutylejima / konstruktorius be parametru

    }
    public Moksleivis(int id, String vardas, String pavarde, String grupe) {
     this.id = id;
     this.vardas = vardas;
     this.pavarde = pavarde;
     this.grupe = grupe;
    }

    // 3. Getteriai / setteriai

    public int getId(){
        return id;
    }

    public void setVardas(String vardas){ // keicia reiksme , nieko negrazina
        this.vardas = vardas;
    }

    public String getVardas(){
        return vardas;
    }

    public void setPavarde(String pavarde){ // keicia reiksme , nieko negrazina
        this.pavarde = pavarde;
    }

    public String getPavarde(){
        return pavarde;
    }

    public void setGrupe(String grupe){ // keicia reiksme , nieko negrazina
        this.grupe = grupe;
    }

    public String getGrupe(){
        return grupe;
    }

}
