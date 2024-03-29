package com.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.*;

import com.game.figures.*;
import com.game.*;

public class Level1 extends LevelScene {

    public Level1() {
        super();

        this.blocks.add(new Block(world, view, 0, 0));

        System.out.println(blocks.toString());

        fig = new Triangle(world, new Vector2(13, 12.5f));
        fig.canPlay = false;

        debugRenderer = new Box2DDebugRenderer();
        world.setContactListener(this);

    }

}
