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

    BodyDef bodyDef;

    public Circle(World world) {
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(10, 50);
        Body dynBody = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = dynBody.createFixture(fixtureDef);
        circle.dispose();

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(-50, 10);
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

}
