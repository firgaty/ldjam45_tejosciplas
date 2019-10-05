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

class View {
    static final int WORLD_WIDTH = 100;
	static final int WORLD_HEIGHT = 100;

    public OrthographicCamera cam;
	public SpriteBatch batch;
	public float rotationSpeed;

    private static View instance = null;

    private View() {
        rotationSpeed = 0.5f;
        float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(10, 10 * (h / w));
        batch = new SpriteBatch();
    }

    public static View get() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }
}