package com.kamenov.martin.a3dobjects.engine;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.EditText;

import com.kamenov.martin.a3dobjects.engine.contracts.Starter;
import com.kamenov.martin.a3dobjects.models.factory.FigureFactory;

/**
 * Created by Martin on 13.10.2018 г..
 */

public class CommandParser {
    private final FigureFactory mFigureFactory;
    private final Starter mStarter;
    private final EditText mConsole;
    private boolean lastCommandWasConsoleWrite;

    private String[] commands = {
            "start",
            "cube",
            "para",
            "pira",
            "plan"
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
                    createObject(commandWords);
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
                    mFigureFactory.createCube(x, y, z, edgeLength, edgePaint, wallPaint, rotation);
                    break;
                case "para":
                    float aLength = Float.valueOf(commandWords[4]);
                    float bLength = Float.valueOf(commandWords[4]);
                    float cLength = Float.valueOf(commandWords[4]);
                    edgePaint = createEdgePaint(commandWords[5]);
                    wallPaint = createWallPaint(commandWords[6]);
                    rotation = Float.valueOf(commandWords[7]);
                    mFigureFactory.createParallelepiped(x, y, z, aLength, bLength, cLength, edgePaint, wallPaint, rotation);
                    break;
            }
        } catch (Exception ex) {
            writeLine("Problem with parameters");
        }
    }

    private Paint createEdgePaint(String color) {
        Paint paint = new Paint();
        paint.setColor(parseColor(color));
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private Paint createWallPaint(String color) {
        Paint paint = new Paint();
        paint.setColor(parseColor(color));
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    private int parseColor(String colorName) {
        for(int i = 0; i < colors.length; i++) {
            if(colors[i].equals(colorName)) {
                return Color.parseColor(colorRepresentations[i]);
            }
        }

        return Color.parseColor("#00000000");
    }
}