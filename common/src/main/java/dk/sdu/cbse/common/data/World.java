package dk.sdu.cbse.common.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    private final Map<String, Entity> entities = new ConcurrentHashMap<>();

    public void addEntity(Entity entity) {
        entities.put(entity.getID(),entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity.getID());
    }

    public List<Entity> getEntities() {
        return new ArrayList<>(entities.values());
    }
}
