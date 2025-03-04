package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;

import java.awt.Graphics2D;

public class Spaceship extends Entity {
    
    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void paintComponent(Graphics2D g) {
        g.drawRect(x, y, 20, 20);
    }
}
