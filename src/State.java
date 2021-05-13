/*
This class is used to describe all the details of the game
 */

public class State {
    private int x, y;
    private int firstResponse, secondResponse, thirdResponse, levelResponse;
    private int step, totalStep;
    private int[][] vertical;
    private int[][] horizontal;
    private int[][] boxes;

    public State(int step, int totalStep, int[][] vertical, int[][] horizontal, int[][] boxes) {
        this.step = step;
        this.totalStep = totalStep;
        int[][] copiedVertical = Method.copy2DArray(vertical);
        int[][] copiedHorizontal = Method.copy2DArray(horizontal);
        int[][] copiedBoxes = Method.copy2DArray(boxes);
        this.vertical = copiedVertical;
        this.horizontal = copiedHorizontal;
        this.boxes = copiedBoxes;
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

    public int getFirstResponse() {
        return firstResponse;
    }

    public int getSecondResponse() {
        return secondResponse;
    }

    public int getThirdResponse() {
        return thirdResponse;
    }

    public int getLevelResponse() {
        return levelResponse;
    }

    public void setFirstResponse(int firstResponse) {
        this.firstResponse = firstResponse;
    }

    public void setSecondResponse(int secondResponse) {
        this.secondResponse = secondResponse;
    }

    public void setThirdResponse(int thirdResponse) {
        this.thirdResponse = thirdResponse;
    }

    public void setLevelResponse(int levelResponse) {
        this.levelResponse = levelResponse;
    }

    public int getStep() {
        return step;
    }

    public int getTotalStep() {
        return totalStep;
    }

    public int[][] getVertical() {
        return vertical;
    }

    public int[][] getHorizontal() {
        return horizontal;
    }

    public int[][] getBoxes() {
        return boxes;
    }
}
