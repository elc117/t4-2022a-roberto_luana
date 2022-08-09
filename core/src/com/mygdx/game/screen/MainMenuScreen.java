package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.FlappyWitch;

public class MainMenuScreen implements Screen {
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_WIDTH = 600;

    private final FlappyWitch game;
    private final OrthographicCamera camera;
    private final Texture imgFundo;

    public MainMenuScreen(final FlappyWitch game) {
        this.game = game;

        this.imgFundo = new Texture("initial_background.jpeg");

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void render(float delta) {
        camera.update();

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(imgFundo, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.getFont().draw(game.getBatch(), "Use espaço ou o botão esquerdo do mouse para começar", 100, 100);
        game.getBatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            game.setScreen(new GameScreen(game));
        }
    }


    @Override
    public void resize(int width, int height) {
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
