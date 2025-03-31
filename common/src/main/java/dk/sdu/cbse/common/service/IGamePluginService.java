package dk.sdu.cbse.common.service;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * This method is called when the game starts. It is used to initialize the plugin and add entities to the world.
     *
     * @param gameData The game data object containing information about the game state.
     * @param world    The world object representing the game world.
     */
    void start(GameData gameData, World world);

    /**
     * This method is called when the game stops. It is used to clean up the plugin and remove entities from the world.
     *
     * @param gameData The game data object containing information about the game state.
     * @param world    The world object representing the game world.
     */
    void stop(GameData gameData, World world);
}
