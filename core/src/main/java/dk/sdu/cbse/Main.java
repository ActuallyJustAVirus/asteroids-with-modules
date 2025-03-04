package dk.sdu.cbse;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

import javax.swing.JFrame;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class Main {
    JFrame frame;
    GameData gameData;
    World world;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        while (true) {
            main.update();
            main.run();
            Thread.sleep(20);
        }
    }

    public Main() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setTitle("Asteroids");
        gameData = new GameData(800, 600);
        world = new World();
        for (IGamePluginService iGamePluginService : getPluginServices()) {
            iGamePluginService.start(gameData, world);
        }
        // print entities
        for (Entity entity : world.getEntities()) {
            System.out.println(entity);
        }
    }

    public void run() {
        
    }

    public void update() {
        Graphics2D g = (Graphics2D) frame.getGraphics();
        for (Entity entity : world.getEntities()) {
            entity.paintComponent(g);
        }
    }


    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}