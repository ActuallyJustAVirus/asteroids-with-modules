package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

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
    public boolean destroyed;
    
    public Spaceship() {
        this.rotation = -3.1415f / 2;
        this.radius = 20;
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
        if (weapon != null && !destroyed) {
            double x = this.x + Math.cos(rotation) * 40;
            double y = this.y + Math.sin(rotation) * 40;
            weapon.fireWeapon(x, y, rotation);
        }
    }

    public void tick(GameData gameData, World world) {
        if (invulnerableTime > 0) {
            invulnerableTime--;
        }
        if (destroyed) {
            world.removeEntity(this);
            return;
        }
        if (this.x < 0) {
            this.x = gameData.getDisplayWidth();
        } else if (this.x > gameData.getDisplayWidth()) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = gameData.getDisplayHeight();
        } else if (this.y > gameData.getDisplayHeight()) {
            this.y = 0;
        }
        for (Entity entity : world.getEntities()) {
            if (this.getID().equals(entity.getID())) {
                continue;
            }
            if (this.collidesWith(entity)) {
                this.invulnerableTime = 200;
                this.health--;
                if (this.health <= 0) {
                    this.destroyed = true;
                }
                break;
            }
        }
    }

    public void move(boolean forward, boolean left, boolean right) {
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
        g.translate(0, -10);
        if (invulnerableTime % 15 > 12) {
            g.drawImage(whiteImage, -25, -25, 50, 50, null);
        } else {
            g.drawImage(image, -25, -25, 50, 50, null);
        }
    }
}
