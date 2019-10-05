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

// TODO: mettre le moteur physique dans une classe à part.
// le world.step() est a appeler en fin de boucle de rendu.

public class Scene {
    protected View view;
    protected World world;

    public Scene() {
        view = View.get();
        view.batch = new SpriteBatch();
    }

    void render() {
        // TODO: ceci est un test, reste à pauffiner.
        handleInput();

    }

    // TODO: à dégager et à gérer comme il faut, en fonction du péon utilisé.
    private void handleInput() {}

    public View getView() {
        return view;
    }

    public World getWorld() {
        return world;
    }
}
