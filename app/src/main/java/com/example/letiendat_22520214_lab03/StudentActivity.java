package com.example.letiendat_22520214_lab03;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letiendat_22520214_lab03.contact.Contact;
import com.example.letiendat_22520214_lab03.contact.DbAdapter;
import com.example.letiendat_22520214_lab03.student.DatabaseAdapter;
import com.example.letiendat_22520214_lab03.student.Student;
import com.example.letiendat_22520214_lab03.student.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Student> students;
    private StudentAdapter adapter;
    private Button btnAdd;
    private DatabaseAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);
        db = new DatabaseAdapter(this);
        db.open();
        btnAdd = findViewById(R.id.btbAdd);
        recyclerView = findViewById(R.id.recyclerView);
        students = new ArrayList<>();
        changeData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(StudentActivity.this);
                dialog.setContentView(R.layout.create_dialog);
                dialog.show();

                Button btnAddStudent = dialog.findViewById(R.id.btnAddStudent);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                EditText etName = dialog.findViewById(R.id.etName);
                EditText etSex = dialog.findViewById(R.id.etSex);
                EditText etBirthday = dialog.findViewById(R.id.etBirthday);
                EditText etAddress = dialog.findViewById(R.id.etAddress);
                EditText etFaculty = dialog.findViewById(R.id.etFaculty);


                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnAddStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Student student = new Student(etName.getText().toString(),etSex.getText().toString()
                                , etBirthday.getText().toString(),etFaculty.getText().toString() ,etAddress.getText().toString());
                        db.addStudent(student);
                        changeData();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void changeData(){
        students = db.getAllStudents();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new StudentAdapter(StudentActivity.this, students);
        recyclerView.setAdapter(adapter);
    }

    public void updateDialog(Student student, int i){
        Dialog dialog = new Dialog(StudentActivity.this);
        dialog.setContentView(R.layout.update_dialog);
        dialog.show();
        TextView tvId = dialog.findViewById(R.id.tvId);
        Button btnUpdateStudent = dialog.findViewById(R.id.btnUpdateStudent);
        Button btnCancel = dialog.findViewById(R.id.btnCancelUpdate);
        EditText etName = dialog.findViewById(R.id.etName);
        EditText etSex = dialog.findViewById(R.id.etSex);
        EditText etBirthday = dialog.findViewById(R.id.etBirthday);
        EditText etAddress = dialog.findViewById(R.id.etAddress);
        EditText etFaculty = dialog.findViewById(R.id.etFaculty);

        tvId.setText("Mã sinh viên: " + String.valueOf(student.getId()));
        etName.setText(student.getName());
        etSex.setText(student.getSex());
        etBirthday.setText(student.getBirthDay());
        etAddress.setText(student.getAddress());
        etFaculty.setText(student.getFaculty());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student.setName(etName.getText().toString());
                student.setSex(etSex.getText().toString());
                student.setBirthDay(etBirthday.getText().toString());
                student.setAddress(etAddress.getText().toString());
                student.setFaculty(etFaculty.getText().toString());
                db.updateStudent(student);
                changeData();
                dialog.dismiss();
                Toast toast = Toast.makeText(StudentActivity.this, "Cập nhật sinh viên thành công !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }
    public void deleteStudent(Student student){
        db.deleteStudent(student);
        changeData();
        Toast toast = Toast.makeText(this, "Xóa sinh viên thành công !", Toast.LENGTH_SHORT);
        toast.show();
    }

}
