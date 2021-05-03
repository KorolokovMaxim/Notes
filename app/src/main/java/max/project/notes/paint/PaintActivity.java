package max.project.notes.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import max.project.notes.R;



public class PaintActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";

    private PaintView paintView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_paint);


        paintView = findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        findViewById(R.id.a_draw_back).setOnClickListener(v -> finish());

        paintView.init(metrics);

    }

}