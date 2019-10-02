import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {

    public static final int WIDTH = 500, HEIGHT = 500;

    private static JFrame frame;
    private static JPanel pane;

    // random stuff for testing puposes
    // representing the current position
    // of an object in the frame
    static int x = 0, y = 0, r = 100;

    static boolean space = false;

    private static class Keyboard implements KeyListener {

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            space = (e.getKeyCode() == KeyEvent.VK_SPACE);
        }

        public void keyReleased(KeyEvent e) {
            space = space && e.getKeyCode() != KeyEvent.VK_SPACE;
            System.out.println("R " + space);
        }
    }

    private static void init() {
        frame = new JFrame();
        pane  = new JPanel();

        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(pane);
        frame.addKeyListener(new Keyboard());
        frame.setVisible(true);
    }

    /**
     * update and evaluate new state of the game
     */
    private static void update(long dt) {
        if (space) {
            x = 0;
            y = 0;
        }

        x += dt;
        y += dt;
    }

    /**
     * draw stuff on frame
     */
    private static void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, r, r);
    }

    public static void main (String[] args) {
        init();

        long dt = 0, prev = 0,
            now = System.currentTimeMillis();

        while (true) {
            prev = now;
            now  = System.currentTimeMillis();
            dt   = now - prev;

            update(dt);
            render(pane.getGraphics());
            pane.repaint(0, 0, WIDTH, HEIGHT);
        }
    }
}
