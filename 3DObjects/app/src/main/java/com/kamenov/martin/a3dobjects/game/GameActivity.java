package com.kamenov.martin.a3dobjects.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kamenov.martin.a3dobjects.R;
import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.services.DrawingService;
import com.kamenov.martin.a3dobjects.models.services.SortingService;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        RelativeLayout relativeLayout = findViewById(R.id.container);
        GamePanel gamePanel = new GamePanel(this,
                DrawingService.getInstance(SortingService.getInstance()));
        relativeLayout.addView(gamePanel);
        LinearLayout buttonContainer = findViewById(R.id.button_container);
        buttonContainer.bringToFront();
    }
}
