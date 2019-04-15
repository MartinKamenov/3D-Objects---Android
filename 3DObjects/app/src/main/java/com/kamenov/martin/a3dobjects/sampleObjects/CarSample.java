package com.kamenov.martin.a3dobjects.sampleObjects;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.engine.constants.EngineConstants;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.contracts.DeepPoint;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.ComplexObject;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.Parallelepiped;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.PartsObject;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.Plane;
import com.kamenov.martin.a3dobjects.engine.models.game_objects.contracts.Object3D;
import com.kamenov.martin.a3dobjects.engine.services.PaintService;

import java.util.ArrayList;

/**
 * Created by Martin on 15.11.2018 г..
 */

public class CarSample {
    public static ArrayList<Object3D> getCarSample() {
        ArrayList<Object3D> objects = new ArrayList<>();
        ArrayList<Object3D> parts = new ArrayList<>();
        Parallelepiped body = new Parallelepiped(EngineConstants.SCREEN_WIDTH / 2, EngineConstants.SCREEN_HEIGHT / 2, -25, 100, 300, 50,
                edgePaint,
                wallPaint,
                1);
        Plane roof = new Plane(EngineConstants.SCREEN_WIDTH / 2, EngineConstants.SCREEN_HEIGHT / 2 + 25, 50,
                edgePaint,
                wallPaint,
                1, 100, 150
        );
        Plane frontGlass = new Plane(EngineConstants.SCREEN_WIDTH / 2, EngineConstants.SCREEN_HEIGHT / 2 - 75, 25,
                edgePaint,
                wallPaint,
                1, 100, 75
        );
        frontGlass.rotateX3D(45);
        Plane backGlass = new Plane(EngineConstants.SCREEN_WIDTH / 2, EngineConstants.SCREEN_HEIGHT / 2 + 125, 25,
                edgePaint,
                wallPaint,
                1, 100, 75
        );
        DeepPoint[] leftGlassPoints = new DeepPoint[]{
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 - 50, EngineConstants.SCREEN_HEIGHT / 2 + 100, 50),
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 - 50, EngineConstants.SCREEN_HEIGHT / 2 - 50, 50),
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 - 50, EngineConstants.SCREEN_HEIGHT / 2 - 100, 0),
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 - 50, EngineConstants.SCREEN_HEIGHT / 2 + 150, 0),
        };
        ArrayList<DeepPoint[]> leftGlassParts = new ArrayList<>();
        leftGlassParts.add(leftGlassPoints);
        PartsObject leftGlass = new PartsObject(0, 0, 0,
                edgePaint, wallPaint, 1, leftGlassPoints, leftGlassParts);
        DeepPoint[] rightGlassPoints = new DeepPoint[]{
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 + 50, EngineConstants.SCREEN_HEIGHT / 2 + 100, 50),
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 + 50, EngineConstants.SCREEN_HEIGHT / 2 - 50, 50),
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 + 50, EngineConstants.SCREEN_HEIGHT / 2 - 100, 0),
                new DeepPoint(EngineConstants.SCREEN_WIDTH / 2 + 50, EngineConstants.SCREEN_HEIGHT / 2 + 150, 0),
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
                EngineConstants.SCREEN_WIDTH / 2 - 55,
                EngineConstants.SCREEN_HEIGHT / 2 - 100));
        parts.add(createTyre(
                EngineConstants.SCREEN_WIDTH / 2 - 55,
                EngineConstants.SCREEN_HEIGHT / 2 + 100));
        parts.add(createTyre(
                EngineConstants.SCREEN_WIDTH / 2 + 55,
                EngineConstants.SCREEN_HEIGHT / 2 - 100));
        parts.add(createTyre(
                EngineConstants.SCREEN_WIDTH / 2 + 55,
                EngineConstants.SCREEN_HEIGHT / 2 + 100));

        ComplexObject car = new ComplexObject(
                EngineConstants.SCREEN_WIDTH / 2,
                EngineConstants.SCREEN_HEIGHT / 2,
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
