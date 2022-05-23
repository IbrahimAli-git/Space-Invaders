package com.codegym.games.spaceinvaders.gameobjects;

import com.codegym.games.spaceinvaders.ShapeMatrix;

public class EnemyShip extends Ship {
    public EnemyShip(double x, double y) {
        super(x, y);
        setStaticView(ShapeMatrix.ENEMY);
    }
}
