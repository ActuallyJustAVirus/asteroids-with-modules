package dk.sdu.cbse.asteroid_splitter;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;
import dk.sdu.cbse.asteroid.Asteroid;

public class AsteroidSplitter implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // do nothing
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof Asteroid)) {
                continue;
            }
            Asteroid asteroid = (Asteroid) entity;
            if (asteroid.isDestroyed()) {
                // Split the asteroid into smaller ones
                int size = asteroid.getSize();
                if (size <= 1) {
                    continue; // Don't split if already small enough
                }
                double splitRotation = Math.random() * 2d * Math.PI;
                for (int i = 0; i < 2; i++) {
                    Asteroid newAsteroid = new Asteroid(asteroid.getPlugin(), size - 1);
                    newAsteroid.x = asteroid.x + Math.cos(splitRotation + (i * Math.PI / 2)) * asteroid.radius;
                    newAsteroid.y = asteroid.y + Math.sin(splitRotation + (i * Math.PI / 2)) * asteroid.radius;
                    newAsteroid.rotation = splitRotation + (i * Math.PI);
                    asteroid.getPlugin().addAsteroid(world, newAsteroid);
                }
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // do nothing
    }
    
}
