package dk.sdu.cbse.spaceship;

import java.awt.event.KeyEvent;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class SpaceshipPlugin implements IGamePluginService {
    private Spaceship spaceship;

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        spaceship = new Spaceship();
        spaceship.x = gameData.getDisplayWidth() / 2;
        spaceship.y = gameData.getDisplayHeight() / 2;
        world.addEntity(spaceship);

        ServiceLoader<IWeaponService> weaponServices = ServiceLoader.load(IWeaponService.class);
        for (IWeaponService weaponService : weaponServices) {
            spaceship.setWeapon(weaponService);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(spaceship);
    }
    
    @Override
    public void process(GameData gameData, World world) {
        if (spaceship == null) {
            return;
        }
        GameKeys keys = gameData.getKeys();
        boolean forward = keys.isDown(KeyEvent.VK_UP) || keys.isDown(KeyEvent.VK_W);
        boolean left = keys.isDown(KeyEvent.VK_LEFT) || keys.isDown(KeyEvent.VK_A);
        boolean right = keys.isDown(KeyEvent.VK_RIGHT) || keys.isDown(KeyEvent.VK_D);
        boolean fire = keys.isDown(KeyEvent.VK_SPACE);
        spaceship.tick(forward, left, right);
        if (fire) {
            spaceship.fireWeapon();
        }

        for (Entity entity : world.getEntities()) {
            if (spaceship.getID().equals(entity.getID())) {
                continue;
            }
            if (spaceship.collidesWith(entity)) {
                spaceship.invulnerableTime = 400;
                spaceship.health--;
                if (spaceship.health <= 0) {
                    world.removeEntity(spaceship);
                    spaceship = null;
                    break;
                }
            }
        }
    }
}
