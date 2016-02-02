package com.ultimateninjadefense.game.Ninja.LeafNinja;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.ultimateninjadefense.game.Ninja.Ninja;

public class LeafNinja extends Ninja {
    public LeafNinja(Array<TextureAtlas.AtlasRegion> runRegions, Array<TextureAtlas.AtlasRegion> walkRegions) {
        super(runRegions, walkRegions);
    }
}

