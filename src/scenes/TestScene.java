package src.scenes;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import src.*;

public class TestScene implements Scene {

    public static final int WIDTH = 1000, HEIGHT = 1000;

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

        camera.x = -App.WIDTH / 2;
        camera.y = -App.WIDTH / 2;

        objects = new ArrayList<>();
        objects.add(circle);
    }

    /**
     * update and evaluate new state of the game
     */
    public void update(IOState st) {
        circle.x += circle.speed * st.getDt();
        circle.y += circle.speed * st.getDt();

        // TODO: proper on released event
        if (st.isSpace()) {
            circle.speed = 1 - circle.speed;
        }

        if (st.isLeft())  camera.x = Math.min(camera.x + 1, WIDTH);
        if (st.isRight()) camera.x = Math.max(camera.x - 1, 0);
        if (st.isUp())    camera.y = Math.max(camera.y - 1, 0);
        if (st.isDown())  camera.y = Math.min(camera.y + 1, HEIGHT);

        // System.out.println("x = " + camera.x + ", y = " + camera.y);

        if (st.isMouse()) {
            Point pos = st.getMouse();
            circle.x = pos.x + camera.x;
            circle.y = pos.y + camera.y;
        }
    }

    /**
     * draw stuff on frame
     */
    public void render(Graphics g) {
        camera.render(g, objects);
    }


}
