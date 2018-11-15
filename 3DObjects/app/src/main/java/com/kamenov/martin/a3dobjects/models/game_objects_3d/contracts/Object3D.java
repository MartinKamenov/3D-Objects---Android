package com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.contracts.GameObject;
import com.kamenov.martin.a3dobjects.contracts.Rotatable;
import com.kamenov.martin.a3dobjects.models.DrawingPart;

import java.util.ArrayList;

/**
 * Created by Martin on 18.3.2018 г..
 */

public abstract class Object3D implements GameObject, Rotatable {
    public DeepPoint[] points;
    public ArrayList<DeepPoint[]> parts;
    public ArrayList<DrawingPart> drawingParts;
    public float x;
    public float y;
    public float z;
    public Paint wallPaint;
    public Paint edgePaint;
    public float rotation;
    private boolean rotateX;
    private boolean rotateY;
    private boolean rotateZ;

    public Object3D(float x, float y, float z, Paint edgePaint, Paint wallPaint,
                    float rotation) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.edgePaint = edgePaint;
        this.wallPaint = wallPaint;
        this.rotation = rotation;
        rotateX = true;
        rotateY = true;
        rotateZ = true;
    }

    public void rotateZ3D(float thetaAngles)
    {
        if(rotateZ) {
            thetaAngles *= rotation;
            float sin_t = (float) Math.sin(Math.toRadians(thetaAngles));
            float cos_t = (float) Math.cos(Math.toRadians(thetaAngles));

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float x = point.getX();
                float y = point.getY();
                point.setX(x * cos_t - y * sin_t);
                point.setY(y * cos_t + x * sin_t);
            }
        }
    }

    public void rotateX3D(float thetaAngles)
    {
        if(rotateX) {
            thetaAngles *= rotation;
            float sin_t = (float) Math.sin(Math.toRadians(thetaAngles));
            float cos_t = (float) Math.cos(Math.toRadians(thetaAngles));

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float y = point.getY();
                float z = point.getZ();
                point.setY(y * cos_t - z * sin_t);
                point.setZ(z * cos_t + y * sin_t);
            }
        }
    }

    public void rotateY3D(float thetaAngles)
    {
        if(rotateY) {
            thetaAngles *= rotation;
            float sin_t = (float) Math.sin(Math.toRadians(thetaAngles));
            float cos_t = (float) Math.cos(Math.toRadians(thetaAngles));

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float x = point.getX();
                float z = point.getZ();
                point.setX(x * cos_t - z * sin_t);
                point.setZ(z * cos_t + x * sin_t);
            }
        }
    }

    public void setRotateX(boolean rotateX) {
        this.rotateX = rotateX;
    }

    public void setRotateY(boolean rotateY) {
        this.rotateY = rotateY;
    }

    public void setRotateZ(boolean rotateZ) {
        this.rotateZ = rotateZ;
    }

    public void move(float pixels, MovingDirection movingDirection) {
        switch (movingDirection) {
            case Up:
                this.y -= pixels;
                break;
            case Down:
                this.y += pixels;
                break;
            case Left:
                this.x -= pixels;
                break;
            case Right:
                this.x += pixels;
                break;
            case Further:
                this.z += pixels;
                break;
            case Closer:
                this.z -= pixels;
                break;
        }
    }

    public void move(float deltaX, float deltaY, float deltaZ) {
        this.x += deltaX;
        this.y += deltaY;
        this.z += deltaZ;
    }

    @Override
    public void update() {
        // Write moving logic here
    }

    public void setDrawingParts() {
        drawingParts = new ArrayList<>();
        for(int i = 0; i < parts.size(); i++) {
            DrawingPart drawingPart;
            if (parts.get(i).length <= 2) {
                drawingPart = new DrawingPart(
                        x,
                        y,
                        z,
                        parts.get(i),
                        edgePaint,
                        getClass());
                drawingParts.add(drawingPart);
            } else {
                drawingPart = new DrawingPart(
                        x,
                        y,
                        z,
                        parts.get(i),
                        wallPaint,
                        getClass());
                drawingParts.add(drawingPart);
            }
        }
    }
}
