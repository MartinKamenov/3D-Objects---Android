package com.kamenov.martin.a3dobjects.models.game_objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.contracts.GameObject;

/**
 * Created by Martin on 10.3.2018 г..
 */

public class Background implements GameObject {
    private final Paint paint;

    public  Background(int color)
    {
        this.paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas) {
        String[] colors = new String[] {
            "#000000", "#111111", "#222222", "#333333",
                "#444444", "#555555", "#666666", "#777777", "#888888", "#999999", "#aaaaaa", "#bbbbbb", "#cccccc",
                "#dddddd", "#eeeeee", "#ffffff"
        };
        /*String[] colors = new String[] {
            "#ffffff", "#edf9f9", "#e8ffff", "#ccf9f9",
                "#bfffff", "#a8ffff", "#8cffff", "#72ffff", "#60ffff", "#4cffff", "#3ffcfc", "#32ffff", "#28ffff",
                "#19ffff", "#0fffff", "#00ffff"
        };*/
        for(int i = 0; i < colors.length; i++) {
            paint.setColor(Color.parseColor(colors[i]));
            canvas.drawRect(0, i * (Constants.SCREEN_HEIGHT / colors.length),
                    Constants.SCREEN_WIDTH, (i * (Constants.SCREEN_HEIGHT / colors.length)) + (Constants.SCREEN_HEIGHT / colors.length), paint);
        }
    }

    @Override
    public void update() {

    }
}
