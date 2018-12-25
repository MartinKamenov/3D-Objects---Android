package com.kamenov.martin.a3dobjects.models.services;

import android.graphics.Paint;

import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.ComplexObject;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cylinder;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.PartsObject;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Pyramid;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Sphere;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;
import com.kamenov.martin.a3dobjects.models.services.contracts.Dimension;
import com.kamenov.martin.a3dobjects.sampleObjects.CarSample;

import java.util.ArrayList;

/**
 * Created by Martin on 27.10.2018 г..
 */

public class FigureSavingService {
    private SavingService savingService;

    public FigureSavingService(SavingService savingService) {
        this.savingService = savingService;
        setFigureConfiguration();
    }

    public ArrayList<Object3D> getConfiguration(int index) {
        return (ArrayList<Object3D>)savingService.load(index);
    }

    private void setFigureConfiguration() {
        ArrayList<ArrayList<Object3D>> figuresConfigurations = new ArrayList<>();
        figuresConfigurations.add(getSimpleCubeConfiguration());
        figuresConfigurations.add(getSolarSystem());
        figuresConfigurations.add(getCubeConfiguration());
        figuresConfigurations.add(getPartComplexObject());
        figuresConfigurations.add(getSimpleCylinder());
        figuresConfigurations.add(CarSample.getCarSample());
        figuresConfigurations.add(getFirstRocketSample());
        savingService.setSavedObjects(figuresConfigurations);
    }

    private ArrayList<Object3D> getSimpleCubeConfiguration() {
        ArrayList<Object3D> objects = new ArrayList<>();
        Cube cube = new Cube(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2, 0, 100, PaintService.createEdgePaint("red"),
                PaintService.createWallPaint("white"), 1);
        // cube.rotateY3D(45);
        objects.add(cube);
        return objects;
    }

    private ArrayList<Object3D> getSolarSystem() {
        float centerX = Constants.SCREEN_WIDTH / 2;
        float centerY = Constants.SCREEN_HEIGHT / 2;
        ArrayList<Object3D> objects = new ArrayList<>();
        objects.add(new Sphere(centerX,
                centerY, 0, PaintService.createEdgePaint("#ffe500"),
                PaintService.createWallPaint("#ffc700"), 1, 120));

        float[] sizes = new float[] {
            10, 20, 20, 15, 60, 52, 51, 39, 8
        };
        String[] edges = new String[] {
            "black", "#e58d4e", "#0cd12a", "#ad2a03", "#DA9C75", "#90846F", "#A6CBD1", "#4070D6", "#BA9777"
        };
        String[] walls = new String[] {
            "#2d2621", "#d69668", "#2c3af7", "#d1640b", "#CE9E7C", "#EBD2A9", "#A5CAD0", "#5B94EE", "#E2BE98"
        };
        for(int i = 0; i < 9; i++) {
            ArrayList<Object3D> planets = new ArrayList<>();
            Sphere planet = new Sphere(centerX + findDistanceFromSun(i + 1, sizes),
                    centerY, 0, PaintService.createEdgePaint(edges[i]),
                    PaintService.createWallPaint(walls[i]), 1, sizes[i]);
            planets.add(planet);

            objects.add(new ComplexObject(centerX, centerY, 0, null, null, (Math.abs(4 - i) + 1) * 0.4f, planets));
        }

        return objects;
    }

    private float findDistanceFromSun(int index, float[] sizes) {
        float sum = 120;
        for(int i = 0; i < index; i++) {
            sum += (sizes[i] * 2);
        }

        return sum;
    }

    private ArrayList<Object3D> getCubeConfiguration() {
        ArrayList<Object3D> objects = new ArrayList<>();
        ArrayList<Object3D> parts = new ArrayList<>();
        float cubeSize = 200;
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 + cubeSize,
                Constants.SCREEN_HEIGHT / 2, 0, cubeSize, PaintService.createEdgePaint("black"),
                PaintService.createWallPaint("red"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 + (cubeSize * 2),
                Constants.SCREEN_HEIGHT / 2 + cubeSize, 0, cubeSize, PaintService.createEdgePaint("red"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 + (cubeSize * 2),
                Constants.SCREEN_HEIGHT / 2 - cubeSize, 0, cubeSize, PaintService.createEdgePaint("red"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 - cubeSize,
                Constants.SCREEN_HEIGHT / 2, 0, cubeSize, PaintService.createEdgePaint("black"),
                PaintService.createWallPaint("blue"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 - (cubeSize * 2),
                Constants.SCREEN_HEIGHT / 2 + cubeSize, 0, cubeSize, PaintService.createEdgePaint("blue"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 - (cubeSize * 2),
                Constants.SCREEN_HEIGHT / 2 - cubeSize, 0, cubeSize, PaintService.createEdgePaint("blue"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2 + cubeSize, 0, cubeSize, PaintService.createEdgePaint("black"),
                PaintService.createWallPaint("white"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 + cubeSize,
                Constants.SCREEN_HEIGHT / 2 + (cubeSize * 2), 0, cubeSize, PaintService.createEdgePaint("white"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 - cubeSize,
                Constants.SCREEN_HEIGHT / 2 + (cubeSize * 2), 0, cubeSize, PaintService.createEdgePaint("white"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2 - cubeSize, 0, cubeSize, PaintService.createEdgePaint("black"),
                PaintService.createWallPaint("green"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 + cubeSize,
                Constants.SCREEN_HEIGHT / 2 - (cubeSize * 2), 0, cubeSize, PaintService.createEdgePaint("green"),
                PaintService.createWallPaint("black"), 1));
        parts.add(new Cube(Constants.SCREEN_WIDTH / 2 - cubeSize,
                Constants.SCREEN_HEIGHT / 2 - (cubeSize * 2), 0, cubeSize, PaintService.createEdgePaint("green"),
                PaintService.createWallPaint("black"), 1));
        objects.add(new ComplexObject(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 0,
                null, null, 1, parts));
        return objects;
    }

    private ArrayList<Object3D> getPartComplexObject() {
        ArrayList<Object3D> objects = new ArrayList<>();
        DeepPoint[] points = new DeepPoint[3];
        points[0] = new DeepPoint(100, 100, 0);
        points[1] = new DeepPoint(100, -100, 0);
        points[2] = new DeepPoint(-100, 0, 0);
        ArrayList<DeepPoint[]> parts = new ArrayList<>();
        parts.add(points);
        PartsObject partsObject = new PartsObject(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 0,
                PaintService.createEdgePaint("red"), PaintService.createWallPaint("blue"), 1, points, parts);
        objects.add(partsObject);
        return objects;
    }

    private ArrayList<Object3D> getSimpleCylinder() {
        ArrayList<Object3D> objects = new ArrayList<>();
        Cylinder partsObject = new Cylinder(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 0,
                PaintService.createEdgePaint("red"), PaintService.createWallPaint("blue"), 1, 200, 100);
        objects.add(partsObject);
        return objects;
    }

    private ArrayList<Object3D> getFirstRocketSample() {
        float width = 80;
        float height = 300;
        float rareWidth = width / 2;
        float rareHeight = height / 2;
        Paint edgePaint = PaintService.createEdgePaint("#ffffff");
        Paint bodyPaint = PaintService.createWallPaint("#ca8dff");
        ArrayList<Object3D> objects = new ArrayList<>();
        ArrayList<Object3D> parts = new ArrayList<>();
        int bodyPartsCount = 10;
        ComplexObject bodyObject = BreakingService.breakParaToSmaller(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2,
                0, width, width, height, edgePaint, bodyPaint, 1, bodyPartsCount, Dimension.Z
        );
        ComplexObject left = BreakingService.breakParaToSmaller(Constants.SCREEN_WIDTH / 2 - (width / 2) - (rareWidth / 2),
                Constants.SCREEN_HEIGHT / 2, - (rareHeight / 2),
                rareWidth, rareWidth, rareHeight, edgePaint, bodyPaint, 1, bodyPartsCount / 2, Dimension.Z);
        ComplexObject right = BreakingService.breakParaToSmaller(Constants.SCREEN_WIDTH / 2 + (width / 2) + (rareWidth / 2),
                Constants.SCREEN_HEIGHT / 2, - (rareHeight / 2),
                rareWidth, rareWidth, rareHeight, edgePaint, bodyPaint, 1, bodyPartsCount / 2, Dimension.Z);
        Pyramid front = new Pyramid(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 0 + (height / 2),
                edgePaint, bodyPaint, 1, width, width, height / 3);

        parts.add(bodyObject);
        parts.add(front);
        parts.add(left);
        parts.add(right);
        ComplexObject rocket = new ComplexObject(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 0,
                edgePaint, bodyPaint, 1, parts);
        objects.add(rocket);
        return objects;
    }
}
