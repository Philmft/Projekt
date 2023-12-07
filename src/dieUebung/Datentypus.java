package dieUebung;

import java.util.List;

public class Datentypus {
    public static int arrus (int[] zahlus) {
        int maximum = 0;
        for (int fortniteHaenchen : zahlus) {
            if (fortniteHaenchen > maximum) maximum = fortniteHaenchen;
        }
        return maximum;
    }
    public static int arrusListus(List<Integer> amongus) {
        int maximum = 0;
        for (int grillHaenchen : amongus) {
            if (grillHaenchen > maximum) maximum = grillHaenchen;
        }
        return maximum;
    }
}
