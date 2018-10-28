package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 18.3.2018 г..
 */

public class Plane extends Object3D {
    private final float aLength;
    private final float bLength;

    public Plane(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, float aLength, float bLength) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.aLength = aLength;
        this.bLength = bLength;
        DeepPoint a = new DeepPoint(0-aLength/2, 0-bLength/2, 0);
        DeepPoint b = new DeepPoint(0-aLength/2, 0+bLength/2, 0);
        DeepPoint c = new DeepPoint(0+aLength/2, 0+bLength/2, 0);
        DeepPoint d = new DeepPoint(0+aLength/2, 0-bLength/2, 0);
        points = new DeepPoint[] {a, b, c, d};
        parts = new ArrayList<>();
        parts.add(new DeepPoint[] {a, b});
        parts.add(new DeepPoint[] {b, c});
        parts.add(new DeepPoint[] {c, d});
        parts.add(new DeepPoint[] {d, a});
        parts.add(new DeepPoint[] {a, b, c, d});

        setDrawingParts();
    }
}
