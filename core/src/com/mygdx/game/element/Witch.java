package com.mygdx.game.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screen.GameScreen;

public class Witch extends Rectangle {

    private static final int WITCH_WIDTH = 98;
    private static final int WITCH_HEIGHT = 78;
    private static final String WITCH_ANIMATION_FILE = "witch_animation.png";
    private static final int WITCH_ACTUAL_WIDTH = 58;
    private static final int WITCH_ACTUAL_HEIGHT = 46;
    private static final float WITCH_FRAME_DURATION = 0.2f;
    private static final float WITCH_FALL_ACCELERATION_RATE = -1000f;
    private static final float WITCH_JUMP_SPEED = 350f;

    private float vertSpeed;
    private float stateTimer;
    private final Animation<TextureRegion> animation;


    public Witch() {
        super.width = WITCH_WIDTH;
        super.height = WITCH_HEIGHT;
        super.x = GameScreen.SCREEN_WIDTH / 2f - WITCH_WIDTH / 2f;
        super.y = GameScreen.SCREEN_HEIGHT / 2f - WITCH_HEIGHT / 2f;

        this.vertSpeed = 0f;

        this.stateTimer = 0f;
        Texture sprite = new Texture(Gdx.files.internal(WITCH_ANIMATION_FILE));
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(sprite, i * WITCH_ACTUAL_WIDTH, 0, WITCH_ACTUAL_WIDTH, WITCH_ACTUAL_HEIGHT));
        }
        this.animation = new Animation<>(WITCH_FRAME_DURATION, frames);
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

    public TextureRegion getFrame(float delta) {
        TextureRegion region;
        region = animation.getKeyFrame(stateTimer, true);
        stateTimer = stateTimer + delta;
        return region;
    }

}
