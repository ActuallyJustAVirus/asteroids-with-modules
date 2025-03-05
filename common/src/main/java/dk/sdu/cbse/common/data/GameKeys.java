package dk.sdu.cbse.common.data;

import java.util.HashMap;

public class GameKeys {
    HashMap<Integer, Boolean> keysDown = new HashMap<>();

    public void press(int key) {
        keysDown.put(key, true);
    }

    public void release(int key) {
        keysDown.put(key, false);
    }

    public boolean isDown(int key) {
        return keysDown.getOrDefault(key, false);
    }
}
