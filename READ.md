# Asteroids Modular Game

This project is a game inspired by the classic game Asteroids with a module based structure. The game will recreate key elements like asteroids, asteroid splitting, and the spaceship. While reimagining other things, graphics, the enemy, and damage mechanic. 

## Building

To build all modules and copy their JARs to the `mods-mvn` directory, run:

```sh
mvn install
```

## Running

1. **Start the highscore microservice:**

   ```sh
   cd highscore-microservice
   mvn spring-boot:run
   ```

2. **Run the game:**

   From the root directory:

   ```sh
   mvn install
   java --module-path mods-mvn --module core/dk.sdu.cbse.Main
   ```

   Or use the provided VS Code launch configurations.

## Notes

- The game window is created using Java Swing/AWT.
- Highscore is managed via a REST microservice (`highscore-microservice`).