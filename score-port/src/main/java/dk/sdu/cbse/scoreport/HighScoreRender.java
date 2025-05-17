package dk.sdu.cbse.scoreport;

import java.awt.Graphics2D;

import dk.sdu.cbse.common.data.Entity;

public class HighScoreRender extends Entity {
    int highScore = 0;

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawString("Highscore: " + highScore, 10, 35);
    }
}
