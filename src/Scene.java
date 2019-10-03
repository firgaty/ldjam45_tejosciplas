import javax.swing.*;
import java.awt.*;

interface Scene {

    void render(Graphics g);

    void update(App.IOState st);
}
