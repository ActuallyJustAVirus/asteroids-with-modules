package dk.sdu.cbse.common.service;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IProcessService {
    void process(GameData gameData, World world);
}
