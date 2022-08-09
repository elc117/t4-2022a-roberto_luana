package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.FlappyWitch;


public class GameOverScreen implements Screen {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private final FlappyWitch game;
    private final OrthographicCamera camera;
    private final Stage stage;
    private final int score;
    private final Skin skin;

    public GameOverScreen(final FlappyWitch game, final int score) {
        this.game = game;
        this.score = score;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, WIDTH, HEIGHT);

        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Gdx.input.setInputProcessor(stage);

        TextButton restartButton = new TextButton("RESTART", skin, "small");
        restartButton.setSize(300, 50);
        restartButton.setPosition(175, 200);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("RESTART");
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(restartButton);

        TextButton leaderBoardButton = new TextButton("ADD TO LEADERBOARD", skin, "small");
        leaderBoardButton.setSize(300, 50);
        leaderBoardButton.setPosition(175, 100);
        leaderBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("LEADERBOARD");
//                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(leaderBoardButton);

        game.getFont().getData().setScale(1, 1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        //TODO: make coordinates dynamic for resizing. Perhaps using a table would be helpful.
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "SCORE", 215, 400);
        game.getFont().draw(game.getBatch(), String.valueOf(score), 230, 380);
        game.getFont().draw(game.getBatch(), "BEST SCORE", 220, 350);
        game.getFont().draw(game.getBatch(), String.valueOf(game.getBestScore()), 230, 330);

        game.getBatch().end();
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }


}
