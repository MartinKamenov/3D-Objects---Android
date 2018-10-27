package com.kamenov.martin.a3dobjects.models.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 27.10.2018 г..
 */

public class SavingService<T> {
    private static SavingService instance;
    private List<T> savedObjects;

    private SavingService() {
        this.savedObjects = new ArrayList<>();
    }

    public static SavingService getInstance() {
        if(instance == null) {
            instance = new SavingService();
        }

        return instance;
    }

    public void addValue(T value) {
        this.savedObjects.add(value);
    }

    public void setSavedObjects(List<T> objects) {
        this.savedObjects = objects;
    }

    public T load(int index) {
        return savedObjects.get(index);
    }
}
