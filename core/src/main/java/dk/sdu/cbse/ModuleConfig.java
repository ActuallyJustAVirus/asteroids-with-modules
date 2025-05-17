package dk.sdu.cbse;

import dk.sdu.cbse.common.service.IGamePluginService;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;
import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModuleConfig {
    @Bean
    public Main Main() {
        return new Main(gamePluginServices());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(createLayer(), IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private ModuleLayer createLayer() {
        ModuleFinder finder = ModuleFinder.of(Paths.get("mods-mvn2"));
        List<String> modules = finder.findAll().stream().map(m -> m.descriptor().name()).collect(toList());
        System.out.println("Modules found: " + modules);
        java.lang.module.Configuration config = ModuleLayer.boot().configuration().resolve(finder, ModuleFinder.of(), modules);
        ModuleLayer layer = ModuleLayer.boot().defineModulesWithOneLoader(config, ClassLoader.getSystemClassLoader());
        return layer;
    }
}
