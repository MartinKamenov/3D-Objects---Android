package com.kamenov.martin.a3dobjects.choser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.kamenov.martin.a3dobjects.command_promt_engine.CommandParser;
import com.kamenov.martin.a3dobjects.command_promt_engine.contracts.Starter;
import com.kamenov.martin.a3dobjects.game.GameActivity;
import com.kamenov.martin.a3dobjects.R;
import com.kamenov.martin.a3dobjects.engine.constants.EngineConstants;
import com.kamenov.martin.a3dobjects.engine.services.factories.FigureFactory;
import com.kamenov.martin.a3dobjects.savingServices.FigureSavingService;
import com.kamenov.martin.a3dobjects.savingServices.SavingService;

public class ChoserActivity extends Activity implements TextWatcher, Starter {
    private EditText mConsole;
    private CommandParser mCommandParser;
    private int mLastConsoleElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        EngineConstants.SCREEN_WIDTH = dm.widthPixels;
        EngineConstants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_choser);
        mLastConsoleElements = 0;
        mConsole = findViewById(R.id.console);
        mCommandParser = new CommandParser(this, FigureFactory.getInstance(),
                mConsole, new FigureSavingService(SavingService.getInstance()));
        mConsole.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString();
        boolean textWasAdded = (text.length() > mLastConsoleElements);
        if(text.length() > 0 && text.charAt(text.length() - 1) == '\n' && textWasAdded) {
            extractCommand(getLastCommand(text));
        }

        mLastConsoleElements = text.length();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void startEngine() {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

    private String getLastCommand(String text) {
        String[] commandLines = text.split("\n");
        return commandLines[commandLines.length - 1];
    }

    private void extractCommand(String commandLine) {
        commandLine = commandLine.toLowerCase().trim();
        mCommandParser.execute(commandLine);
    }
}
