package dk.sdu.cbse;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class Main {
    JFrame frame;
    GameData gameData;
    World world;
    JPanel panel;
    Collection<IGamePluginService> gamePlugins = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        boolean isLinux = System.getProperty("os.name").toLowerCase().contains("linux");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        
        Main main = ctx.getBean(Main.class);
        while (true) {
            main.run();
            main.panel.repaint();
            if (isLinux) {
                Toolkit.getDefaultToolkit().sync();
            }
            Thread.sleep(20);
        }
    }

    public Main(Collection<IGamePluginService> gamePlugins) {
        this.gamePlugins = gamePlugins;
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

        // load plugins
        // ModuleLayer layer = createLayer();
        // gamePlugins = getPluginServices(layer);
        for (IGamePluginService gamePlugin : gamePlugins) {
            System.out.println("Found plugin: " + gamePlugin.getClass().getName());
            gamePlugin.start(gameData, world);
        }
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
        for (IGamePluginService plugin : gamePlugins) {
            plugin.process(gameData, world);
        }
    }
}