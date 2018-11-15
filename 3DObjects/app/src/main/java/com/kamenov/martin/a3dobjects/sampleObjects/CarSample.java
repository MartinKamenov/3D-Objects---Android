package com.kamenov.martin.a3dobjects.sampleObjects;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.ComplexObject;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Parallelepiped;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.PartsObject;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Plane;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;
import com.kamenov.martin.a3dobjects.models.services.PaintService;

import java.util.ArrayList;

/**
 * Created by Martin on 15.11.2018 г..
 */

public class CarSample {
    public static ArrayList<Object3D> getCarSample() {
        ArrayList<Object3D> objects = new ArrayList<>();
        ArrayList<Object3D> parts = new ArrayList<>();
        Parallelepiped body = new Parallelepiped(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, -25, 100, 300, 50,
                edgePaint,
                wallPaint,
                1);
        Plane roof = new Plane(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 + 25, 50,
                edgePaint,
                wallPaint,
                1, 100, 150
        );
        Plane frontGlass = new Plane(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 - 75, 25,
                edgePaint,
                wallPaint,
                1, 100, 75
        );
        frontGlass.rotateX3D(45);
        Plane backGlass = new Plane(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 + 125, 25,
                edgePaint,
                wallPaint,
                1, 100, 75
        );
        DeepPoint[] leftGlassPoints = new DeepPoint[]{
                new DeepPoint(Constants.SCREEN_WIDTH / 2 - 50, Constants.SCREEN_HEIGHT / 2 + 100, 50),
                new DeepPoint(Constants.SCREEN_WIDTH / 2 - 50, Constants.SCREEN_HEIGHT / 2 - 50, 50),
                new DeepPoint(Constants.SCREEN_WIDTH / 2 - 50, Constants.SCREEN_HEIGHT / 2 - 100, 0),
                new DeepPoint(Constants.SCREEN_WIDTH / 2 - 50, Constants.SCREEN_HEIGHT / 2 + 150, 0),
        };
        ArrayList<DeepPoint[]> leftGlassParts = new ArrayList<>();
        leftGlassParts.add(leftGlassPoints);
        PartsObject leftGlass = new PartsObject(0, 0, 0,
                edgePaint, wallPaint, 1, leftGlassPoints, leftGlassParts);
        DeepPoint[] rightGlassPoints = new DeepPoint[]{
                new DeepPoint(Constants.SCREEN_WIDTH / 2 + 50, Constants.SCREEN_HEIGHT / 2 + 100, 50),
                new DeepPoint(Constants.SCREEN_WIDTH / 2 + 50, Constants.SCREEN_HEIGHT / 2 - 50, 50),
                new DeepPoint(Constants.SCREEN_WIDTH / 2 + 50, Constants.SCREEN_HEIGHT / 2 - 100, 0),
                new DeepPoint(Constants.SCREEN_WIDTH / 2 + 50, Constants.SCREEN_HEIGHT / 2 + 150, 0),
        };
        ArrayList<DeepPoint[]> rightGlassParts = new ArrayList<>();
        leftGlassParts.add(rightGlassPoints);
        PartsObject rightGlass = new PartsObject(0, 0, 0,
                edgePaint, wallPaint, 1, rightGlassPoints, rightGlassParts);
        backGlass.rotateX3D(-45);


        parts.add(body);
        parts.add(roof);
        parts.add(frontGlass);
        parts.add(backGlass);
        parts.add(leftGlass);
        parts.add(rightGlass);
        parts.add(createTyre(
                Constants.SCREEN_WIDTH / 2 - 55,
                Constants.SCREEN_HEIGHT / 2 - 100));
        parts.add(createTyre(
                Constants.SCREEN_WIDTH / 2 - 55,
                Constants.SCREEN_HEIGHT / 2 + 100));
        parts.add(createTyre(
                Constants.SCREEN_WIDTH / 2 + 55,
                Constants.SCREEN_HEIGHT / 2 - 100));
        parts.add(createTyre(
                Constants.SCREEN_WIDTH / 2 + 55,
                Constants.SCREEN_HEIGHT / 2 + 100));

        ComplexObject car = new ComplexObject(
                Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2,
                0,
                edgePaint,
                PaintService.createWallPaint("blue"),
                1,
                parts);
        objects.add(car);
        return objects;
    }

    private static Parallelepiped createTyre(float x, float y) {
        Parallelepiped tyre = new Parallelepiped(
                x,
                y,
                -50,
                20,
                30,
                30,
                wallPaint,
                tyreWallPaint,
                1

        );
        return tyre;
    }

    private static Paint edgePaint = PaintService.createEdgePaint("red");
    private static Paint wallPaint = PaintService.createWallPaint("white");
    private static Paint tyreEdgePaint = PaintService.createEdgePaint("black");
    private static Paint tyreWallPaint = PaintService.createWallPaint("black");
}
