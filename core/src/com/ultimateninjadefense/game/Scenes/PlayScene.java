package com.ultimateninjadefense.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.Background.Background;
import com.ultimateninjadefense.game.UNDGame;

public class PlayScene {

    public Stage stage;
    private Viewport viewport;
    private Background background;
    private SpriteBatch sb;
    private Table layout;
    ImageButton pause;

    public PlayScene(final UNDGame game) {
        sb = game.batch;
        viewport = new FitViewport(UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        background = new Background((Texture) game.getAssetManager().get("background.png"));

        pause = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/Game/pause.png"))));

        layout = new Table();
        layout.setFillParent(true);
        layout.top();
        layout.add(pause).expandX().right().top();

        pause.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("PAUSE");
            }
        });

        stage.addActor(background);
        stage.addActor(layout);
    }

    public void draw() {
        sb.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());
    }

    public Table getLayout() {
        return layout;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
