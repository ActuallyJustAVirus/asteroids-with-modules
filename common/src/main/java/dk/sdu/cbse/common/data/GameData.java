package dk.sdu.cbse.common.data;

public class GameData {
    private int displayWidth;
    private int displayHeight;
    private GameKeys keys = new GameKeys();
    private int score;
    
    public GameData(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }
    
    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
