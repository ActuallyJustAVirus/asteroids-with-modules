package dk.sdu.cbse.common.service;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IProcessService {
    /**
     * Process the game data and world state.
     * This method is called every frame to update the game state.
     *
     * @param gameData The current game data.
     * @param world    The current world state.
     */
    void process(GameData gameData, World world);
}
