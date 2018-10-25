package com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.contracts.GameObject;
import com.kamenov.martin.a3dobjects.contracts.Rotatable;
import com.kamenov.martin.a3dobjects.models.DrawingPart;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Sphere;
import com.kamenov.martin.a3dobjects.models.services.SortingService;

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

    public void rotateZ3D(float theta)
    {
        if(rotateZ) {
            theta *= rotation;
            float sin_t = (float) Math.sin(theta);
            float cos_t = (float) Math.cos(theta);

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float x = point.getX();
                float y = point.getY();
                point.setX(x * cos_t - y * sin_t);
                point.setY(y * cos_t + x * sin_t);
            }
        }
    }

    public void rotateX3D(float theta)
    {
        if(rotateX) {
            theta *= rotation;
            float sin_t = (float) Math.sin(theta);
            float cos_t = (float) Math.cos(theta);

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float y = point.getY();
                float z = point.getZ();
                point.setY(y * cos_t - z * sin_t);
                point.setZ(z * cos_t + y * sin_t);
            }
        }
    }

    public void rotateY3D(float theta)
    {
        if(rotateY) {
            theta *= rotation;
            float sin_t = (float) Math.sin(theta);
            float cos_t = (float) Math.cos(theta);

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

    @Override
    public void update() {
        // Write moving logic here
    }

    protected void setDrawingParts() {
        for(int i = 0; i < parts.size(); i++) {
            if(getClass() == Sphere.class) {
                drawingParts.add(new DrawingPart(
                        x,
                        y,
                        z,
                        ((Sphere)this).radius,
                        parts.get(0),
                        edgePaint,
                        getClass()));
                drawingParts.add(new DrawingPart(
                        x,
                        y,
                        z,
                        ((Sphere)this).radius,
                        parts.get(0),
                        wallPaint,
                        getClass()));
            } else {
                for (int j = 0; j < parts.size(); j++) {
                    DrawingPart drawingPart;
                    if (parts.get(j).length <= 2) {
                        drawingPart = new DrawingPart(
                                x,
                                y,
                                z,
                                parts.get(j),
                                edgePaint,
                                getClass());
                        drawingParts.add(drawingPart);
                    } else {
                        drawingPart = new DrawingPart(
                                x,
                                y,
                                z,
                                parts.get(j),
                                wallPaint,
                                getClass());
                        drawingParts.add(drawingPart);
                    }
                }
            }
        }
    }
}
