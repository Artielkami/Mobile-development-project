package com.ultimateninjadefense.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ultimateninjadefense.game.Ninja.LeafNinja.LeafNinja;
import com.ultimateninjadefense.game.Ninja.Ninja;
import com.ultimateninjadefense.game.Screens.LoadScreen;

public class UNDGame extends Game {
    public static final int WORLD_WIDTH = 640;
    public static final int WORLD_HEIGHT = 352;
    public SpriteBatch batch;
    private AssetManager assetManager;
    public Ninja ninja;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        setScreen(new LoadScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        this.assetManager.dispose();
        this.batch.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
