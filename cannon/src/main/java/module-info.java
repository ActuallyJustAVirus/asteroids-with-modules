module cannon {
    requires common;
    requires spaceship;
    provides dk.sdu.cbse.spaceship.IWeaponService with dk.sdu.cbse.cannon.Cannon;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.cannon.CannonPlugin;
}
