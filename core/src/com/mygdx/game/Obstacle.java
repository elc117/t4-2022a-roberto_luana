package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

import java.security.PrivateKey;
import java.util.Random;

public class Obstacle {

    private static final int OBSTACLE_WIDTH = 50;
    private static final int OBSTACLE_INITIAL_X = 600;
    private static final int OBSTACLE_GAP_HEIGHT = 200;
    private static final int OBSTACLE_LOWER_PART_MAX_HEIGHT = GameScreen.SCREEN_HEIGHT - OBSTACLE_GAP_HEIGHT;
private static final int OBSTACLE_HORIZONTAL_DISPLACEMENT = -150;

    private final Rectangle upperPart;
    private final Rectangle lowerPart;

    public Obstacle() {
        int obstacleLowerPartHeight = new Random().ints(0, OBSTACLE_LOWER_PART_MAX_HEIGHT).findFirst().getAsInt();
        int obstacleLowerPartY = 0;

        int obstacleUpperPartHeight = GameScreen.SCREEN_HEIGHT - OBSTACLE_GAP_HEIGHT - obstacleLowerPartHeight;
        int obstacleUpperPartY = GameScreen.SCREEN_HEIGHT - obstacleUpperPartHeight;

        lowerPart = new Rectangle(OBSTACLE_INITIAL_X, obstacleLowerPartY, OBSTACLE_WIDTH, obstacleLowerPartHeight);
        upperPart = new Rectangle(OBSTACLE_INITIAL_X, obstacleUpperPartY, OBSTACLE_WIDTH, obstacleUpperPartHeight);
    }

    public Rectangle getUpperPart() {
        return upperPart;
    }

    public Rectangle getLowerPart() {
        return lowerPart;
    }

    public void moveHorizontally(float delta) {
        this.upperPart.x += OBSTACLE_HORIZONTAL_DISPLACEMENT * delta;
        this.lowerPart.x += OBSTACLE_HORIZONTAL_DISPLACEMENT * delta;
    }

    public boolean collides(Rectangle comparedRectangle) {
        return this.lowerPart.overlaps(comparedRectangle) || this.upperPart.overlaps(comparedRectangle);
    }

    public boolean outOfVisibleScreenRange() {
        return this.lowerPart.x + this.lowerPart.width < 0;
    }
}
