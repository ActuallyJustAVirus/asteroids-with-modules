package dk.sdu.cbse.enemy;

import java.util.ArrayList;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;
import dk.sdu.cbse.spaceship.IWeaponService;
import dk.sdu.cbse.spaceship.Spaceship;

public class EnemyPlugin implements IGamePluginService {
    private ArrayList<Spaceship> enemies = new ArrayList<>();
    private IWeaponService weaponService;
    
    @Override
    public void start(GameData gameData, World world) {
        ServiceLoader<IWeaponService> weaponServices = ServiceLoader.load(IWeaponService.class);
        for (IWeaponService weaponService : weaponServices) {
            this.weaponService = weaponService;
            break; // Assuming only one weapon service is needed
        }
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
            if (weaponService != null) {
                enemy.setWeapon(weaponService);
            }
            enemies.add(enemy);
            world.addEntity(enemy);
        }

        // Process all enemies
        for (int i = 0; i < enemies.size(); i++) {
            Spaceship enemy = enemies.get(i);
            if (enemy.destroyed) {
                world.removeEntity(enemy);
                enemies.remove(enemy);
                gameData.setScore(gameData.getScore() + 5);
                i--;
                continue;
            }
            if (!targets.isEmpty()) {
                
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
                if (Math.abs(enemy.x - target.x) < 250 && Math.abs(enemy.y - target.y) < 250) {
                    enemy.fireWeapon();
                } else {
                    forward = true;
                }
                enemy.move(forward, left, right);
            }
            enemy.tick(gameData, world);
        }
    }
}
