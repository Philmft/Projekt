package schule.unipassau;

import java.util.HashMap;
import java.util.Scanner;

public class Solution9 {

    static HashMap<String, Integer> prices = new HashMap<>() {{
        put("Backhendl", 17);
        put("Brotzeitteller", 7);
        put("Currywurst", 18);
        put("Fleischpflanzerl", 7);
        put("Gemischtes Reindl", 10);
        put("Gyros", 14);
        put("Jaegerschnitzel", 3);
        put("Kaesespaetzle", 7);
        put("Kaiserschmarrn", 5);
        put("Kalbshaxe", 16);
        put("Obazda", 8);
        put("Putenschnitzel", 6);
        put("Rindsrouladen", 17);
        put("Schweinebraten", 18);
        put("Spaghetti Bolognese", 19);
        put("Weiswuerschtl", 15);
        put("Wiener Schnitzel", 14);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] products;

        int sum;
        for (int i = 0; i < n; i++) {
            sum = 0;
            products = scanner.nextLine().split(", ");
            for (String product : products)
                sum += prices.get(product);

            System.out.println(sum);
        }

    }
}
