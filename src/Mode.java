/*
This class is used to store all the modes of the game
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Mode {
    public static int[] human(int levelResponse, int x, int y, int[][] vertical, int[][] horizontal, int[][] boxes,
                              int step, int totalStep, Checkerboard checkerboard) throws InterruptedException {
        int initialStep = totalStep;
        while (true) {
            Rectangle undo = new Rectangle(350, 275, 120, 50);
            Rectangle save = new Rectangle(-470, 275, 120, 50);
            Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
            if (undo.contains(mousePoint) && StdDraw.isMousePressed()) {
                return null;
            } else if (save.contains(mousePoint) && StdDraw.isMousePressed()) {
                Main.save = true;
                return null;
            } else {
                for (Edge e : checkerboard.getEdges()) {
                    if (e.getBounds().contains(mousePoint) && e.isFree()) {
                        e.setVisible(true);
                        if (StdDraw.isMousePressed()) {
                            e.setVisible(false);
                            e.setFree(false);
                            totalStep--;
                            int[] result = Method.getResult(e, x, y);
                            if (result[0] == 0) {
                                vertical[result[1]][result[2]] = (step % 2 == 0) ? 1 : -1;
                            } else {
                                horizontal[result[1]][result[2]] = (step % 2 == 0) ? 1 : -1;
                            }
                            step = Method.getPoint(step, result, vertical, horizontal, boxes);
                        }
                    } else {
                        e.setVisible(false);
                    }
                }
                StdDraw.clear();
                StdDraw.setPenColor(StdDraw.CYAN);
                StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                if (levelResponse == 3) {
                    StdDraw.picture(-425, -250, ".\\picture\\ai.png", 160, 228);
                    StdDraw.picture(425, -250, ".\\picture\\ai2.png", 160, 228);
                }
                StdDraw.filledRectangle(410, 300, 60, 25);
                StdDraw.filledRectangle(-410, 300, 60, 25);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setFont(GUI.font2);
                StdDraw.text(410, 300, "Undo");
                StdDraw.text(-410, 300, "Save");
                for (Edge e : checkerboard.getEdges()) {
                    e.see(step % 2 == 0, e.isVisible());
                }
                Method.resetCheckerboard(vertical, horizontal, checkerboard);
                Method.redraw(checkerboard, boxes);
                StdDraw.show();
                if (initialStep - totalStep == 1) {
                    int[] info = {totalStep, step};
                    return info;
                }
            }
            Thread.sleep(20);
        }
    }

    public static int idiot(int[][] vertical, int[][] horizontal, int[][] boxes, int step, int totalStep, Checkerboard checkerboard) throws InterruptedException {
        Thread.sleep(300);
        int[] result = Method.randomEdge(step, totalStep, vertical, horizontal);
        step = Method.getPoint(step, result, vertical, horizontal, boxes);
        Method.resetCheckerboard(vertical, horizontal, checkerboard);
        Method.redraw(checkerboard, boxes);
        StdDraw.show();
        return step;
    }

    public static void HumanVsAI(int thirdResponse, int levelResponse, int x, int y, int[][] vertical, int[][] horizontal, int[][] boxes,
                                 int step, int totalStep, Checkerboard checkerboard) throws InterruptedException {
        switch (thirdResponse) {
            case (1):
                while (totalStep != 0) {
                    if (step % 2 == 0) {
                        int[] info = human(levelResponse, x, y, vertical, horizontal, boxes, step, totalStep, checkerboard);
                        if (Main.save) {
                            break;
                        }
                        if (info != null) {
                            totalStep = info[0];
                            step = info[1];
                            Main.stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
                        } else if (Main.stateList.size() >= 2) {
                            int last = Main.stateList.size() - 2;
                            Main.stateList.remove(last + 1);
                            for (int i = last; i >= 0; i--) {
                                if (Main.stateList.get(i).getStep() % 2 == 1) {
                                    Main.stateList.remove(i);
                                } else {
                                    step = Main.stateList.get(i).getStep();
                                    totalStep = Main.stateList.get(i).getTotalStep();
                                    vertical = Method.copy2DArray(Main.stateList.get(i).getVertical());
                                    horizontal = Method.copy2DArray(Main.stateList.get(i).getHorizontal());
                                    boxes = Method.copy2DArray(Main.stateList.get(i).getBoxes());
                                    Method.resetCheckerboard(vertical, horizontal, checkerboard);
                                    Method.redraw(checkerboard, boxes);
                                    StdDraw.show();
                                    Thread.sleep(300);
                                    break;
                                }
                            }
                        }
                    } else {
                        Thread.sleep(500);
                        if (levelResponse == 1) {
                            StdDraw.clear();
                            StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                            step = idiot(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                        } else if (levelResponse == 2){
                            step = normal(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                        } else {
                            step = superAI(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                        }
                        Main.stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
                    }
                }
                break;
            case (2):
                while (totalStep != 0) {
                    if (step % 2 == 0) {
                        Thread.sleep(500);
                        if (levelResponse == 1) {
                            StdDraw.clear();
                            StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                            step = idiot(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                        } else if (levelResponse == 2){
                            step = normal(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                        } else {
                            step = superAI(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                        }
                        Main.stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
                    } else {
                        int[] info = human(levelResponse, x, y, vertical, horizontal, boxes, step, totalStep, checkerboard);
                        if (Main.save) {
                            break;
                        }
                        if (info != null) {
                            totalStep = info[0];
                            step = info[1];
                            Main.stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
                        } else if (Main.stateList.size() > 2) {
                            int last = Main.stateList.size() - 2;
                            Main.stateList.remove(last + 1);
                            for (int i = last; i >= 0; i--) {
                                if (Main.stateList.get(i).getStep() % 2 == 0) {
                                    Main.stateList.remove(i);
                                } else {
                                    step = Main.stateList.get(i).getStep();
                                    totalStep = Main.stateList.get(i).getTotalStep();
                                    vertical = Method.copy2DArray(Main.stateList.get(i).getVertical());
                                    horizontal = Method.copy2DArray(Main.stateList.get(i).getHorizontal());
                                    boxes = Method.copy2DArray(Main.stateList.get(i).getBoxes());
                                    Method.resetCheckerboard(vertical, horizontal, checkerboard);
                                    Method.redraw(checkerboard, boxes);
                                    StdDraw.show();
                                    Thread.sleep(300);
                                    break;
                                }
                            }
                        }
                    }
                }
            default:
        }
    }

    public static int[] ableToGetPoint(int[][] vertical, int[][] horizontal, int[][] boxes) {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                if (Math.abs(vertical[i][j]) + Math.abs(vertical[i][j + 1]) +
                        Math.abs(horizontal[i][j]) + Math.abs(horizontal[i + 1][j]) == 3) {
                    int[] result = new int[3];
                    result[0] = (vertical[i][j] == 0 || vertical[i][j + 1] == 0) ? 0 : 1;
                    result[1] = (horizontal[i + 1][j] != 0) ? i : i + 1;
                    result[2] = (vertical[i][j + 1] != 0) ? j : j + 1;
                    return result;
                }
            }
        }
        return null;
    }

    public static int normal(int[][] vertical, int[][] horizontal, int[][] boxes, int step, int totalStep, Checkerboard checkerboard) throws InterruptedException {
        Thread.sleep(200);
        int[] result = new int[3];

        boolean noBroken = true;
        boolean noFree = true;

        int[] bestStep = ableToGetPoint(vertical, horizontal, boxes);
        if (bestStep != null) {
            result = bestStep;
            noBroken = false;
            noFree = false;
        }

        lable:
        while (noBroken) {
            for (int i = 0, j = vertical[0].length - 1; i < vertical.length; i++) {
                if (vertical[i][0] == 0 && Math.abs(vertical[i][1]) + Math.abs(horizontal[i][0]) + Math.abs(horizontal[i + 1][0]) < 2) {
                    result[0] = 0;
                    result[1] = i;
                    result[2] = 0;
                    noFree = false;
                    break lable;
                }
                if (vertical[i][j] == 0 && Math.abs(vertical[i][j - 1]) + Math.abs(horizontal[i][j - 1]) + Math.abs(horizontal[i + 1][j - 1]) < 2) {
                    result[0] = 0;
                    result[1] = i;
                    result[2] = j;
                    noFree = false;
                    break lable;
                }
            }
            for (int j = 0, i = horizontal.length - 1; j < horizontal[0].length; j++) {
                if (horizontal[0][j] == 0 && Math.abs(horizontal[1][j]) + Math.abs(vertical[0][j]) + Math.abs(vertical[0][j + 1]) < 2) {
                    result[0] = 1;
                    result[1] = 0;
                    result[2] = j;
                    noFree = false;
                    break lable;
                }
                if (horizontal[i][j] == 0 && Math.abs(horizontal[i - 1][j]) + Math.abs(vertical[i - 1][j]) + Math.abs(vertical[i - 1][j + 1]) < 2) {
                    result[0] = 1;
                    result[1] = i;
                    result[2] = j;
                    noFree = false;
                    break lable;
                }
            }
            for (int i = 0; i < boxes.length; i++) {
                for (int j = 0; j < boxes[0].length - 1; j++) {
                    if (vertical[i][j + 1] == 0 &&
                            Math.abs(vertical[i][j]) + Math.abs(horizontal[i][j]) + Math.abs(horizontal[i + 1][j]) < 2 &&
                            Math.abs(vertical[i][j + 2]) + Math.abs(horizontal[i][j + 1]) + Math.abs(horizontal[i + 1][j + 1]) < 2) {
                        result[0] = 0;
                        result[1] = i;
                        result[2] = j + 1;
                        noFree = false;
                        break lable;
                    }
                }
            }
            for (int i = 0; i < boxes.length - 1; i++) {
                for (int j = 0; j < boxes[0].length; j++) {
                    if (horizontal[i + 1][j] == 0 &&
                            Math.abs(horizontal[i][j]) + Math.abs(vertical[i][j]) + Math.abs(vertical[i][j + 1]) < 2 &&
                            Math.abs(horizontal[i + 2][j]) + Math.abs(vertical[i + 1][j]) + Math.abs(vertical[i + 1][j + 1]) < 2) {
                        result[0] = 1;
                        result[1] = i + 1;
                        result[2] = j;
                        noFree = false;
                        break lable;
                    }
                }
            }
            break;
        }

        if (noFree) {
            int[] losePoints = new int[totalStep];
            ArrayList<int[]> remainSteps = new ArrayList<>();
            for (int i = 0; i < vertical.length; i++) {
                for (int j = 0; j < vertical[0].length; j++) {
                    if (vertical[i][j] == 0) {
                        int[] remainStep = {0, i, j};
                        remainSteps.add(remainStep);
                    }
                }
            }
            for (int i = 0; i < horizontal.length; i++) {
                for (int j = 0; j < horizontal[0].length; j++) {
                    if (horizontal[i][j] == 0) {
                        int[] remainStep = {1, i, j};
                        remainSteps.add(remainStep);
                    }
                }
            }
            int minLosePoints = Integer.MAX_VALUE;
            for (int k = 0; k < totalStep; k++) {
                int[][] exVertical = Method.copy2DArray(vertical);
                int[][] exHorizontal = Method.copy2DArray(horizontal);
                if (remainSteps.get(k)[0] == 0) {
                    exVertical[remainSteps.get(k)[1]][remainSteps.get(k)[2]] = 1;
                } else {
                    exHorizontal[remainSteps.get(k)[1]][remainSteps.get(k)[2]] = 1;
                }
                int[] enemyStep = ableToGetPoint(exVertical, exHorizontal, boxes);
                while (enemyStep != null) {
                    if (enemyStep[0] == 0) {
                        exVertical[enemyStep[1]][enemyStep[2]] = 1;
                    } else {
                        exHorizontal[enemyStep[1]][enemyStep[2]] = 1;
                    }
                    losePoints[k]++;
                    enemyStep = ableToGetPoint(exVertical, exHorizontal, boxes);
                }
                minLosePoints = (losePoints[k] < minLosePoints) ? losePoints[k] : minLosePoints;
            }
            for (int k = 0; k < totalStep; k++) {
                if (losePoints[k] == minLosePoints) {
                    result = remainSteps.get(k);
                    break;
                }
            }
        }

        if (result[0] == 0) {
            vertical[result[1]][result[2]] = (step % 2 == 0) ? 1 : -1;
        } else {
            horizontal[result[1]][result[2]] = (step % 2 == 0) ? 1 : -1;
        }
        step = Method.getPoint(step, result, vertical, horizontal, boxes);
        StdDraw.clear();
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        Method.resetCheckerboard(vertical, horizontal, checkerboard);
        Method.redraw(checkerboard, boxes);
        StdDraw.show();
        return step;
    }

    private static URL url;
    private static AudioClip ac;
    private static File file1 = new File(".\\sound\\Q1.wav");
    private static File file2 = new File(".\\sound\\Q2.wav");
    private static File file3 = new File(".\\sound\\Q3.wav");
    private static File file4 = new File(".\\sound\\Q4.wav");
    private static File file5 = new File(".\\sound\\Q5.wav");

    public static int superAI(int[][] vertical, int[][] horizontal, int[][] boxes, int step, int totalStep, Checkerboard checkerboard) throws InterruptedException {
        Thread.sleep(200);
        int[] result = new int[3];
        boolean noBroken = true;
        boolean noFree = true;

        int[] bestStep = ableToGetPoint(vertical, horizontal, boxes);
        if (bestStep != null) {
            result = bestStep;
            noBroken = false;
            noFree = false;
        }

        if (noBroken) {
            ArrayList<int[]> remainSteps = new ArrayList<>();
            for (int i = 0, j = vertical[0].length - 1; i < vertical.length; i++) {
                if (vertical[i][0] == 0 && Math.abs(vertical[i][1]) + Math.abs(horizontal[i][0]) + Math.abs(horizontal[i + 1][0]) < 2) {
                    int[] remainStep = {0, i, 0};
                    remainSteps.add(remainStep);
                }
                if (vertical[i][j] == 0 && Math.abs(vertical[i][j - 1]) + Math.abs(horizontal[i][j - 1]) + Math.abs(horizontal[i + 1][j - 1]) < 2) {
                    int[] remainStep = {0, i, j};
                    remainSteps.add(remainStep);
                }
            }
            for (int j = 0, i = horizontal.length - 1; j < horizontal[0].length; j++) {
                if (horizontal[0][j] == 0 && Math.abs(horizontal[1][j]) + Math.abs(vertical[0][j]) + Math.abs(vertical[0][j + 1]) < 2) {
                    int[] remainStep = {1, 0, j};
                    remainSteps.add(remainStep);
                }
                if (horizontal[i][j] == 0 && Math.abs(horizontal[i - 1][j]) + Math.abs(vertical[i - 1][j]) + Math.abs(vertical[i - 1][j + 1]) < 2) {
                    int[] remainStep = {1, i, j};
                    remainSteps.add(remainStep);
                }
            }
            for (int i = 0; i < boxes.length; i++) {
                for (int j = 0; j < boxes[0].length - 1; j++) {
                    if (vertical[i][j + 1] == 0 &&
                            Math.abs(vertical[i][j]) + Math.abs(horizontal[i][j]) + Math.abs(horizontal[i + 1][j]) < 2 &&
                            Math.abs(vertical[i][j + 2]) + Math.abs(horizontal[i][j + 1]) + Math.abs(horizontal[i + 1][j + 1]) < 2) {
                        int[] remainStep = {0, i, j + 1};
                        remainSteps.add(remainStep);
                    }
                }
            }
            for (int i = 0; i < boxes.length - 1; i++) {
                for (int j = 0; j < boxes[0].length; j++) {
                    if (horizontal[i + 1][j] == 0 &&
                            Math.abs(horizontal[i][j]) + Math.abs(vertical[i][j]) + Math.abs(vertical[i][j + 1]) < 2 &&
                            Math.abs(horizontal[i + 2][j]) + Math.abs(vertical[i + 1][j]) + Math.abs(vertical[i + 1][j + 1]) < 2) {
                        int[] remainStep = {1, i + 1, j};
                        remainSteps.add(remainStep);
                    }
                }
            }
            if (remainSteps.size() != 0) {
                Random random = new Random();
                int edgeIndex = random.nextInt(remainSteps.size());
                result = remainSteps.get(edgeIndex);
                noFree = false;
            }
        }

        if (noFree) {
            int[] losePoints = new int[totalStep];
            ArrayList<int[]> remainSteps = new ArrayList<>();
            for (int i = 0; i < vertical.length; i++) {
                for (int j = 0; j < vertical[0].length; j++) {
                    if (vertical[i][j] == 0) {
                        int[] remainStep = {0, i, j};
                        remainSteps.add(remainStep);
                    }
                }
            }
            for (int i = 0; i < horizontal.length; i++) {
                for (int j = 0; j < horizontal[0].length; j++) {
                    if (horizontal[i][j] == 0) {
                        int[] remainStep = {1, i, j};
                        remainSteps.add(remainStep);
                    }
                }
            }
            int minLosePoints = Integer.MAX_VALUE;
            for (int k = 0; k < totalStep; k++) {
                int[][] exVertical = Method.copy2DArray(vertical);
                int[][] exHorizontal = Method.copy2DArray(horizontal);
                if (remainSteps.get(k)[0] == 0) {
                    exVertical[remainSteps.get(k)[1]][remainSteps.get(k)[2]] = 1;
                } else {
                    exHorizontal[remainSteps.get(k)[1]][remainSteps.get(k)[2]] = 1;
                }
                int[] enemyStep = ableToGetPoint(exVertical, exHorizontal, boxes);
                while (enemyStep != null) {
                    if (enemyStep[0] == 0) {
                        exVertical[enemyStep[1]][enemyStep[2]] = 1;
                    } else {
                        exHorizontal[enemyStep[1]][enemyStep[2]] = 1;
                    }
                    losePoints[k]++;
                    enemyStep = ableToGetPoint(exVertical, exHorizontal, boxes);
                }
                minLosePoints = (losePoints[k] < minLosePoints) ? losePoints[k] : minLosePoints;
            }
            for (int k = 0; k < totalStep; k++) {
                if (losePoints[k] == minLosePoints) {
                    result = remainSteps.get(k);
                    break;
                }
            }
        }

        if (result[0] == 0) {
            vertical[result[1]][result[2]] = (step % 2 == 0) ? 1 : -1;
        } else {
            horizontal[result[1]][result[2]] = (step % 2 == 0) ? 1 : -1;
        }
        step = Method.getPoint(step, result, vertical, horizontal, boxes);
        StdDraw.clear();
        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
        StdDraw.picture(-425, -250, ".\\picture\\ai.png", 160, 228);
        StdDraw.picture(425, -250, ".\\picture\\ai2.png", 160, 228);
        try {
            int index = 1 + Main.random.nextInt(5);
            switch (index) {
                case (1):
                    url = file1.toURL();
                    break;
                case (2):
                    url = file2.toURL();
                    break;
                case (3):
                    url = file3.toURL();
                    break;
                case (4):
                    url = file4.toURL();
                    break;
                default:
                    url = file5.toURL();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
        Method.resetCheckerboard(vertical, horizontal, checkerboard);
        Method.redraw(checkerboard, boxes);
        StdDraw.show();
        return step;
    }
}
