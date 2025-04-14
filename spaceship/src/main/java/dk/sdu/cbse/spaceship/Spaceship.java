package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class Spaceship extends Entity {
    Image image;
    Image whiteImage;
    IWeaponService weapon;
    int invulnerableTime = 0;
    int health = 3;
    
    public Spaceship() {
        this.rotation = -3.1415f / 2;
        this.radius = 60;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.getImage(this.getClass().getClassLoader().getResource("spaceship.png"));
        ImageFilter whiteFilter= new RGBImageFilter() {
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb & 0xFF000000) == 0xFF000000) {
                    return 0xFFFFFFFF;
                } else {
                    return 0x00000000;
                }
            };
        };
      
        ImageProducer ip = new FilteredImageSource(image.getSource(), whiteFilter);
        whiteImage = Toolkit.getDefaultToolkit().createImage(ip);
    }

    public void setWeapon(IWeaponService weapon) {
        this.weapon = weapon;
        weapon.setSpaceship(this);
    }

    public IWeaponService getWeapon() {
        return weapon;
    }

    public void fireWeapon() {
        if (weapon != null) {
            double x = this.x + Math.cos(rotation) * 100;
            double y = this.y + Math.sin(rotation) * 100;
            weapon.fireWeapon(x, y, rotation);
        }
    }

    public void tick(boolean forward, boolean left, boolean right) {
        if (invulnerableTime > 0) {
            invulnerableTime--;
        }
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
    public boolean collidesWith(Entity other) {
        if (invulnerableTime > 0) {
            return false;
        }
        return super.collidesWith(other);
    }
    
    @Override
    public void paintComponent(Graphics2D g) {
        g.rotate(Math.PI / 2);
        g.translate(0, -60);
        if (invulnerableTime % 15 > 12) {
            g.drawImage(whiteImage, -128, -128, 256, 256, null);
        } else {
            g.drawImage(image, -128, -128, 256, 256, null);
        }
    }
}
