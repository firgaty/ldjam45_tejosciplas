package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public interface Scene
    extends KeyListener, MouseListener, MouseMotionListener  {

    void render(Graphics g);

    void update(long dt);

    default void onStart() {}
    default void onEnd() {}
}
