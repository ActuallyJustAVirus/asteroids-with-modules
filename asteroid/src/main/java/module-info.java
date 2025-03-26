module asteroid {
    requires common;
    requires java.desktop;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides dk.sdu.cbse.common.service.IProcessService with dk.sdu.cbse.asteroid.AsteroidPlugin;
}
