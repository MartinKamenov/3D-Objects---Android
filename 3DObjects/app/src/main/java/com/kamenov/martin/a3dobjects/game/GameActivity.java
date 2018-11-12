package com.kamenov.martin.a3dobjects.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kamenov.martin.a3dobjects.R;
import com.kamenov.martin.a3dobjects.contracts.GamePanelState;
import com.kamenov.martin.a3dobjects.models.Constants;
import com.kamenov.martin.a3dobjects.models.services.DrawingService;
import com.kamenov.martin.a3dobjects.models.services.SortingService;

public class GameActivity extends Activity implements View.OnClickListener {

    private GamePanel gamePanel;
    private DrawingService drawingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        RelativeLayout relativeLayout = findViewById(R.id.container);
        drawingService = DrawingService.getInstance(SortingService.getInstance());
        gamePanel = new GamePanel(this, drawingService);
        relativeLayout.addView(gamePanel);
        LinearLayout buttonContainer = findViewById(R.id.button_container);
        buttonContainer.bringToFront();
        Button rotateButton = findViewById(R.id.rotate_btn);
        Button moveButton = findViewById(R.id.move_btn);
        rotateButton.setOnClickListener(this);
        moveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.move_btn:
                gamePanel.setGamePanelState(GamePanelState.Moving);
                drawingService.shouldUpdate = true;
                break;
            case R.id.rotate_btn:
                gamePanel.setGamePanelState(GamePanelState.Rotating);
                drawingService.shouldUpdate = false;
                break;
        }
    }
}
