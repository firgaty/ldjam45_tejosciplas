package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class View {
    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    public OrthographicCamera cam;
    public SpriteBatch batch;
    public float rotationSpeed;
    public Viewport viewport;

    private static View instance = null;

    private View() {
        rotationSpeed = 0.5f;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(10, 10 * (h / w));
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 400, cam);
    }

    public static View get() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }
}
