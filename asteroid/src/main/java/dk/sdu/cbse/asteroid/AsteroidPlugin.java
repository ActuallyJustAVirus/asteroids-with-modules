package dk.sdu.cbse.asteroid;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;
import dk.sdu.cbse.common.service.IProcessService;

public class AsteroidPlugin implements IProcessService, IGamePluginService {
    private static List<Asteroid> asteroids = new ArrayList<>();


    private void createAsteroid(GameData gameData, World world) {
        Asteroid asteroid = new Asteroid(this);
        asteroid.x = (float) (Math.random() * gameData.getDisplayWidth());
        asteroid.y = (float) (Math.random() * gameData.getDisplayHeight());
        asteroid.rotation = Math.random() * 2d * Math.PI;
        world.addEntity(asteroid);
        asteroids.add(asteroid);
    }

    private void removeAsteroid(World world, Asteroid asteroid) {
        world.removeEntity(asteroid);
        asteroids.remove(asteroid);
    }

    void destroyAsteroid(World world, Asteroid asteroid) {
        removeAsteroid(world, asteroid);
    }

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 10; i++) {
            createAsteroid(gameData, world);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        while (!asteroids.isEmpty()) {
            // world.removeEntity(asteroids.removeFirst());
            removeAsteroid(world, asteroids.getFirst());
        }
    }

    @Override
    public void process(GameData gameData, World world) {
        if (asteroids.size() < 10) {
            createAsteroid(gameData, world);
        }
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            asteroid.tick(gameData, world);
            if (asteroid.x < 0 || asteroid.x > gameData.getDisplayWidth()
               || asteroid.y < 0 || asteroid.y > gameData.getDisplayHeight()) {
                removeAsteroid(world, asteroid);
                i--;
            }
        }
    }
}
