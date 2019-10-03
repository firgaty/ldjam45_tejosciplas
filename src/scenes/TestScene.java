package src.scenes;

import src.*;
import javax.swing.*;
import java.awt.*;

public class TestScene implements Scene {

    // random stuff for testing puposes
    // representing the current position
    // of an object in the frame
    static int x = 0, y = 0, r = 100;

    /**
     * update and evaluate new state of the game
     */
    public void update(IOState st) {
        if (st.isSpace()) {
            x = 0;
            y = 0;
        }

        x += st.getDt();
        y += st.getDt();
    }

    /**
     * draw stuff on frame
     */
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, r, r);
    }


}
