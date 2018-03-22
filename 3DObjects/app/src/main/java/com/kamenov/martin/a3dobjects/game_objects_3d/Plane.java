package com.kamenov.martin.a3dobjects.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.DeepPoint;
import com.kamenov.martin.a3dobjects.game_objects_3d.contracts.Object3D;

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
        edges = new ArrayList<>();
        walls = new ArrayList<>();
        edges.add(new DeepPoint[] {a, b});
        edges.add(new DeepPoint[] {b, c});
        edges.add(new DeepPoint[] {c, d});
        edges.add(new DeepPoint[] {d, a});
        walls.add(new DeepPoint[] {a, b, c, d});
    }

    @Override
    public void update() {

    }
}
