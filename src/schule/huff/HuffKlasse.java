package schule.huff;

public class HuffKlasse
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int anzahl;
    private char buchstabe;
    private String huffcode;
    /**

     Konstruktor für Objekte der Klasse HuffKlasse*/
    public HuffKlasse(char buchstabe, int anzahl){
        this.buchstabe = buchstabe;
        this.anzahl = anzahl;}

    public HuffKlasse(int anzahl)
    {
        // Instanzvariable initialisieren
        this.anzahl = anzahl;
    }

    public int getAnzahl()
    {
        return anzahl;
    }

    public char gibBuchstabe(){
        return buchstabe;
    }

    public boolean istBlatt(){
        if (buchstabe == '\u0000')
            return false;
        return true;
    }

    public void setHuffcode(String s){
        huffcode = s;
    }

    public String getHuffcode(){
        return huffcode;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", buchstabe, huffcode, anzahl);
    }
}

