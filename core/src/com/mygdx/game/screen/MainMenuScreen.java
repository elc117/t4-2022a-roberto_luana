package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.FlappyWitch;
import com.mygdx.game.screen.GameScreen;

public class MainMenuScreen implements Screen {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private final FlappyWitch game;
    private final OrthographicCamera camera;

    public MainMenuScreen(final FlappyWitch game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, WIDTH, HEIGHT);
        //game.getFont().getData().setScale(1,1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Welcome to NOT Drop!", 100, 150);
        game.getFont().draw(game.getBatch(), "Tap anywhere to begin!", 100, 100);
        game.getBatch().end();

        if (Gdx.input.isTouched()) {
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
    }

    @Override
    public void pause() {
    }
}
