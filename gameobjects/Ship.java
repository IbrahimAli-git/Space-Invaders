package com.codegym.games.spaceinvaders.gameobjects;

import com.codegym.engine.cell.Game;
import java.util.ArrayList;
import java.util.List;

public class Ship extends GameObject {
    public boolean isAlive = true;
    private List<int[][]> frames;
    private int frameIndex;
    private boolean loopAnimation = false;

    public Ship(double x, double y) {
        super(x, y);
    }

    public Bullet fire() {
        return null;
    }

    public void setStaticView(int[][] viewFrame) {
        setMatrix(viewFrame);
        frames = new ArrayList<>();
        frames.add(viewFrame);
        frameIndex = 0;
    }

    public void kill() {
        isAlive = false;
    }

    public void setAnimatedView(boolean isLoopAnimation, int[][]... viewFrames){
        loopAnimation = isLoopAnimation;
    }


    public void nextFrame() {
        frameIndex++;
        if (frameIndex >= frames.size() && !loopAnimation) {
            return;
        } else if (frameIndex >= frames.size()){
            frameIndex = 0;
        }
        matrix = frames.get(frameIndex);
    }


    public void draw(Game game) {
        super.draw(game);
        nextFrame();
    }

    public boolean isVisible() {
        return isAlive || frameIndex < frames.size();
    }
}
