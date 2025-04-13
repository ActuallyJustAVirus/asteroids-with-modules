package dk.sdu.cbse.cannon;

import dk.sdu.cbse.spaceship.IWeaponService;
import dk.sdu.cbse.spaceship.Spaceship;

public class Cannon implements IWeaponService {
    private Spaceship spaceship;
    
    @Override
    public void fireWeapon(double x, double y, double rotation) {
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
