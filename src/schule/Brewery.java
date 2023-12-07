package schule;
import java.util.Scanner;

public class Brewery {
    public static void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Wie viele Testläufe sollen durchgeführt werden?");
        int testruns = in.nextInt();
        int p = 0;
        int k = 0;
        int f = 0;
        for (int i = 0; i < testruns; i++) {
            System.out.println("Für wieviel Liter Bier soll berechnet werden?");
            int liter = in.nextInt();
            if (liter >= 450) {
                p = liter / 450;
                liter -=p * 450;
            }
            if (liter >= 10) {
                k = liter / 10;
                liter -= k * 10;
            }
            if (liter >= 0.5) {
                f = (int) (liter / 0.5);
            }
            System.out.println("p = " + p + "  k = " + k + "  f= " + f);
        }
    }
}

