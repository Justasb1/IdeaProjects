import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programa skaiciuojanti dvieju skaicu sudeti");
        System.out.println("Ivesktie pirma skaiciu"); //Paprasom skaiciaus.
        Scanner vartotojoIvedimoNuskaitymas = new Scanner(System.in); // Reikalinga kad nuskaitytume vartotojo ivedima.
        int sk1 = vartotojoIvedimoNuskaitymas.nextInt(); // Nuskaitome vartotojo ivesta pirma sveika skaiciu.
        System.out.println("Iveskite antra skaiciu");
        int sk2 = vartotojoIvedimoNuskaitymas.nextInt(); // Nuskaitome vartotojo ivesta antra sveika skaiciu.
        int suma = Sudeti(sk1, sk2); // issikviecimae funkcija kuri sudeda vartotojo ivestus skaicius
        // System.out.println(sk1 + " + " + sk2 + " = " + suma); // sudedame ir isvedame vartotojo duotus du skaicius
        Isvesti(sk1, sk2, suma); // Panaudojam isvedimo funkcija
    }
    static int Sudeti(int sk1, int sk2){  // Pasidarom nauja funkcija sudeti skaicius
        return sk1 + sk2;
    }
    static void Isvesti(int sk1, int sk2, int suma){  //Funkcija skaiciu isvedimui
        System.out.println(sk1 + " + " + sk2 + " = " + suma);
    }
}
