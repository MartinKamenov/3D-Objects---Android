package com.kamenov.martin.a3dobjects.models;

import android.graphics.Paint;

/**
 * Created by Martin on 20.10.2018 г..
 */

public class DrawingPart {
    public DrawingPart(DeepPoint[] part, Paint paint) {
        this.part = part;
        this.paint = paint;
    }
    public DeepPoint[] part;
    public Paint paint;
}
