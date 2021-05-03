package max.project.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import max.project.notes.paint.PaintActivity;


public class MainActivity extends AppCompatActivity implements Repo.Listener {

    private NotesList notesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       findViewById(R.id.add_notes_button_ma).setOnClickListener(v -> startActivity(new Intent(
                MainActivity.this, NoteCreate.class)));


        notesList = new NotesList(findViewById(R.id.notes_list_rv), note -> startActivity(
                new Intent(MainActivity.this,
                        NotesActivity.class)
                        .putExtra(NotesActivity.EXTRA_ID, note.id))

        );
        notesList.setNotes(Repo.getInstance(this).getNotes());
        onDataChange();
        Repo.getInstance(this).addListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Repo.getInstance(this).removeListener(this);
    }

    @Override
    public void onDataChange() {
        notesList.setNotes(Repo.getInstance(this).getNotes());
    }
}