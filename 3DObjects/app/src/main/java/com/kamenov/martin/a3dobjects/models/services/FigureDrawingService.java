package com.kamenov.martin.a3dobjects.models.services;

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
    }

    public ArrayList<Object3D> getConfiguration(int index) {
        return (ArrayList<Object3D>)savingService.load(index);
    }

    private void setFigureConfiguration() {
        ArrayList<ArrayList<Object3D>> figuresConfigurations = new ArrayList<ArrayList<Object3D>>(){
            /*new ArrayList<> () {
                //new Cube(0, 0, 0, 100, )
            }*/
        };
        savingService.setSavedObjects(figuresConfigurations);
    }
}
