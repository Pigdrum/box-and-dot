/*
This class is used to initialize and draw the dots of checkerboard
 */

import java.awt.*;

public class Dot {
    private int x, y;
    private final int size = 10;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledCircle(x, y, size);
    }
}
