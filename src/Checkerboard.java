/*
This class is used to set up a basic checkerboard
Including drawing it, get dots and edges (whether they are drawn) in it
 */

import java.util.ArrayList;

public class Checkerboard {
    private int x, y;
    private ArrayList<Dot> dots = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Checkerboard(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawBasicCheckerboard() {
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                dots.add(new Dot(100 * i - 50 * x, 100 * j - 50 * y));
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j <= x; j++) {
                edges.add(new Edge(-50 * x + 100 * j, 50 * (y - 1) - 100 * i, true));
            }
        }
        for (int i = 0; i <= y; i++) {
            for (int j = 0; j < x; j++) {
                edges.add(new Edge(-50 * (x - 1) + 100 * j, 50 * y - 100 * i, false));
            }
        }
        for (Dot e : dots) {
            e.draw();
        }
        StdDraw.show();
    }
}
