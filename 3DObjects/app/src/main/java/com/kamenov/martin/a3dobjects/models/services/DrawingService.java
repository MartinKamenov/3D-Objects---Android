package com.kamenov.martin.a3dobjects.models.services;

import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;

/**
 * Created by Martin on 19.10.2018 г..
 */

public class DrawingService {
    private static DrawingService instance;

    private DrawingService() {}

    public static DrawingService getInstance() {
        if(instance == null) {
            instance = new DrawingService();
        }

        return instance;
    }

    public void drawFigures(ArrayList<Object3D> figures) {}
}
