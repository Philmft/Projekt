package schule.turtle;
/**
 * Beschreiben Sie hier die Klasse Test.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Test
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    SchildkroetenGrafik schildkroetenGrafik = new SchildkroetenGrafik("X", 2240, 1680);
    Schildkroete schildkroete = schildkroetenGrafik.createSchildkroete();
    /**
     * Konstruktor für Objekte der Klasse Test
     */
    public Test()
    {
        // Instanzvariable initialisieren
        //schildkroete.positionieren(20, 800);
        // schildkroete.drehen(-90);
        schildkroete.positionieren(1120, 1680);
        schildkroete.drehen(90);
        binTree(320);

    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */

    public void binTree(int s){
        if(s<50) return;
        schildkroete.laufen(s);
        schildkroete.drehen(25);
        binTree(s-50);
        schildkroete.drehen(-50);
        binTree(s-50);
        schildkroete.drehen(25);
        schildkroete.laufen(-s);

    }

    public void schafGarbe(int s){
        if(s<1) return;
        schildkroete.laufen(200);
        schildkroete.drehen(45);
        binTree(100);
        schildkroete.drehen(-90);
        binTree(200);
        schildkroete.drehen(45);
        schildkroete.laufen(-200);
        s--;
    }
}
