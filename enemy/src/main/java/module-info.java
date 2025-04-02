module enemy {
    requires common;
    requires spaceship;
    requires java.desktop;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides dk.sdu.cbse.common.service.IProcessService with dk.sdu.cbse.enemy.EnemyPlugin;
}
