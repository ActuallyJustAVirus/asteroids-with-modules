package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import java.awt.Graphics2D;

public class Spaceship extends Entity {
    
    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void tick(GameData gameData, World world) {
        x++;
    }
    
    @Override
    public void paintComponent(Graphics2D g) {
        g.drawRect(-10, -10, 20, 20);
    }
}
