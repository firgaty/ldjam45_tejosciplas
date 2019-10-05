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

public abstract class Figure {

    public boolean isContact = false;
    public abstract Body getBody();

    public void onLeftPressed() {}
    public void onRightPressed() {}
    public void onUpPressed() {}
    public void onDownPressed() {}

    public void onLeftJustPressed() {}
    public void onRightJustPressed() {}
    public void onUpJustPressed() {}
    public void onDownJustPressed() {}

    public void update() {}

}
