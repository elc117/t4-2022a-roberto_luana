package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    public static final int SCREEN_HEIGHT = 600;

    private final FlappyWitch game;
    private final Texture imgBruxa;
    private final Witch bruxa;
    private final Texture imgObstaculo;
    private final OrthographicCamera camera;
    private final List<Obstacle> obstacles;

    private int counter;


    public GameScreen(FlappyWitch game) {
        this.game = game;
        this.counter = 0;
        this.obstacles = new ArrayList<>();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 600, SCREEN_HEIGHT);

        this.imgBruxa = new Texture("bruxa.png");
        this.imgObstaculo = new Texture("obstaculo.jpg");

        this.bruxa = new Witch();
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

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(imgBruxa, bruxa.x, bruxa.y, bruxa.width, bruxa.height);
        obstacles.forEach(o -> {
            game.getBatch().draw(imgObstaculo, o.getUpperPart().x, o.getUpperPart().y, o.getUpperPart().width, o.getUpperPart().height);
            game.getBatch().draw(imgObstaculo, o.getLowerPart().x, o.getLowerPart().y, o.getLowerPart().width, o.getLowerPart().height);
        });
        game.getBatch().end();

        bruxa.moveVertically(delta);

        if (bruxa.y == 0)
            game.setScreen(new GameOverScreen(game));

        for (Obstacle o : obstacles) {
            o.moveHorizontally(delta);
            if (o.collides(bruxa))
                game.setScreen(new GameOverScreen(game));
        }
        obstacles.removeIf(Obstacle::outOfVisibleScreenRange);
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
     * TODO: verificar o que mais deve ser disposed. Check AssetManager
     */
    @Override
    public void dispose() {
        imgBruxa.dispose();
        imgObstaculo.dispose();
    }
}
