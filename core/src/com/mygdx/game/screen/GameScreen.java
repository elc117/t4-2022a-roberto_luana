package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.FlappyWitch;
import com.mygdx.game.element.Obstacle;
import com.mygdx.game.element.Witch;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends FlappyWitchScreen {
    private final Witch bruxa;
    private final Texture imgObstaculoCima;
    private final Texture imgObstaculoBaixo;
    private final List<Obstacle> obstacles;

    private int counter;
    private int score;

    public GameScreen(final FlappyWitch game) {
        super(game);

        this.counter = 0;
        this.score = 0;

        this.obstacles = new ArrayList<>();

        this.imgObstaculoCima = new Texture("brick.png");
        this.imgObstaculoBaixo = new Texture("torch.png");
        super.backgroundImage = new Texture("dark_background.png");

        this.bruxa = new Witch();
        super.game.getFont().getData().setScale(3, 3);
    }

    @Override
    public void show() {
    }

    @Override
    public void renderContent(float delta) {
        counter++;
        if (counter % 125 == 0)
            obstacles.add(new Obstacle());

        game.getBatch().draw(backgroundImage, 0, 0, FlappyWitch.SCREEN_WIDTH, FlappyWitch.SCREEN_HEIGHT);
        obstacles.forEach(o -> {
            game.getBatch().draw(imgObstaculoCima, o.getUpperPart().x, o.getUpperPart().y, o.getUpperPart().width, o.getUpperPart().height);
            game.getBatch().draw(imgObstaculoBaixo, o.getLowerPart().x, o.getLowerPart().y, o.getLowerPart().width, o.getLowerPart().height);
        });
        game.getFont().draw(game.getBatch(), String.valueOf(score), 300, 550);
        game.getBatch().draw(bruxa.getFrame(delta), bruxa.x, bruxa.y, bruxa.width, bruxa.height);
        game.getBatch().end();

        bruxa.moveVertically(delta);

        if (bruxa.y == 0)
            finishGame();

        obstacles.forEach(o -> {
            o.moveHorizontally(delta);
            if (!o.getScored() && o.isOnLeftOf(bruxa)) {
                o.setScored(true);
                score++;
            }
            if (o.collides(bruxa))
                finishGame();
        });
        obstacles.removeIf(Obstacle::isOutOfVisibleScreenRange);
    }

    private void finishGame() {
        game.updateBestScore(score);
        game.setScreen(new GameOverScreen(game, score));
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        imgObstaculoBaixo.dispose();
        imgObstaculoCima.dispose();
        backgroundImage.dispose();
        bruxa.dispose();
    }
}
