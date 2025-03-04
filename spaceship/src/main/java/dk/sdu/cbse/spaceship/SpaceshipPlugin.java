package dk.sdu.cbse.spaceship;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class SpaceshipPlugin implements IGamePluginService {
    private Spaceship spaceship;

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        spaceship = new Spaceship(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        world.addEntity(spaceship);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(spaceship);
    }
    
}
