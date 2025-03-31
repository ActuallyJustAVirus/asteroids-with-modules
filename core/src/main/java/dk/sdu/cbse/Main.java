package dk.sdu.cbse;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;
import dk.sdu.cbse.common.service.IProcessService;

public class Main {
    JFrame frame;
    GameData gameData;
    World world;
    JPanel panel;
    Collection<IProcessService> processServices = new ArrayList<>();

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
        processServices = (Collection<IProcessService>) getProcessServices();
        // print entities
        for (Entity entity : world.getEntities()) {
            System.out.println(entity);
        }

        // add key listener
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(java.awt.event.KeyEvent e) {}

            public void keyPressed(java.awt.event.KeyEvent e) {
                gameData.getKeys().press(e.getKeyCode());
            }

            public void keyReleased(java.awt.event.KeyEvent e) {
                gameData.getKeys().release(e.getKeyCode());
            }
        });
    }

    public void run() {
        for (IProcessService iProcessService : processServices) {
            iProcessService.process(gameData, world);
        }
    }


    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IProcessService> getProcessServices() {
        return ServiceLoader.load(IProcessService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}