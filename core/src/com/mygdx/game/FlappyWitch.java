package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.MainMenuScreen;

public class FlappyWitch extends Game {

    private SpriteBatch batch;
    private BitmapFont font;

    private int bestScore;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();

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

    public int getBestScore() {
        return bestScore;
    }

    public void updateBestScore(int newScore) {
        if (newScore > bestScore) {
            bestScore = newScore;
        }
    }
}
