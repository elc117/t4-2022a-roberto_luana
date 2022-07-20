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
    Rectangle bucket;
//    OrthographicCamera camera;

    int jumpSpeed = 300;
    int fallingConstant = 450;
    int vertSpeed = 0;


    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("bola.png");

//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 600, 480);

        bucket = new Rectangle();

        bucket.width = 167;
        bucket.height = 166;

        bucket.x = 300 - bucket.width / 2;
        bucket.y = 300 - bucket.height / 2;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 1, 0, 1);
        batch.begin();
        batch.draw(img, bucket.x, bucket.y);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            vertSpeed = jumpSpeed;
//            bucket.y += 800 * Gdx.graphics.getDeltaTime();
        }
        bucket.y += vertSpeed * Gdx.graphics.getDeltaTime();
        vertSpeed -= fallingConstant * Gdx.graphics.getDeltaTime();

//        else
//        bucket.y -= 150 * Gdx.graphics.getDeltaTime();

        if (bucket.y < 0)
            bucket.y = 0;
//        if (bucket.y > 600 - bucket.height)
//            bucket.y = 600 - bucket.height;

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
