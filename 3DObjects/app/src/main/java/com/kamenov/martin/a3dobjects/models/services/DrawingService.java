package com.kamenov.martin.a3dobjects.models.services;

import android.graphics.Canvas;
import android.graphics.Path;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Martin on 19.10.2018 г..
 */

public class DrawingService {
    private static DrawingService instance;
    private SortingService sortingService;

    private DrawingService(SortingService sortingService) {}

    public static DrawingService getInstance(SortingService sortingService) {
        if(instance == null) {
            instance = new DrawingService(sortingService);
        }

        return instance;
    }

    public void drawFigures(Canvas canvas, ArrayList<Object3D> figures) {
        ArrayList<DeepPoint[]> wallsAndEdges = new ArrayList<>();
        ArrayList<Float> zts = new ArrayList<>();

        drawWallsAndEdges(canvas, figures);
    }

    private void drawWallsAndEdges(Canvas canvas, List<Object3D> figures) {
        for (int i = 0; i < figures.size(); i++) {
            Object3D figure = figures.get(i);
            sortingService.sortParts(figure.parts);
            for(int k = 0; k < figure.parts.size(); k++) {
                DeepPoint[] part = figure.parts.get(k);
                if (part.length == 2) {
                    canvas.drawLine(part[0].getX() + figure.x, part[0].getY() + figure.y,
                            part[1].getX() + figure.x, part[1].getY() + figure.y, figure.edgePaint);
                } else {
                    // Doesn't draw wall if no walls added
                    if (figure.wallPaint != null) {
                        Path wallPath = new Path();
                        wallPath.moveTo(part[0].getX() + figure.x, part[0].getY() + figure.y);
                        for (int j = 1; j < part.length; j++) {
                            wallPath.lineTo(part[j].getX() + figure.x, part[j].getY() + figure.y);
                        }
                        canvas.drawPath(wallPath, figure.wallPaint);
                    }
                }
            }
        }
    }
}
