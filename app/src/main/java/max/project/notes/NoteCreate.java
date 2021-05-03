package max.project.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import max.project.notes.paint.PaintActivity;

public class NoteCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_create_note);
        EditText a_create_title = findViewById(R.id.a_create_title);
        EditText a_create_description = findViewById(R.id.a_create_description);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        findViewById(R.id.a_note_back_c).setOnClickListener(v -> finish());



        findViewById(R.id.a_create_draw).setOnClickListener(
            v -> startActivity(new Intent(
                    NoteCreate.this , PaintActivity.class)
                    .putExtra(PaintActivity.EXTRA_ID , v.getId() ))
        );
        findViewById(R.id.a_create_save).setOnClickListener(v -> {
            Repo.getInstance(this)
                    .addNote(a_create_title.getText().toString().trim(),
                            currentDate,
                            a_create_description.getText().toString().trim());
            finish();

        });




    }
}