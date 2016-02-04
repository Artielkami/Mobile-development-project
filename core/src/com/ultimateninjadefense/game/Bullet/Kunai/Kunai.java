package com.ultimateninjadefense.game.Bullet.Kunai;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ultimateninjadefense.game.Bullet.Bullet;

public class Kunai extends Bullet {

    public static final float COLLISION_WIDTH = 40f;
    public static final float COLLISION_HEIGHT = 8f;
    private static final float FRAME_DURATION = 0.2f;
    private static final float FLYING_SPEED = 100f;
    private static final float srcAngle = 0f;
    private float angle = 0f;
    public Kunai(AssetManager assetManager, float startX, float startY) {
        super(new Rectangle(startX, startY, COLLISION_WIDTH, COLLISION_HEIGHT), startX, startY, FLYING_SPEED);
        this.flying = new Animation(FRAME_DURATION, new TextureRegion(assetManager.get("Bullet/Kunai/kunai.png", Texture.class)));
        Vector2 src = new Vector2(1, 0);
        Vector2 des = new Vector2(position.x - finalPosition.x, position.y - finalPosition.y);
        angle = src.angle(des);
    }


    @Override
    protected void updateCollisionArea() {
        ((Rectangle) collisionArea).setX(this.position.x);
        ((Rectangle) collisionArea).setY(this.position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.begin();
        Affine2 t = new Affine2();
        t.translate(position.x, position.y);
        t.rotate(180+angle);
        batch.draw(flying.getKeyFrame(0), COLLISION_WIDTH, COLLISION_HEIGHT, t);
        batch.end();
    }
}
