package com.codegym.games.spaceinvaders.gameobjects;

import com.codegym.games.spaceinvaders.ShapeMatrix;
import com.codegym.games.spaceinvaders.SpaceInvadersGame;

public class PlayerShip extends Ship {
    public PlayerShip(){
        super(SpaceInvadersGame.WIDTH/2.0, SpaceInvadersGame.HEIGHT- ShapeMatrix.PLAYER.length-1);
        setStaticView(ShapeMatrix.PLAYER);
    }
}
