
package schule.turtle;
import java.io.PrintStream;

/**
 * Eine Schildkröte, deren Bewegungen als "Textkommandos"
 * ausgegeben werden. Kann zur Fehlersuche verwendet werden.
 */
public class SchildkroeteText implements Schildkroete {

	private Schildkroete schildkroete;
	
	private PrintStream out = System.out;
	
	public SchildkroeteText(double x, double y) {
		schildkroete = new Chelone(x, y);
	}

	public SchildkroeteText(double x, double y, PrintStream out) {
		schildkroete = new Chelone(x, y);
		this.out = out;
	}
	
	public void drehen(double winkel) {
		out.println("DREHE " + winkel + "\t\t(" + schildkroete + ")");
		schildkroete.drehen(winkel);
	}

	public void laufen(double distanz) {
		out.println("LAUFE " + distanz + "\t\t(" + schildkroete + ")");
		schildkroete.laufen(distanz);
	}

	public double getX() {
		return schildkroete.getX();
	}

	public double getY() {
		return schildkroete.getY();
	}

	public String toString() {
		return schildkroete.toString();
	}

    public void geheZu(double x, double y) {
        out.println("GEHE ZU " + "(" + x + ", " + y  + ")\t\t(" + schildkroete + ")");
        schildkroete.geheZu(x, y);
    }

    public void positionieren(double x, double y) {
        out.println("POSITIONIEREN " + "(" + x + ", " + y  + ")\t\t(" + schildkroete + ")");
        schildkroete.positionieren(x, y);
    }
}
