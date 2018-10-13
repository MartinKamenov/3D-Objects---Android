package com.kamenov.martin.a3dobjects.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.kamenov.martin.a3dobjects.game.GamePanel;

/**
 * Created by Martin on 6.3.2018 г..
 */

public class GameThread extends Thread {
    public static final int MAX_FPS = 30;
    private GamePanel gamePanel;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    private Canvas canvas;
    private double averageFPS;
    private int counter = 0;

    public GameThread(SurfaceHolder surfaceHolder, GamePanel panel) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = panel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if(canvas!=null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                if(waitTime > 0) {
                    this.sleep(50);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime = System.nanoTime() - startTime;
            frameCount++;

            if(frameCount==MAX_FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }
}
