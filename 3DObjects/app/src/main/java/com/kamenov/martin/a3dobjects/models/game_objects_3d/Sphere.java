package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 21.10.2018 г..
 */

public class Sphere extends Object3D {
    public float radius;
    public Sphere(float x, float y, float z, Paint edgePaint, Paint wallPaint,
                  float rotation, float radius) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.radius = radius;
        DeepPoint a = new DeepPoint(0, 0, 0);
        points = new DeepPoint[] {a};
        parts = new ArrayList<>();
        parts.add(new DeepPoint[]{a});
        /*parts.add(new DeepPoint[] {
            new DeepPoint(x, y, z + radius)
        });*/
    }
}
