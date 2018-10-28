package com.kamenov.martin.a3dobjects.models;

import android.graphics.Paint;

/**
 * Created by Martin on 20.10.2018 г..
 */

public class DrawingPart {
    public DeepPoint[] parts;
    public Paint paint;
    public float x;
    public float y;
    public float z;
    public float radius;
    public Class clazz;

    public DrawingPart(float x, float y, float z, DeepPoint[] part, Paint paint, Class clazz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = 0;
        this.parts = part;
        this.paint = paint;
        this.clazz = clazz;
    }

    public DrawingPart(float x, float y, float z, float radius, DeepPoint[] part, Paint paint, Class clazz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
        this.parts = part;
        this.paint = paint;
        this.clazz = clazz;
    }
}
