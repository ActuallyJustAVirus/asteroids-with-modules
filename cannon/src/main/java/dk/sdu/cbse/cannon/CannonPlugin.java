package dk.sdu.cbse.cannon;

import java.util.ArrayList;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class CannonPlugin implements IGamePluginService {
    private static ArrayList<CannonBullet> bullets = new ArrayList<>();
    private static World world;

    public static void addBullet(CannonBullet bullet) {
        bullets.add(bullet);
        world.addEntity(bullet);
    }

    public static void removeBullet(CannonBullet bullet) {
        bullets.remove(bullet);
        world.removeEntity(bullet);
    }

    @Override
    public void start(GameData gameData, World world) {
        CannonPlugin.world = world;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (int i = 0; i < bullets.size(); i++) {
            CannonBullet bullet = bullets.get(i);
            if (bullet.isExpired()) {
                CannonPlugin.removeBullet(bullet);
                i--;
            }
            bullet.tick(gameData, world);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // do nothing
    }

}
