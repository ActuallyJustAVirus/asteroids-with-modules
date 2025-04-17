package dk.sdu.cbse.cannon;

import dk.sdu.cbse.spaceship.IWeaponService;
import dk.sdu.cbse.spaceship.Spaceship;

public class Cannon implements IWeaponService {
    private Spaceship spaceship;
    private int cooldown = 0;
    private int cooldownTime = 10;
    
    @Override
    public void fireWeapon(double x, double y, double rotation) {
        if (cooldown > 0) {
            cooldown--;
            return;
        }
        cooldown = cooldownTime;
        CannonBullet bullet = new CannonBullet(x, y, rotation);
        CannonPlugin.addBullet(bullet);
    }

    @Override
    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    @Override
    public Spaceship getSpaceship() {
        return spaceship;
    }
}
