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
    private static final int STEP = ShapeMatrix.ENEMY.length+1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet(){
        createShips();
    }

    private void createShips(){
        ships = new ArrayList<>();
        for (double x = 0; x < COLUMNS_COUNT; x++) {
            for (double y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
    }

    public void draw(Game game){
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    public void move(){
        if (ships.size() == 0) return;

        double speed = getSpeed();
        if (direction == Direction.LEFT && getLeftBorder() < 0){
            direction = Direction.RIGHT;
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, speed);
            }
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH){
            direction = Direction.LEFT;
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, speed);
            }
        } else {
            for (EnemyShip ship : ships) {
                ship.move(direction, speed);
            }
        }
    }

    private double getSpeed(){
        return Math.min(2.0, 3.0 / ships.size());
    }

    private double getLeftBorder(){
        double smallestX = 100;
        for (EnemyShip ship : ships) {
            if (ship.x < smallestX){
                smallestX = ship.x;
            }
        }
        return smallestX;
    }

    private double getRightBorder(){
        double largestXWidth = 0;
        for (EnemyShip ship : ships) {
            if (ship.x + ship.width > largestXWidth){
                largestXWidth = ship.x + ship.width;
            }
        }
        return largestXWidth;
    }
}
