package com.kamenov.martin.a3dobjects.models.services;

import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.ComplexObject;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Sphere;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 27.10.2018 г..
 */

public class FigureDrawingService {
    private SavingService savingService;

    public FigureDrawingService(SavingService savingService) {
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
        savingService.setSavedObjects(figuresConfigurations);
    }

    private ArrayList<Object3D> getSimpleCubeConfiguration() {
        ArrayList<Object3D> objects = new ArrayList<>();
        objects.add(new Cube(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2, 0, 100, PaintService.createEdgePaint("red"),
                PaintService.createWallPaint("white"), 1));
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
}
