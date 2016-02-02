package com.ultimateninjadefense.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.Ninja.Ninja;
import com.ultimateninjadefense.game.Scenes.PlayScene;
import com.ultimateninjadefense.game.UNDGame;

public class PlayScreen extends ScreenAdapter {

    private UNDGame game;
    private Viewport viewport;
    private Camera camera;
    private ShapeRenderer shapeRenderer;
    private PlayScene playScene;

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
        Gdx.input.setInputProcessor(playScene.stage);
    }

    @Override
    public void render(float delta) {
        update();
        clearScreen();
        draw();
        game.ninja.update(delta);
        game.ninja.draw(game.batch, UNDGame.WORLD_WIDTH / 10, UNDGame.WORLD_HEIGHT / 8);
        playScene.draw();
    }

    private void update() {
    }

    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
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
