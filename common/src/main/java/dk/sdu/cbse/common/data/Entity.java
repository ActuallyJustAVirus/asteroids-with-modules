package dk.sdu.cbse.common.data;

import java.awt.Graphics2D;

public abstract class Entity {
    public double x;
    public double y;
    public double rotation;

    public Entity() {
        System.out.println("Entity created");
    }
    public abstract void tick(GameData gameData, World world);
    public abstract void paintComponent(Graphics2D g);
}