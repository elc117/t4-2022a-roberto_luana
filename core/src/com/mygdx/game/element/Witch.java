package com.mygdx.game.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.screen.GameScreen;

public class Witch extends Rectangle {

    private static final float WITCH_WIDTH = 95f;
    private static final float WITCH_HEIGHT = 95f;
    private static final float WITCH_FALL_ACCELERATION_RATE = -1000;
    private static final float WITCH_JUMP_SPEED = 350;
    private float vertSpeed;

    public Witch() {
        super.width = WITCH_WIDTH;
        super.height = WITCH_HEIGHT;
        super.x = GameScreen.SCREEN_WIDTH / 2f - WITCH_WIDTH / 2f;
        super.y = GameScreen.SCREEN_HEIGHT / 2f - WITCH_HEIGHT / 2f;

        this.vertSpeed = 0f;
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

}
