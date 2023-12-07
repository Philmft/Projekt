
package schule.turtle;
import java.awt.Container;
import java.awt.Graphics2D;

import javax.swing.SwingUtilities;

/**
 * Eine Schildkroete, deren Bewegungen gezeichnet werden.
 * 
 * @author pape
 */
public class SchildkroeteGrafisch implements Schildkroete {

	private Schildkroete schildkroete;
	
	private Graphics2D graphics2D;
	
    private Container container;
    
	public SchildkroeteGrafisch(Container container, Graphics2D graphics2D, Schildkroete schildkroete) {
		this.schildkroete = schildkroete;
		this.graphics2D = graphics2D;
        this.container = container;
	}

	public void drehen(double winkel) {
		// Es wird in "umgekehrter" Richtung gedreht, da
		// im Koordinatensystem vom Graphics2D-Objekt
		// links oben die Position 0,0 ist
		// und x,y im rechten unteren Quadrant positiv sind
		// statt im rechten oberen.
		schildkroete.drehen(-winkel);
	}

	/**
	 * Die Schildkröte läuft von ihrere Position und Richtung
	 * über die gegeben Distanz. Je nach Grafikkarte
	 * kriecht sie aber vielleicht auch nur.
	 */
	public void laufen(double distanz) {
		int x = (int) (getX() + 0.5);
		int y = (int) (getY() + 0.5);
		schildkroete.laufen(distanz);
		zeichneLinie(x, y, (int) (getX() + 0.5), (int) (getY() + 0.5));
	}

    private void zeichneLinie(final int x1, final int y1, final int x2, final int y2) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        graphics2D.drawLine(x1, y1, x2, y2);
                        container.repaint();
                    }
                }
        );
    }
    
	public double getX() {
		return schildkroete.getX();
	}

	public double getY() {
		return schildkroete.getY();
	}

    public void geheZu(double x, double y) {
        zeichneLinie((int) (getX() + 0.5), (int) (getY() + 0.5), (int) (x + 0.5), (int) (y + 0.5));
        schildkroete.geheZu(x, y);
    }

    public void positionieren(double x, double y) {
        schildkroete.positionieren(x, y);
    }

	
}
