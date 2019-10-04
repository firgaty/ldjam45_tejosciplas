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

    private static void init() {
        frame = new JFrame();
        pane  = new JPanel();
        state = new IOState();

        setScene(new LoadScene());

        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(pane);

        frame.addKeyListener(state);
        frame.addMouseMotionListener(state);
        frame.addMouseListener(state);

        frame.setVisible(true);
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
        scene.onStart();
    }

    public static void main (String[] args) {
        init();
        scene.onStart();

        long prev = 0, last_repaint = 0,
            now = System.currentTimeMillis();

        // TODO: sync repaint cycle with frame cycle
        while (true) {
            prev       = now;
            now        = System.currentTimeMillis();
            state.dt   = (now - prev);

            scene.update(state);

            if (now - last_repaint > 1000 / 60) {
                last_repaint = now;
                pane.repaint(0, 0, WIDTH, HEIGHT);
            }

            scene.render(pane.getGraphics());
        }
    }
}
