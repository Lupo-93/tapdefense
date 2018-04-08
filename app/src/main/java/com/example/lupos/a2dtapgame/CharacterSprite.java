package com.example.lupos.a2dtapgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by lupos on 07.03.2018.
 */

public class CharacterSprite {
    private Bitmap image;
    private double x, y;
    private double centerX, centerY;
    private double xVelocity = 0;
    private double yVelocity = 0;
    private double speed = 0;

    public CharacterSprite(Bitmap bitmap, double x, double y){
        this.image = bitmap;
        this.x=x;
        this.y=y;
        this.centerX = x + (double)image.getWidth()/2.0;
        this.centerY = y + (double)image.getHeight()/2.0;
    }

    /* Getter ---------------------------------------------------------*/
    public Bitmap getImage(){
        return image;
    }

    public double getX(){
        return x;
    }

    public double getCenterX(){
        return centerX;
    }

    public double getY(){
        return y;
    }

    public double getCenterY(){
        return centerY;
    }


    /* Setter ---------------------------------------------------------*/
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setSpeed(double spd){
        this.speed = spd;
    }


    /* Methods ---------------------------------------------------------*/
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, (int)x, (int)y, null);
    }

    public void update(){
        x += (xVelocity * speed);
        y += (yVelocity * speed);
    }
}
