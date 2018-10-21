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
        mergeSortParts(0, parts.size(), parts);
        return parts;
    }

    private ArrayList<DeepPoint[]> mergeSortParts(int start, int end, ArrayList<DeepPoint[]> parts) {
        if(end - start <= 1) {
            return parts;
        }

        int middle = start + ((end - start) / 2);

        mergeSortParts(start, middle, parts);
        mergeSortParts(middle, end, parts);

        int leftIndex = 0;
        int rightIndex = 0;
        ArrayList<DeepPoint[]> sortedParts = new ArrayList<>();

        // Merge part
        while (leftIndex < middle - start || rightIndex < end - middle) {
            if(leftIndex < middle - start && rightIndex < end - middle) {
                if(avrgZ(parts.get(start + leftIndex)) >= avrgZ(parts.get(middle + rightIndex))) {
                    sortedParts.add(parts.get(start + leftIndex));
                    leftIndex++;
                } else {
                    sortedParts.add(parts.get(middle + rightIndex));
                    rightIndex++;
                }
                continue;
            }
            else if(leftIndex < middle - start) {
                sortedParts.add(parts.get(start + leftIndex));
                leftIndex++;
            }
            else {
                sortedParts.add(parts.get(middle + rightIndex));
                rightIndex++;
            }
        }

        for(int i = 0; i < sortedParts.size(); i++) {
            parts.set(start + i, sortedParts.get(i));
        }

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
