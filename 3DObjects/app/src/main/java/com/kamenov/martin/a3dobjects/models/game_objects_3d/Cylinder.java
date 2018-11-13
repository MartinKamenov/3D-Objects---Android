package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.DrawingPart;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 4.11.2018 г..
 */

public class Cylinder extends Object3D {
    public float height;
    public float radius;

    public Cylinder(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation,
                    float height, float radius) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.height = height;
        this.radius = radius;
        float halfHeight = height/2;
        /* DeepPoint a = new DeepPoint(0 - radius, 0, 0 - halfHeight);
        DeepPoint b = new DeepPoint(0, 0 - radius, 0 - halfHeight);
        DeepPoint c = new DeepPoint(0 + radius, 0, 0 - halfHeight);
        DeepPoint d = new DeepPoint(0, 0 + radius, 0 - halfHeight);
        DeepPoint a1 = new DeepPoint(0 - radius, 0, 0 + halfHeight);
        DeepPoint b1 = new DeepPoint(0, 0 - radius, 0 + halfHeight);
        DeepPoint c1 = new DeepPoint(0 + radius, 0, 0 + halfHeight);
        DeepPoint d1 = new DeepPoint(0, 0 + radius, 0 + halfHeight);
        points = new DeepPoint[] {
            a, b ,c, d, a1, b1, c1, d1
        };
        parts = new ArrayList<>();
        // Defines the two ovals of the cylinder
        DeepPoint[] base = new DeepPoint[]{a, b, c, d};
        DeepPoint[] top = new DeepPoint[]{a1, b1, c1, d1};
        parts.add(base);
        parts.add(top);*/
        DeepPoint a = new DeepPoint(0, 0, 0 - halfHeight);
        DeepPoint b = new DeepPoint(0, 0, 0 + halfHeight);
        points = new DeepPoint[] {a, b};

        parts = new ArrayList<>();
        parts.add(new DeepPoint[] {a});
        parts.add(new DeepPoint[] {b});

        setDrawingParts();
    }

    @Override
    public void setDrawingParts() {
        drawingParts = new ArrayList<>();
        for(int i = 0; i < parts.size(); i++) {
            drawingParts.add(new DrawingPart(
                    x,
                    y,
                    z,
                    radius,
                    parts.get(i),
                    edgePaint,
                    getClass()));
            drawingParts.add(new DrawingPart(
                    x,
                    y,
                    z,
                    radius,
                    parts.get(i),
                    wallPaint,
                    getClass()));
        }
    }

}
