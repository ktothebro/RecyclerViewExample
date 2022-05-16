package org.overlake.mat803.databaseexample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import org.overlake.mat803.databaseexample.database.SisDatabase;
import org.overlake.mat803.databaseexample.database.SisDatabaseDao;
import org.overlake.mat803.databaseexample.database.Student;
import org.overlake.mat803.databaseexample.databinding.FragmentAddStudentBinding;

public class StudentAddDialogFragment extends DialogFragment {

    public static final String STUDENT_FIRST = "first";
    public static final String STUDENT_LAST = "last";
    public static final String SISID = "sisid";
    public static final String REQ_KEY = "student_values";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        FragmentAddStudentBinding binding = FragmentAddStudentBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder
                .setView(binding.getRoot())
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Bundle bundle = new Bundle();
                        bundle.putString(STUDENT_FIRST, binding.firstName.getText().toString());
                        bundle.putString(STUDENT_LAST, binding.secondName.getText().toString());
                        bundle.putInt(SISID, Integer.valueOf(binding.studentId.getText().toString()));
                        getParentFragmentManager().setFragmentResult(REQ_KEY, bundle);

                        Toast.makeText(getActivity(), "Student successfully added", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null);
        return builder.create();
    }
}