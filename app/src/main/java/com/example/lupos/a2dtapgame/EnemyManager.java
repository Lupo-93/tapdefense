package com.example.lupos.a2dtapgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by lupos on 09.03.2018.
 */

public class EnemyManager {
    private ArrayList<CharacterSprite> enemies;
    private Bitmap enemySprite;
    private int maxEnemies = 30;
    private double enemySpeed = 20.0;
    private double targetX;
    private double targetY;
    public Paint p;

    public EnemyManager(Bitmap bitmap){
        enemies = new ArrayList<>();
        enemySprite = bitmap;
        populateEnemies();
        p = new Paint();
        p.setColor(Color.rgb(250,0,0));
    }

    public void drawRects(Canvas canvas){
        canvas.drawRect((float)targetX, (float)targetY, (float)targetX+10, (float)targetY+10, p);
    }

    private void populateEnemies(){
        while(enemies.size() < maxEnemies){
            enemies.add(new CharacterSprite(enemySprite, (int)(Math.random()*Constants.SCREEN_WIDTH), -50));
        }
    }

    public void startEnemies(double princessX, double princessY) {
        targetX = princessX;
        targetY = princessY;

        for (CharacterSprite e : enemies) {
            calculateDirection(e);
            e.setSpeed(enemySpeed);
        }

    }

    public boolean touchEnemies(double x, double y){
        for(CharacterSprite e : enemies){
            if(x >= e.getX() && x < (e.getX() + e.getImage().getWidth()) && y >= e.getY() && y < (e.getY() + e.getImage().getHeight())){
                this.reset(e);
                return true;
            }
        }
        return false;
    }

    private void calculateDirection(CharacterSprite e){
        double xVec, yVec, l;
        xVec = targetX - (double)e.getX();
        yVec = targetY - (double)e.getY();
        l = Math.sqrt((xVec * xVec) + (yVec * yVec));
        e.setVelo(xVec / l, yVec / l);
    }

    public void reset(CharacterSprite e){
        e.setPos((int)(Math.random()*Constants.SCREEN_WIDTH), (int)(Math.random()*-300)-50);
        this.calculateDirection(e);
    }

    public void update(){
        for (CharacterSprite e : enemies){
            e.update();
            if(e.getY() > Constants.SCREEN_HEIGHT){
                reset(e);
            }
        }

    }

    public void draw(Canvas canvas){
        for (CharacterSprite e : enemies) e.draw(canvas);
        this.drawRects(canvas);
    }
}
