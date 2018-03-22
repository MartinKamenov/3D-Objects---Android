package com.kamenov.martin.a3dobjects.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.DeepPoint;
import com.kamenov.martin.a3dobjects.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 18.3.2018 г..
 */

public class Piramid extends Object3D {
    public Piramid(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, float aLength, float bLength, float h) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        DeepPoint a = new DeepPoint(0-aLength/2, 0-bLength/2, 0);
        DeepPoint b = new DeepPoint(0-aLength/2, 0+bLength/2, 0);
        DeepPoint c = new DeepPoint(0+aLength/2, 0+bLength/2, 0);
        DeepPoint d = new DeepPoint(0+aLength/2, 0-bLength/2, 0);
        DeepPoint e = new DeepPoint(0, 0, 0 + h);
        points = new DeepPoint[] {a, b, c, d, e};
        edges = new ArrayList<>();
        walls = new ArrayList<>();

        edges.add(new DeepPoint[] {a, b});
        edges.add(new DeepPoint[] {b, c});
        edges.add(new DeepPoint[] {c, d});
        edges.add(new DeepPoint[] {d, a});
        edges.add(new DeepPoint[] {a, e});
        edges.add(new DeepPoint[] {b, e});
        edges.add(new DeepPoint[] {c, e});
        edges.add(new DeepPoint[] {d, e});

        walls.add(new DeepPoint[] {a, b, c, d});
        walls.add(new DeepPoint[] {a, b, e});
        walls.add(new DeepPoint[] {b, c, e});
        walls.add(new DeepPoint[] {c, d, e});
        walls.add(new DeepPoint[] {d, a, e});
    }

    @Override
    public void update() {

    }
}
