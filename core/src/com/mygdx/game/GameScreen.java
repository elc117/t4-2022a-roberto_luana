package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private final FlappyWitch game;
    public static final int SCREEN_HEIGHT = 600;
    Texture imgBruxa;
    Rectangle bruxa;
    Texture imgObstaculo;
    OrthographicCamera camera;

    int jumpSpeed = 250;
    int fallingConstant = 1000;
    int vertSpeed = 0;

    private List<Obstacle> obstacles;
    private int counter;

    int gameRunning;

    public GameScreen(FlappyWitch game) {
        this.game = game;
        this.counter = 0;
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, SCREEN_HEIGHT);
        imgBruxa = new Texture("bruxa.png");
        imgObstaculo = new Texture("obstaculo.jpg");
        gameRunning = 1; //running

        bruxa = new Rectangle();
        bruxa.width = 100;
        bruxa.height = 115;
        bruxa.x = 0;
        // bruxa.x = 300 - bruxa.width / 2;
        bruxa.y = 300 - bruxa.height / 2;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();

        counter++;
        if (counter % 125 == 0)
            obstacles.add(new Obstacle());
  /*
        if(gameRunning == 1){
            counter++;
            if (counter % 125 == 0)
                obstacles.add(new Obstacle());
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
            if (bruxa.y > 600 - bruxa.height)
                bruxa.y = 600 - bruxa.height;
            if(bruxa.y == 0){
                gameRunning = 0;
                game.setScreen(new GameOverScreen(game));
            }

            for (Obstacle o : obstacles) {
                o.moveHorizontally(-150 * Gdx.graphics.getDeltaTime());
                if (o.collides(bruxa)) {
                    //bruxa.y = 0
                    System.out.println("COLIDIU");
                    gameRunning = 0;
                    game.setScreen(new GameOverScreen(game));
                    //game over
                }
            }
            obstacles.removeIf(o -> o.getLowerPart().x + o.getLowerPart().width < 0);
            System.out.println(obstacles.size());
        }
*/
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(imgBruxa, bruxa.x, bruxa.y);
        obstacles.forEach(o -> {
            game.getBatch().draw(imgObstaculo, o.getUpperPart().x, o.getUpperPart().y, o.getUpperPart().width, o.getUpperPart().height);
            game.getBatch().draw(imgObstaculo, o.getLowerPart().x, o.getLowerPart().y, o.getLowerPart().width, o.getLowerPart().height);
        });
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
        if (bruxa.y > 600 - bruxa.height)
            bruxa.y = 600 - bruxa.height;
        if(bruxa.y == 0)
            game.setScreen(new GameOverScreen(game));

        for (Obstacle o : obstacles) {
            o.moveHorizontally(-150 * Gdx.graphics.getDeltaTime());
            if (o.collides(bruxa)) {
                //bruxa.y = 0
                System.out.println("COLIDIU");
                game.setScreen(new GameOverScreen(game));
            }
        }
        obstacles.removeIf(o -> o.getLowerPart().x + o.getLowerPart().width < 0);
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

    /**
     * TODO: verificar o que mais deve ser disposed
     */
    @Override
    public void dispose() {
//        game.getBatch().dispose();
        imgBruxa.dispose();
        imgObstaculo.dispose();
    }
}
