package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Spaceship extends Entity {
    
    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void tick(GameData gameData, World world) {
        GameKeys keys = gameData.getKeys();
        if (keys.isDown(KeyEvent.VK_UP) || keys.isDown(KeyEvent.VK_W)) {
            x += Math.cos(rotation) * 3;
            y += Math.sin(rotation) * 3;
        }
        if (keys.isDown(KeyEvent.VK_LEFT) || keys.isDown(KeyEvent.VK_A)) {
            rotation -= 0.1;
        }
        if (keys.isDown(KeyEvent.VK_RIGHT) || keys.isDown(KeyEvent.VK_D)) {
            rotation += 0.1;
        }
        if (rotation > Math.PI * 2) {
            rotation -= Math.PI * 2;
        } else if (rotation < 0) {
            rotation += Math.PI * 2;
        }
    }
    
    @Override
    public void paintComponent(Graphics2D g) {
        g.drawRect(-10, -10, 20, 20);
    }
}
