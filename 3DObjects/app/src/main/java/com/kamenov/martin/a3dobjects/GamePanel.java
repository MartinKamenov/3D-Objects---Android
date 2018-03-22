package com.kamenov.martin.a3dobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kamenov.martin.a3dobjects.game_objects_3d.contracts.Object3D;
import com.kamenov.martin.a3dobjects.game_objects.Background;
import com.kamenov.martin.a3dobjects.game_objects_3d.ComplexObject;
import com.kamenov.martin.a3dobjects.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.contracts.GameObject;
import com.kamenov.martin.a3dobjects.game_objects_3d.Parallelepiped;
import com.kamenov.martin.a3dobjects.contracts.Rotatable;
import com.kamenov.martin.a3dobjects.game_objects_3d.Piramid;

import java.util.ArrayList;

/**
 * Created by Martin on 6.3.2018 г..
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, GameObject {
    private ArrayList<Rotatable> figures;
    private GameThread thread;
    private GameObject background;
    private float x1;
    private float y1;
    private float x2;
    private float y2;

    public GamePanel(Context context) {
        super(context);
        x1 = -1;
        x2 = -1;
        figures = new ArrayList<>();

        Paint paintBlue = new Paint();
        paintBlue.setColor(Color.parseColor("#0291DB"));
        paintBlue.setStyle(Paint.Style.FILL_AND_STROKE);
        paintBlue.setStrokeWidth(10);
        Paint paintRed = new Paint();
        paintRed.setColor(Color.parseColor("#F52C6E"));
        paintRed.setStyle(Paint.Style.FILL_AND_STROKE);
        paintRed.setStrokeWidth(15);
        Paint paintYellow = new Paint();
        paintYellow.setColor(Color.parseColor("#f3ff19"));
        paintYellow.setStyle(Paint.Style.FILL);
        Paint paintGreen = new Paint();
        paintGreen.setColor(Color.WHITE);
        paintGreen.setStyle(Paint.Style.FILL);
        //figures.add(new Parallelepiped(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, 0, 100, 200, 300, paintRed, paintGreen, 2));
        //figures.add(new Cube(250, 250, 0, 200, paintBlue, paintYellow, 6));
        //figures.add(new Plane(500, 500, 0, paintRed, paintYellow, 6, 100, 150));
        //figures.add(new Piramid(250, 250, 0, paintRed, paintYellow, 6, 200, 200, 150));
        Object3D cube = new Cube(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, 0, 200, paintBlue, paintYellow, 1);
        Object3D para = new Parallelepiped(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2+125, 0, 50, 100, 0, paintBlue, paintYellow, 1);
        Object3D piramid = new Piramid(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, 100, paintBlue, paintYellow, 1,200, 200,100);
        ArrayList<Object3D> complexList = new ArrayList<>();
        complexList.add(cube);
        complexList.add(piramid);
        complexList.add(para);
        Object3D house = new ComplexObject(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT/2,0,paintRed,paintYellow,1,complexList);
        figures.add(house);
        background = new Background(Color.BLACK);

        getHolder().addCallback(this);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getX();
                y2 = event.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;

                for(int i = 0; i < figures.size(); i++)
                {
                    Rotatable figure = figures.get(i);
                    figure.rotateX3D(deltaY/100);
                    figure.rotateY3D(deltaX/100);
                }
                draw();
                x1 = x2;
                y1 = y2;
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                deltaX = x2 - x1;
                deltaY = y2 - y1;
                for(int i = 0; i < figures.size(); i++)
                {
                    Rotatable figure = figures.get(i);
                    figure.rotateX3D(deltaY/100);
                    figure.rotateY3D(deltaX/100);
                }
                draw();
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.thread = new GameThread(getHolder(), GamePanel.this);
        thread.setRunning(true);

        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void update() {
        for(int i = 0; i < figures.size(); i++)
        {
            Rotatable figure = figures.get(i);
            figure.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        background.draw(canvas);
        for(int i = 0; i < figures.size(); i++)
        {
            Rotatable figure = figures.get(i);
            figure.draw(canvas);
        }
    }

    private void draw() {
        Canvas canvas = null;
        try {
            canvas = this.getHolder().lockCanvas();
            synchronized (getHolder()) {
                this.draw(canvas);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(canvas!=null) {
                try {
                    getHolder().unlockCanvasAndPost(canvas);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
