package org.overlake.mat803.databaseexample;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.overlake.mat803.databaseexample.database.SisDatabaseDao;
import org.overlake.mat803.databaseexample.database.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    List<Student> mStudents;

    public StudentAdapter(SisDatabaseDao dao) {
        mStudents = dao.getStudents();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = mStudents.get(position);
        holder.sisId.setText(String.valueOf(student.sisId));
        holder.firstName.setText(student.firstName);
        holder.lastName.setText(student.lastName);
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    protected class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView sisId;
        public TextView firstName;
        public TextView lastName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            sisId = itemView.findViewById(R.id.sis_id);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
        }
    }
}