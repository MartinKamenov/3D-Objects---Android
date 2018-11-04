package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

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
    }
}
