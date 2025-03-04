package dk.sdu.cbse;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class Main {
    JFrame frame;
    GameData gameData;
    World world;
    JPanel panel;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        while (true) {
            main.run();
            main.panel.repaint();
            Thread.sleep(20);
        }
    }

    public Main() {
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                for (Entity entity : world.getEntities()) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.translate(entity.x, entity.y);
                    g2d.rotate(entity.rotation);
                    entity.paintComponent(g2d);
                }
            }
        };
        frame = new JFrame();
        frame.add(panel);
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
        for (Entity entity : world.getEntities()) {
            entity.tick(gameData, world);
        }
    }


    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}