package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.FlappyWitch;

public abstract class FlappyWitchScreen implements Screen {

    protected final FlappyWitch game;
    protected Texture backgroundImage;
    protected Rectangle viewport;

    public FlappyWitchScreen(final FlappyWitch game) {
        this.game = game;
    }

    @Override
    public abstract void show();

    @Override
    public abstract void render(float delta);

    /**
     * Solução para redimensionamento encontrada em
     * <http://acamara.es/blog/2012/02/keep-screen-aspect-ratio-with-different-resolutions-using-libgdx/>
     *
     * @param width
     * @param height
     */
    @Override
    public final void resize(int width, int height) {
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
    public abstract void pause();

    @Override
    public abstract void resume();

    @Override
    public final void hide() {
        this.dispose();
    }

    @Override
    public abstract void dispose();
}
