package com.ultimateninjadefense.game.Ninja;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public abstract class Ninja {
    public static final int WALKING = 0;
    public static final int RUNNING = 1;

    private int status;
    protected Animation run;
    protected Animation walk;
    protected ArrayList<Animation> skills;
    protected Animation physical;
    protected float animationTimer = 0;

    public Ninja(Array<TextureAtlas.AtlasRegion> runRegions, Array<TextureAtlas.AtlasRegion> walkRegions) {
        run = new Animation(0.1F, runRegions);
        run.setPlayMode(Animation.PlayMode.LOOP);
        walk = new Animation(0.1F, walkRegions);
        walk.setPlayMode(Animation.PlayMode.LOOP);
        status = WALKING;
    }

    public void update(float delta) {
        animationTimer += delta;
    }

    public void draw(SpriteBatch sb, float x, float y) {
        sb.begin();
        if (status == RUNNING) {
            sb.draw(run.getKeyFrame(animationTimer), x, y, 128, 128);
        } else if (status == WALKING) {
            sb.draw(walk.getKeyFrame(animationTimer), x, y, 128, 128);
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
