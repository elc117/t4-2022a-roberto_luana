package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.FlappyWitch;

public class MainMenuScreen extends FlappyWitchScreen {

    public MainMenuScreen(final FlappyWitch game) {
        super(game);
        super.backgroundImage = new Texture("initial_background.jpeg");
    }

    @Override
    public void renderContent(float delta) {
        game.getBatch().draw(backgroundImage, 0, 0, FlappyWitch.SCREEN_WIDTH, FlappyWitch.SCREEN_HEIGHT);
        game.getFont().draw(game.getBatch(), "Use espaço ou o botão esquerdo do mouse para começar", 100, 100);
        game.getBatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
    }

    @Override
    public void pause() {
    }
}
