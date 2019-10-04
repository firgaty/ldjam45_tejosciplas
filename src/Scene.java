package src;

import javax.swing.*;
import java.awt.*;

public interface Scene {

    void render(Graphics g);

    void update(IOState st);

    default void onStart() {}
    default void onEnd() {}
}
