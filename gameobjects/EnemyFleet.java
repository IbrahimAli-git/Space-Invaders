package com.codegym.games.spaceinvaders.gameobjects;

import com.codegym.engine.cell.Game;
import com.codegym.games.spaceinvaders.Direction;
import com.codegym.games.spaceinvaders.ShapeMatrix;
import com.codegym.games.spaceinvaders.SpaceInvadersGame;
import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    public void draw(Game game) {
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
        Boss boss = new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5);
        ships.add(boss);
    }

    private double getLeftBorder() {
        double left = SpaceInvadersGame.WIDTH;
        for (GameObject ship : ships) {
            if (ship.x < left) {
                left = ship.x;
            }
        }
        return left;
    }

    private double getRightBorder() {
        double right = 0;
        for (GameObject ship : ships) {
            if (ship.x + ship.width > right) {
                right = ship.x + ship.width;
            }
        }
        return right;
    }

    public double getBottomBorder(){
        double maxXY = 0;
        for (EnemyShip ship : ships) {
            if (ship.y + ship.height > maxXY){
                maxXY = ship.y + ship.height;
            }
        }
        return maxXY;
    }

    private double getSpeed() {
        int count = ships.size();
        double speed = 3. / count;
        return Math.min(speed, 2.);
    }

    public int getShipCount(){
        return ships.size();
    }

    public void move() {
        if (ships.isEmpty()) {
            return;
        }

        Direction currentDirection = direction;
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            currentDirection = Direction.DOWN;
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            currentDirection = Direction.DOWN;
        }

        double speed = getSpeed();
        for (EnemyShip ship : ships) {
            ship.move(currentDirection, speed);
        }
    }

    public Bullet fire(Game game) {
        if (ships.isEmpty()) {
            return null;
        }

        int random = game.getRandomNumber(100 / SpaceInvadersGame.DIFFICULTY);
        if (random > 0) {
            return null;
        }

        int shipNumber = game.getRandomNumber(ships.size());
        EnemyShip ship = ships.get(shipNumber);

        return ship.fire();
    }

    public int checkHit(List<Bullet> bullets){
        if (bullets.isEmpty()) return 0;
        int sumScore = 0;
        for (EnemyShip ship : ships) {
            sumScore += ship.score;
        }
        return sumScore;
    }

    public void deleteHiddenShips() {
        ships.removeIf(ship -> !ship.isVisible());
    }
}
