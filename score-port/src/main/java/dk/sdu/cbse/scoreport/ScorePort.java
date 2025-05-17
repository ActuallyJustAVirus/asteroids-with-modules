package dk.sdu.cbse.scoreport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.service.IGamePluginService;

public class ScorePort implements IGamePluginService {
    private int highScore = 0;
    private HighScoreRender highScoreRender;

    @Override
    public void start(GameData gameData, World world) {
        highScoreRender = new HighScoreRender();
        world.addEntity(highScoreRender);

        URI uri = URI.create("http://localhost:8080/highscore");
        String response;
        try {
            response = send(uri);
            highScore = Integer.parseInt(response);
            highScoreRender.highScore = highScore;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(GameData gameData, World world) {
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
        HttpURLConnection con;
        con = (HttpURLConnection) uri.toURL().openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}