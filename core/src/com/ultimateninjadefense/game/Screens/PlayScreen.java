package com.ultimateninjadefense.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.Bullet.Kunai.Kunai;
import com.ultimateninjadefense.game.Ninja.Ninja;
import com.ultimateninjadefense.game.Scenes.PlayScene;
import com.ultimateninjadefense.game.UNDGame;

public class PlayScreen extends ScreenAdapter {

    private UNDGame game;
    private Viewport viewport;
    private Camera camera;
    private ShapeRenderer shapeRenderer;
    private PlayScene playScene;
    private Array<Kunai> k = new Array<Kunai>();

    public PlayScreen(UNDGame game) {
        this.game = game;
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(UNDGame.WORLD_WIDTH / 2, UNDGame.WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        game.ninja.setStatus(Ninja.RUNNING);
        playScene = new PlayScene(game);

        Label fire = new Label("fire", new Label.LabelStyle(new BitmapFont(), Color.RED));
        fire.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                k.add(new Kunai(game.getAssetManager(), UNDGame.WORLD_WIDTH + Kunai.COLLISION_WIDTH, (float) Math.random() * UNDGame.WORLD_HEIGHT));
            }
        });
        playScene.getLayout().row();
        playScene.getLayout().add(fire).center().expandY().expandX();
        Gdx.input.setInputProcessor(playScene.stage);
    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();

        playScene.draw();

        for (Kunai kunai : k) {
            kunai.draw(game.batch, 0);
        }

        game.ninja.update(delta);
        game.ninja.draw(game.batch);
        draw();
    }

    private void update(float delta) {
        playScene.stage.act(delta);
        for (Kunai kunai : k) {
            kunai.act(delta);
        }
    }

    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect((Ninja.GAME_POSITION.x * Ninja.NINJA_WIDTH), (Ninja.GAME_POSITION.y * Ninja.NINJA_HEIGHT), Ninja.NINJA_WIDTH, Ninja.NINJA_HEIGHT);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        playScene.resize(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
