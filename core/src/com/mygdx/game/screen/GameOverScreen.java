package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.FlappyWitch;

import java.awt.*;


public class GameOverScreen extends FlappyWitchScreen {
    private static final float BUTTON_WIDTH = 300;
    private static final float BUTTON_HEIGHT = 50;
    private final Stage stage;
    private final int score;
    private final Skin skin;


    public GameOverScreen(final FlappyWitch game, final int score) {
        super(game);
        this.score = score;

        this.stage = new Stage();
        super.backgroundImage = new Texture("game_over_background.jpeg");
        this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Gdx.input.setInputProcessor(stage);

        TextButton restartButton = new TextButton("RESTART", skin, "small");
        restartButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        restartButton.setPosition(FlappyWitch.SCREEN_WIDTH / 2f - BUTTON_WIDTH / 2f, FlappyWitch.SCREEN_HEIGHT / 8f);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("RESTART");
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(restartButton);

//        TextButton leaderBoardButton = new TextButton("ADD TO LEADERBOARD", skin, "small");
//        leaderBoardButton.setSize(300, 50);
//        leaderBoardButton.setPosition(175, 100);
//        leaderBoardButton.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("LEADERBOARD");
//
//            }
//        });
//        stage.addActor(leaderBoardButton);

        super.game.getFont().getData().setScale(1, 1);
    }

    @Override
    public void show() {
    }

    @Override
    public void renderContent(float delta) {

        game.getBatch().draw(backgroundImage, 0, 0, FlappyWitch.SCREEN_WIDTH, FlappyWitch.SCREEN_HEIGHT);
        game.getFont().draw(game.getBatch(), "SCORE", FlappyWitch.SCREEN_WIDTH / 2f, FlappyWitch.SCREEN_HEIGHT / 8f * 3);
        game.getFont().draw(game.getBatch(), String.valueOf(score), FlappyWitch.SCREEN_WIDTH / 2f + 120, FlappyWitch.SCREEN_HEIGHT / 8f * 3);
        game.getFont().draw(game.getBatch(), "BEST SCORE", FlappyWitch.SCREEN_WIDTH / 2f, FlappyWitch.SCREEN_HEIGHT / 16f * 5);
        game.getFont().draw(game.getBatch(), String.valueOf(game.getBestScore()), FlappyWitch.SCREEN_WIDTH / 2f + 120, FlappyWitch.SCREEN_HEIGHT / 16f * 5);
        game.getBatch().end();

        stage.act();
        stage.draw();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundImage.dispose();
    }


}
