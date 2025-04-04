package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Spaceship extends Entity {
    Image image;
    
    public Spaceship() {
        this.rotation = -3.1415f / 2;
        this.radius = 60;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage(this.getClass().getClassLoader().getResource("spaceship.png"));
    }

    public void tick(boolean forward, boolean left, boolean right) {
        if (forward) {
            x += Math.cos(rotation) * 5;
            y += Math.sin(rotation) * 5;
        }
        if (left) {
            rotation -= 0.05;
        }
        if (right) {
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
