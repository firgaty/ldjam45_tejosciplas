package src;

import java.awt.*;

public interface GameObject {

    int getX();
    int getY();
    boolean intersects(Rectangle area);

    void render(Graphics g, int x, int y);

}
