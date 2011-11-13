/*
 * Naam: Jelte Fennema
 * Studentnummer: 10183159
 * Opleiding: Informatica
 *
 * Functionaliteit: Het programma moet, achtereenvolgens, de volgende vier,
 * onderling niet geralateerde, onderdelen uitvoeren:
 *  1. Het programma vraagt de gebruiker om een totaal aantal seconden
 * (een integer) in te voeren. Het programma print vervolgens dezelfde
 * hoeveelheid tijd, maar nu gerepresenteerd in uren, minuten en seconden.
 *
 * 2. Het programma vraagt de gebruiker om een geheel getal (integer) tussen
 * 0 en 128 in te voeren. Het programma print vervolgens het ASCII karakter dat
 * door dit getal wordt gerepresenteerd.
 *
 * 3. Het programma vraagt de gebruiker om de straal van een bol (een double)
 * in te voeren. Het programma print vervolgens de oppervlakte en het volume van
 * deze bol.
 *
 * 4. Schrijf een hulpfunctie die de uitvoer bij onderdeel 3 afrondt op 3
 * decimalen.
 */
import java.util.*;

public class Opgave2 {
/*
 * Main method: Deze roept alle drie de functies aan en vraagt om de invoer voor
 * voor die functies.
 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Voer een geheel aantal seconden in:");
        tijd(scanner.nextInt());

        System.out.println("Geef een integer tussen 0 en 128:");
        charConverter(scanner.nextInt());

        System.out.println("Geef de straal van de bol:");
        bol(scanner.nextDouble());
        

    }

    /*
     * Bol method: Deze methode berekent de oppervlakte en de inhoud van een bol
     * aan de hand van de straal. Ook worden deze waardes daarna afgerond op
     * drie decimalen.
     */
    static void bol (double straal){
        if(straal >= 0){
            double oppervlakte = 4*Math.PI*straal*straal;
            double inhoud = 4/3*Math.PI*straal*straal*straal;
            System.out.println("\nOppervlakte: " + oppervlakte + "\nInhoud: " +
                    inhoud);

            System.out.println("\nAfgerond op drie decimalen is het dan:\n" +
                    "Oppervlakte: "+ roundNumber3(oppervlakte) +
                    "\nInhoud: " + roundNumber3(inhoud));
        } else {
            System.out.println("Ga eens positieve getallen invullen ofzo.\n");
        }
    }

    /*
     * RoundNumber3 method: Deze methode rond doubles af op 3 decimalen.
     */
    static double roundNumber3(double roundable){
        roundable=Math.round(roundable*1000)/1000.0;
        return roundable;
    }

    /*
     * CharConverter method: Deze methode converteerd een int tussen
     * de 0 en 128 naar een char.
     */
    static void charConverter (int convertable){
        if (convertable > 0 && convertable < 128)
            System.out.println("\nHet corresponderende ASCII karakter is: " +
                    (char) convertable + "\n");
        else
            System.out.println("Ga eens leren tellen, " + convertable +
                    " zit niet tussen de 0 en 128.\n");
    }

    /*
     * Tijd method: Deze methode geeft aan hoeveel uur, minuten en secondes er
     * zitten in een bepaald aantal secondes.
     */
    static void tijd(int sec){
        if(sec>=0){
            int uren = sec/3600;
            int minuten = (sec%3600)/60;
            int secondes = (sec%3600)%60;

            System.out.println("\nHet totaal aantal seconden komt overeen met:"
                    + "\n" + uren + uurOfUren(uren) +
                    minuten + minuutOfMinuten(minuten) +
                    secondes + secondeOfSecondes(secondes) +
                    "\n");
        } else {
            System.out.println("Really, ben je dom? Er bestaat toch geen "
                    + "negatieve tijd.\n");
        }
    }

    /*
     * Deze drie methodes kijken of er een meervoud van het woord uur, minuut of
     * seconde gebruikt moet worden.
     */
    static String uurOfUren(int k){
        if (k>1) {
            return " uren, ";
        } else {
            return " uur, ";
        }
    }

    static String minuutOfMinuten(int k){
        if (k>1) {
            return " minuten en ";
        } else {
            return " minuut en ";
        }
    }

    static String secondeOfSecondes(int k){
        if (k>1) {
            return " secondes.";
        } else {
            return " seconde.";
        }
    }
}
