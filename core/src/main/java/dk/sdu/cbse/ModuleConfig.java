package dk.sdu.cbse;

import dk.sdu.cbse.common.service.IGamePluginService;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jcs
 */
@Configuration
class ModuleConfig {
    
    public ModuleConfig() {
    }

    @Bean
    public Main Main(){
        return new Main(gamePluginServices());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
