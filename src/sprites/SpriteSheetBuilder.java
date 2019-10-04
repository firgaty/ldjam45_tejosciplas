package src.sprites;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class SpriteSheetBuilder {

    private BufferedImage spriteSheet;
    private int rows, cols;
    private int spriteWidth, spriteHeight;
    private int spriteCount;
    private int resizeWidth = 0, resizeHeight = 0;
    private boolean flipHorizontally = false, flipVertically = false;

    public SpriteSheetBuilder withSheet(BufferedImage img) {
        spriteSheet = img;
        return this;
    }

    public SpriteSheetBuilder withRows(int rows) {
        this.rows = rows;
        return this;
    }

    public SpriteSheetBuilder withColumns(int cols) {
        this.cols = cols;
        return this;
    }

    public SpriteSheetBuilder withSpriteSize(int width, int height) {
        this.spriteWidth = width;
        this.spriteHeight = height;
        return this;
    }

    public SpriteSheetBuilder withSpriteCount(int count) {
        this.spriteCount = count;
        return this;
    }

    public SpriteSheetBuilder withResize(int width, int height) {
        this.resizeWidth = width;
        this.resizeHeight = height;
        return this;
    }

    public SpriteSheetBuilder withFlipHorizontally() {
        this.flipHorizontally = true;
        return this;
    }

    public SpriteSheetBuilder withFlipVertically() {
        this.flipVertically = true;
        return this;
    }

    protected int getSpriteCount() {
        return spriteCount;
    }

    protected int getCols() {
        return cols;
    }

    protected int getRows() {
        return rows;
    }

    protected int getSpriteHeight() {
        return spriteHeight;
    }

    protected BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    protected int getSpriteWidth() {
        return spriteWidth;
    }

    protected BufferedImage resize(BufferedImage img) {
        int newW = resizeWidth != 0 ? resizeWidth : spriteWidth;
        int newH = resizeHeight != 0 ? resizeHeight : spriteHeight;

        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    protected BufferedImage flipHorizontally(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }

    protected BufferedImage flipVertically(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -img.getHeight(null));

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }

    protected BufferedImage flipBoth(BufferedImage img) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-img.getWidth(null), -img.getHeight(null));

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);

        return img;
    }

    public SpriteSheet build() {
        int count = getSpriteCount();
        int rows = getRows();
        int cols = getCols();
        if (count == 0) {
            count = rows * cols;
        }

        BufferedImage sheet = getSpriteSheet();

        int width = getSpriteWidth();
        int height = getSpriteHeight();
        if (width == 0) {
            width = sheet.getWidth() / cols;
        }
        if (height == 0) {
            height = sheet.getHeight() / rows;
        }

        int x = 0;
        int y = 0;
        List<BufferedImage> sprites = new ArrayList<>(count);

        for (int index = 0; index < count; index++) {
            BufferedImage sub = sheet.getSubimage(x, y, width, height);
            if (flipHorizontally && flipVertically) {
                sub = flipBoth(sub);
            } else {
                if (flipVertically)
                    sub = flipVertically(sub);
                if (flipHorizontally)
                    sub = flipHorizontally(sub);
            }
            if (resizeHeight != 0 || resizeWidth != 0)
                sub = resize(sub);
            sprites.add(sub);
            x += width;
            if (x >= width * cols) {
                x = 0;
                y += height;
            }
        }

        return new SpriteSheet(sprites);
    }
}