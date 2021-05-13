/*
This class is used to store all the GUI edges of the game
 */

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public final class GUI {
    public static final Font occupiedFont = new Font("Times New Roman", Font.BOLD, 50);
    public static final Font font1 = new Font("Microsoft YaHei", 2, 100);
    public static final Font font2 = new Font("New Roman", 1, 30);
    public static final Font font3 = new Font("Microsoft YaHei", 1, 50);
    public static final Font font4 = new Font("Microsoft YaHei", 1, 40);

    public static int beginPlay() {
        StdDraw.setXscale(-500, 500);
        StdDraw.setYscale(-350, 350);
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        StdDraw.setFont(font1);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0, 200, "Dots and Boxes");
        StdDraw.setPenColor(Color.CYAN);
        Rectangle rectangle1 = new Rectangle(-150, 0, 300, 100);
        Rectangle rectangle2 = new Rectangle(-150, -150, 300, 100);
        Rectangle rectangle3 = new Rectangle(-150, -300, 300, 100);
        StdDraw.filledRectangle(0, 50, 150, 50);
        StdDraw.filledRectangle(0, -100, 150, 50);
        StdDraw.filledRectangle(0, -250, 150, 50);
        StdDraw.setPenColor(Color.black);
        StdDraw.setFont(new Font("New Roman", 1, 30));
        StdDraw.text(0, 50, "Play Game");
        StdDraw.text(0, -100, "Load Game");
        StdDraw.text(0, -250, "Exit Game");
        if (rectangle1.contains(mousePoint) && isMousePressed) {
            return 1;
        }
        if (rectangle2.contains(mousePoint) && isMousePressed) {
            return 2;
        }
        if (rectangle3.contains(mousePoint) && isMousePressed) {
            return 3;
        }
        return 0;
    }

    public static int startingGUI() {
        StdDraw.setXscale(-1000, 1000);
        StdDraw.setYscale(-500, 500);
        boolean isMousePressed = StdDraw.isMousePressed();
        StdDraw.setFont(font1);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0, 200, "Dots and Boxes");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(-600, -300, 200, 100);
        StdDraw.filledRectangle(0, -300, 200, 100);
        StdDraw.filledRectangle(600, -300, 200, 100);
        StdDraw.filledRectangle(-800, 457, 200, 43);
        StdDraw.setFont(font2);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(-600, -250, "Machine");
        StdDraw.text(-600, -300, "vs.");
        StdDraw.text(-600, -350, "Machine");
        StdDraw.text(0, -250, "Human");
        StdDraw.text(0, -300, "vs.");
        StdDraw.text(0, -350, "Human");
        StdDraw.text(600, -250, "Human");
        StdDraw.text(600, -300, "vs.");
        StdDraw.text(600, -350, "Machine");
        StdDraw.text(-800, 457, "Back to Menu");
        Rectangle rectangle = new Rectangle(-1000, 413, 400, 86);
        Rectangle rectangle1 = new Rectangle(-800, -400, 400, 200);
        Rectangle rectangle2 = new Rectangle(-200, -400, 400, 200);
        Rectangle rectangle3 = new Rectangle(400, -400, 400, 200);
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        if (rectangle1.contains(mousePoint) && isMousePressed) {
            return 1;
        }
        if (rectangle2.contains(mousePoint) && isMousePressed) {
            return 2;
        }
        if (rectangle3.contains(mousePoint) && isMousePressed) {
            return 3;
        }
        if ((rectangle.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            return -1;
        }
        return 0;
    }

    public static int chooseSize() {
        StdDraw.setFont(font1);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0, 200, "Choose the size");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(-750, -300, 150, 100);
        StdDraw.filledRectangle(-250, -300, 150, 100);
        StdDraw.filledRectangle(250, -300, 150, 100);
        StdDraw.filledRectangle(750, -300, 150, 100);
        StdDraw.filledRectangle(-800, 457, 200, 43);
        StdDraw.filledRectangle(-100,-100,80,30);
        StdDraw.filledRectangle(100,-100,80,30);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font2);
        StdDraw.text(0,110,"Input the number between 3 and 7:");
        StdDraw.text(0,75,"(click \"enter\" after confirming.)");
        StdDraw.text(-750, -300, "3 * 3");
        StdDraw.text(-250, -300, "4 * 4");
        StdDraw.text(250, -300, "5 * 5");
        StdDraw.text(750, -300, "Random");
        StdDraw.text(-100,-100,"enter");
        StdDraw.text(100,-100,"back");
        StdDraw.text(-800, 457, "Back to Menu");
        StdDraw.text(0,-180,"or click the block:");
        Rectangle rectangle = new Rectangle(-1000, 413, 400, 86);
        Rectangle rectangle1 = new Rectangle(-900, -400, 300, 200);
        Rectangle rectangle2 = new Rectangle(-400, -400, 300, 200);
        Rectangle rectangle3 = new Rectangle(100, -400, 300, 200);
        Rectangle rectangle4 = new Rectangle(600, -400, 300, 200);
        Rectangle rectangle5 = new Rectangle(-180,-130,160,60);
        Rectangle rectangle6 = new Rectangle(20,-130,160,60);
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        if (rectangle1.contains(mousePoint) && StdDraw.isMousePressed()) {
            return 1;
        }
        if (rectangle2.contains(mousePoint) && StdDraw.isMousePressed()) {
            return 2;
        }
        if (rectangle3.contains(mousePoint) && StdDraw.isMousePressed()) {
            return 3;
        }
        if (rectangle4.contains(mousePoint) && StdDraw.isMousePressed()) {
            return 4;
        }
        if ((rectangle.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            return -1;
        }
        if((rectangle5.contains(mousePoint)&&StdDraw.isMousePressed())||StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){
            return -2;
        }
        if((rectangle6.contains(mousePoint)&&StdDraw.isMousePressed())||StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
            return -3;
        }
        return 0;
    }

    public static int chooseSide() {
        StdDraw.clear();
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font1);
        StdDraw.text(0, 200, "Choose your side");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledEllipse(-250, -150, 100, 75);
        StdDraw.filledEllipse(250, -150, 100, 75);
        StdDraw.filledRectangle(-400, 320, 100, 30);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font2);
        StdDraw.text(-250, -150, "Offensive");
        StdDraw.text(250, -150, "Defensive");
        StdDraw.text(-400, 320, "Back to Menu");
        Ellipse ellipse1 = new Ellipse(-250, -150, 100, 75);
        Ellipse ellipse2 = new Ellipse(250, -150, 100, 75);
        Rectangle rectangle = new Rectangle(-500, 290, 200, 60);
        Point2D mousePoint2D = new Point2D(StdDraw.mouseX(), StdDraw.mouseY());
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        if (ellipse1.contains(mousePoint2D) && StdDraw.isMousePressed()) {
            return 1;
        }
        if (ellipse2.contains(mousePoint2D) && StdDraw.isMousePressed()) {
            return 2;
        }
        if ((rectangle.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            return -1;
        }
        return 0;
    }

    public static int chooseLevel() {
        StdDraw.clear();
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font1);
        StdDraw.text(0, 200, "Choose your level");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledEllipse(-300, -150, 100, 75);
        StdDraw.filledEllipse(0, -150, 100, 75);
        StdDraw.filledEllipse(300, -150, 100, 75);
        StdDraw.filledRectangle(-400, 320, 100, 30);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font2);
        StdDraw.text(-300, -150, "Easy");
        StdDraw.text(0, -150, "Normal");
        StdDraw.text(300, -150, "Super AI");
        StdDraw.text(-400, 320, "Back to Menu");
        Ellipse ellipse1 = new Ellipse(-300, -150, 100, 75);
        Ellipse ellipse2 = new Ellipse(0, -150, 100, 75);
        Ellipse ellipse3 = new Ellipse(300, -150, 100, 75);
        Rectangle rectangle = new Rectangle(-500, 290, 200, 60);
        Point2D mousePoint2D = new Point2D(StdDraw.mouseX(), StdDraw.mouseY());
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        if (ellipse1.contains(mousePoint2D) && StdDraw.isMousePressed()) {
            return 1;
        }
        if (ellipse2.contains(mousePoint2D) && StdDraw.isMousePressed()) {
            return 2;
        }
        if (ellipse3.contains(mousePoint2D) && StdDraw.isMousePressed()) {
            return 3;
        }
        if ((rectangle.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            return -1;
        }
        return 0;
    }

    public static int calculateScores(int[][] boxes) {
        StdDraw.clear();
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        int O_point = 0, D_point = 0;
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                if (boxes[i][j] > 0) {
                    O_point++;
                } else {
                    D_point++;
                }
            }
        }
        StdDraw.setPenColor((O_point > D_point) ? Color.RED : Color.BLUE);
        if (O_point == D_point) {
            StdDraw.setPenColor(Color.BLACK);
        }
        String string1 = "Offensive side gets " + O_point + " points";
        String string2 = "Defensive side gets " + D_point + " points";
        String winner = (O_point > D_point) ? "Offensive side wins !" : "Defensive side wins !";
        if (O_point == D_point) {
            winner = "Game over, no winner.";
        }
        StdDraw.setFont(occupiedFont);
        StdDraw.text(0, 200, string1);
        StdDraw.text(0, 140, string2);
        StdDraw.text(0, 80, winner);
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(-350, -100, 150, 75);
        StdDraw.filledRectangle(0, -100, 150, 75);
        StdDraw.filledRectangle(350, -100, 150, 75);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(occupiedFont);
        StdDraw.text(-350, -100, "Review");
        StdDraw.text(0, -100, "Play Again");
        StdDraw.text(350, -100, "Exit");
        StdDraw.show();
        Point point = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        Rectangle rectangle1 = new Rectangle(-500, -175, 300, 150);
        Rectangle rectangle2 = new Rectangle(-150, -175, 300, 150);
        Rectangle rectangle3 = new Rectangle(300, -175, 300, 150);
        if (rectangle1.contains(point) && StdDraw.isMousePressed()) {
            return 1;
        } else if (rectangle2.contains(point) && StdDraw.isMousePressed()) {
            return 2;
        } else if (rectangle3.contains(point) && StdDraw.isMousePressed()) {
            return 3;
        } else {
            return 0;
        }
    }

    public static int drawNumberBoard(int a, int b, int c, int d, String string) {
        ArrayList<Circle> circles = new ArrayList<>();
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        StdDraw.setPenColor(Color.CYAN);
        Circle circle0 = new Circle(0, -200, 50);

        //draw basic screen
        StdDraw.filledRectangle(400, -320, 100, 30);
        StdDraw.filledRectangle(-225, 300, 50, 50);
        StdDraw.filledRectangle(-75, 300, 50, 50);
        StdDraw.filledRectangle(75, 300, 50, 50);
        StdDraw.filledRectangle(225, 300, 50, 50);
        StdDraw.filledCircle(-100, -200, 50);
        StdDraw.filledCircle(0, -200, 50);
        StdDraw.filledCircle(100, -200, 50);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font4);
        StdDraw.text(-100, -200, "enter");
        StdDraw.text(100, -200, "back");
        StdDraw.text(0, -200, "0");
        StdDraw.setFont(font2);
        StdDraw.text(0, 210, string);
        StdDraw.text(400, -320, "Back to Menu");
        Rectangle rectangle = new Rectangle(300, -350, 200, 60);
        StdDraw.setFont(font3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                circles.add(new Circle(-100 + j * 100, -100 + i * 100, 50));
                StdDraw.setPenColor(Color.CYAN);
                StdDraw.filledCircle(-100 + j * 100, -100 + i * 100, 50);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(-100 + j * 100, -100 + i * 100, (j + 1 + i * 3) + "");
            }
        }

        if (a == -1) {
            StdDraw.setPenColor(Color.CYAN);
        }
        StdDraw.text(-225, 300, a + "");
        if (b == -1) {
            StdDraw.setPenColor(Color.CYAN);
        }
        StdDraw.text(-75, 300, b + "");
        if (c == -1) {
            StdDraw.setPenColor(Color.CYAN);
        }
        StdDraw.text(75, 300, c + "");
        if (d == -1) {
            StdDraw.setPenColor(Color.CYAN);
        }
        StdDraw.text(225, 300, d + "");

        //test whether and which is being pressed
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Method.containedInCircle(mousePoint, circles.get(j + i * 3)) && StdDraw.isMousePressed()) {
                    return j + 1 + i * 3;
                }
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
            return 1;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
            return 2;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_3)) {
            return 3;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_4)) {
            return 4;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_5)) {
            return 5;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_6)) {
            return 6;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_7)) {
            return 7;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_8)) {
            return 8;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_9)) {
            return 9;
        }
        if ((Method.containedInCircle(mousePoint, circle0) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_0)) {
            return 0;
        }
        if ((rectangle.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            return -2;
        }
        return -1;
    }

    public static int[] numberBoardFunction() {
        int[] reply = {0, 0};
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        Circle circle = new Circle(-100, -200, 50);
        Circle circle1 = new Circle(100, -200, 50);
        if ((Method.containedInCircle(mousePoint, circle) && StdDraw.isMousePressed())||StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
            reply[0] = 1;
            return reply;
        }
        if ((Method.containedInCircle(mousePoint, circle1) && StdDraw.isMousePressed())||StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
            reply[1] = 1;
            return reply;
        }
        return reply;
    }

    public static int doCover() {
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        StdDraw.setFont(font3);
        StdDraw.text(0, 200, "The security number have existed,");
        StdDraw.text(0, 100, "do you want cover it?");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(-250, -100, 100, 50);
        StdDraw.filledRectangle(250, -100, 100, 50);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(-250, -100, "YES");
        StdDraw.text(250, -100, "NO");
        Rectangle rectangleYes = new Rectangle(-350, -150, 200, 100);
        Rectangle rectangleNo = new Rectangle(150, -150, 200, 100);
        if (rectangleYes.contains(mousePoint) && StdDraw.isMousePressed()) {
            return 2;
        }
        if (rectangleNo.contains(mousePoint) && StdDraw.isMousePressed()) {
            return 1;
        }
        return 0;
    }

    public static int drawNumber(int a,int b ){
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledRectangle(-100,0,50,50);
        StdDraw.setFont(font3);
        StdDraw.text(0,0,"X");
        StdDraw.filledRectangle(100,0,50,50);
        StdDraw.setPenColor(Color.BLACK);
        if (a == -1) {
            StdDraw.setPenColor(Color.CYAN);
        }
        StdDraw.text(-100, 0, a + "");
        if (b == -1) {
            StdDraw.setPenColor(Color.CYAN);
        }
        StdDraw.text(100, 0, b + "");

        if (StdDraw.isKeyPressed(KeyEvent.VK_3)) {
            System.out.printf("2");return 3;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_4)) {
            return 4;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_5)) {
            return 5;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_6)) {
            return 6;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_7)) {
            return 7;
        }
        return -1;
    }
}
