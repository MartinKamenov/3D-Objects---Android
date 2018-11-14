package com.kamenov.martin.a3dobjects.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kamenov.martin.a3dobjects.contracts.GamePanelState;
import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.factories.FigureFactory;
import com.kamenov.martin.a3dobjects.models.game_objects.Background;
import com.kamenov.martin.a3dobjects.contracts.GameObject;
import com.kamenov.martin.a3dobjects.contracts.Rotatable;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;
import com.kamenov.martin.a3dobjects.models.services.DrawingService;

import java.util.ArrayList;

/**
 * Created by Martin on 6.3.2018 г..
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback, GameObject {
    private ArrayList<Object3D> figures;
    private DrawingService drawingService;
    private GameThread thread;
    private GameObject background;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private GamePanelState gamePanelState;

    public GamePanel(Context context, DrawingService drawingService) {
        super(context);
        gamePanelState = GamePanelState.Rotating;
        x1 = -1;
        x2 = -1;
        this.drawingService = drawingService;
        figures = FigureFactory.getInstance().getFigures();
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
                    Object3D figure = figures.get(i);
                    moveObject(deltaX, deltaY, figure, gamePanelState);
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
                    Object3D figure = figures.get(i);
                    moveObject(deltaX, deltaY, figure, gamePanelState);
                }
                draw();
                break;
        }
        return true;
    }

    private void moveObject(float deltaX, float deltaY, Object3D figure, GamePanelState gamePanelState) {
        if(gamePanelState == GamePanelState.Rotating) {
            figure.rotateX3D(deltaY * Constants.ROTATINGCOEF);
            figure.rotateY3D(deltaX * Constants.ROTATINGCOEF);
        } else {
            figure.move(deltaX, deltaY, 0);
        }
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
        //background.draw(canvas);
        drawingService.drawFigures(canvas, figures);
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
            if(canvas != null) {
                try {
                    getHolder().unlockCanvasAndPost(canvas);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setGamePanelState(GamePanelState gamePanelState) {
        this.gamePanelState = gamePanelState;
    }
}
