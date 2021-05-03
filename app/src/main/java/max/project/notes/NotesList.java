package max.project.notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class NotesList {


    private final NotesAdapter mAdapter;
    private final Listener mListener;

    public NotesList(RecyclerView rv, Listener listener) {
        mListener = listener;
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        mAdapter = new NotesAdapter();
        rv.setAdapter(mAdapter);
    }

    public void setNotes(List<Notes> list) {
        mAdapter.setData(list);
    }


    class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {


        private List<Notes> mNotesDate;

        NotesAdapter() {
            setHasStableIds(true);
        }

        void setData(List<Notes> data) {
            mNotesDate = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(mNotesDate.get(position));
        }

        @Override
        public int getItemCount() {
            return mNotesDate.size();
        }

        @Override
        public long getItemId(int position) {
            return mNotesDate.get(position).id;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView title_notes_rv;
            private final TextView notes_date_rv;
            private final TextView notes_description_rv;

            public ViewHolder(@NonNull ViewGroup parent) {
                super(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.i_notes_rv, parent, false));
                title_notes_rv = itemView.findViewById(R.id.title_notes_rv);
                notes_date_rv = itemView.findViewById(R.id.notes_date_rv);
                notes_description_rv = itemView.findViewById(R.id.notes_description_rv);

                itemView.findViewById(R.id.i_note).setOnClickListener(v ->
                        mListener.onOpen(mNotesDate.get(getAdapterPosition())));

            }

            void bind(Notes notes) {

                title_notes_rv.setText(notes.title);
                notes_date_rv.setText(notes.date);
                notes_description_rv.setText(notes.description);

            }
        }
    }

}
