package com.kamenov.martin.a3dobjects.engine.services.factories;

import com.kamenov.martin.a3dobjects.engine.models.game_objects.contracts.Object3D;

import java.util.ArrayList;

public interface IFigureFactory {
    ArrayList<Object3D> getFigures();
    void setFigures(ArrayList<Object3D> figures);
    void clearFigures();
    void removeFigure(int i);
}
