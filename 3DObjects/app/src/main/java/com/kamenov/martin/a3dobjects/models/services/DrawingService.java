package com.kamenov.martin.a3dobjects.models.services;

import android.graphics.Canvas;
import android.graphics.Path;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.DrawingPart;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 19.10.2018 г..
 */

public class DrawingService {
    private static DrawingService instance;
    private SortingService sortingService;

    // Dependencies: SortingService
    private DrawingService(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    public static DrawingService getInstance(SortingService sortingService) {
        if(instance == null) {
            instance = new DrawingService(sortingService);
        }

        return instance;
    }

    public void drawFigures(Canvas canvas, ArrayList<Object3D> figures) {
        drawParts(canvas, figures);
    }

    private void drawParts(Canvas canvas, List<Object3D> figures) {
        ArrayList<DrawingPart> drawingParts = new ArrayList<>();
        for (int i = 0; i < figures.size(); i++) {
            Object3D figure = figures.get(i);
            sortingService.sortParts(figure.parts);

            for(int j = 0; j < figure.parts.size(); j++) {
                if(figure.parts.get(j).length <= 2) {
                    drawingParts.add(new DrawingPart(
                            figure.x,
                            figure.y,
                            figure.z,
                            figure.parts.get(j),
                            figure.edgePaint));
                } else {
                    drawingParts.add(new DrawingPart(
                            figure.x,
                            figure.y,
                            figure.z,
                            figure.parts.get(j),
                            figure.wallPaint));
                }
            }
        }

        // Draw part depending how many points it has
        // If part has 2 points then it's an edge else it's a wall
        for(int k = 0; k < drawingParts.size(); k++) {
            DrawingPart drawingPart = drawingParts.get(k);
            DeepPoint[] part = drawingPart.part;
            if (part.length == 2) {
                canvas.drawLine(part[0].getX() + drawingPart.x, part[0].getY() + drawingPart.y,
                        part[1].getX() + drawingPart.x, part[1].getY() + drawingPart.y, drawingPart.paint);
            } else {
                // Doesn't draw wall if no wall paint has been added
                if (drawingPart.paint != null) {
                    Path wallPath = new Path();
                    wallPath.moveTo(part[0].getX() + drawingPart.x, part[0].getY() + drawingPart.y);
                    for (int j = 1; j < part.length; j++) {
                        wallPath.lineTo(part[j].getX() + drawingPart.x, part[j].getY() + drawingPart.y);
                    }
                    canvas.drawPath(wallPath, drawingPart.paint);
                }
            }
        }
    }
}
