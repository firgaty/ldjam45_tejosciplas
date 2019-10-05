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

class TestScene extends Scene {
    Texture img;
    Box2DDebugRenderer debugRenderer;

    public TestScene() {
        super();
        img = new Texture("badlogic.jpg");
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        setupPhysics();
    }

    private Body dynBody;

    private void setupPhysics() {
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we
        // would set it to StaticBody
        bodyDef.type = BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(10, 50);

        // Create our body in the world using our body definition
        dynBody = world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(2f);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = dynBody.createFixture(fixtureDef);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

        // Create our body definition
        BodyDef groundBodyDef = new BodyDef();
        // Set its world position
        groundBodyDef.position.set(-50, 10);

        // Create a body from the defintion and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
        // Set the polygon shape as a box which is twice the size of our view port and
        // 20 high
        // (setAsBox takes half-width and half-height as arguments)
        groundBox.setAsBox(100, 1);
        // Create a fixture from our polygon shape and add it to our ground body
        groundBody.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
    }

    public void render() {
        handleInput();
        view.cam.update();
        view.batch.setProjectionMatrix(view.cam.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // view.batch.begin();
        // // view.batch.draw(img, 0, 0);
        // view.batch.end();
        debugRenderer.render(world, view.cam.combined);
        world.setGravity(new Vector2(-10 * view.cam.up.x, -10 * view.cam.up.y));
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
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dynBody.applyLinearImpulse(-0.1f, 0, 0, 0.5f, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dynBody.applyLinearImpulse(0.1f, 0, 0, 0.5f, true);
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