package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.FlappyWitch;

public class MainMenuScreen implements Screen {
    private final FlappyWitch game;
    private final Texture imgFundo;
    private Rectangle viewport;

    public MainMenuScreen(final FlappyWitch game) {
        this.game = game;

        this.imgFundo = new Texture("initial_background.jpeg");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.getCamera().update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);

        game.getBatch().begin();
        game.getBatch().draw(imgFundo, 0, 0, FlappyWitch.SCREEN_WIDTH, FlappyWitch.SCREEN_HEIGHT);
        game.getFont().draw(game.getBatch(), "Use espaço ou o botão esquerdo do mouse para começar", 100, 100);
        game.getBatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            game.setScreen(new GameScreen(game));
        }
    }


    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);

        if (aspectRatio > FlappyWitch.ASPECT_RATIO) {
            scale = (float) height / (float) FlappyWitch.SCREEN_HEIGHT;
            crop.x = (width - FlappyWitch.SCREEN_WIDTH * scale) / 2f;
        } else if (aspectRatio < FlappyWitch.ASPECT_RATIO) {
            scale = (float) width / (float) FlappyWitch.SCREEN_WIDTH;
            crop.y = (height - FlappyWitch.SCREEN_HEIGHT * scale) / 2f;
        } else
            scale = (float) width / (float) FlappyWitch.SCREEN_WIDTH;

        float w = (float) FlappyWitch.SCREEN_WIDTH * scale;
        float h = (float) FlappyWitch.SCREEN_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        imgFundo.dispose();
    }

    @Override
    public void pause() {
    }
}
