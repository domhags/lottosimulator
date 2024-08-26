import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int anzahl = getAnzahlZiehungen(scanner);

        // Array zur Speicherung der Häufigkeit jeder Zahl (Index 0 bleibt ungenutzt)
        int[] ergebnisse = new int[46];

        simuliereZiehungen(anzahl, ergebnisse, random);

        zeigeErgebnisse(anzahl, ergebnisse);
    }

    private static int getAnzahlZiehungen(Scanner scanner) {
        System.out.println("Lottoziehung");
        System.out.println("Wie oft möchten Sie eine Ziehung simulieren: ");
        int anzahl;

        while (true) {
            anzahl = scanner.nextInt();
            if (anzahl <= 0) {
                System.out.println("Die Eingabe muss eine positive Zahl sein.");
            } else {
                System.out.println("Die Eingabe war erfolgreich.");
                break;
            }
        }
        return anzahl;
    }

    private static void simuliereZiehungen(int anzahl, int[] ergebnisse, Random random) {
        for (int n = 0; n < anzahl; n++) {
            int[] lottoNumbers = zieheLottoZahlen(random);
            aktualisiereErgebnisse(ergebnisse, lottoNumbers);
        }
    }

    private static int[] zieheLottoZahlen(Random random) {
        int[] lottoNumbers = new int[6];

        for (int i = 0; i < lottoNumbers.length; i++) {
            int newNumber;
            do {
                newNumber = random.nextInt(45) + 1; // Generiert eine Zahl zwischen 1 und 45
            } while (istSchonVorhanden(lottoNumbers, newNumber)); // Wiederholt, falls die Zahl bereits vorhanden ist

            lottoNumbers[i] = newNumber;
        }
        return lottoNumbers;
    }

    private static void aktualisiereErgebnisse(int[] ergebnisse, int[] lottoNumbers) {
        // Erhöht die Zählung für jede gezogene Zahl
        for (int zahl : lottoNumbers) {
            ergebnisse[zahl]++;
        }
    }

    private static void zeigeErgebnisse(int anzahl, int[] ergebnisse) {
        System.out.println("Häufigkeit des Vorkommens jeder Zahl (1-45) und deren prozentualer Anteil:");

        // Schleife durch jede Zahl von 1 bis 45 zur Anzeige der Häufigkeit und des Prozentsatzes
        for (int i = 1; i <= 45; i++) {
            double percentage = (double) ergebnisse[i] / (anzahl * 6) * 100;
            System.out.printf("Zahl %2d: %d Mal, %.2f%%\n", i, ergebnisse[i], percentage);
        }
    }

    private static boolean istSchonVorhanden(int[] zahlen, int zahl) {
        for (int j : zahlen) {
            if (j == zahl) {
                return true;
            }
        }
        return false;
    }
}
