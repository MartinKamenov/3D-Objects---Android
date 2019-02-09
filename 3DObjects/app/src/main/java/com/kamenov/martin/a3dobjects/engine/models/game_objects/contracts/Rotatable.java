package com.kamenov.martin.a3dobjects.engine.models.game_objects.contracts;

/**
 * Created by Martin on 12.3.2018 г..
 */

public interface Rotatable extends GameObject {
    void rotateX3D(float delta);

    void rotateY3D(float delta);

    void rotateZ3D(float delta);
}
