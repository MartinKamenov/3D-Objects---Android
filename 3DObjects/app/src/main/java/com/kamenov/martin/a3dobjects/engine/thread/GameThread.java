package com.kamenov.martin.a3dobjects.engine.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.kamenov.martin.a3dobjects.engine.GamePanel;
import com.kamenov.martin.a3dobjects.engine.services.CanvasService;

/**
 * Created by Martin on 6.3.2018 г..
 */

public class GameThread extends Thread {
    public static final int MAX_FPS = 30;
    private final GamePanel gamePanel;
    private boolean running;
    private double averageFPS;

    public GameThread(GamePanel panel) {
        this.gamePanel = panel;
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
            CanvasService.updateAndDraw(gamePanel);

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

    public void setRunning(boolean running) {
        this.running = running;
    }
}
