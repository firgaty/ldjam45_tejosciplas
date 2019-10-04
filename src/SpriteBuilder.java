package src;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class SpriteBuilder {

    public static BufferedImage spriteSheet;
    public static boolean flipHorizontally = false, flipVertically = false;

    public static BufferedImage resize(BufferedImage img,
                                          int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg =
            new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static BufferedImage flipHorizontally(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);

        AffineTransformOp op =
            new AffineTransformOp(tx,
                                  AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }

    public static BufferedImage flipVertically(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -img.getHeight(null));

        AffineTransformOp op =
            new AffineTransformOp(tx,
                                  AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }

    public static BufferedImage flipBoth(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-img.getWidth(null), -img.getHeight(null));

        AffineTransformOp op =
            new AffineTransformOp(tx,
                                  AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }

    public static Iterator<BufferedImage> build(
        final BufferedImage sheet, Iterable<Rectangle> images) {
        int x = 0, y = 0;
        Iterator<Rectangle> areas = images.iterator();

        return new Iterator<BufferedImage>() {

            public boolean hasNext() {
                return areas.hasNext();
            }

            public BufferedImage next() {
                Rectangle area = areas.next();
                try { Thread.sleep(1000); } catch (Exception e) {}
                return sheet.getSubimage(area.x, area.y,
                                         area.width, area.height);
            }
        };
    }
}
