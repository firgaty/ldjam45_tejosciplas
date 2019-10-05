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

import com.game.figures.*;

class TestScene extends Scene {

    Box2DDebugRenderer debugRenderer;
    Figure fig;

    public TestScene() {
        super();

        world = new World(new Vector2(0, -10), true);
        Figure.CIRCLE = new Circle(world);

        debugRenderer = new Box2DDebugRenderer();

        // platform
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(-50, 10);
        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(100, 1);
        groundBody.createFixture(groundBox, 0.0f);

        // Clean up after ourselves
        groundBox.dispose();


        fig = Figure.CIRCLE;
    }

    public void render() {
        handleInput();
        view.cam.update();
        view.batch.setProjectionMatrix(view.cam.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, view.cam.combined);
        world.setGravity(new Vector2(-10 * view.cam.up.x,
                                     -10 * view.cam.up.y));
        world.step(1/60f, 6, 2);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            view.cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            view.cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            view.cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            view.cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            view.cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            view.cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            view.cam.rotate(-view.rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            view.cam.rotate(view.rotationSpeed, 0, 0, 1);
        }


        view.cam.zoom = MathUtils.clamp(view.cam.zoom, 0.1f, 100 / view.cam.viewportWidth);

        float effectiveViewportWidth = view.cam.viewportWidth * view.cam.zoom;
        float effectiveViewportHeight = view.cam.viewportHeight * view.cam.zoom;

        view.cam.position.x = MathUtils.clamp(view.cam.position.x, effectiveViewportWidth / 2f,
                100 - effectiveViewportWidth / 2f);
        view.cam.position.y = MathUtils.clamp(view.cam.position.y, effectiveViewportHeight / 2f,
                100 - effectiveViewportHeight / 2f);
    }
}
