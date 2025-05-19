module spaceship {
    requires transitive common;
    requires transitive java.desktop;
    exports dk.sdu.cbse.spaceship;
    uses dk.sdu.cbse.spaceship.IWeaponService;
}