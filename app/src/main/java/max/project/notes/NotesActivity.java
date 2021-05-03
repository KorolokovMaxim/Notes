package max.project.notes;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity implements Repo.Listener , ConfirmDeleteDialog.Host {

    public static final String EXTRA_ID = "EXTRA_ID";


    private long mNoteId;
    private EditText mNoteTitle;
    private EditText mNoteDescription;
    private TextView mNoteData;
    private ImageView mNoteDraw;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNoteId = getIntent().getLongExtra(EXTRA_ID, -1);


        setContentView(R.layout.a_note);



        mNoteTitle = findViewById(R.id.a_note_title);
        mNoteDescription = findViewById(R.id.a_note_description);
        mNoteData = findViewById(R.id.a_note_data);
        findViewById(R.id.a_note_back).setOnClickListener(v -> updateNoteInfo());

        findViewById(R.id.a_note_del).setOnClickListener(v ->
                new ConfirmDeleteDialog().show(getSupportFragmentManager() , null));
        onDataChange();
        Repo.getInstance(this).addListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateNoteInfo();


    }

    private Notes getNotes() {
        return Repo.getInstance(this).getNote(mNoteId);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Repo.getInstance(this).removeListener(this);
    }

    @Override
    public void onDataChange() {
        Notes note = getNotes();
        if (note != null) {
            mNoteTitle.setText(note.title);
            mNoteDescription.setText(note.description);
            mNoteData.setText(note.date);
        } else {
            finish();
        }
    }

    @Override
    public void onConfirm() {
        Repo.getInstance(this).deleteNote(mNoteId);
    }

    private void updateNoteInfo() {
        Repo.getInstance(this).updateNote(mNoteId,
                mNoteTitle.getText().toString().trim(),
                mNoteData.getText().toString().trim(),
                mNoteDescription.getText().toString().trim());
        finish();
    }
}
