package com.game.figures;

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

import com.game.*;

public class Circle extends Figure {

    protected Body    body;
    protected Fixture fixture;

    public Circle(World world, Vector2 pos,
                     Vector2 velocity, float angularVelocity) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(pos.x, pos.y);

        body = world.createBody(bodyDef);
        body.applyLinearImpulse(velocity.x, velocity.y, pos.x, pos.y, true);
        body.setAngularVelocity(angularVelocity);

        CircleShape shape = new CircleShape();
        shape.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0f;

        fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Circle(World world, Vector2 pos) {
        this(world, pos, new Vector2(0, 0), 0);
    }

    public Body getBody() { return body; }

}
