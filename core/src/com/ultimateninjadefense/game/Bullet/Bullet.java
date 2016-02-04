package com.ultimateninjadefense.game.Bullet;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ultimateninjadefense.game.Ninja.Ninja;

import java.awt.geom.Point2D;

public abstract class Bullet extends Actor {

    protected Animation flying;
    protected final Shape2D collisionArea;
    protected float flyingSpeed;
    protected Vector2 vectorSpeed = new Vector2();
    protected Point2D.Float position = new Point2D.Float();
    protected final Point2D.Float finalPosition = new Point2D.Float((float) (Ninja.GAME_POSITION.x + Math.random()*Ninja.NINJA_WIDTH),(float) (Ninja.GAME_POSITION.y + Math.random()*Ninja.NINJA_HEIGHT));

    public Bullet(Shape2D collisionArea, float x, float y, float speed) {
        this.collisionArea = collisionArea;
        this.flyingSpeed = speed;
        setPosition(x, y);
        this.vectorSpeed = new Vector2((float) (flyingSpeed * ((position.x - finalPosition.x) / position.distance(finalPosition))), (float) (flyingSpeed * ((position.y - finalPosition.y) / position.distance(finalPosition))));
    }

    public void setPosition(float x, float y) {
        this.position.setLocation(x, y);
        updateCollisionArea();
    }

    protected abstract void updateCollisionArea();

    @Override
    public void draw(Batch batch, float parentAlpha) {
    }

    @Override
    public void act(float delta) {
        setPosition(position.x - vectorSpeed.x * delta, position.y - vectorSpeed.y * delta);
    }
}
