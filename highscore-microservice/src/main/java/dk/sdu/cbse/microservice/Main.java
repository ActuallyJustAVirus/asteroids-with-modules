package dk.sdu.cbse.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    private int highscore = 0;
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/highscore")
    public String getHighScore() {
        return highscore + "";
    }

    @GetMapping("/newhighscore")
    public String setHighScore(@RequestParam(value = "score") int score) {
        boolean isHighscore = score > highscore;
        if (isHighscore) {
            highscore = score;
        }
        return isHighscore + "";
    }

}
