package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.DrawingPart;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 18.3.2018 г..
 */

public class ComplexObject extends Object3D {
    private final ArrayList<Object3D> objects;

    public ComplexObject(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, ArrayList<Object3D> objects) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.objects = objects;
        int pointsLength = 0;
        for(int i = 0; i < objects.size(); i++)
        {
            pointsLength += objects.get(i).points.length;
            objects.get(i).setRotateX(false);
            objects.get(i).setRotateY(false);
            objects.get(i).setRotateZ(false);
        }
        points = new DeepPoint[pointsLength];
        parts = new ArrayList<>();
        int index = 0;
        for(int i = 0; i < objects.size(); i++)
        {
            Object3D object = objects.get(i);
            float differenceX = object.x - x;
            float differenceY = object.y - y;
            float differenceZ = object.z - z;
            for(int j = 0; j < objects.get(i).points.length; j++)
            {
                DeepPoint point = object.points[j];
                point.setX(point.getX() + differenceX);
                point.setY(point.getY() + differenceY);
                point.setZ(point.getZ() + differenceZ);
                points[index++] = point;
            }
            for(int j = 0; j < object.parts.size(); j++)
            {
                DeepPoint[] part = object.parts.get(j);
                parts.add(part);
            }
        }

        setDrawingParts();
    }

    @Override
    public void setDrawingParts() {
        drawingParts = new ArrayList<>();
        int index = 0;
        for(int i = 0; i < objects.size(); i++) {
            Object3D object = objects.get(i);
            if (object.getClass() == Sphere.class) {
                drawingParts.add(new DrawingPart(
                        x,
                        y,
                        z,
                        ((Sphere)object).radius,
                        parts.get(index),
                        object.edgePaint,
                        object.getClass()));
                drawingParts.add(new DrawingPart(
                        x,
                        y,
                        z,
                        ((Sphere)object).radius,
                        parts.get(index++),
                        object.wallPaint,
                        object.getClass()));
            } else {
                for (int j = 0; j < object.parts.size(); j++) {
                    DrawingPart drawingPart;
                    if (index < parts.size() && parts.get(index).length <= 2) {
                        drawingPart = new DrawingPart(
                                x,
                                y,
                                z,
                                parts.get(index++),
                                object.edgePaint,
                                object.getClass());
                    } else {
                        drawingPart = new DrawingPart(
                                x,
                                y,
                                z,
                                parts.get(index++),
                                object.wallPaint,
                                object.getClass());
                    }
                    drawingParts.add(drawingPart);
                }
            }
        }
    }
}
