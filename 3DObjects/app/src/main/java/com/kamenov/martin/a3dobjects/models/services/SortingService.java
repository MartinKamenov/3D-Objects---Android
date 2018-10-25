package com.kamenov.martin.a3dobjects.models.services;

import com.kamenov.martin.a3dobjects.models.DeepPoint;
import com.kamenov.martin.a3dobjects.models.DrawingPart;

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


    public ArrayList<DrawingPart> sortParts(ArrayList<DrawingPart> parts) {
        // Should sort points depending on z of each part
        // using best sorting algorithm for the case
        // maybe this should change arrange of parts of figure
        // in order to be faster next iteration

        if(!checkIfSorted(parts)) {
            parts = mergeSortParts(parts);
        }

        return parts;
    }

    public ArrayList<DrawingPart> mergeSortedDrawingParts(ArrayList<DrawingPart> left, ArrayList<DrawingPart> right) {
        int leftIndex = 0;
        int rightIndex = 0;
        ArrayList<DrawingPart> mergedParts = new ArrayList<>();

        // Merge part
        while (leftIndex < left.size() || rightIndex < right.size()) {
            if(leftIndex < left.size() && rightIndex < right.size()) {
                if(avrgZ(left.get(leftIndex)) >= avrgZ(right.get(rightIndex))) {
                    mergedParts.add(left.get(leftIndex));
                    leftIndex++;
                } else {
                    mergedParts.add(right.get(rightIndex));
                    rightIndex++;
                }
            }
            else if(leftIndex < left.size()) {
                mergedParts.add(left.get(leftIndex));
                leftIndex++;
            }
            else {
                mergedParts.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        return mergedParts;
    }

    private boolean checkIfSorted(ArrayList<DrawingPart> parts) {
        if (parts.size() < 2) {
            return true;
        }
        for(int i = 1; i < parts.size(); i++) {
            if(avrgZ(parts.get(i - 1)) > avrgZ(parts.get(i))) {
                return false;
            }
        }

        return true;
    }

    private ArrayList<DrawingPart> mergeSortParts(ArrayList<DrawingPart> parts) {
        mergeSortParts(0, parts.size(), parts);
        return parts;
    }

    private ArrayList<DrawingPart> mergeSortParts(int start, int end, ArrayList<DrawingPart> parts) {
        if(end - start <= 1) {
            return parts;
        }

        int middle = start + ((end - start) / 2);

        mergeSortParts(start, middle, parts);
        mergeSortParts(middle, end, parts);

        int leftIndex = 0;
        int rightIndex = 0;
        ArrayList<DrawingPart> sortedParts = new ArrayList<>();

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

        // Changing the original array
        for(int i = 0; i < sortedParts.size(); i++) {
            parts.set(start + i, sortedParts.get(i));
        }

        return parts;
    }

    private float avrgZ(DrawingPart part)
    {
        DeepPoint[] points = part.parts;
        float avrg = 0;
        for(int i = 0; i < points.length; i++)
        {
            avrg += points[i].getZ();
        }
        avrg /= points.length;
        return avrg;
    }
}
