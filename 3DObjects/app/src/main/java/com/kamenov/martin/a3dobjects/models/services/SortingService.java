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
