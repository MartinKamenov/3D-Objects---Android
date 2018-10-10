package com.kamenov.martin.a3dobjects.choser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kamenov.martin.a3dobjects.GameActivity;
import com.kamenov.martin.a3dobjects.R;

public class ChoserActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choser);
        mStartButton = (Button) findViewById(R.id.startBtn);
        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }
}
