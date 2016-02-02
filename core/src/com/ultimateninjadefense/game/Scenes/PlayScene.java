package com.ultimateninjadefense.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.UNDGame;

public class PlayScene {

    public Stage stage;
    private Viewport viewport;
    private SpriteBatch sb;

    ImageButton pause;

    public PlayScene(final UNDGame game) {
        this.sb = game.batch;
        viewport = new FitViewport(UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        pause = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/Game/pause.png"))));

        final Table gameMenu = new Table();
        gameMenu.setFillParent(true);
        gameMenu.top();
        gameMenu.add(pause).expandX().right().top();

        pause.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("PAUSE");
            }
        });

        this.stage.addActor(gameMenu);
    }

    public void draw() {
        this.sb.setProjectionMatrix(this.stage.getCamera().combined);
        this.stage.draw();
    }

    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }
}
