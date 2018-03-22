package com.kamenov.martin.a3dobjects.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.DeepPoint;
import com.kamenov.martin.a3dobjects.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 12.3.2018 г..
 */

public class Parallelepiped extends Object3D {
    private final float aLength;
    private final float bLength;
    private final float cLength;

    public Parallelepiped(float x, float y, float z, float aLength, float bLength, float cLength, Paint edgePaint, Paint wallPaint, float rotation)
    {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.rotation = rotation;
        this.aLength = aLength;
        this.bLength = bLength;
        this.cLength = cLength;
        DeepPoint a = new DeepPoint(0-aLength/2, 0-bLength/2, 0-cLength/2);
        DeepPoint b = new DeepPoint(0-aLength/2, 0+bLength/2, 0-cLength/2);
        DeepPoint c = new DeepPoint(0+aLength/2, 0+bLength/2, 0-cLength/2);
        DeepPoint d = new DeepPoint(0+aLength/2, 0-bLength/2, 0-cLength/2);
        DeepPoint a1 = new DeepPoint(0-aLength/2, 0-bLength/2, 0+cLength/2);
        DeepPoint b1 = new DeepPoint(0-aLength/2, 0+bLength/2, 0+cLength/2);
        DeepPoint c1 = new DeepPoint(0+aLength/2, 0+bLength/2, 0+cLength/2);
        DeepPoint d1 = new DeepPoint(0+aLength/2, 0-bLength/2, 0+cLength/2);
        points = new DeepPoint[] {a, b, c, d, a1, b1, c1, d1};
        edges = new ArrayList<>();
        walls = new ArrayList<>();
        edges.add(new DeepPoint[] {a, b});
        edges.add(new DeepPoint[] {b, c});
        edges.add(new DeepPoint[] {c, d});
        edges.add(new DeepPoint[] {d, a});
        edges.add(new DeepPoint[] {a1, b1});
        edges.add(new DeepPoint[] {b1, c1});
        edges.add(new DeepPoint[] {c1, d1});
        edges.add(new DeepPoint[] {d1, a1});
        edges.add(new DeepPoint[] {a, a1});
        edges.add(new DeepPoint[] {b, b1});
        edges.add(new DeepPoint[] {c, c1});
        edges.add(new DeepPoint[] {d, d1});

        walls.add(new DeepPoint[] {a, b, c, d});
        walls.add(new DeepPoint[] {a1, b1, c1, d1});
        walls.add(new DeepPoint[] {a, b, b1, a1});
        walls.add(new DeepPoint[] {b, c, c1, b1});
        walls.add(new DeepPoint[] {c, d, d1, c1});
        walls.add(new DeepPoint[] {d, a, a1, d});
    }

    public ArrayList<DeepPoint[]> getEdges() {
        return this.edges;
    }

    @Override
    public void update() {
        rotateX3D(0.005f);
    }
}
