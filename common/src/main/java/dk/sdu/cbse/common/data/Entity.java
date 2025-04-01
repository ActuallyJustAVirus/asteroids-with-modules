package dk.sdu.cbse.common.data;

import java.awt.Graphics2D;
import java.util.UUID;

public abstract class Entity {
    private final UUID ID = UUID.randomUUID();
    public double x;
    public double y;
    public double rotation;
    public double radius;
    public boolean collidable = true;

    public Entity() {
        System.out.println("Entity created");
    }
    public String getID() {
        return ID.toString();
    }
    public boolean collidesWith(Entity other) {
        return collidable && other.collidable && (Math.abs(x - other.x) < radius + other.radius) && (Math.abs(y - other.y) < radius + other.radius);
    }
    public abstract void paintComponent(Graphics2D g);
}