package src.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import src.*;

public class TestScene implements Scene {

    public static final int WIDTH = 1000, HEIGHT = 1000;
    private Point mousePosition = new Point(0, 0);
    private boolean mouse = false;

    // random stuff for testing puposes
    // representing the current position
    // of an object in the frame
    static class Circle implements GameObject {
        int x = 0, y = 0, r = 100, speed = 1;

        public int getX() { return x; }
        public int getY() { return y; }
        public boolean intersects(Rectangle rect) {
            return rect.intersects(
                new Rectangle(x - r / 2, y - r / 2, r, r));
        }

        public void render(Graphics g, int x, int y) {
            g.setColor(Color.red);
            g.fillOval(x - r / 2, y - r / 2, r, r);
        }
    }

    ArrayList<GameObject> objects;
    Circle circle;
    Camera camera;

    public void onStart() {
        circle = new Circle();
        camera = new Camera();

        camera.x = 0;
        camera.y = 0;

        objects = new ArrayList<>();
        objects.add(circle);
    }

    /**
     * update and evaluate new state of the game
     */
    public void update(long dt) {
        circle.x += circle.speed * dt;
        circle.y += circle.speed * dt;

        System.out.println("x = " + camera.x + ", y = " + camera.y);

        if (mouse) {
            circle.x = mousePosition.x + camera.x;
            circle.y = mousePosition.y + camera.y;
        }
    }

    /**
     * draw stuff on frame
     */
    public void render(Graphics g) {
        camera.render(g, objects);
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_SPACE:
            circle.speed = 1 - circle.speed;
            break;

        case KeyEvent.VK_LEFT:
            camera.x = Math.min(camera.x + 1, WIDTH);
            break;

        case KeyEvent.VK_RIGHT:
            camera.x = Math.max(camera.x - 1, 0);
            break;

        case KeyEvent.VK_UP:
            camera.y = Math.max(camera.y - 1, 0);
            break;

        case KeyEvent.VK_DOWN:
            camera.y = Math.min(camera.y + 1, HEIGHT);
            break;
        }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) { mouse = true; }
    public void mouseReleased(MouseEvent e) { mouse = false; }

    public void mouseDragged(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }


}
