package org.overlake.mat803.databaseexample;

import static org.overlake.mat803.databaseexample.StudentAddDialogFragment.REQ_KEY;
import static org.overlake.mat803.databaseexample.StudentAddDialogFragment.STUDENT_FIRST;
import static org.overlake.mat803.databaseexample.StudentAddDialogFragment.STUDENT_LAST;
import static org.overlake.mat803.databaseexample.StudentAddDialogFragment.SISID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import org.overlake.mat803.databaseexample.database.SisDatabase;
import org.overlake.mat803.databaseexample.database.SisDatabaseDao;
import org.overlake.mat803.databaseexample.database.Student;
import org.overlake.mat803.databaseexample.databinding.FragmentStudentBinding;

public class StudentFragment extends Fragment {

    private FragmentStudentBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentStudentBinding.inflate(inflater, container, false);

        SisDatabase database = Room.databaseBuilder(getContext(),SisDatabase.class,"SISDatabase").allowMainThreadQueries().build();
        SisDatabaseDao dao = database.getDao();

        binding.recycler.setAdapter(new StudentAdapter(dao));

        StudentAddDialogFragment addStudentFragment = new StudentAddDialogFragment();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentFragment.show(getParentFragmentManager(), null);
            }
        });

        FragmentManager fm = getParentFragmentManager();

        fm.setFragmentResultListener(REQ_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Student student = new Student(result.getInt(SISID), result.getString(STUDENT_FIRST), result.getString(STUDENT_LAST));
                dao.addStudent(student);
            }
        });

        return binding.getRoot();

    }


}