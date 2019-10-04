package src;

import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class Animation {

    private HashMap<String, AnimationState> animations = new HashMap<>();
    private AnimationState animation;

    private static class AnimationState {
        ArrayList<BufferedImage> imgs;
        int i, timeLapse;
        long lastSwitch;

        public AnimationState(ArrayList<BufferedImage> imgs, int t) {
            this.imgs = imgs;
            this.timeLapse = t;
        }

        public void render(Graphics g, int x, int y) {
            long now = System.currentTimeMillis();
            if (now - lastSwitch > timeLapse) {
                i = (i + 1) % imgs.size();
                lastSwitch = now;
            }

            g.drawImage(imgs.get(i), x, y, null);
        }
    }

    public void addAnimation(String name, ArrayList<BufferedImage> imgs,
                             int timeLapse) {
        animations.put(name, new AnimationState(imgs, timeLapse));
    }

    public void setAnimation(String name) {
        animation = animations.get(name);
    }

    public void render(Graphics g, int x, int y) {
        animation.render(g, x, y);
    }

}
