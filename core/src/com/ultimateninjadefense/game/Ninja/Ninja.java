package com.ultimateninjadefense.game.Ninja;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ultimateninjadefense.game.UNDGame;

import java.util.ArrayList;

public abstract class Ninja {
    public static final int WALKING = 0;
    public static final int RUNNING = 1;
    public static final float NINJA_WIDTH = 120f;
    public static final float NINJA_HEIGHT = 120f;

    private static final Vector2 PREVIEW_POSITION = new Vector2(3 * UNDGame.WORLD_WIDTH / 4 - NINJA_WIDTH / 2, (UNDGame.WORLD_HEIGHT - NINJA_HEIGHT) / 2);
    public static final Vector2 GAME_POSITION = new Vector2(UNDGame.WORLD_WIDTH / 10, UNDGame.WORLD_HEIGHT / 8);

    private int status;
    protected Animation run;
    protected Animation walk;
    protected ArrayList<Animation> skills;
    protected Animation physical;
    protected float animationTimer = 0;

    public Ninja(Array<TextureAtlas.AtlasRegion> runRegions, Array<TextureAtlas.AtlasRegion> walkRegions) {
        run = new Animation(0.06F, runRegions);
        run.setPlayMode(Animation.PlayMode.LOOP);
        walk = new Animation(0.1F, walkRegions);
        walk.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void update(float delta) {
        animationTimer += delta;
    }

    public void draw(SpriteBatch sb) {
        sb.begin();
        if (status == RUNNING) {
            sb.draw(run.getKeyFrame(animationTimer), GAME_POSITION.x, GAME_POSITION.y, NINJA_WIDTH, NINJA_HEIGHT);
        } else if (status == WALKING) {
            sb.draw(walk.getKeyFrame(animationTimer), PREVIEW_POSITION.x, PREVIEW_POSITION.y, NINJA_WIDTH, NINJA_HEIGHT);
        }
        sb.end();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
