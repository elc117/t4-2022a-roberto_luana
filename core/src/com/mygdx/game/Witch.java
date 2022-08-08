package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class Witch extends Rectangle {

    private static final float WITCH_WIDTH = 95f;
    private static final float WITCH_HEIGHT = 95f;
    private static final float WITCH_FALL_ACCELERATION_RATE = -1000;
    private static final float WITCH_JUMP_SPEED = 350;
    private float vertSpeed;

    public Texture witchSprite;
    private Animation<TextureRegion> witchAnimation;
    private float stateTimer;


    public Witch() {
        super.width = WITCH_WIDTH;
        super.height = WITCH_HEIGHT;
        super.x = 0f;
        super.y = GameScreen.SCREEN_HEIGHT / 2f - WITCH_HEIGHT / 2f;

        this.vertSpeed = 0f;

        stateTimer =0;
        witchSprite = new Texture(Gdx.files.internal("witch_animation.png"));
        Array<TextureRegion> frames = new Array<>();
        for(int i=0;i<3;i++){
            frames.add(new TextureRegion(witchSprite,i*58,0,58,46));
        }
        witchAnimation = new Animation<>(0.2f,frames);

    }

    public void moveVertically(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            vertSpeed = WITCH_JUMP_SPEED;
        }
        super.y += vertSpeed * delta;

        updateVerticalSpeed(delta);
        adjustCoordinatesToScreen();
    }

    private void updateVerticalSpeed(float delta) {
        vertSpeed += WITCH_FALL_ACCELERATION_RATE * delta;
    }

    private void adjustCoordinatesToScreen() {
        if (super.y < 0)
            super.y = 0;
        if (super.y > GameScreen.SCREEN_HEIGHT - super.height)
            super.y = GameScreen.SCREEN_HEIGHT - super.height;
    }

    public TextureRegion getFrame(float delta){
        TextureRegion region;
        region = witchAnimation.getKeyFrame(stateTimer,true);
        stateTimer = stateTimer + delta;
        return region;

    }

}
