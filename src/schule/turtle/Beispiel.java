package schule.turtle;

/**
 * Beschreiben Sie hier die Klasse Beispiel.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Beispiel
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    SchildkroetenGrafik schildkroetenGrafik = new SchildkroetenGrafik("Wir lieben Schafgarbe", 2240, 1680);
    Schildkroete schildkroete = schildkroetenGrafik.createSchildkroete();
    /**
     * Konstruktor für Objekte der Klasse Beispiel
     */
    public Beispiel()
    {
        // Instanzvariable initialisieren
        schildkroete.positionieren(1120, 1680);
        schildkroete.drehen(90);
        beispielBaum(320);
       
    }
    
    public void beispielBaum(int s){
       schildkroete.laufen(s);
        schildkroete.drehen(25);
        schildkroete.laufen(s);
        schildkroete.laufen(-s);
        schildkroete.drehen(-50);
        schildkroete.laufen(s);
        schildkroete.laufen(-s);
        schildkroete.drehen(25);
        schildkroete.laufen(-s);
    
    
    }

  
}
