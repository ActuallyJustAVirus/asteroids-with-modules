package dk.sdu.cbse.common.data;

import java.awt.Graphics2D;
import java.util.HashMap;

public class GameKeys extends Entity{
    HashMap<Integer, Boolean> keysPressed = new HashMap<>();
    HashMap<Integer, Boolean> keysDown = new HashMap<>();

    public void press(int key) {
        keysPressed.put(key, true);
        keysDown.put(key, true);
    }

    public void release(int key) {
        keysDown.put(key, false);
    }

    public boolean isDown(int key) {
        return keysDown.getOrDefault(key, false);
    }

    public boolean isPressed(int key) {
        return keysPressed.getOrDefault(key, false);
    }

    @Override
    public void tick(GameData gameData, World world) {
        keysPressed.clear();
    }

    @Override
    public void paintComponent(Graphics2D g) {}
}
