/*
This class is used to store all the static methods in the game
 */

import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.*;
import java.util.Random;

public class Method {
    //to get the random edge for each side
    public static int[] randomEdge(int step, int totalStep, int[][] vertical, int[][] horizontal) {
        Random random = new Random();
        int counter = random.nextInt(totalStep);
        int[] result = new int[3];
        for (int i = 0; i < vertical.length; i++) {
            for (int j = 0; j < vertical[0].length; j++) {
                if (counter == 0 && vertical[i][j] == 0) {
                    if (step % 2 == 0) {
                        vertical[i][j]++;
                    } else {
                        vertical[i][j]--;
                    }
                    result[0] = 0;
                    result[1] = i;
                    result[2] = j;
                    return result;
                }
                if (vertical[i][j] == 0) {
                    counter--;
                }
            }
        }
        for (int i = 0; i < horizontal.length; i++) {
            for (int j = 0; j < horizontal[0].length; j++) {
                if (counter == 0 && horizontal[i][j] == 0) {
                    if (step % 2 == 0) {
                        horizontal[i][j]++;
                    } else {
                        horizontal[i][j]--;
                    }
                    result[0] = 1;
                    result[1] = i;
                    result[2] = j;
                    return result;
                }
                if (horizontal[i][j] == 0) {
                    counter--;
                }
            }
        }
        return result;
    }

    //to find the only "result" array according to the coordinate
    public static int[] getResult(Edge e, int x, int y) {
        int[] result = new int[3];
        if (e.isVertical()) {
            result[0] = 0;
            result[1] = (50 * (y - 1) - e.getY()) / 100;
            result[2] = (e.getX() + 50 * x) / 100;
            return result;
        }
        result[0] = 1;
        result[1] = (50 * y - e.getY()) / 100;
        result[2] = (e.getX() + 50 * (x - 1)) / 100;
        return result;
    }

    //to check if the box on the left/right/upper/lower block of new occupied edge is occupied
    public static int getPoint(int step, int[] result, int[][] vertical, int[][] horizontal, int[][] boxes) {
        if (result[0] == 0) {
            if ((boxLeft(step, result[1], result[2], vertical, horizontal, boxes) &&
                    boxRight(step, result[1], result[2], vertical, horizontal, boxes)) ||
                    boxLeft(step, result[1], result[2], vertical, horizontal, boxes) ||
                    boxRight(step, result[1], result[2], vertical, horizontal, boxes)) {
            } else {
                step++;
            }
        } else {
            if ((boxUpper(step, result[1], result[2], vertical, horizontal, boxes) &&
                    boxLower(step, result[1], result[2], vertical, horizontal, boxes)) ||
                    boxUpper(step, result[1], result[2], vertical, horizontal, boxes) ||
                    boxLower(step, result[1], result[2], vertical, horizontal, boxes)) {
            } else {
                step++;
            }
        }
        return step;
    }

    public static boolean boxLeft(int step, int x, int y, int[][] vertical, int[][] horizontal, int[][] boxes) {
        if (y == 0 || vertical[x][y - 1] == 0 || horizontal[x][y - 1] == 0 || horizontal[x + 1][y - 1] == 0) {
            return false;
        } else {
            if (step % 2 == 0) {
                boxes[x][y - 1] = 1;
            } else {
                boxes[x][y - 1] = -1;
            }
            return true;
        }
    }

    public static boolean boxRight(int step, int x, int y, int[][] vertical, int[][] horizontal, int[][] boxes) {
        if (y == vertical[0].length - 1 || vertical[x][y + 1] == 0 || horizontal[x][y] == 0 || horizontal[x + 1][y] == 0) {
            return false;
        } else {
            if (step % 2 == 0) {
                boxes[x][y] = 1;
            } else {
                boxes[x][y] = -1;
            }
            return true;
        }
    }

    public static boolean boxUpper(int step, int x, int y, int[][] vertical, int[][] horizontal, int[][] boxes) {
        if (x == 0 || horizontal[x - 1][y] == 0 || vertical[x - 1][y] == 0 || vertical[x - 1][y + 1] == 0) {
            return false;
        } else {
            if (step % 2 == 0) {
                boxes[x - 1][y] = 1;
            } else {
                boxes[x - 1][y] = -1;
            }
            return true;
        }
    }

    public static boolean boxLower(int step, int x, int y, int[][] vertical, int[][] horizontal, int[][] boxes) {
        if (x == horizontal.length - 1 || horizontal[x + 1][y] == 0 || vertical[x][y] == 0 || vertical[x][y + 1] == 0) {
            return false;
        } else {
            if (step % 2 == 0) {
                boxes[x][y] = 1;
            } else {
                boxes[x][y] = -1;
            }
            return true;
        }
    }

    //reset and redraw the checkerboard when necessary
    public static void resetCheckerboard(int[][] vertical, int[][] horizontal, Checkerboard checkerboard) {
        for (int i = 0; i < vertical.length; i++) {
            for (int j = 0; j < vertical[0].length; j++) {
                for (Edge e : checkerboard.getEdges()) {
                    if (e.getX() == -50 * horizontal[0].length + 100 * j
                            && e.getY() == 50 * (vertical.length - 1) - 100 * i) {
                        if (vertical[i][j] != 0) {
                            e.setFree(false);
                            e.setColor((vertical[i][j] > 0) ? Color.RED : Color.BLUE);
                        } else {
                            e.setFree(true);
                            e.setColor(Color.WHITE);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < horizontal.length; i++) {
            for (int j = 0; j < horizontal[0].length; j++) {
                for (Edge e : checkerboard.getEdges()) {
                    if (e.getX() == -50 * (horizontal[0].length - 1) + 100 * j
                            && e.getY() == 50 * vertical.length - 100 * i) {
                        if (horizontal[i][j] != 0) {
                            e.setFree(false);
                            e.setColor((horizontal[i][j] > 0) ? Color.RED : Color.BLUE);
                        } else {
                            e.setFree(true);
                            e.setColor(Color.WHITE);
                        }
                    }
                }
            }
        }
    }

    public static void redraw(Checkerboard checkerboard, int[][] boxes) {
        for (Edge e : checkerboard.getEdges()) {
            if (!e.getColor().equals(Color.WHITE)) {
                StdDraw.setPenColor(e.getColor());
                StdDraw.filledRectangle(e.getX(), e.getY(), e.getWidth() / 2, e.getHeight() / 2);
            }
        }
        int O_point = 0, D_point = 0;
        String text;
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                if (boxes[i][j] != 0) {
                    StdDraw.setFont(GUI.occupiedFont);
                    if (boxes[i][j] > 0) {
                        StdDraw.setPenColor(Color.RED);
                        text = "O";
                        O_point++;
                    } else {
                        StdDraw.setPenColor(Color.BLUE);
                        text = "D";
                        D_point++;
                    }
                    StdDraw.text(-50 * (boxes[0].length - 1) + 100 * j, 50 * (boxes.length - 1) - 100 * i, text);
                }
            }
        }
        StdDraw.setFont(GUI.font2);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(-425, 100, "Offensive");
        StdDraw.text(-425, 0, "Score");
        StdDraw.text(-425, -100, O_point + "");
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.text(425, 100, "Defensive");
        StdDraw.text(425, 0, "Score");
        StdDraw.text(425, -100, D_point + "");
        for (Dot d : checkerboard.getDots()) {
            d.draw();
        }
    }

    public static int[][] copy2DArray(int[][] formal) {
        int[][] copy = new int[formal.length][formal[0].length];
        for (int i = 0; i < formal.length; i++) {
            for (int j = 0; j < formal[0].length; j++) {
                copy[i][j] = formal[i][j];
            }
        }
        return copy;
    }

    public static int[] getNumberInput(String string) throws InterruptedException {
        int counter = 0;
        int[][] input = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
        while (true) {
            while (true) {
                StdDraw.clear();
                StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                switch (counter) {
                    case (0):
                        input[1][0] = GUI.drawNumberBoard(input[0][0], input[0][1], input[0][2], input[0][3], string);
                        if (input[1][0] > -1) {
                            counter++;
                            Thread.sleep(200);
                        } else if (input[1][0] == -2) {
                            return null;
                        }
                        break;
                    case (1):
                        input[2][1] = GUI.drawNumberBoard(input[1][0], input[1][1], input[1][2], input[1][3], string);
                        if (input[2][1] > -1) {
                            input[2][0] = input[1][0];
                            counter++;
                            Thread.sleep(200);
                        } else if (input[2][1] == -2) {
                            return null;
                        }
                        break;
                    case (2):
                        input[3][2] = GUI.drawNumberBoard(input[2][0], input[2][1], input[2][2], input[2][3], string);
                        if (input[3][2] > -1) {
                            input[3][1] = input[2][1];
                            input[3][0] = input[2][0];
                            counter++;
                            Thread.sleep(200);
                        } else if (input[3][2] == -2) {
                            return null;
                        }
                        break;
                    case (3):
                        input[4][3] = GUI.drawNumberBoard(input[3][0], input[3][1], input[3][2], input[3][3], string);
                        if (input[4][3] > -1) {
                            input[4][0] = input[3][0];
                            input[4][1] = input[3][1];
                            input[4][2] = input[3][2];
                            counter++;
                            Thread.sleep(200);
                        } else if (input[4][3] == -2) {
                            return null;
                        }
                        break;
                    default:
                        GUI.drawNumberBoard(input[4][0], input[4][1], input[4][2], input[4][3], string);
                }
                //if click "back"
                if (GUI.numberBoardFunction()[1] != 0) {
                    for (int j = 0; j < 4; j++) {
                        input[counter][j] = -1;
                    }
                    if (counter != 0) {
                        counter--;
                    }
                    Thread.sleep(200);
                    break;
                }
                //if click "sure"
                if ((GUI.numberBoardFunction()[0] != 0) && counter == 4) {
                    return input[4];
                }
                StdDraw.show();
                Thread.sleep(20);
            }
        }
    }

    public static State readGame(int index) {
        String path = index + ".txt";
        File file = new File(path);
        BufferedReader reader = null;
        State state = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split("a");
                int x = Integer.parseInt(strings[0]);
                int y = Integer.parseInt(strings[1]);
                int[][] vertical = getFromFile(strings[2], y, x + 1);
                int[][] horizontal = getFromFile(strings[3], y + 1, x);
                int[][] boxes = getFromFile(strings[4], y, x);
                int step = Integer.parseInt(strings[5]);
                int totalStep = Integer.parseInt(strings[6]);
                int firstResponse = Integer.parseInt(strings[7]);
                int secondResponse = Integer.parseInt(strings[8]);
                int thirdResponse = Integer.parseInt(strings[9]);
                int levelResponse = Integer.parseInt(strings[10]);
                state = new State(step, totalStep, vertical, horizontal, boxes);
                state.setFirstResponse(firstResponse);
                state.setSecondResponse(secondResponse);
                state.setThirdResponse(thirdResponse);
                state.setLevelResponse(levelResponse);
                state.setX(x);
                state.setY(y);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return state;
    }

    public static int[][] getFromFile(String s, int x, int y) {
        int[][] i1 = new int[x][y];
        String[] strings = s.split("e");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                i1[i][j] = Integer.parseInt(strings[j + i * y]);
            }
        }
        return i1;
    }

    public static void save(State state, int firstResponse, int secondResponse, int thirdResponse, int levelResponse, int index, int x, int y) {
        String path = index + ".txt";
        File file = new File(path);
        String verticalst = "";
        String horizontalst = "";
        String boxesst = "";
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < state.getVertical().length; i++) {
                for (int j = 0; j < state.getVertical()[0].length; j++) {
                    if (i + j == state.getVertical().length + state.getVertical()[0].length - 2) {
                        verticalst = verticalst + "" + state.getVertical()[i][j];
                        break;
                    }
                    verticalst = verticalst + state.getVertical()[i][j];
                    verticalst = verticalst + "e";
                }
            }
            for (int i = 0; i < state.getHorizontal().length; i++) {
                for (int j = 0; j < state.getHorizontal()[0].length; j++) {
                    if (i + j == state.getHorizontal().length + state.getHorizontal()[0].length - 2) {
                        horizontalst = horizontalst + "" + state.getHorizontal()[i][j];
                        break;
                    }
                    horizontalst = horizontalst + state.getHorizontal()[i][j] + "e";
                }
            }
            for (int i = 0; i < state.getBoxes().length; i++) {
                for (int j = 0; j < state.getBoxes()[0].length; j++) {
                    if (i + j == state.getBoxes().length + state.getBoxes()[0].length - 2) {
                        boxesst = boxesst + "" + state.getBoxes()[i][j];
                        break;
                    }
                    boxesst = boxesst + state.getBoxes()[i][j] + "e";
                }
            }
            fileWriter.flush();
            bufferedWriter.flush();
            fileWriter.write(x + "a" + y + "a" + verticalst + "a" + horizontalst + "a" + boxesst + "a" + state.getStep() + "a" + state.getTotalStep() + "a"
                    + firstResponse + "a" + secondResponse + "a" + thirdResponse + "a" + levelResponse);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean containedInCircle(Point point, Circle circle) {
        if ((point.getX() - circle.getCenterX()) * (point.getX() - circle.getCenterX()) + (point.getY() - circle.getCenterY()) * (point.getY() - circle.getCenterY())
                <= circle.getRadius() * circle.getRadius()) {
            return true;
        } else return false;
    }
}
