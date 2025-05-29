module score_port {
    requires common;
    requires spring.web;
    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.scoreport.HighScorePlugin;
}
