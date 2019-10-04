package src.scenes;

import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;

import src.*;

public class LoadScene implements Scene {

    private int loadCount = 0;
    private Iterator<BufferedImage> mariosSprite;
    private ArrayList<Mario> marios = new ArrayList<>();
    private ArrayList<Rectangle> areas = new ArrayList<>();

    private static class Mario implements GameObject {
        static int count = 0;

        int x, y;
        BufferedImage img;

        public Mario(BufferedImage img) {
            x = (count * 100) % 500;
            y = (count * 100) / 500;
            count++;

            this.img = img;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public boolean intersects(Rectangle r) {
            return r.intersects(new Rectangle(x, y,
                                              img.getWidth(),
                                              img.getHeight()));
        }

        public void render(Graphics g, int x, int y) {
            g.drawImage(img, x, y, null);
        }
    }

    public void onStart() {
        areas.add(new Rectangle(1, 72, 16, 33));
        areas.add(new Rectangle(18, 72, 16, 33));
        areas.add(new Rectangle(35, 72, 16, 33));
        areas.add(new Rectangle(52, 72, 16, 33));
        areas.add(new Rectangle(69, 72, 16, 33));
        areas.add(new Rectangle(86, 72, 16, 33));
        areas.add(new Rectangle(103, 72, 16, 33));
        areas.add(new Rectangle(120, 72, 16, 33));
        areas.add(new Rectangle(137, 72, 16, 33));
        areas.add(new Rectangle(154, 72, 16, 33));
    }

    public void update(IOState st) {
        if (mariosSprite == null) {
            try {
                BufferedImage marioFile =
                    ImageIO.read(new File("./assets/mario-sprite.png"));
                mariosSprite = SpriteBuilder.build(marioFile, areas);
            } catch (Exception e) {}
        }

        if (mariosSprite.hasNext()) {
            marios.add(new Mario(mariosSprite.next()));
            loadCount += 1;
        }

        if (loadCount == 10) {
            App.setScene(new TestScene());
        }
    }

    public void render(Graphics g) {
        for (Mario m: marios) {
            m.render(g, 0, 0);
        }

        g.setColor(Color.black);
        g.drawRect(100, 100, 100, 30);

        g.setColor(Color.green);
        g.fillRect(105, 105, (loadCount * 90) / 10, 20);
    }

}
