public class Main {
    public static void main(String[] args) {
        System.out.println();
        // 4. Klases objekto sukurimas

        //Klases pavadimas      obejkto pavadinimas         konstruktorius-pavadinimas
        Moksleivis mok = new Moksleivis();

        //(int id, String vardas, String pavarde, String grupe)
        Moksleivis mok1 = new Moksleivis(0, "Kamile", "Miskinaite", "T23");

        System.out.println(mok1.getVardas() + " istojo i kitm grupe " + mok1.getGrupe());
        System.out.println(mok1.getVardas() + " apsigalvojo ir pakeite grupe");
        mok1.setGrupe("J23");
        System.out.println("Nuo siol ji mokinasi " + mok1.getGrupe() +" grupeje");

    }
}
