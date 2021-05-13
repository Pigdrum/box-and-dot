import java.awt.*;

public class Block {
    private int x, y;
    private final int width = 10, height = 10;
    private Color color;

    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(x, y, width / 2, height / 2);
    }
}
