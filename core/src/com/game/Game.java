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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.game.levels.*;

public class Game extends ApplicationAdapter {
	View view;
	Scene mainScene;

	@Override
	public void create() {
		view = View.get();
		mainScene = new TestScene();
		// mainScene = new Level1();
	}

	@Override
	public void render() {
		view.batch.begin();
		mainScene.render();
		view.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		view.viewport.update(width, height);
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
