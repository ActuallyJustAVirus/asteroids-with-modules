package dk.sdu.cbse.enemy;

import java.util.ArrayList;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;
import dk.sdu.cbse.spaceship.Spaceship;

public class EnemyPlugin implements IGamePluginService {
    private static ArrayList<Spaceship> enemies = new ArrayList<>();
    // private static ArrayList<Entity> targets = new ArrayList<>();
    
    @Override
    public void start(GameData gameData, World world) {
        
    }
    
    @Override
    public void stop(GameData gameData, World world) {
    }
    
    @Override
    public void process(GameData gameData, World world) {
        // Identify all targets as all spaceships not in the enemies list
        ArrayList<Entity> targets = new ArrayList<>();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Spaceship) {
                if (enemies.contains(entity)) {
                    continue;
                }
                targets.add(entity);
            }
        }

        // Create new enemies if there are no enemies
        if (enemies.isEmpty()) {
            Spaceship enemy = new Spaceship();
            enemy.x = Math.random() * gameData.getDisplayWidth();
            enemy.y = Math.random() * gameData.getDisplayHeight();
            enemy.rotation = Math.random() * Math.PI * 2;
            enemies.add(enemy);
            world.addEntity(enemy);
        }

        // Process all enemies
        for (Spaceship enemy : enemies) {
            if (targets.isEmpty()) {
                break;
            }
            Entity target = targets.get((int) (Math.random() * targets.size()));
            double desiredRotation = Math.atan2(target.y - enemy.y, target.x - enemy.x);
            if (desiredRotation < 0) {
                desiredRotation += Math.PI * 2;
            }
            double rotationDiff = desiredRotation - enemy.rotation;
            boolean forward = false;
            boolean left = false;
            boolean right = false;
            if (Math.abs(rotationDiff) < 0.05) {
                enemy.rotation = desiredRotation;
            } else if (rotationDiff > 0) {
                if (rotationDiff < Math.PI) {
                    right = true;
                } else {
                    left = true;
                }
            } else {
                if (rotationDiff > -Math.PI) {
                    left = true;
                } else {
                    right = true;
                }
            }
            enemy.tick(forward, left, right);

        }
    }
}
