package schule.unipassau;

import java.util.Scanner;

public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i<lines; i++){
            String input = scanner.nextLine();
            StringBuilder finish = new StringBuilder();

            for (char c:input.toCharArray()) {
                finish.append((char) ((c - 'a' + 39) % 26 + 'a'));

            }
            System.out.println(finish);
        }

    }
}
