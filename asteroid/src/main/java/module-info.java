module asteroid {
    requires transitive common;
    requires java.desktop;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;

    exports dk.sdu.cbse.asteroid;
}
