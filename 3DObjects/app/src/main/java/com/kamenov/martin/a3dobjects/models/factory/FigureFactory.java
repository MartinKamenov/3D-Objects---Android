package com.kamenov.martin.a3dobjects.models.factory;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.contracts.Rotatable;
import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.ComplexObject;
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

    public Cube createCube(float x, float y, float z, float edgeLength, Paint edgePaint, Paint wallPaint, float rotation) {
        x += Constants.SCREEN_WIDTH / 2;
        y += Constants.SCREEN_HEIGHT / 2;
        Cube cube = new Cube(x, y, z, edgeLength, edgePaint, wallPaint, rotation);
        figures.add(cube);
        return cube;
    }

    public Parallelepiped createParallelepiped(float x, float y, float z,
                                            float aLength, float bLength, float cLength,
                                            Paint edgePaint, Paint wallPaint, float rotation) {
        x += Constants.SCREEN_WIDTH / 2;
        y += Constants.SCREEN_HEIGHT / 2;
        Parallelepiped para = new Parallelepiped(x, y, z, aLength, bLength, cLength, edgePaint, wallPaint, rotation);
        figures.add(para);
        return para;
    }

    public Piramid createPyramid(float x, float y, float z, Paint edgePaint,
                                 Paint wallPaint, float rotation, float aLength, float bLength, float h) {
        x += Constants.SCREEN_WIDTH / 2;
        y += Constants.SCREEN_HEIGHT / 2;
        Piramid pyramid = new Piramid(x, y, z, edgePaint, wallPaint, rotation, aLength, bLength, h);
        figures.add(pyramid);
        return pyramid;
    }

    public Plane createPlane(float x, float y, float z, Paint edgePaint, Paint wallPaint,
                              float rotation, float aLength, float bLength) {
        x += Constants.SCREEN_WIDTH / 2;
        y += Constants.SCREEN_HEIGHT / 2;
        Plane plane = new Plane(x, y, z, edgePaint, wallPaint, rotation, aLength, bLength);
        figures.add(plane);
        return plane;
    }

    public ComplexObject createComplexObject(float x, float y, float z, Paint edgePaint,
                                             Paint wallPaint, float rotation, ArrayList<Object3D> figures) {
        x += Constants.SCREEN_WIDTH / 2;
        y += Constants.SCREEN_HEIGHT / 2;
        ComplexObject complexObject = new ComplexObject(x, y, z, edgePaint, wallPaint, rotation, figures);
        figures.add(complexObject);
        return complexObject;
    }

    public ArrayList<Rotatable> getFigures() {
        return this.figures;
    }

    public void clearFigures() {
        this.figures = new ArrayList<>();
    }

    private void removeFigure(int i) {
        this.figures.remove(i);
    }
}
