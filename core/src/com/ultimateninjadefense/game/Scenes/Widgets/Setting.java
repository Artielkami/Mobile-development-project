package com.ultimateninjadefense.game.Scenes.Widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ultimateninjadefense.game.UNDGame;


public class Setting extends Window {
    public Setting(AssetManager assetManager) {
        super("", new Window.WindowStyle(new BitmapFont(), Color.BLUE, new TextureRegionDrawable(new TextureRegion(new Texture("badlogic.jpg")))));
        this.setPosition(UNDGame.WORLD_WIDTH / 12, UNDGame.WORLD_HEIGHT / 12);
        this.setWidth(10 * UNDGame.WORLD_WIDTH / 12);
        this.setHeight(10 * UNDGame.WORLD_HEIGHT / 12);
        this.setColor(Color.BROWN);
        TextureAtlas menuAtlas = assetManager.get("Buttons/Menu/menu.atlas");
        ImageButton close = new ImageButton(new TextureRegionDrawable(menuAtlas.findRegion("quit")));

        Table t = new Table();
        t.setFillParent(true);
        t.top().right().add(close);
        final Setting widget = this;
        close.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                for (Actor actor : widget.getStage().getActors()) {
                    if (actor instanceof Table) {
                        actor.setTouchable(Touchable.childrenOnly);
                    } else {
                        actor.setTouchable(Touchable.enabled);
                    }
                }
                widget.remove();
            }
        });
        widget.addActor(t);
    }
}
