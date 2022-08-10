package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.screen.MainMenuScreen;

public class FlappyWitch extends Game {
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 600;
    public static final float ASPECT_RATIO = (float) FlappyWitch.SCREEN_WIDTH / (float) FlappyWitch.SCREEN_HEIGHT;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    private int bestScore;

    @Override
    public void create() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(this.camera.combined);

        this.font = new BitmapFont();
        this.font.setColor(1,0,1,1);

        this.bestScore = 0;

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void updateBestScore(int newScore) {
        if (newScore > bestScore) {
            bestScore = newScore;
        }
    }
}
