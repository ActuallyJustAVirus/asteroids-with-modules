module spaceship {
    requires transitive common;
    requires transitive java.desktop;
    // provides dk.sdu.cbse.common.data.Entity with dk.sdu.cbse.spaceship.Spaceship;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.spaceship.SpaceshipPlugin;
    exports dk.sdu.cbse.spaceship;
    uses dk.sdu.cbse.spaceship.IWeaponService;
}