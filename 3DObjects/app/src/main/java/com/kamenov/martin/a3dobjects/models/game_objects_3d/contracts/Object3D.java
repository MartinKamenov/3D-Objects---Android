package com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.contracts.GameObject;
import com.kamenov.martin.a3dobjects.contracts.Rotatable;

import java.util.ArrayList;

/**
 * Created by Martin on 18.3.2018 г..
 */

public abstract class Object3D implements GameObject, Rotatable {
    public DeepPoint[] points;
    public ArrayList<DeepPoint[]> edges;
    public ArrayList<DeepPoint[]> walls;
    public float x;
    public float y;
    public float z;
    protected Paint wallPaint;
    protected Paint edgePaint;
    protected float rotation;
    private boolean rotateX;
    private boolean rotateY;
    private boolean rotateZ;

    public Object3D(float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.edgePaint = edgePaint;
        this.wallPaint = wallPaint;
        this.rotation = rotation;
        rotateX = true;
        rotateY = true;
        rotateZ = true;
    }

    public void rotateZ3D(float theta)
    {
        if(rotateZ) {
            theta *= rotation;
            float sin_t = (float) Math.sin(theta);
            float cos_t = (float) Math.cos(theta);

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float x = point.getX();
                float y = point.getY();
                point.setX(x * cos_t - y * sin_t);
                point.setY(y * cos_t + x * sin_t);
            }
        }
    }

    public void rotateX3D(float theta)
    {
        if(rotateX) {
            theta *= rotation;
            float sin_t = (float) Math.sin(theta);
            float cos_t = (float) Math.cos(theta);

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float y = point.getY();
                float z = point.getZ();
                point.setY(y * cos_t - z * sin_t);
                point.setZ(z * cos_t + y * sin_t);
            }
        }
    }

    public void rotateY3D(float theta)
    {
        if(rotateY) {
            theta *= rotation;
            float sin_t = (float) Math.sin(theta);
            float cos_t = (float) Math.cos(theta);

            for (int i = 0; i < points.length; i++) {
                DeepPoint point = points[i];
                float x = point.getX();
                float z = point.getZ();
                point.setX(x * cos_t - z * sin_t);
                point.setZ(z * cos_t + x * sin_t);
            }
        }
    }

    public void draw(Canvas canvas) {
        ArrayList<DeepPoint[]> wallsAndEdges = new ArrayList<>();
        ArrayList<Float> zts = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++)
        {
            boolean added = false;
            float avg = avrgZ(edges.get(i));
            if(zts.size()==0)
            {
                zts.add(avg);
                wallsAndEdges.add(edges.get(i));
                continue;
            }
            for(int j = 0; j < zts.size(); j++)
            {
                if(avg>zts.get(j))
                {
                    zts.add(j, avg);
                    wallsAndEdges.add(j, edges.get(i));
                    added = true;
                    break;
                }
            }
            if(!added)
            {
                zts.add(avg);
                wallsAndEdges.add(edges.get(i));
            }
        }
        for (int i = 0; i < walls.size(); i++)
        {
            boolean added = false;
            float avg = avrgZ(walls.get(i));
            if(zts.size()==0)
            {
                zts.add(avg);
                wallsAndEdges.add(walls.get(i));
                continue;
            }
            for(int j = 0; j < zts.size(); j++)
            {
                if(avg>zts.get(j))
                {
                    zts.add(j, avg);
                    wallsAndEdges.add(j, walls.get(i));
                    added = true;
                    break;
                }
            }
            if(!added)
            {
                zts.add(avg);
                wallsAndEdges.add(walls.get(i));
            }
        }
        for (int i = 0; i < wallsAndEdges.size(); i++)
        {
            DeepPoint[] wallOrEdge = wallsAndEdges.get(i);
            if(wallsAndEdges.get(i).length==2)
            {
                canvas.drawLine(wallOrEdge[0].getX() + x, wallOrEdge[0].getY() + y,
                        wallOrEdge[1].getX() + x, wallOrEdge[1].getY() + y, edgePaint);
            }
            else
            {
                if(wallPaint!=null) {
                    Path wallPath = new Path();
                    wallPath.moveTo(wallOrEdge[0].getX() + x, wallOrEdge[0].getY() + y);
                    for(int j = 1; j < wallOrEdge.length; j++) {
                        wallPath.lineTo(wallOrEdge[j].getX() + x, wallOrEdge[j].getY() + y);
                    }
                    canvas.drawPath(wallPath, wallPaint);
                }
            }
        }
        /*if(wallPaint != null) {
            for (int i = 0; i < walls.size(); i++) {
                DeepPoint[] wall = walls.get(i);
                Path wallPath = new Path();
                wallPath.moveTo(wall[0].getX() + x, wall[0].getY() + y);
                for(int j = 1; j < wall.length; j++) {
                    wallPath.lineTo(wall[j].getX() + x, wall[j].getY() + y);
                }
                canvas.drawPath(wallPath, wallPaint);
            }
        }

        for (int i = 0; i < edges.size(); i++)
        {
            canvas.drawLine(edges.get(i)[0].getX() + x, edges.get(i)[0].getY() + y,
                    edges.get(i)[1].getX() + x, edges.get(i)[1].getY() + y, edgePaint);
        }*/
    }

    public void setRotateX(boolean rotateX) {
        this.rotateX = rotateX;
    }

    public void setRotateY(boolean rotateY) {
        this.rotateY = rotateY;
    }

    public void setRotateZ(boolean rotateZ) {
        this.rotateZ = rotateZ;
    }

    private float avrgZ(DeepPoint[] points)
    {
        float avrg = 0;
        for(int i = 0; i < points.length; i++)
        {
            avrg += points[i].getZ();
        }
        avrg/=points.length;
        return avrg;
    }
}
