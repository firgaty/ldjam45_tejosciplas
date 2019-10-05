package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Scaling;

public class Block {

    protected Sprite sprite;
    protected World world;
    protected View view;

    protected int size;

    public Body body;

    public Block(World world, View view, float x, float y) {
        this.world = world;
        sprite = new Sprite(new Texture("block.png"));
        size = 4; // changer.

        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        body = world.createBody(bdef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(size, size);
        body.createFixture(box, 0.0f);
        box.dispose();
    }

    public void render(SpriteBatch batch) {
        // First we position and rotate the sprite correctly
        double rotation = Math.toDegrees(body.getAngle());
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        sprite.setRotation((float) rotation);

        // Then we simply draw it as a normal sprite.
        sprite.draw(batch);
    }

    public int getSize() {
        return size;
    }

}