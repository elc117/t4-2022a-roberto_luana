package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameOverScreen implements Screen{
    private final FlappyWitch game;
    OrthographicCamera camera;
    Stage stage;
    TextButton restartButton;
    TextButton leaderBoardButton;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private int score;
    private int bestScore;

    public GameOverScreen(final FlappyWitch game){
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, WIDTH, HEIGHT);
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        restartButton = new TextButton("RESTART",mySkin,"small");
        restartButton.setSize(300,50);
        restartButton.setPosition(175,200);
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        stage.addActor(restartButton);

        leaderBoardButton = new TextButton("ADD TO LEADERBOARD",mySkin,"small");
        leaderBoardButton.setSize(300,50);
        leaderBoardButton.setPosition(175,100);
        leaderBoardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("LEADER BOARD");
                //game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        stage.addActor(leaderBoardButton);
        //this.score = score;
        //this.bestScore = bestScore;
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

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "SCORE", 215, 400);
        //game.getFont().draw(game.getBatch(), ""+score, WIDTH/2, HEIGHT/2 - 100);
        //game.getFont().draw(game.getBatch(), ""+bestScore, WIDTH/2, HEIGHT/2 - 200);
        game.getFont().draw(game.getBatch(), "X", 230, 380);
        game.getFont().draw(game.getBatch(), "BEST SCORE", 220, 350);
        game.getFont().draw(game.getBatch(), "Y", 230, 330);

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

    }

    @Override
    public void dispose() {

    }


}