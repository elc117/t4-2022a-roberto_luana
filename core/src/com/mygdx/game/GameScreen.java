package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final FlappyWitch game;
    public static final int SCREEN_HEIGHT = 600;
    Texture imgBruxa;
    Rectangle bruxa;
    Texture imgObstaculo;
    Obstacle obstacle;
    OrthographicCamera camera;

    int jumpSpeed = 200;
    int fallingConstant = 1000;
    int vertSpeed = 0;


    public GameScreen(FlappyWitch game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, SCREEN_HEIGHT);
        imgBruxa = new Texture("bruxa.png");
        imgObstaculo = new Texture("obstaculo.jpg");


        bruxa = new Rectangle();
        bruxa.width = 100;
        bruxa.height = 115;
        bruxa.x = 0;
        // bruxa.x = 300 - bruxa.width / 2;
        bruxa.y = 300 - bruxa.height / 2;

        obstacle = new Obstacle();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(imgBruxa, bruxa.x, bruxa.y);
        game.getBatch().draw(imgObstaculo, obstacle.getUpperPart().x, obstacle.getUpperPart().y, obstacle.getUpperPart().width, obstacle.getUpperPart().height);
        game.getBatch().draw(imgObstaculo, obstacle.getLowerPart().x, obstacle.getLowerPart().y, obstacle.getLowerPart().width, obstacle.getLowerPart().height);
        game.getBatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            vertSpeed = jumpSpeed;
//            bruxa.y += 800 * Gdx.graphics.getDeltaTime();
        }
        bruxa.y += vertSpeed * Gdx.graphics.getDeltaTime();
        vertSpeed -= fallingConstant * Gdx.graphics.getDeltaTime();

//        else
//        bruxa.y -= 150 * Gdx.graphics.getDeltaTime();

        if (bruxa.y < 0)
            bruxa.y = 0;
//        if (bruxa.y > 600 - bruxa.height)
//            bruxa.y = 600 - bruxa.height;

        obstacle.moveHorizontally(-2);

        if (obstacle.collides(bruxa)) {
            //bruxa.y = 0
            System.out.println("COLIDIU");
            //game over
        }
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
        game.getBatch().dispose();
        imgBruxa.dispose();
    }
}
