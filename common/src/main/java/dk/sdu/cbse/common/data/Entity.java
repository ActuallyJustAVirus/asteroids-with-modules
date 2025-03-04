package dk.sdu.cbse.common.data;

import java.awt.Graphics2D;

public abstract class Entity {
    public int x;
    public int y;

    public Entity() {
        System.out.println("Entity created");
    }
    public abstract void paintComponent(Graphics2D g);
}