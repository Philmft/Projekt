
package schule.turtle;
/**
 * Um im zwei-dimensionalen Raum zu zeichen, braucht man
 * zwei Koordinaten. Diese sind in dieser Klasse gelandet,
 * damit sie niemals verloren oder auseinander gehen können.
 * Außerdem kann man so  später leicht eine dritte Koordinate
 * hinzugesellen.
 * 
 * @author pape
 *
 */
public interface Position {

	public double getX();
	
	public double getY();

}
