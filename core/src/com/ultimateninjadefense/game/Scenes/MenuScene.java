package com.ultimateninjadefense.game.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ultimateninjadefense.game.Scenes.Widgets.PickNinja;
import com.ultimateninjadefense.game.Scenes.Widgets.Setting;
import com.ultimateninjadefense.game.Scenes.Widgets.Store;
import com.ultimateninjadefense.game.Screens.PlayScreen;
import com.ultimateninjadefense.game.UNDGame;


public class MenuScene {

    public Stage stage;
    private Viewport viewport;
    private SpriteBatch sb;
    private Table layout;
    ImageButton pickNinjas;
    ImageButton store;
    ImageButton setting;
    ImageButton start;
    ImageButton quit;

    public MenuScene(final UNDGame game) {
        this.sb = game.batch;
        viewport = new FitViewport(UNDGame.WORLD_WIDTH, UNDGame.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        TextureAtlas menuAtlas = game.getAssetManager().get("Buttons/Menu/menu.atlas");
        pickNinjas = new ImageButton(new TextureRegionDrawable(menuAtlas.findRegion("chooseninja")));
        store = new ImageButton(new TextureRegionDrawable(menuAtlas.findRegion("store")));
        setting = new ImageButton(new TextureRegionDrawable(menuAtlas.findRegion("setting")));
        quit = new ImageButton(new TextureRegionDrawable(menuAtlas.findRegion("quit")));
        start = new ImageButton(new TextureRegionDrawable(menuAtlas.findRegion("start")));

        layout = new Table();
        layout.setName("Left Menu");
        layout.left().padLeft(10).right().left();
        layout.setFillParent(true);
        layout.add(pickNinjas).expandY().padTop(10);
        layout.row();
        layout.add(store).expandY();
        layout.row();
        layout.add(setting).expandY();
        layout.row();
        layout.add(quit).expandY().padBottom(10);
        layout.add(start).bottom().right().expandX().pad(10);


        start.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                game.setScreen(new PlayScreen(game));
            }
        });

        quit.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                Gdx.app.exit();
            }
        });

        pickNinjas.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                PickNinja widget = new PickNinja(game.getAssetManager());
                for (Actor actor : stage.getActors()) {
                    actor.setTouchable(Touchable.disabled);
                }
                stage.addActor(widget);
            }
        });

        store.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                Store widget = new Store(game.getAssetManager());
                for (Actor actor : stage.getActors()) {
                    actor.setTouchable(Touchable.disabled);
                }
                stage.addActor(widget);
            }
        });

        setting.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                Setting widget = new Setting(game.getAssetManager());
                for (Actor actor : stage.getActors()) {
                    actor.setTouchable(Touchable.disabled);
                }
                stage.addActor(widget);
            }
        });

        stage.addActor(layout);
    }

    public void draw(){
        this.sb.setProjectionMatrix(this.stage.getCamera().combined);
        this.stage.draw();
    }

    public void resize(int width, int height){
        this.viewport.update(width, height);
    }
}
