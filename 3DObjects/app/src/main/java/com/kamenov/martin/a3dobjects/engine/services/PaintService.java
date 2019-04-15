package com.kamenov.martin.a3dobjects.engine.services;

import android.graphics.Color;
import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.engine.constants.EngineConstants;

/**
 * Created by Martin on 27.10.2018 г..
 */

public class PaintService {


    public static Paint createEdgePaint(String color) {
        Paint paint = new Paint();
        paint.setColor(parseColor(color));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    public static Paint createWallPaint(String color) {
        Paint paint = new Paint();
        paint.setColor(parseColor(color));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }


    private static int parseColor(String colorName) {
        for(int i = 0; i < EngineConstants.COLORS.length; i++) {
            if(EngineConstants.COLORS[i].equals(colorName)) {
                return Color.parseColor(EngineConstants.COLOR_REPRESENTATIONS[i]);
            }
        }

        if((colorName.length() == 7 || colorName.length() == 9) && colorName.charAt(0) == '#') {
            return Color.parseColor(colorName);
        }

        return Color.parseColor("#00000000");
    }
}
