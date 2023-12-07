package schule;

public class Blockchiffre1 {
    public Blockchiffre1() {
    }

    public String otp(String key, String klar) {
        String s = "";
        for (int i = 0; i < klar.length(); i++) {
            if (klar.charAt(i) == key.charAt(i % key.length())) {
                s += 0;
            } else s += 1;
        }
        return s;
    }

    public String binaryASCII(char c) {
        String s = Integer.toBinaryString(c);
        while (s.length() < 8) {
            s = "0" + s;
        }
        return s;
    }

    public String binA(String e) {
        String s = "";
        for (int i = 0; i < e.length(); i++) {
            s += binaryASCII(e.charAt(i));
        }
        return s;
    }

    public char binarytoChar(String s) {
        int parseInt = Integer.parseInt(s, 2);
        return (char) parseInt;
    }

    public String trans(String e, int[] reihenfolge) {
        String s = "";
        int a = reihenfolge[0];
        int b = reihenfolge[1];
        int c = reihenfolge[2];
        int d = reihenfolge[3];
        a -= 1;
        b -= 1;
        c -= 1;
        d -= 1;

        for (int i = 0; i < e.length() / 4; i++) {
            s += e.charAt(a);
            s += e.charAt(b);
            s += e.charAt(c);
            s += e.charAt(d);
            a += 4;
            b += 4;
            c += 4;
            d += 4;
        }
        return s;
    }

    public int[] transRueck(int[] array) {
        int[] rueck = new int[4];
        for (int i = 0; i < array.length; i++) {
            rueck[i] = transIndexOf(array, i + 1) + 1;
        }
        return rueck;
    }

    public int transIndexOf(int[] transp, int stelle) {
        for (int i = 0; i < transp.length; i++) {
            if (stelle == transp[i]) {
                return i;
            }
        }
        return -1;
    }

    public String password(String s) {
        int a = 1;
        for (int i = 0; i < s.length(); i++) {
            a *= s.charAt(i) * 3;
        }
        String b = Long.toString(a);
        return b;
    }

    public String pswInBinary(String s) {
        String binaryPsw = binA(password(s));
        return binaryPsw;
    }

    public String generateOTP(String s) {
        String b = "";
        for (int i = 0; i < 8; i++) {
            b += s.charAt(i);
        }
        return b;
    }

    public String otpFinal(String s) {
        String f = generateOTP(binA(password(s)));
        return f;
    }

    public boolean arrayContains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return true;
        }
        return false;
    }

    public int nextEmptyIndex(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) return i;
        }
        return -1;
    }

    public int[] transGen(String s) {
        int[] trans = new int[4];
        for (int i = 0; i < s.length(); i++) {
            if (nextEmptyIndex(trans) == -1) break;
            int value = Integer.parseInt(String.valueOf(s.charAt(i))) % 4 + 1;
            if (!arrayContains(trans, value)) trans[nextEmptyIndex(trans)] = value;
        }
        for (int j = 1; j <= 4; j++) {
            if (!arrayContains(trans, j)) trans[nextEmptyIndex(trans)] = j;
        }
        return trans;
    }

    public int[] transFinal(String s) {
        int[] array = new int[4];
        transGen(password(s));
        return array;
    }

    public String wortVerschluesseln(String s, String otpkey, int[] transposition, int runden) {
        String a = binA(s);
        for (int i = 0; i < runden; i++) {
            String b = trans(otp(otpkey, a), transposition);
            a = b;
        }
        return a;
    }

    public String wortEntschluesseln(String s, String otpkey, int[] transposition, int runden) {
        String b = "";
        String a = s;
        for (int i = 0; i < runden; i++) {
            a = otp(otpkey, trans(a, transRueck(transposition)));
        }
        for (int i = 0; i < s.length() / 8; i++) {
            b += binarytoChar(a.substring(i * 8, i * 8 + 8));

        }
        return b;
    }

    public String pswVersch(String password, String message) {
        String s = binA(message);
        String otpKey = otpFinal(password);
        int[] trans = transGen(password(password));
        int runden = password.charAt(0);
        return wortVerschluesseln(s, otpKey, trans, runden);
    }

    public String pswEntsch(String password, String message) {
        String a = wortEntschluesseln(message, otpFinal(password), transGen(password(password)), password.charAt(0));
        String b = "";
        for (int i = 0; i < a.length() / 8; i++) {
            b += binarytoChar(a.substring(i * 8, i * 8 + 8));
        }
        return b;
    }
}

