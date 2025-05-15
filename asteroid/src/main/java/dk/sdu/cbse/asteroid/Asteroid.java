package dk.sdu.cbse.asteroid;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class Asteroid extends Entity {
    int rotateSpeed = 1;
    double visualRotation = 0;
    Image image;
    AsteroidPlugin plugin;
    boolean destroyed = false;
    int size;

    public Asteroid(AsteroidPlugin plugin, int size) {
        this.plugin = plugin;
        this.size = size;
        radius = 13 * size;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage(this.getClass().getClassLoader().getResource("asteroid.png"));
        rotateSpeed = (int) (Math.random() * 7) - 3;
    }

    public void tick(GameData gameData, World world) {
        if (destroyed) {
            plugin.destroyAsteroid(world, this);
            gameData.setScore(gameData.getScore() + 1);
            return;
        }
        for (Entity entity : world.getEntities()) {
            if (entity != this && collidesWith(entity)) {
                destroyed = true;
                return;
            }
        }
        x += Math.cos(rotation);
        y += Math.sin(rotation);
        visualRotation += rotateSpeed * 0.01;
        if (visualRotation > Math.PI * 2) {
            visualRotation -= Math.PI * 2;
        } else if (visualRotation < 0) {
            visualRotation += Math.PI * 2;
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
        if (destroyed) {
            return;
        }
        g.rotate(visualRotation);
        g.scale(size, size);
        g.drawImage(image, -16, -16, 32, 32, null);
    }

    public AsteroidPlugin getPlugin() {
        return plugin;
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
