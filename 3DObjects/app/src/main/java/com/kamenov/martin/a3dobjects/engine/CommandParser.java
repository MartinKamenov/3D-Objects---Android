package com.kamenov.martin.a3dobjects.engine;

import android.app.Activity;

import com.kamenov.martin.a3dobjects.engine.contracts.Starter;
import com.kamenov.martin.a3dobjects.models.factory.FigureFactory;

/**
 * Created by Martin on 13.10.2018 г..
 */

public class CommandParser {
    private final FigureFactory mFigureFactory;
    private final Starter mStarter;

    private String[] commands = {
            "start",
            "cube",
            "para",
            "pira",
            "plan"
    };

    public CommandParser(Starter starter, FigureFactory figureFactory) {
        this.mFigureFactory = figureFactory;
        this.mStarter = starter;
    }

    public void execute(String commandLine) {
        commandLine = commandLine.trim().toLowerCase();
        String[] commandWords = commandLine.split("\\s+");
        String command = findCommand(commandWords[0]);
        if(command != null) {

            switch (command) {
                case "start":
                    mStarter.startEngine();
                case "cube":
                    //mFigureFactory.getCube();
                    break;
                case "para":
                    //mFigureFactory.getCube();
                    break;
            }
        }
    }

    private String findCommand(String command) {
        for(int i = 0; i < commands.length; i++) {
            if(commands[i].equals(command)) {
                return command;
            }
        }

        return null;
    }
}
