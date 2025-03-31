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
    public abstract void paintComponent(Graphics2D g);
}