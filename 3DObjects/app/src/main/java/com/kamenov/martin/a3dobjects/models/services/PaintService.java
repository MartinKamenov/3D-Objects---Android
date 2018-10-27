package com.kamenov.martin.a3dobjects.models.services;

import android.graphics.Color;
import android.graphics.Paint;
import android.provider.SyncStateContract;

import com.kamenov.martin.a3dobjects.models.Constants;

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
        for(int i = 0; i < Constants.COLORS.length; i++) {
            if(Constants.COLORS[i].equals(colorName)) {
                return Color.parseColor(Constants.COLORREPRESENTATIONS[i]);
            }
        }

        if((colorName.length() == 7 || colorName.length() == 9) && colorName.charAt(0) == '#') {
            return Color.parseColor(colorName);
        }

        return Color.parseColor("#00000000");
    }
}
