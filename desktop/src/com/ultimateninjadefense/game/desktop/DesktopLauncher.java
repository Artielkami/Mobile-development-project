package com.ultimateninjadefense.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.ultimateninjadefense.game.UNDGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = UNDGame.WORLD_WIDTH;
        config.height = UNDGame.WORLD_HEIGHT;
//        TexturePacker.process("Ninja/LeafNinja/Walk", "Ninja/LeafNinja/Walk", "walk");
        new LwjglApplication(new UNDGame(), config);
    }
}
