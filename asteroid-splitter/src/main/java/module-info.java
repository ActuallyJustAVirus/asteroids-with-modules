module asteroid_splitter {
    requires common;
    requires asteroid;

    provides dk.sdu.cbse.common.service.IGamePluginService with dk.sdu.cbse.asteroid_splitter.AsteroidSplitter;
}
