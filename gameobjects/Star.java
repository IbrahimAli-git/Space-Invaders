package com.codegym.games.spaceinvaders.gameobjects;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.*;
import com.codegym.games.spaceinvaders.gameobjects.GameObject;

public class Star extends GameObject {
    private static final String STAR_SIGN = "\\u2605";

    public Star(double x, double y) {
        super(x, y);
    }

    public void draw(Game game){
        game.setCellValueEx((int) x, (int) y, Color.NONE, STAR_SIGN, Color.WHITE, 100);
    }
}
