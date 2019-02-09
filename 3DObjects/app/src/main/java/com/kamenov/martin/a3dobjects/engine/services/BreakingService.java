package com.kamenov.martin.a3dobjects.engine.services;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.engine.models.game_objects.ComplexObject;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.Parallelepiped;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.contracts.Dimension;

import java.util.ArrayList;

/**
 * Created by Martin on 25.12.2018 г..
 */

public class BreakingService {
    public static ComplexObject breakParaToSmaller(float x, float y, float z, float width, float height, float depth,
            Paint edgePaint, Paint wallPaint, int rotation, int count, Dimension dimension) {
        ArrayList parts = new ArrayList();

        switch (dimension) {
            case X:
                float startWidth = x - (width / 2) + ((width / count) / 2);
                for(int i = 0; i < count; i++) {
                    float xPosition = startWidth + i * (width / count);
                    Parallelepiped part = new Parallelepiped(xPosition,
                            y,
                            z,
                            width / count, height, depth, edgePaint, wallPaint, 1);
                    parts.add(part);
                }
                break;
            case Y:
                float startHeight = y - (height / 2) + ((height / count) / 2);
                for(int i = 0; i < count; i++) {
                    float yPosition = startHeight + i * (height / count);
                            Parallelepiped part = new Parallelepiped(x,
                            yPosition,
                            z,
                            width, height / count, depth, edgePaint, wallPaint, 1);
                    parts.add(part);
                }
                break;
            case Z:
                float startDepth = (z - (depth / 2)) + ((depth / count) / 2);
                for(int i = 0; i < count; i++) {
                    float zPosition = startDepth + i * (depth / count);
                    Parallelepiped part = new Parallelepiped(x,
                            y,
                            zPosition,
                            width, height, depth / count, edgePaint, wallPaint, 1);
                    parts.add(part);
                }
                break;
        }

        ComplexObject resultObject = new ComplexObject(x, y, z, edgePaint, wallPaint, rotation, parts);
        return resultObject;
    }
}
