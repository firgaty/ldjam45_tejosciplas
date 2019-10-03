package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import src.scenes.*;

public class App {

    public static final int WIDTH = 500, HEIGHT = 500;

    private static JFrame frame;
    private static JPanel pane;
    private static IOState state;
    private static Scene scene;

    private static class Keyboard implements KeyListener {

        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            state.space = (e.getKeyCode() == KeyEvent.VK_SPACE);
        }

        public void keyReleased(KeyEvent e) {
            state.space = state.space && e.getKeyCode() != KeyEvent.VK_SPACE;
        }
    }

    private static void init() {
        frame = new JFrame();
        pane  = new JPanel();
        scene = new TestScene();
        state = new IOState();

        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(pane);
        frame.addKeyListener(new Keyboard());
        frame.setVisible(true);
    }

    public static void main (String[] args) {
        init();

        long prev = 0,
            now = System.currentTimeMillis();

        // TODO: sync repaint cycle with frame cycle
        while (true) {
            prev       = now;
            now        = System.currentTimeMillis();
            state.dt   = (now - prev);

            scene.update(state);
            scene.render(pane.getGraphics());
            pane.repaint(0, 0, WIDTH, HEIGHT);
        }
    }
}
