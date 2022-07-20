package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Rectangle bruxa;
//    OrthographicCamera camera;

    int jumpSpeed = 200;
    int fallingConstant = 1000;
    int vertSpeed = 0;


    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("bruxa.png");

//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 600, 480);

        bruxa = new Rectangle();

        bruxa.width = 167;
        bruxa.height = 166;

        bruxa.x = 300 - bruxa.width / 2;
        bruxa.y = 300 - bruxa.height / 2;
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        batch.draw(img, bruxa.x, bruxa.y);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            vertSpeed = jumpSpeed;
//            bruxa.y += 800 * Gdx.graphics.getDeltaTime();
        }
        bruxa.y += vertSpeed * Gdx.graphics.getDeltaTime();
        vertSpeed -= fallingConstant * Gdx.graphics.getDeltaTime();

//        else
//        bruxa.y -= 150 * Gdx.graphics.getDeltaTime();

        if (bruxa.y < 0)
            bruxa.y = 0;
//        if (bruxa.y > 600 - bruxa.height)
//            bruxa.y = 600 - bruxa.height;

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
