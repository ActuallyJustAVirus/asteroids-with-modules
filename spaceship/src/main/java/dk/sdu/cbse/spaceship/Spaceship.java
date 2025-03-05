package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Spaceship extends Entity {
    Image image;
    
    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
        this.rotation = -3.1415f / 2;
        this.radius = 60;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage(this.getClass().getClassLoader().getResource("spaceship.png"));
    }

    @Override
    public void collide(Entity other, World world) {
        System.out.println("Spaceship collided with " + other.getClass().getSimpleName());
    }
    
    @Override
    public void tick(GameData gameData, World world) {
        GameKeys keys = gameData.getKeys();
        if (keys.isDown(KeyEvent.VK_UP) || keys.isDown(KeyEvent.VK_W)) {
            x += Math.cos(rotation) * 5;
            y += Math.sin(rotation) * 5;
        }
        if (keys.isDown(KeyEvent.VK_LEFT) || keys.isDown(KeyEvent.VK_A)) {
            rotation -= 0.05;
        }
        if (keys.isDown(KeyEvent.VK_RIGHT) || keys.isDown(KeyEvent.VK_D)) {
            rotation += 0.05;
        }
        if (rotation > Math.PI * 2) {
            rotation -= Math.PI * 2;
        } else if (rotation < 0) {
            rotation += Math.PI * 2;
        }
    }
    
    @Override
    public void paintComponent(Graphics2D g) {
        g.rotate(Math.PI / 2);
        g.translate(0, -60);
        g.drawImage(image, -128, -128, 256, 256, null);
    }
}
