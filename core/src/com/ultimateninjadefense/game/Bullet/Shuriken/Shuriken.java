package com.ultimateninjadefense.game.Bullet.Shuriken;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.ultimateninjadefense.game.Bullet.Bullet;
import com.ultimateninjadefense.game.UNDGame;

public class Shuriken extends Bullet {

    private static final float COLLISION_RADIUS = 20f;
    private static final float FRAME_DURATION = 0.2f;
    private static final float FLYING_SPEED = 200f;

    public Shuriken(AssetManager assetManager, float startX, float startY) {
        super(new Circle(startX, startY, COLLISION_RADIUS), startX, startY, FLYING_SPEED);
        this.flying = new Animation(FRAME_DURATION, assetManager.get("Bullet/Shuriken/shuriken.png", TextureRegion.class));
    }

    @Override
    protected void updateCollisionArea() {
        ((Circle) this.collisionArea).setX(this.position.x);
        ((Circle) this.collisionArea).setY(this.position.y);
    }
}
