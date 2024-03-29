package com.game;

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

class TestScene extends Scene implements ContactListener {

    Box2DDebugRenderer debugRenderer;
    Figure fig;
    int figCode = 0;

    public TestScene() {
        super();

        world = new World(new Vector2(0, -10), true);
        world.setContactListener(this);
        debugRenderer = new Box2DDebugRenderer();

        // platform
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 1);
        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(100, 1);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        // platform 2
        groundBodyDef = new BodyDef();
        groundBodyDef.position.set(25, 10);
        groundBody = world.createBody(groundBodyDef);

        groundBox = new PolygonShape();
        groundBox.setAsBox(1, 50);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        Pics p = new Pics(world, 7, 25, 5, 1);

        fig = new Triangle(world, new Vector2(13, 12.5f));
        fig.canPlay = false;
    }

    public void render() {
        handleInput();
        view.cam.update();
        view.batch.setProjectionMatrix(view.cam.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, view.cam.combined);
        world.setGravity(new Vector2(-10 * view.cam.up.x, -10 * view.cam.up.y));
        world.step(1 / 30f, 6, 2);
    }

    private void figUp() {
        if (figCode < 3) {
            figCode++;
            setFig();
        }
    }

    private void figDown() {
        if (figCode > 0) {
            figCode--;
            setFig();
        }
    }

    private void setFig() {
        Body body = fig.getBody();
        Vector2 pos = body.getPosition(), velocity = body.getLinearVelocity();
        float omega = body.getAngularVelocity();

        world.destroyBody(body);

        System.out.println("mode " + figCode);

        switch (figCode) {
        case 0:
            fig = new Triangle(world, pos, velocity, omega);
            break;

        case 1:
            fig = new Rectangle(world, pos, velocity, omega);
            break;

        case 2:
            fig = new Circle(world, pos, velocity, omega);
            break;

        case 3:
            fig = new Cross(world, pos, velocity, omega);
            break;

        default:
            System.err.println("setFig default !");
            System.exit(1);
        }
    }

    private void handleInput() {
        if (fig.canPlay) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                fig.onLeftPressed();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                fig.onLeftJustPressed();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                fig.onRightPressed();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                fig.onRightJustPressed();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                fig.onUpPressed();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                fig.onUpJustPressed();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                fig.onDownPressed();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                fig.onDownJustPressed();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            figUp();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            figDown();
        }

        fig.canPlay = fig.getBody().getLinearVelocity().len() < 0.1f;
        fig.update();
    }

    public void beginContact(Contact c) {
    }

    public void endContact(Contact c) {
    }

    public void preSolve(Contact contact, Manifold oldManifold) {}
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
