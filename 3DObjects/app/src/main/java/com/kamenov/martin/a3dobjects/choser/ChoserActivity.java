package com.kamenov.martin.a3dobjects.choser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kamenov.martin.a3dobjects.engine.CommandParser;
import com.kamenov.martin.a3dobjects.engine.contracts.Starter;
import com.kamenov.martin.a3dobjects.game.GameActivity;
import com.kamenov.martin.a3dobjects.R;
import com.kamenov.martin.a3dobjects.models.factory.FigureFactory;

public class ChoserActivity extends Activity implements TextWatcher, Starter {
    private Button mStartButton;
    private EditText mConsole;
    private TextView mStatusShower;
    private CommandParser mCommandParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCommandParser = new CommandParser(this, FigureFactory.getInstance());
        setContentView(R.layout.activity_choser);
        mStatusShower = (TextView) findViewById(R.id.status_shower);
        mConsole = (EditText) findViewById(R.id.console);
        mConsole.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString();
        if(text.length() > 0 && text.charAt(text.length() - 1) == '\n') {
            extractCommand(getLastCommand(text));
        }
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
