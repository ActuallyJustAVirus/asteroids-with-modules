package dk.sdu.cbse.scoreport;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class ScorePort implements IGamePluginService {
    private int highScore = 0;
    private HighScoreRender highScoreRender;
    private boolean started = false;
    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void start(GameData gameData, World world) {
        URI uri = URI.create("http://localhost:8080/highscore");
        String response;
        try {
            response = send(uri);
            highScore = Integer.parseInt(response);
            highScoreRender = new HighScoreRender();
            highScoreRender.highScore = highScore;
            world.addEntity(highScoreRender);
            started = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(GameData gameData, World world) {
        if (!started) {
            return;
        }
        if (gameData.getScore() > highScore) {
            int newHighScore = gameData.getScore();
            URI uri = URI.create("http://localhost:8080/newhighscore?score=" + newHighScore);
            String response;
            try {
                response = send(uri);
                if (response.equals("true")) {
                    System.out.println("New high score: " + newHighScore);
                    highScore = newHighScore;
                    highScoreRender.highScore = highScore;
                } else {
                    System.out.println("Failed to set new high score");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        process(gameData, world);
    }

    public String send(URI uri) throws Exception {
        return restTemplate.getForObject(uri, String.class);
    }
}