package com.ultimateninjadefense.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.Ninja.LeafNinja.LeafNinja;
import com.ultimateninjadefense.game.UNDGame;

public class LoadScreen extends ScreenAdapter {
    private UNDGame game;
    private Viewport viewport;
    private Camera camera;
    private ShapeRenderer shapeRenderer;

    private static final float PROGRESS_BAR_WIDTH = 840;
    private static final float PROGRESS_BAR_HEIGHT = 50;
    private float loadProgress = 0f;


    public LoadScreen(UNDGame game) {
        this.game = game;
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(UNDGame.WORLD_WIDTH / 2, UNDGame.WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
        game.getAssetManager().load("Buttons/Menu/menu.atlas", TextureAtlas.class);
        game.getAssetManager().load("Ninja/LeafNinja/Run/run_assets.atlas", TextureAtlas.class);
        game.getAssetManager().load("Ninja/LeafNinja/Walk/walk.atlas", TextureAtlas.class);
        game.getAssetManager().load("background.png", Texture.class);
    }

    @Override
    public void render(float delta) {
        update();
        clearScreen();
        draw();
        drawDebug();
    }

    private void update() {
        if (game.getAssetManager().update()) {
            initGame();
            game.setScreen(new MenuScreen(game));
        } else {
            loadProgress = game.getAssetManager().getProgress();
        }
    }
    private void initGame(){
        game.ninja = new LeafNinja(game.getAssetManager().get("Ninja/LeafNinja/Run/run_assets.atlas", TextureAtlas.class).getRegions(),game.getAssetManager().get("Ninja/LeafNinja/Walk/walk.atlas", TextureAtlas.class).getRegions());
    }
    private void draw() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect((UNDGame.WORLD_WIDTH - PROGRESS_BAR_WIDTH) / 2, UNDGame.WORLD_HEIGHT / 4 - PROGRESS_BAR_HEIGHT / 2, loadProgress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        shapeRenderer.end();
    }

    private void drawDebug() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
