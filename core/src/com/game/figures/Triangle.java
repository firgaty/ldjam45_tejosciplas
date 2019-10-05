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

public class Triangle extends Figure {

    protected Body    body;
    protected Fixture fixture;
    private float angleOffset;

    public Triangle(World world, Vector2 pos,
                     Vector2 velocity, float angularVelocity) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(pos.x, pos.y);

        body = world.createBody(bodyDef);
        body.applyLinearImpulse(velocity.x, velocity.y, pos.x, pos.y, true);
        body.setAngularVelocity(angularVelocity + .1f);

        PolygonShape shape = new PolygonShape();

        // TODO adjust size with rect and circ
        float w = 3f;
        shape.set(new float[] { w, 0,

                                w * (float)Math.cos(2 * Math.PI / 3),
                                w * (float)Math.sin(2 * Math.PI / 3),

                                w * (float)Math.cos(4 * Math.PI / 3),
                                w * (float)Math.sin(4 * Math.PI / 3)});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0f;

        fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public Triangle(World world, Vector2 pos) {
        this(world, pos, new Vector2(0, 0), 0);
    }

    public Body getBody() { return body; }

    public void onUpJustPressed() {
        System.out.println("pressed");
        if (!isContact) return;

        System.out.println("up");
        float angle = body.getAngle() % (2 * (float)Math.PI);
        angle = angle > 0 ? angle : angle + 2 * (float)Math.PI;
        angle += angleOffset;
        Vector2 pos = body.getPosition();
        body.applyLinearImpulse(100 * (float)Math.cos(angle),
                                100 * (float)Math.sin(angle),
                                pos.x, pos.y, true);
    }

    // TODO correct
    public void update() {
         Vector2 v = body.getLinearVelocity();
         if (v.len() < 0.01f) { // velocity = 0
             float angle = body.getAngle() % (2 * (float)Math.PI);
             angle = angle > 0 ? angle : angle + 2 * (float)Math.PI;
           if (angle > 4 * Math.PI / 3) { // right
                 angleOffset = 2 * (float)Math.PI / 3;
           } else if (angle > 2 * Math.PI / 3) { // left
                 angleOffset = -2 * (float)Math.PI / 3;
           } else angleOffset = 0; // top
         }
    }

}
