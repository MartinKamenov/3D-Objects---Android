package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
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
            float differenceX = objects.get(i).x - x;
            float differenceY = objects.get(i).y - y;
            float differenceZ = objects.get(i).z - z;
            for(int j = 0; j < objects.get(i).points.length; j++)
            {
                DeepPoint point = objects.get(i).points[j];
                point.setX(point.getX()+differenceX);
                point.setY(point.getY()+differenceY);
                point.setZ(point.getZ()+differenceZ);
                points[index++] = point;
            }
            for(int j = 0; j < objects.get(i).parts.size(); j++)
            {
                parts.add(objects.get(i).parts.get(j));
            }
        }
    }

    @Override
    public void update() {
    }
}
