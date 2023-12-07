
package schule.turtle;
/**
 * Eine Schildkröte, die eine genau definierte Position
 * einnimmt und immer in eine Laufrichtung schaut.
 * 
 * @author pape
 *
 */
public interface Schildkroete {

	/**
	 * Dreht die Schildkroete um den Winkel gegen den Uhrzeigersinn.
	 * 
	 * @param winkel in Grad
	 */
	public void drehen(double winkel);
	
    /**
     * Lauft von der aktuellen Position in Laufrichtung
     * die gegeben <code>distanz</code> und zeichnet
     * dabei die zurückgelegte Strecke.
     */
	public void laufen(double distanz);
	
	public double getX();
	
	public double getY();

    /**
     * Geht zu der angegebenen Position ohne eine
     * Strecke zu zeichnen.
     */
    public void positionieren(double x, double y);

    /**
     * Geht von der aktuellen Position zum neuen Punkt
     * und zeichnet dabei die zurückgelegte Strecke.
     */
    public void geheZu(double x, double y);
}
