package com.kamenov.martin.a3dobjects.engine;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.EditText;

import com.kamenov.martin.a3dobjects.engine.contracts.Starter;
import com.kamenov.martin.a3dobjects.models.factory.FigureFactory;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Cube;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Parallelepiped;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Piramid;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.Plane;
import com.kamenov.martin.a3dobjects.models.game_objects_3d.contracts.Object3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 13.10.2018 г..
 */

public class CommandParser {
    private final FigureFactory mFigureFactory;
    private final Starter mStarter;
    private final EditText mConsole;
    private boolean isCreatingComplexObject;
    private ArrayList<Object3D> complexObjectFigures;
    private boolean lastCommandWasConsoleWrite;
    private float coX;
    private float coY;
    private float coZ;
    private float coRotation;
    private Paint coEdgePaint;
    private Paint coWallPaint;

    private String[] commands = {
            "start",
            "cube",
            "para",
            "pira",
            "plane",
            "co",
            "complexobject",
            "finish",
            "stop",
            "restart",
            "reset",
            "clear",
            "cls",
            "help"
    };

    private String[] colors = {
            "red",
            "blue",
            "green",
            "white",
            "black"
    };

    private String[] colorRepresentations = {
            "#ff0000",
            "#0000ff",
            "#00ff00",
            "#ffffff",
            "#000000"
    };

    public CommandParser(Starter starter, FigureFactory figureFactory, EditText console) {
        this.mFigureFactory = figureFactory;
        this.mStarter = starter;
        this.mConsole = console;
        lastCommandWasConsoleWrite = false;
        isCreatingComplexObject = false;
    }

    public void execute(String commandLine) {
        if(lastCommandWasConsoleWrite) {
            lastCommandWasConsoleWrite = false;
            return;
        }
        commandLine = commandLine.trim().toLowerCase();
        String[] commandWords = commandLine.split("\\s+");
        String command = findCommand(this.commands, commandWords[0]);
        if(command != null) {
            switch (command) {
                case "start":
                    writeLine("Starting engine...");
                    mStarter.startEngine();
                    break;
                case "cube":
                case "para":
                case "pira":
                case "plane":
                case "co":
                case "complexobject":
                    createObject(commandWords);
                    break;
                case "reset":
                case "restart":
                    if(isCreatingComplexObject) {
                        isCreatingComplexObject = false;
                        complexObjectFigures = new ArrayList<>();
                    } else {
                        mFigureFactory.clearFigures();
                    }
                    break;
                case "finish":
                case "stop":
                    if(isCreatingComplexObject) {
                        try {
                            mFigureFactory.createComplexObject(coX, coY, coZ, coEdgePaint,
                                    coWallPaint, coRotation, complexObjectFigures);
                            writeLine("Object was created");
                        } catch (Exception ex) {
                            writeLine("Invalid params of complex object");
                        }
                        complexObjectFigures = new ArrayList<>();
                        isCreatingComplexObject = false;
                    } else {
                        writeLine("No complex object was being created");
                    }
                    break;
                case "clear":
                case "cls":
                    clearConsole();
                    break;
                case "help":
                    writeLine(showCommands());
                    break;
            }
        } else {
            writeLine("Command " + commandWords[0] + " was not recognized");
        }
    }

    public void write(String message) {
        lastCommandWasConsoleWrite = true;
        String text = mConsole.getText().toString();
        mConsole.setText(text + message);
        int pos = mConsole.getText().length();
        mConsole.setSelection(pos);
    }

    public void writeLine(String message) {
        write(message + "\n");
        int pos = mConsole.getText().length();
        mConsole.setSelection(pos);
    }

    private String showCommands() {
        StringBuilder commandsString = new StringBuilder();
        commandsString.append("Commands:\n");
        for (int i = 0; i < commands.length; i++) {
            commandsString.append("[" + commands[i] + "]");
            if(i != commands.length - 1) {
                commandsString.append("\n");
            }
        }

        return commandsString.toString();
    }

    private String findCommand(String commands[], String command) {
        for(int i = 0; i < commands.length; i++) {
            if(commands[i].equals(command)) {
                return command;
            }
        }

        return null;
    }

    private void createObject(String[] commandWords) {
        try {
            float x = Float.valueOf(commandWords[1]);
            float y = Float.valueOf(commandWords[2]);
            float z = Float.valueOf(commandWords[3]);
            switch (commandWords[0]) {
                case "cube":
                    float edgeLength = Float.valueOf(commandWords[4]);
                    Paint edgePaint = createEdgePaint(commandWords[5]);
                    Paint wallPaint = createWallPaint(commandWords[6]);
                    float rotation = Float.valueOf(commandWords[7]);
                    if(!isCreatingComplexObject) {
                        mFigureFactory
                                .createCube(x, y, z, edgeLength, edgePaint, wallPaint, rotation);
                        writeLine("Cube was created");
                    } else {
                        Cube cube = mFigureFactory
                                .createCube(x, y, z, edgeLength, edgePaint, wallPaint, rotation);
                        complexObjectFigures.add(cube);
                        writeLine("Cube was added");
                    }
                    break;
                case "para":
                    float aLength = Float.valueOf(commandWords[4]);
                    float bLength = Float.valueOf(commandWords[5]);
                    float cLength = Float.valueOf(commandWords[6]);
                    edgePaint = createEdgePaint(commandWords[7]);
                    wallPaint = createWallPaint(commandWords[8]);
                    rotation = Float.valueOf(commandWords[9]);
                    if(!isCreatingComplexObject) {
                        mFigureFactory
                                .createParallelepiped(x, y, z, aLength, bLength, cLength, edgePaint, wallPaint, rotation);
                        writeLine("Parallelepiped was created");
                    } else {
                        Parallelepiped parallelepiped = mFigureFactory
                                .createParallelepiped(x, y, z, aLength, bLength, cLength, edgePaint, wallPaint, rotation);
                        complexObjectFigures.add(parallelepiped);
                        writeLine("Parallelepiped was added");
                    }
                    break;
                case "pira":
                    edgePaint = createEdgePaint(commandWords[4]);
                    wallPaint = createWallPaint(commandWords[5]);
                    rotation = Float.valueOf(commandWords[6]);
                    aLength = Float.valueOf(commandWords[7]);
                    bLength = Float.valueOf(commandWords[8]);
                    float h = Float.valueOf(commandWords[9]);
                    if(!isCreatingComplexObject) {
                        mFigureFactory
                                .createPyramid(x, y, z, edgePaint, wallPaint, rotation, aLength, bLength, h);
                        writeLine("Pyramid was created");
                    } else {
                        Piramid pyramid = mFigureFactory
                                .createPyramid(x, y, z, edgePaint, wallPaint, rotation, aLength, bLength, h);
                        complexObjectFigures.add(pyramid);
                        writeLine("Pyramid was added");
                    }
                    break;
                case "plane":
                    edgePaint = createEdgePaint(commandWords[4]);
                    wallPaint = createWallPaint(commandWords[5]);
                    rotation = Float.valueOf(commandWords[6]);
                    aLength = Float.valueOf(commandWords[7]);
                    bLength = Float.valueOf(commandWords[8]);
                    if(!isCreatingComplexObject) {
                        mFigureFactory.createPlane(x, y, z,
                                edgePaint, wallPaint, rotation, aLength, bLength);
                        writeLine("Plane was created");
                    } else {
                        Plane plane = mFigureFactory
                                .createPlane(x, y, z,
                                        edgePaint, wallPaint, rotation, aLength, bLength);
                        complexObjectFigures.add(plane);
                        writeLine("Plane was added");
                    }
                    break;
                case "co":
                case "complexobject":
                    coX = x;
                    coY = y;
                    coZ = z;
                    coEdgePaint = createEdgePaint(commandWords[4]);
                    coWallPaint = createWallPaint(commandWords[5]);
                    coRotation = Float.valueOf(commandWords[6]);
                    complexObjectFigures = new ArrayList<>();
                    isCreatingComplexObject = true;
                    writeLine("Start adding figures\nType finish or stop to create complex object");
                    break;
            }
        } catch (Exception ex) {
            switch (commandWords[0]) {
                case "cube":
                    writeLine("Params: x, y, z, edgeLength, colorEdge, colorWall, rotation");
                    break;
                case "para":
                    writeLine("Params: x, y, z, aLength, bLength, cLength, colorEdge, colorWall, rotation");
                    break;
                case "pira":
                    writeLine("Params: x, y, z, edgeColor, wallColor, rotation, aLength, bLength, h");
                    break;
                case "plane":
                    writeLine("Params: x, y, z, edgeColor, wallColor, rotation, aLength, bLength");
                    break;
                case "co":
                case "complexobject":
                    writeLine("Params: x, y, z, edgeColor, wallColor, rotation");
                    break;
                default:
                    writeLine("Invalid params");
                    break;
            }
        }
    }

    private Paint createEdgePaint(String color) {
        Paint paint = new Paint();
        paint.setColor(parseColor(color));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    private Paint createWallPaint(String color) {
        Paint paint = new Paint();
        paint.setColor(parseColor(color));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private int parseColor(String colorName) {
        for(int i = 0; i < colors.length; i++) {
            if(colors[i].equals(colorName)) {
                return Color.parseColor(colorRepresentations[i]);
            }
        }

        if((colorName.length() == 7 || colorName.length() == 9) && colorName.charAt(0) == '#') {
            return Color.parseColor(colorName);
        }

        return Color.parseColor("#00000000");
    }

    private void clearConsole() {
        mConsole.setText("");
    }
}
