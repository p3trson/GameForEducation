package org.example;

import org.example.logic.*;
import org.example.Game;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameLogic {
    private Ball ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    private final int BALL_STEP = 5;
    private final int ENEMY_STEPS = 2;


    public GameLogic() {
        this.ball = null;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public void initialize() {

        ball = new Ball(20, 20, "Player right1.png");

        Enemy enemy = new Enemy(50,150,"bomb.jpg", 1);
        enemies.add(enemy);

        Wall wall1 = new Wall(250, 30, 250, 130, Color.BLACK);
        Wall wall2 = new Wall(100, 50, 150, 50, Color.BLACK);
        walls.add(wall1);
        walls.add(wall2);
    }

    public void update() {
        for (Enemy enemy: enemies) {
            int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
            int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);

            if(ball.isCollided(enemy.getRectangle())) {
                gameOver();
            }

            if (differenceX > differenceY) {
                if (ball.getCoord().x - enemy.getCoord().x > 0) {
                    controlledEnemyMove(Direction.RIGHT);
                } else {
                    controlledEnemyMove(Direction.LEFT);
                }
            } else {
                if (ball.getCoord().y - enemy.getCoord().y > 0) {
                    controlledEnemyMove(Direction.DOWN);
                } else {
                    controlledEnemyMove(Direction.UP);
                }
            }
        }

    }

    public boolean predictCollisionBall(Direction direction){
        Rectangle moveRectangle = new Rectangle();
        switch (direction){
            case UP -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() - BALL_STEP, ball.getWidth(), ball.getHeight());
            }
            case DOWN -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() + BALL_STEP, ball.getWidth(), ball.getHeight());
            }
            case LEFT -> {
                moveRectangle = new Rectangle(ball.getX() - BALL_STEP, ball.getY(), ball.getWidth(), ball.getHeight());
            }
            case RIGHT -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() + BALL_STEP, ball.getWidth(), ball.getHeight());
            }
        }
        for (Wall wall : walls) {
            if(moveRectangle.intersects(wall.getRectangle())) {
                return true;
            }
        }
        return false;
    }

    public boolean predictCollisionEnemy(Enemy enemy, Direction direction){
        Rectangle moveEnemyRectangle = new Rectangle();
        switch (direction){
            case UP -> {
                moveEnemyRectangle = new Rectangle(enemy.getCoord().x, enemy.getCoord().y - ENEMY_STEPS, enemy.getWidth(), enemy.getHeight());
            }
            case DOWN -> {
                moveEnemyRectangle = new Rectangle(enemy.getCoord().x, enemy.getCoord().y + ENEMY_STEPS, enemy.getWidth(), enemy.getHeight());
            }
            case LEFT -> {
                moveEnemyRectangle = new Rectangle(enemy.getCoord().x - ENEMY_STEPS, enemy.getCoord().y, enemy.getWidth(), enemy.getHeight());
            }
            case RIGHT -> {
                moveEnemyRectangle = new Rectangle(enemy.getCoord().x + ENEMY_STEPS, enemy.getCoord().y, enemy.getWidth(), enemy.getHeight());
            }
        }
        for (Wall wall : walls) {
            if(moveEnemyRectangle.intersects(wall.getRectangle())) {
                return true;
            }
        }
        return false;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Enemy> getEnemy() {
        return enemies;
    }

    public void movePlayer(Direction direction) {
        ball.move(BALL_STEP, direction);
    }
    private void controlledEnemyMove(Direction direction) {
        for (Enemy enemy: enemies) {
            if (!predictCollisionEnemy(enemy, direction)) {
                enemy.move(ENEMY_STEPS, direction);
            }
        }
    }

    public void gameOver() {
        System.out.println("Game Over");
        System.exit(0);
    }
}
