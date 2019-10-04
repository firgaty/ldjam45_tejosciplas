package src;

import java.awt.*;

public class Camera {

    public int x, y;

    public void render(Graphics g, Iterable<GameObject> objects) {
        Rectangle camArea = new Rectangle(x, y, App.WIDTH, App.HEIGHT);

        for (GameObject obj : objects) {
            if (obj.intersects(camArea))
                obj.render(g, obj.getX() - x, obj.getY() - y);
        }
    }

}
