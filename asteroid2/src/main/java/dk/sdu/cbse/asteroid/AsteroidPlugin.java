package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        System.out.println("Spilt package AsteroidPlugin started");
    }

    @Override
    public void process(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
    }
}
