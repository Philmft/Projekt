
package schule.turtle;
/**
 * Implementierung einer Schildkroete: Chelone ist griechisch für
 * Schildkröte. Nach griechischer Mythologie handelt es sich dabei
 * um eine Jungfrau, die von Hermes zur Strafe
 * in eine Schildkröte verwandelt wurde.
 * Da Schildkröten aber extrem alt werden können, kann man dies
 * auch als positive Lebensverlängerung ansehen (aber vielleicht ist das
 * bei älteren Jungfrauen auch wieder eine subtile Form der Bestrafung).
 * 
 * @author pape
 */
public class Chelone implements Schildkroete {

	/**
	 * Winkel in radiant 0..2PI..4PI..6PI.. für (0..360..720..usw)
	 */
	private double richtung;
	
	private SchildkroetenPosition position;

	public Chelone(Position position) {
		this.position = new SchildkroetenPosition(position);
		this.richtung = 0.0;
	}
	
	public Chelone(double x, double y) {
		this.position = new SchildkroetenPosition(x, y);
		this.richtung = 0.0;
	}
	
	/**
	 * Dreht die Schildkröte um den Winkel gegen den Uhrzeigersinn.
	 * 
	 * @param winkel in Grad
	 */
	public void drehen(double winkel) {
		this.richtung = (richtung + getRadiant(winkel));
	}

	private double getRadiant(double winkel) {
		return (winkel * Math.PI) / 180.0;
	}
	
	public void laufen(double distanz) {
		position.x += distanz * Math.cos(richtung);
		position.y += distanz * Math.sin(richtung);
	}

	public double getX() {
		return position.x;
	}
	
	public double getY() {
		return position.y;
	}

	public double getRichtung() {
		return richtung;
	}
	
	public String toString() {
		return getX() + " / " + getY() + " / " + getRichtung();
	}
	
	private class SchildkroetenPosition implements Position {
		
		private double x;
		
		private double y;
		
		public SchildkroetenPosition(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public SchildkroetenPosition(Position position) {
			this.x = position.getX();
			this.y = position.getY();
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}
		
	}

    public void geheZu(double x, double y) {
        positionieren(x, y);
    }

    public void positionieren(double x, double y) {
        position.x = x;
        position.y = y;
    }
}
