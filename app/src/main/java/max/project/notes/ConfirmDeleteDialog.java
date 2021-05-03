package max.project.notes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ConfirmDeleteDialog extends AppCompatDialogFragment {

    public interface Host {
        void onConfirm();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Delete note")
                .setMessage("This action can't be undone")
                .setPositiveButton("Yes", (dialog, which) ->
                        ((Host) requireActivity()).onConfirm())
                .setNegativeButton("Cancel", null)
                .create();

    }
}
