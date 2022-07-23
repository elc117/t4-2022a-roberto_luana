package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final FlappyWitch game;
    Texture imgBruxa;
    Rectangle bruxa;
    Texture imgObstaculo;
    Rectangle obstaculoCima, obstaculoBaixo, gap;
    OrthographicCamera camera;

    int jumpSpeed = 200;
    int fallingConstant = 1000;
    int vertSpeed = 0;


    public GameScreen(FlappyWitch game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 600);
        imgBruxa = new Texture("bruxa.png");
        imgObstaculo = new Texture("obstaculo.jpg");


        bruxa = new Rectangle();
        bruxa.width = 100;
        bruxa.height = 115;
        bruxa.x = 0;
        // bruxa.x = 300 - bruxa.width / 2;
        bruxa.y = 300 - bruxa.height / 2;

        obstaculoCima = new Rectangle();
        obstaculoBaixo = new Rectangle();
        gap = new Rectangle();

        //gap.y = gerado aleatoriamente
        //gap.height = tamanho do gap
        //gap.width = mesma do obstaculo
        //gap.x = mesma do obstaculo
        //y eh onde comeca e onde acaba eh o y + heigth

        gap.x = 400;
        gap.y = 200;
        gap.height = 200;
        gap.width = 100;

        obstaculoCima.width = 100;
        obstaculoCima.height = 600;
        obstaculoCima.x = 400;
        obstaculoCima.y = gap.y + gap.height;

        obstaculoBaixo.width = 100;
        obstaculoBaixo.height = 600;
        obstaculoBaixo.x = 400;
        obstaculoBaixo.y = gap.y - 600;
        //criar classe de obstaculo
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
        game.getBatch().draw(imgObstaculo, obstaculoCima.x, obstaculoCima.y);
        game.getBatch().draw(imgObstaculo, obstaculoBaixo.x, obstaculoBaixo.y);
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

        obstaculoCima.x -= 1;
        obstaculoBaixo.x -= 1;

        if ((bruxa.x + bruxa.width == obstaculoCima.x) && (bruxa.y <= gap.y || bruxa.y + bruxa.height >= obstaculoCima.y)) {
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
