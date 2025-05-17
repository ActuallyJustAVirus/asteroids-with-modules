module score_port {
    requires common;

    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.scoreport.ScorePort;
}
