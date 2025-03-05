module common {
    exports dk.sdu.cbse.common.data;
    exports dk.sdu.cbse.common.service;
    requires transitive java.desktop;
    provides dk.sdu.cbse.common.service.IProcessService with dk.sdu.cbse.common.data.CollisionPlugin;
}
