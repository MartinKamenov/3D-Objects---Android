package com.kamenov.martin.a3dobjects.models.game_objects_3d;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 3.11.2018 г..
 */

public class PartsObject extends Object3D {

    public PartsObject(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation,
                       DeepPoint[] points, ArrayList<DeepPoint[]> parts) {
        super(x, y, z, edgePaint, wallPaint, rotation);
        this.points = points;
        this.parts = parts;
        setDrawingParts();
    }
}
