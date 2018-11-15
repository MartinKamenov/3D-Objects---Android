package com.kamenov.martin.a3dobjects.models.services;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.DrawingPart;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cylinder;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Sphere;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 19.10.2018 г..
 */

public class DrawingService {
    public boolean shouldUpdate;
    private static DrawingService instance;
    private SortingService sortingService;

    // Dependencies: SortingService
    private DrawingService(SortingService sortingService) {
        this.sortingService = sortingService;
        shouldUpdate = false;
    }

    public static DrawingService getInstance(SortingService sortingService) {
        if(instance == null) {
            instance = new DrawingService(sortingService);
        }

        return instance;
    }

    public void drawFigures(Canvas canvas, ArrayList<Object3D> figures) {
        List<DrawingPart> drawingParts = arrangeDrawingParts(figures);
        drawParts(canvas, drawingParts);
    }

    private List<DrawingPart> arrangeDrawingParts(List<Object3D> figures) {
        ArrayList<DrawingPart> drawingParts = new ArrayList<>();
        for (int i = 0; i < figures.size(); i++) {
            Object3D figure = figures.get(i);
            // Should be used if we need moving
            if(shouldUpdate) {
                figure.setDrawingParts();
            }
            sortingService.sortParts(figure.drawingParts);
            if(i == 0) {
                drawingParts = figure.drawingParts;
            } else {
                drawingParts = sortingService.mergeSortedDrawingParts(drawingParts, figure.drawingParts);
            }
        }

        return drawingParts;
    }

    private void drawParts(Canvas canvas, List<DrawingPart> drawingParts) {
        // Draw part depending how many points it has
        // If part has 2 points then it's an edge else it's a wall
        for(int k = 0; k < drawingParts.size(); k++) {
            DrawingPart drawingPart = drawingParts.get(k);
            DeepPoint[] part = drawingPart.parts;
            if (drawingPart.clazz == Sphere.class || drawingPart.clazz == Cylinder.class) {
                drawCircle(canvas, drawingPart);
            }
            else if (part.length == 2) {
                drawLine(canvas, drawingPart);
            } else {
                // Doesn't draw wall if no wall paint has been added
                if (drawingPart.paint != null) {
                    drawWall(canvas, drawingPart);
                }
            }
        }
    }

    private void drawOval(Canvas canvas, DrawingPart drawingPart) {
        DeepPoint[] part = drawingPart.parts;
        canvas.drawOval(part[0].getX() + drawingPart.x,
                part[1].getY() + drawingPart.y, part[2].getX() + drawingPart.x,
                part[3].getY() + drawingPart.y, drawingPart.paint);
    }

    private void drawCircle(Canvas canvas, DrawingPart drawingPart) {
        DeepPoint[] part = drawingPart.parts;
        canvas.drawCircle(part[0].getX() + drawingPart.x
                , part[0].getY() + drawingPart.y,
                drawingPart.radius, drawingPart.paint);
    }

    private void drawLine(Canvas canvas, DrawingPart drawingPart) {
        DeepPoint[] part = drawingPart.parts;
        canvas.drawLine(part[0].getX() + drawingPart.x, part[0].getY() + drawingPart.y,
                part[1].getX() + drawingPart.x, part[1].getY() + drawingPart.y, drawingPart.paint);
    }

    private void drawWall(Canvas canvas, DrawingPart drawingPart) {
        DeepPoint[] part = drawingPart.parts;
        Path wallPath = new Path();
        wallPath.moveTo(part[0].getX() + drawingPart.x, part[0].getY() + drawingPart.y);
        for (int j = 1; j < part.length; j++) {
            wallPath.lineTo(part[j].getX() + drawingPart.x, part[j].getY() + drawingPart.y);
        }
        canvas.drawPath(wallPath, drawingPart.paint);
    }
}
