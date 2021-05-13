/*
This class is used to initialize and draw the edges of checkerboard
Set and get some fundamental properties of edge
 */

import java.awt.*;
import java.util.Random;

public class Edge {
    private int x, y;
    private int width, height;
    private boolean vertical;
    private boolean free = true;
    private Color color = Color.WHITE;
    private boolean visible = false;

    public Edge(int x, int y, boolean vertical) {
        this.x = x;
        this.y = y;
        this.vertical = vertical;
        if (vertical) {
            this.width = 10;
            this.height = 100;
        } else {
            this.width = 100;
            this.height = 10;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVertical() {
        return vertical;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - width / 2, y - height / 2, width, height);
    }

    public void see(boolean side, boolean visible) {
        if (visible == false) {
            return;
        }
        int midValue = (isVertical() ? width : height) / 2;
        int alphaStep = free ? 255 / midValue : 0;
        for (int i = 0; i < midValue; i++) {
            StdDraw.setPenColor(side ? new Color(255, 0, 0, 255 - alphaStep * i) :
                    new Color(0, 0, 255, 255 - alphaStep * i));
            if (vertical) {
                StdDraw.filledRectangle(x + i, y, i, height / 2.0);
                StdDraw.filledRectangle(x - i, y, i, height / 2.0);
            } else {
                StdDraw.filledRectangle(x, y + i, width / 2.0, i);
                StdDraw.filledRectangle(x, y - i, width / 2.0, i);
            }
        }
    }
}
