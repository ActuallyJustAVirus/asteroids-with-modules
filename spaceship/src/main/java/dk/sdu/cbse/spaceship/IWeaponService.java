package dk.sdu.cbse.spaceship;

public interface IWeaponService {
    void fireWeapon(double x, double y, double rotation);

    void setSpaceship(Spaceship spaceship);

    Spaceship getSpaceship();
}
