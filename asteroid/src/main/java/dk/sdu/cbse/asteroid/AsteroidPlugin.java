package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        for (int i = 0; i < 100; i++) {
            Entity asteroid = new Asteroid();
            asteroid.x = (float) (Math.random() * gameData.getDisplayWidth());
            asteroid.y = (float) (Math.random() * gameData.getDisplayHeight());
            asteroid.rotation = Math.random() * 2d * Math.PI;
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroid) {
                world.removeEntity(entity);
            }
        }
    }

}
