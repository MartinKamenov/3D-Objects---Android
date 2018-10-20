package com.kamenov.martin.a3dobjects.models.services;

import com.kamenov.martin.a3dobjects.models.DeepPoint;

import java.util.ArrayList;

/**
 * Created by Martin on 20.10.2018 г..
 */

public class SortingService {
    private static SortingService instance;
    private SortingService() {}

    public static SortingService getInstance() {
        if (instance == null) {
            instance = new SortingService();
        }

        return instance;
    }

    public ArrayList<DeepPoint[]> sortParts(ArrayList<DeepPoint[]> parts) {
        // Should sort points depending on z of each part
        // using best sorting algorithm for the case
        // maybe this should change arrange of parts of figure
        // in order to be faster next iteration

        if(!checkIfSorted(parts)) {
            parts = mergeSortParts(parts);
        }
        return parts;
    }

    private boolean checkIfSorted(ArrayList<DeepPoint[]> parts) {
        for(int i = 1; i < parts.size(); i++) {
            if(avrgZ(parts.get(i - 1)) > avrgZ(parts.get(i))) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<DeepPoint[]> mergeSortParts(ArrayList<DeepPoint[]> parts) {
        // To Do:
        // Implement merge sort with one arrayList
        return parts;
    }

    private float avrgZ(DeepPoint[] points)
    {
        float avrg = 0;
        for(int i = 0; i < points.length; i++)
        {
            avrg += points[i].getZ();
        }
        avrg /= points.length;
        return avrg;
    }
}
