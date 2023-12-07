package schule;
public class Blockchiffre {
    public Blockchiffre() {
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
        String s = Integer.toBinaryString((int) c);
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
    public int[] transRueck (int [] array){
        int [] rueck = new int[4];
        for(int i = 0; i<array.length; i++) {
            rueck[i] = transIndexOf(array, i+1)+1;
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
        for(int i = 0; i < runden; i++) {
            a = otp(otpkey,trans(a, transRueck(transposition)));
        }
       for (int i = 0; i<s.length()/8; i++) {
          b += binarytoChar(a.substring(i*8,i*8+8));

       }
        return b;
    }
}

