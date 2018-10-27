package com.kamenov.martin.a3dobjects.models.services;

import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

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
        figuresConfigurations.add(getSimpleCubConfiguration());
        savingService.setSavedObjects(figuresConfigurations);
    }

    private ArrayList<Object3D> getSimpleCubConfiguration() {
        ArrayList<Object3D> objects = new ArrayList<>();
        objects.add(new Cube(Constants.SCREEN_WIDTH / 2,
                Constants.SCREEN_HEIGHT / 2, 0, 100, PaintService.createEdgePaint("red"),
                PaintService.createEdgePaint("black"), 1));
        return objects;
    }
}
