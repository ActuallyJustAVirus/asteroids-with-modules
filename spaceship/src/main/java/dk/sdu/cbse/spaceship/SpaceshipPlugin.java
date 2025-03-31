package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;
import dk.sdu.cbse.common.service.IProcessService;

public class SpaceshipPlugin implements IGamePluginService, IProcessService {
    private static Spaceship spaceship;

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        spaceship = new Spaceship();
        spaceship.x = gameData.getDisplayWidth() / 2;
        spaceship.y = gameData.getDisplayHeight() / 2;
        world.addEntity(spaceship);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(spaceship);
    }
    
    @Override
    public void process(GameData gameData, World world) {
        spaceship.tick(gameData, world);
    }
}
