module enemy {
    requires common;
    requires spaceship;
    requires java.desktop;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
}
