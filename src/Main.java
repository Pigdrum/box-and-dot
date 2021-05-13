/*
Choose Main and we can start to game
Four responds stand for the return int from three GUI edges
x and y stands for the size of checkerboard
Step used to control who go next and totalStep control the number of steps
Three arrays are used to record whether edges and blocks are occupied, 1 for offensive and -1 for defensive
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static int beginningResponse, firstResponse, secondResponse, thirdResponse, fourthResponse, levelResponse;
    private static int x, y;
    public static Random random = new Random();
    public static List<State> stateList = new ArrayList<>();
    public static boolean save = false;
    private static URL url;
    private static AudioClip ac;
    private static File play = new File(".\\sound\\play.wav");
    private static File ending = new File(".\\sound\\ending.wav");

    public static void initializeResponse() {
        beginningResponse = 0;
        firstResponse = 0;
        secondResponse = 0;
        thirdResponse = 0;
        fourthResponse = 0;
        levelResponse = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();
        int times = 0;

        MainLable:
        while (true) {
            if (times > 0) {
                ac.stop();
                try {
                    url = ending.toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                ac = Applet.newAudioClip(url);
                ac.play();
                Thread.sleep(4000);
            }
            times++;
            try {
                url = play.toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            ac = Applet.newAudioClip(url);
            ac.play();
            initializeResponse();
            stateList.clear();
            save = false;
            int[] input = {-1, -1, -1, -1};
            int[][] xy = {{-1,-1},{-1,-1},{-1,-1}};

            //choose to start game or load game
            while (true) {
                beginningResponse = GUI.beginPlay();
                if (beginningResponse != 0) {
                    break;
                }
                StdDraw.show();
                Thread.sleep(20);
            }
            Thread.sleep(200);

            //if load a game
            if (beginningResponse == 2) {
                File file;
                input = Method.getNumberInput("Input the security number to load, click the \"enter\" after confirming.");
                if (input == null) {
                    continue MainLable;
                } else {
                    file = new File((input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3]) + ".txt");
                }
                while (!file.exists()) {
                    input = Method.getNumberInput("No number is founded,please input again!");
                    if (input == null) {
                        continue MainLable;
                    } else {
                        file = new File((input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3]) + ".txt");
                    }
                }
                Thread.sleep(200);
            } else if (beginningResponse == 3) {
                break MainLable;
            }
            if (input[0] != -1) {
                stateList.add(Method.readGame(input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3]));
            }

            //if start a new game
            if (stateList.size() == 0) {
                while (true) {
                    StdDraw.clear();
                    StdDraw.picture(0, 0, ".\\picture\\background.jpg", 2000, 1000);
                    firstResponse = GUI.startingGUI();
                    if (firstResponse > 0) {
                        break;
                    } else if (firstResponse == -1) {
                        continue MainLable;
                    }
                    StdDraw.show();
                    Thread.sleep(20);
                }
                StdDraw.clear();
                StdDraw.picture(0, 0, ".\\picture\\background.jpg", 2000, 1000);
                Thread.sleep(100);
                int counter = 0;
                while (true) {
                    StdDraw.clear();
                    StdDraw.picture(0, 0, ".\\picture\\background.jpg", 2000, 1000);
                        switch (counter) {
                            case (0):
                                xy[1][0] = GUI.drawNumber(xy[0][0], xy[0][1]);
                                if (xy[1][0] != -1) {
                                    counter++;
                                    Thread.sleep(200);
                                }
                                break ;
                            case (1):
                                xy[2][1] = GUI.drawNumber(xy[1][0], xy[1][1]);
                                if (xy[2][1] != -1) {
                                    xy[2][0] = xy[1][0];
                                    counter++;
                                    Thread.sleep(200);
                                }
                                break;
                            default:
                                GUI.drawNumber(xy[2][0], xy[2][1]);
                        }
                    secondResponse = GUI.chooseSize();
                    StdDraw.show();
                    //if click the block
                    if (secondResponse > 0) {
                        break;
                    }
                    if(secondResponse == -1){
                    continue MainLable;
                    }
                    //if click "sure"
                    if (secondResponse == -2 && xy[2][1]!=-1) {
                        break;
                    }
                    //if click "back"
                    if(secondResponse == -3){
                        xy[counter][0]=-1;
                        xy[counter][1]=-1;
                        if(counter!=0){
                            counter--;
                        }
                        Thread.sleep(200);
                    }
                    Thread.sleep(20);
                }
            }  else {
                //or load an old one
                firstResponse = stateList.get(0).getFirstResponse();
                secondResponse = stateList.get(0).getSecondResponse();
            }

            switch (secondResponse) {
                case (1):
                    x = 2;
                    y = 2;
                    break;
                case (2):
                    x = 3;
                    y = 3;
                    break;
                case (3):
                    x = 4;
                    y = 4;
                    break;
                case (4):
                    if (stateList.size() != 0) {
                        x = stateList.get(0).getX();
                        y = stateList.get(0).getY();
                        break;
                    }
                    x = random.nextInt(5) + 2;
                    y = random.nextInt(5) + 2;
                    break ;
                case(-2):
                    if (stateList.size() != 0) {
                        x = stateList.get(0).getX();
                        y = stateList.get(0).getY();
                        break;
                    }
                    x = xy[2][0]-1;
                    y = xy[2][1]-1;
                default:
            }
            Checkerboard checkerboard = new Checkerboard(x, y);
            StdDraw.clear();

            //game start
            int step = 0;
            int totalStep = (x + 1) * y + x * (y + 1);
            int[][] vertical = new int[y][x + 1];
            int[][] horizontal = new int[y + 1][x];
            int[][] boxes = new int[y][x];
            if (input[0] == -1) {
                stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
            }
            step = stateList.get(0).getStep();
            totalStep = stateList.get(0).getTotalStep();
            vertical = Method.copy2DArray(stateList.get(0).getVertical());
            horizontal = Method.copy2DArray(stateList.get(0).getHorizontal());
            boxes = Method.copy2DArray(stateList.get(0).getBoxes());
            StdDraw.setXscale(-500, 500);
            StdDraw.setYscale(-350, 350);
            switch (firstResponse) {
                case (1):
                    checkerboard.drawBasicCheckerboard();
                    while (totalStep != 0) {
                        StdDraw.clear();
                        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                        Rectangle next = new Rectangle(350, 275, 120, 50);
                        Rectangle back = new Rectangle(-470, 275, 120, 50);
                        Rectangle rectangle = new Rectangle(300, -350, 200, 60);
                        StdDraw.setPenColor(StdDraw.CYAN);
                        StdDraw.filledRectangle(410, 300, 60, 25);
                        StdDraw.filledRectangle(-410, 300, 60, 25);
                        StdDraw.filledRectangle(400, -320, 100, 30);
                        StdDraw.setPenColor(Color.BLACK);
                        StdDraw.setFont(GUI.font2);
                        StdDraw.text(410, 300, "Next");
                        StdDraw.text(-410, 300, "Back");
                        StdDraw.text(400, -320, "Back to Menu");
                        Method.redraw(checkerboard, boxes);
                        StdDraw.show();
                        Point mousePoint = new Point((int)StdDraw.mouseX(), (int)StdDraw.mouseY());
                        if ((next.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                            StdDraw.clear();
                            StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                            StdDraw.setPenColor(StdDraw.CYAN);
                            StdDraw.filledRectangle(410, 300, 60, 25);
                            StdDraw.filledRectangle(-410, 300, 60, 25);
                            StdDraw.setPenColor(Color.BLACK);
                            StdDraw.setFont(GUI.font2);
                            StdDraw.text(410, 300, "Next");
                            StdDraw.text(-410, 300, "Back");
                            step = Mode.idiot(vertical, horizontal, boxes, step, totalStep--, checkerboard);
                            stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
                            Thread.sleep(300);
                        } else if (stateList.size() > 1 && back.contains(mousePoint) && StdDraw.isMousePressed()) {
                            int last = stateList.size() - 2;
                            stateList.remove(last + 1);
                            step = stateList.get(last).getStep();
                            totalStep = stateList.get(last).getTotalStep();
                            vertical = Method.copy2DArray(stateList.get(last).getVertical());
                            horizontal = Method.copy2DArray(stateList.get(last).getHorizontal());
                            boxes = Method.copy2DArray(stateList.get(last).getBoxes());
                            Method.resetCheckerboard(vertical, horizontal, checkerboard);
                            Method.redraw(checkerboard, boxes);
                            StdDraw.show();
                            Thread.sleep(300);
                        } else if ((rectangle.contains(mousePoint) && StdDraw.isMousePressed()) || StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                            continue MainLable;
                        }
                        Thread.sleep(20);
                    }
                    break;
                case (2):
                    checkerboard.drawBasicCheckerboard();
                    while (totalStep != 0) {
                        int[] info = Mode.human(levelResponse, x, y, vertical, horizontal, boxes, step, totalStep, checkerboard);
                        if (save) {
                            break;
                        }
                        if (info != null) {
                            totalStep = info[0];
                            step = info[1];
                            stateList.add(new State(step, totalStep, vertical, horizontal, boxes));
                        } else if (stateList.size() >= 2) {
                            int last = stateList.size() - 2;
                            stateList.remove(last + 1);
                            step = stateList.get(last).getStep();
                            totalStep = stateList.get(last).getTotalStep();
                            vertical = Method.copy2DArray(stateList.get(last).getVertical());
                            horizontal = Method.copy2DArray(stateList.get(last).getHorizontal());
                            boxes = Method.copy2DArray(stateList.get(last).getBoxes());
                            Method.resetCheckerboard(vertical, horizontal, checkerboard);
                            Method.redraw(checkerboard, boxes);
                            StdDraw.show();
                            Thread.sleep(300);
                        }
                    }
                    break;
                case (3):
                    Thread.sleep(100);
                    while (true) {
                        if (stateList.get(0).getThirdResponse() != 0) {
                            thirdResponse = stateList.get(0).getThirdResponse();
                            break;
                        }
                        thirdResponse = GUI.chooseSide();
                        StdDraw.show();
                        if (thirdResponse > 0) {
                            break;
                        } else if (thirdResponse == -1) {
                            continue MainLable;
                        }
                        Thread.sleep(20);
                    }
                    Thread.sleep(200);
                    while (true) {
                        if (stateList.get(0).getLevelResponse() != 0) {
                            levelResponse = stateList.get(0).getLevelResponse();
                            break;
                        }
                        levelResponse = GUI.chooseLevel();
                        StdDraw.show();
                        if (levelResponse > 0) {
                            break;
                        } else if (levelResponse == -1) {
                            continue MainLable;
                        }
                    }
                    if (levelResponse == 3) {
                        ac.stop();
                    }
                    Thread.sleep(100);
                    StdDraw.clear();
                    StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                    checkerboard.drawBasicCheckerboard();
                    StdDraw.show();
                    Mode.HumanVsAI(thirdResponse, levelResponse, x, y, vertical, horizontal, boxes, step, totalStep, checkerboard);
                default:
            }

            while (save) {
                Thread.sleep(100);
                input = Method.getNumberInput("Please input the security number to save.");
                File file;
                if (input == null) {
                    continue MainLable;
                } else {
                    file = new File((input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3]) + ".txt");

                }
                if (!file.exists()) {
                    Method.save(stateList.get(stateList.size() - 1), firstResponse, secondResponse, thirdResponse, levelResponse, input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3], x, y);
                    continue MainLable;
                } else {
                    int whetherCover;
                    while (true) {
                        StdDraw.clear();
                        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                        whetherCover = GUI.doCover();
                        if (whetherCover != 0) {
                            break;
                        }
                        StdDraw.show();
                        Thread.sleep(20);
                    }
                    if (whetherCover == 2) {
                        file.delete();
                        Method.save(stateList.get(stateList.size() - 1), firstResponse, secondResponse, thirdResponse, levelResponse, input[0] * 1000 + input[1] * 100 + input[2] * 10 + input[3], x, y);
                        continue MainLable;
                    }
                }
            }

            Thread.sleep(100);
            while (!StdDraw.isMousePressed() && !StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                Thread.sleep(20);
            }
            Thread.sleep(200);

            //The ending cartoon
            ArrayList<Block> blocks = new ArrayList<>();
            ArrayList<int[]> randomCoordinates = new ArrayList<>();
            for (Edge e : checkerboard.getEdges()) {
                for (int i = -5; i <= 4; i++) {
                    int[] result = Method.getResult(e, x, y);
                    if (e.isVertical()) {
                        Color color = (stateList.get(stateList.size() - 1).getVertical()[result[1]][result[2]] > 0) ? Color.RED : Color.BLUE;
                        blocks.add(new Block(e.getX(), e.getY() + 5 + 10 * i, color));
                    } else {
                        Color color = (stateList.get(stateList.size() - 1).getHorizontal()[result[1]][result[2]] > 0) ? Color.RED : Color.BLUE;
                        blocks.add(new Block(e.getX() + 5 + 10 * i, e.getY(), color));
                    }
                }
            }
            for (int i = 0; i < blocks.size(); i++) {
                int[] randomCoordinate = {-30 + random.nextInt(60), -30 + random.nextInt(60)};
                while (Math.abs(randomCoordinate[0]) <= 10 || Math.abs(randomCoordinate[1]) <= 10) {
                    randomCoordinate[0] = -50 + random.nextInt(100);
                    randomCoordinate[1] = -50 + random.nextInt(100);
                }
                randomCoordinates.add(randomCoordinate);
            }
            int frames = 1;
            while (!StdDraw.isMousePressed() && !StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && frames <= 500) {
                StdDraw.clear();
                StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                for (int i = 0; i < blocks.size(); i++) {
                    blocks.get(i).setX(blocks.get(i).getX() + (int) (randomCoordinates.get(i)[0] / 500.0 * frames));
                    blocks.get(i).setY(blocks.get(i).getY() + (int) (randomCoordinates.get(i)[1] / 500.0 * frames));
                    blocks.get(i).draw();
                }
                StdDraw.show();
                frames++;
                Thread.sleep(20);
            }
            Thread.sleep(100);

            //ending choose
            while (true) {
                fourthResponse = GUI.calculateScores(stateList.get(stateList.size() - 1).getBoxes());
                if (fourthResponse == 1) {
                    for (int i = 0; i < stateList.size(); i++) {
                        StdDraw.clear();
                        StdDraw.picture(0, 0, ".\\picture\\background.jpg", 1000, 700);
                        Method.resetCheckerboard(stateList.get(i).getVertical(), stateList.get(i).getHorizontal(), checkerboard);
                        Method.redraw(checkerboard, stateList.get(i).getBoxes());
                        StdDraw.show();
                        Thread.sleep(300);
                    }
                } else if (fourthResponse == 2) {
                    break;
                } else if (fourthResponse == 3) {
                    break MainLable;
                }
                Thread.sleep(20);
            }
            Thread.sleep(100);
        }
        ac.stop();
        try {
            url = ending.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
        Thread.sleep(4000);
        System.exit(0);
    }
}
