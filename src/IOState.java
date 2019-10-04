package src;

import java.awt.*;
import java.awt.event.*;

public class IOState
    implements KeyListener, MouseListener, MouseMotionListener {

    private static final int TYPED_DELAY = 1000;

    long dt;
    boolean space, mouse, left, right, up, down;
    int spaceTyped;
    Point mousePosition = new Point(0, 0);

    public long getDt()      { return dt; }

    public boolean isSpace() { return space; }
    public boolean isMouse() { return mouse; }
    public boolean isLeft()  { return left; }
    public boolean isRight() { return right; }
    public boolean isUp()    { return up; }
    public boolean isDown()  { return down; }

    public Point getMouse()  { return new Point(mousePosition); }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        space = e.getKeyCode() == KeyEvent.VK_SPACE;
        up    = e.getKeyCode() == KeyEvent.VK_UP;
        left  = e.getKeyCode() == KeyEvent.VK_LEFT;
        right = e.getKeyCode() == KeyEvent.VK_RIGHT;
        down  = e.getKeyCode() == KeyEvent.VK_DOWN;
    }

    public void keyReleased(KeyEvent e) {
        space = space && e.getKeyCode() == KeyEvent.VK_SPACE;
        up    = up && e.getKeyCode() == KeyEvent.VK_UP;
        left  = left && e.getKeyCode() == KeyEvent.VK_LEFT;
        right = right && e.getKeyCode() == KeyEvent.VK_RIGHT;
        down  = down && e.getKeyCode() == KeyEvent.VK_DOWN;

        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            spaceTyped -= 1;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        mouse = true;
    }

    public void mouseReleased(MouseEvent e) {
        mouse = false;
    }

    public void mouseDragged(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
    }


}
