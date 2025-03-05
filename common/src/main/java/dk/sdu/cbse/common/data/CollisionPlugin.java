package dk.sdu.cbse.common.data;

import dk.sdu.cbse.common.service.IProcessService;

public class CollisionPlugin implements IProcessService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity other : world.getEntities()) {
                if (entity != other) {
                    if (Math.abs(entity.x - other.x) < entity.radius + other.radius
                            && Math.abs(entity.y - other.y) < entity.radius + other.radius) {
                        entity.collide(other, world);
                        other.collide(entity, world);
                    }
                }
            }
        }
    }
    
}
