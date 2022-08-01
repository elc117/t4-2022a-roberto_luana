package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 600;
    public static final float ASPECT_RATIO = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;

    private final FlappyWitch game;
    private final Witch bruxa;
    private final Texture imgBruxa;
    private final Texture imgObstaculo;
    private final Texture imgFundo;
    private final OrthographicCamera camera;
    private final List<Obstacle> obstacles;

    private int counter;
    private int score;
    private Rectangle viewport;


    public GameScreen(FlappyWitch game) {
        this.game = game;

        this.counter = 0;
        this.score = 0;

        this.obstacles = new ArrayList<>();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 600, SCREEN_HEIGHT);

        this.imgBruxa = new Texture("bruxa.png");
        this.imgObstaculo = new Texture("obstaculo.jpg");
        this.imgFundo = new Texture("dark_background.png");

        this.bruxa = new Witch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);

        counter++;
        if (counter % 125 == 0)
            obstacles.add(new Obstacle());

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(imgFundo, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.getBatch().draw(imgBruxa, bruxa.x, bruxa.y, bruxa.width, bruxa.height);
        obstacles.forEach(o -> {
            game.getBatch().draw(imgObstaculo, o.getUpperPart().x, o.getUpperPart().y, o.getUpperPart().width, o.getUpperPart().height);
            game.getBatch().draw(imgObstaculo, o.getLowerPart().x, o.getLowerPart().y, o.getLowerPart().width, o.getLowerPart().height);
        });
        game.getBatch().end();

        bruxa.moveVertically(delta);

        if (bruxa.y == 0)
            finishGame();

        obstacles.forEach(o -> {
            o.moveHorizontally(delta);
            if (o.outOfVisibleScreenRange())
                score++;
            if (o.collides(bruxa))
                finishGame();
        });
        obstacles.removeIf(Obstacle::outOfVisibleScreenRange);
    }

    private void finishGame() {
        game.updateBestScore(score);
        game.setScreen(new GameOverScreen(game, score));
    }

    /**
     * Solução para redimensionamento encontrada em
     * <http://acamara.es/blog/2012/02/keep-screen-aspect-ratio-with-different-resolutions-using-libgdx/>
     *
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);

        if (aspectRatio > ASPECT_RATIO) {
            scale = (float) height / (float) SCREEN_HEIGHT;
            crop.x = (width - SCREEN_WIDTH * scale) / 2f;
        } else if (aspectRatio < ASPECT_RATIO) {
            scale = (float) width / (float) SCREEN_WIDTH;
            crop.y = (height - SCREEN_HEIGHT * scale) / 2f;
        } else
            scale = (float) width / (float) SCREEN_WIDTH;

        float w = (float) SCREEN_WIDTH * scale;
        float h = (float) SCREEN_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
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
