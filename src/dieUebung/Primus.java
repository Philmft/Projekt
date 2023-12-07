package dieUebung;

public class Primus {
    public static boolean prime (int zahl) {
    boolean isPrime;
    int divider = 0;
    for (int i = 1; i<= zahl; i++) {
        if (zahl % i == 0) divider++;
    }
    isPrime = divider == 2;
    return isPrime;
}
    public static void primeBetween(int unterGrenze, int oberGrenze) {
        for (int i = unterGrenze; i <= oberGrenze; i++){
            if (prime(i)) {
                System.out.println(i);
            }
        }
    }
}
