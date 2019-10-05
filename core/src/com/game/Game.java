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

public class Game extends ApplicationAdapter {
    View view;
    Scene mainScene;

    @Override
    public void create() {
        view = View.get();
        mainScene = new TestScene();
    }

    @Override
    public void render() {
        mainScene.render();
    }

    @Override
    public void resize(int width, int height) {
        view.cam.viewportWidth = 60f;
        view.cam.viewportHeight = 60f * height / width;
        view.cam.update();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        view.batch.dispose();
    }

    @Override
    public void pause() {
    }
}
