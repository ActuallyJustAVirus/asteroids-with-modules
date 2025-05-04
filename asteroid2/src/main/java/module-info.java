module asteroid2 {
    requires transitive common;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
}
