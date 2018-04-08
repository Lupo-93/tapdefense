package com.example.lupos.a2dtapgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by lupos on 06.03.2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite princess;
    private EnemyManager enemyManager;

    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        //Bitmap enemySprite = BitmapFactory.decodeResource(getResources(),R.drawable.enemy);
        //enemySprite = Bitmap.createScaledBitmap(enemySprite, 100, 100, false);
        Bitmap princessSprite = BitmapFactory.decodeResource(getResources(),R.drawable.princess);
        princessSprite = Bitmap.createScaledBitmap(princessSprite, (int)(princessSprite.getWidth()*0.5), (int)(princessSprite.getHeight()*0.5),false);
        princess = new CharacterSprite(princessSprite, Constants.SCREEN_WIDTH/2 - princessSprite.getWidth()/2, Constants.SCREEN_HEIGHT-250);
        //enemyManager = new EnemyManager(enemySprite);
        //enemyManager.startEnemies(princess.getCenterX(), princess.getCenterY());
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        princess.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null){
            canvas.drawColor(Color.BLACK);
            princess.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        return false;
    }
}
