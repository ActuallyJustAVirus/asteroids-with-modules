package dk.sdu.cbse.cannon;

import java.awt.Graphics2D;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class CannonBullet extends Entity {
    private double speed;
    private int ticksLeft;
    private boolean destroyed = false;
    
    public CannonBullet(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.radius = 5;
        this.speed = 10;
        this.ticksLeft = 100; // Set the bullet to live for 100 ticks
    }
    
    public void tick(GameData gameData, World world) {
        ticksLeft--;
        // Update the position of the bullet based on its speed and rotation
        x += Math.cos(rotation) * speed;
        y += Math.sin(rotation) * speed;

        // Check if the bullet is out of bounds
        if (x < 0 || x > gameData.getDisplayWidth() || y < 0 || y > gameData.getDisplayHeight()) {
            CannonPlugin.removeBullet(this);
        }

        // Check for collision with other entities
        for (Entity entity : world.getEntities()) {
            if (entity != this && collidesWith(entity)) {
                destroyed = true;
                break;
            }
        }
    }

    public boolean isExpired() {
        return ticksLeft <= 0 || destroyed;
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.fillOval(-2, -2, 4, 4);
    }
}
