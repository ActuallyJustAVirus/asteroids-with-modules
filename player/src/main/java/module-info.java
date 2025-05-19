module player {
    requires common;
    requires spaceship;
    requires java.desktop;
    uses dk.sdu.cbse.spaceship.IWeaponService;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;
}
