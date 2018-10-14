package com.kamenov.martin.a3dobjects.models.factory;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.contracts.Rotatable;
import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Parallelepiped;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Piramid;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Plane;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 12.10.2018 г..
 */

public class FigureFactory {
    private static FigureFactory figureFactory;
    private ArrayList<Rotatable> figures;

    private FigureFactory() {
        figures = new ArrayList<>();
    }

    public static FigureFactory getInstance() {
        if(figureFactory == null) {
            figureFactory = new FigureFactory();
        }

        return figureFactory;
    }

    public String createCube(float x, float y, float z, float edgeLength, Paint edgePaint, Paint wallPaint, float rotation) {
        try {
            x += Constants.SCREEN_WIDTH / 2;
            y += Constants.SCREEN_HEIGHT / 2;
            Cube cube = new Cube(x, y, z, edgeLength, edgePaint, wallPaint, rotation);
            figures.add(cube);
            return "Cube was created";
        } catch (Exception ex) {
            return "Params: x, y, z, edgeLength, colorEdge, colorWall, rotation";
        }
    }

    public String createParallelepiped(float x, float y, float z,
                                            float aLength, float bLength, float cLength,
                                            Paint edgePaint, Paint wallPaint, float rotation) {
        try {
            x += Constants.SCREEN_WIDTH / 2;
            y += Constants.SCREEN_HEIGHT / 2;
            Parallelepiped para = new Parallelepiped(x, y, z, aLength, bLength, cLength, edgePaint, wallPaint, rotation);
            figures.add(para);
            return "Parallelepiped was created";
        } catch (Exception ex) {
            return "Params: x, y, z, aLength, bLength, cLength, colorEdge, colorWall, rotation";
        }
    }

    public String createPyramid(float x, float y, float z, Paint edgePaint,
                                 Paint wallPaint, float rotation, float aLength, float bLength, float h) {
        try {
            x += Constants.SCREEN_WIDTH / 2;
            y += Constants.SCREEN_HEIGHT / 2;
            Piramid pyramid = new Piramid(x, y, z, edgePaint, wallPaint, rotation, aLength, bLength, h);
            figures.add(pyramid);
            return "Pyramid was created";
        } catch (Exception ex) {
            return "Params: x, y, z, edgeColor, wallColor, rotation, aLength, bLength, h";
        }
    }

    public String createPlane(float x, float y, float z, Paint edgePaint, Paint wallPaint,
                              float rotation, float aLength, float bLength) {
        try {
            x += Constants.SCREEN_WIDTH / 2;
            y += Constants.SCREEN_HEIGHT / 2;
            Plane plane = new Plane(x, y, z, edgePaint, wallPaint, rotation, aLength, bLength);
            figures.add(plane);
            return "Plane was created";
        } catch (Exception ex) {
            return "Params: x, y, z, edgeColor, wallColor, rotation, aLength, bLength";
        }
    }

    public ArrayList<Rotatable> getFigures() {
        return this.figures;
    }
}
