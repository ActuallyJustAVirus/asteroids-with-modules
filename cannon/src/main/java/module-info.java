module cannon {
    requires common;
    requires spaceship;
    provides dk.sdu.cbse.spaceship.IWeaponService with dk.sdu.cbse.cannon.Cannon;
}
