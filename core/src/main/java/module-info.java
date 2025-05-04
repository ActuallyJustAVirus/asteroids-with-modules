module core {
    requires common;
    requires java.desktop;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    opens dk.sdu.cbse to spring.core;
    exports dk.sdu.cbse to spring.beans,spring.context;
    uses dk.sdu.cbse.common.data.Entity;
    uses dk.sdu.cbse.common.service.IGamePluginService;
}
