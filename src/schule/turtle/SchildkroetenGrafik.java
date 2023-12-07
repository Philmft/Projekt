
package schule.turtle;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class SchildkroetenGrafik extends JFrame implements WindowListener{

    private Image image;

    public SchildkroetenGrafik(String name, int width, int height) {
        super(name);
        setPreferredSize(new Dimension(width, height));
        pack();
        image = getContentPane().createImage(width, height);
        addWindowListener(this);
        setVisible(true);
    }

    public Schildkroete createSchildkroete() {
        return new SchildkroeteGrafisch(this, (Graphics2D) image.getGraphics(), new Chelone(0, 0));
    }

    public void warten() {
        while (isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                setVisible(false);
            }
        }
    }

    public void windowActivated(WindowEvent e) {    
        paint(getGraphics());
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        setVisible(false);
        dispose();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
        paint(getGraphics());
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(image, 0, 0, Color.white, null);
    }
}
