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

    public Asteroid() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage(this.getClass().getClassLoader().getResource("asteroid.png"));
        rotateSpeed = (int) (Math.random() * 7) - 3;
    }

    @Override
    public void tick(GameData gameData, World world) {
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
        g.rotate(visualRotation);
        g.drawImage(image, -16, -16, 32, 32, null);
    }
    
}
