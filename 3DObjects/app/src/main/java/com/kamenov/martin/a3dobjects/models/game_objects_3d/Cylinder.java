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
    private float rotatedX;
    private float rotatedY;
    private float rotatedZ;

    public Cylinder(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation,
                    float height, float radius) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.rotatedX = 0;
        this.rotatedY = 0;
        this.rotatedZ = 0;
        this.height = height;
        this.radius = radius;
        float halfHeight = height / 2;
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

    @Override
    public void rotateX3D(float thetaAngles) {
        super.rotateX3D(thetaAngles);
        rotatedX += thetaAngles;
    }

    @Override
    public void rotateY3D(float thetaAngles) {
        super.rotateY3D(thetaAngles);
        rotatedY += thetaAngles;
    }

    @Override
    public void rotateZ3D(float thetaAngles) {
        super.rotateZ3D(thetaAngles);
        rotatedZ += thetaAngles;
    }
}
