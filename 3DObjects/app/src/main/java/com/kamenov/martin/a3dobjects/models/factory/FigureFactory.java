package com.kamenov.martin.a3dobjects.models.factory;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Parallelepiped;

/**
 * Created by Martin on 12.10.2018 г..
 */

public class FigureFactory {
    private static FigureFactory figureFactory;

    private FigureFactory() {
    }

    public static FigureFactory getInstance() {
        if(figureFactory == null) {
            figureFactory = new FigureFactory();
        }

        return figureFactory;
    }

    public Cube getCube(float x, float y, float z, float edgeLength, Paint edgePaint, Paint wallPaint, float rotation) {
        return new Cube(x, y, z, edgeLength, edgePaint, wallPaint, rotation);
    }

    public Parallelepiped getParallelepiped(float x, float y, float z,
                                            float aLength, float bLength, float cLength,
                                            Paint edgePaint, Paint wallPaint, float rotation) {
        return new Parallelepiped(x, y, z, aLength, bLength, cLength, edgePaint, wallPaint, rotation);
    }
}
