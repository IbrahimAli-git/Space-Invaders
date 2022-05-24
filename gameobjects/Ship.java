package com.codegym.games.spaceinvaders.gameobjects;

import com.codegym.games.spaceinvaders.Direction;

public class Ship extends GameObject {
    public boolean isAlive = true;

    public Ship(double x, double y) {
        super(x, y);
    }

    public void setStaticView(int[][] viewFrame){
        setMatrix(viewFrame);
    }

    public Bullet fire(){
        return null;
    }

    public void kill(){
        isAlive = false;
    }
}
