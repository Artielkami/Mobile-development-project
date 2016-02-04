package com.ultimateninjadefense.game.Background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ultimateninjadefense.game.UNDGame;

public class Background extends Actor {
    private final TextureRegion background;
    private Rectangle regionBounds1;
    private Rectangle regionBounds2;
    private int speed = 100;

    public Background(Texture bg) {
        background = new TextureRegion(bg);
        regionBounds1 = new Rectangle(0 - UNDGame.WORLD_WIDTH / 2, 0, UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT);
        regionBounds2 = new Rectangle(UNDGame.WORLD_WIDTH / 2, 0, UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT);
    }

    private boolean leftBoundReach(float delta) {
        return (regionBounds2.x - (delta * speed)) <= 0;
    }

    private void updateBound(float delta) {
        regionBounds1.x += delta * speed;
        regionBounds2.x += delta * speed;
    }

    private void resetBound() {
        regionBounds1 = regionBounds2;
        regionBounds2 = new Rectangle(UNDGame.WORLD_WIDTH, 0, UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT);
    }

    @Override
    public void act(float delta) {
        if (leftBoundReach(delta)) {
            resetBound();
        } else {
            updateBound(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(background, regionBounds1.x, regionBounds1.y, UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT);
        batch.draw(background, regionBounds2.x, regionBounds2.y, UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT);
    }


}
