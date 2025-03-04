package dk.sdu.cbse.common.data;

public class GameData {
    private int displayWidth;
    private int displayHeight;
    private GameKeys keys = new GameKeys();
    
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
}
