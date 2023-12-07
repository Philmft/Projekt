package schule.bwinf;

import java.util.*;

public class SpielVerteilung {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wundertueten = scanner.nextInt();
        int wuerfelspiele = scanner.nextInt();
        int kartenspiele = scanner.nextInt();
        int geschicklichkeitsspiele = scanner.nextInt();

        int[] spieleProTuete = new int[wundertueten];
        int[] spieleSorten = {wuerfelspiele, kartenspiele, geschicklichkeitsspiele};

        for (int spiele : spieleSorten) {
            for (int i = 0; i < spiele; i++) {
                spieleProTuete[i % wundertueten]++;
            }
        }

        for (int i = 0; i < wundertueten; i++) {
            System.out.println("Wundertüte " + (i+1) + ": " + spieleProTuete[i] + " Spiele");
        }
    }
}