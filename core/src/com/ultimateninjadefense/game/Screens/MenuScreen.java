package com.ultimateninjadefense.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.Scenes.MenuScene;
import com.ultimateninjadefense.game.UNDGame;


public class MenuScreen extends ScreenAdapter {
    private UNDGame game;
    private Viewport viewport;
    private Camera camera;
    private ShapeRenderer shapeRenderer;
    private MenuScene menu;

    public MenuScreen(UNDGame game) {
        this.game = game;
        menu = new MenuScene(game);
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
        Gdx.input.setInputProcessor(this.menu.stage);
    }

    @Override
    public void render(float delta) {
        update();
        clearScreen();
        draw();
        game.ninja.update(delta);
        game.ninja.draw(game.batch, 350, 150);
        menu.draw();
    }

    private void update() {
    }

    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        menu.resize(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
