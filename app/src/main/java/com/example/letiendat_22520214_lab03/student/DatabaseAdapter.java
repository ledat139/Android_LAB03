package com.example.letiendat_22520214_lab03.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.letiendat_22520214_lab03.contact.Contact;
import com.example.letiendat_22520214_lab03.contact.DatabaseHandler;
import com.example.letiendat_22520214_lab03.contact.DbAdapter;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "studentManager";
    // Contacts table name
    private static final String TABLE_STUDENTS = "students";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SEX = "sex";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_FACULTY = "faculty";
    private final Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() {
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    public void addStudent(Student student) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, student.getName());
        inititalValues.put(KEY_SEX, student.getSex());
        inititalValues.put(KEY_BIRTHDAY, student.getBirthDay());
        inititalValues.put(KEY_ADDRESS, student.getAddress());
        inititalValues.put(KEY_FACULTY, student.getFaculty());
        sqLiteDatabase.insert(TABLE_STUDENTS, null, inititalValues);
    }

    // Getting single contact
    public Student getStudent(int id) {
        String queryString = "SELECT * FROM " + TABLE_STUDENTS + " WHERE " + KEY_ID + " = ?";
        String[] whereArgs  = { String.valueOf(id) };
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, whereArgs);
        Student student = new Student();
        while (cursor.moveToNext()){
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setSex(cursor.getString(2));
            student.setBirthDay(cursor.getString(3));
            student.setAddress(cursor.getString(4));
            student.setFaculty(cursor.getString(5));
        }
        return student;
    }

    // Getting All Students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_STUDENTS, new String[]{KEY_ID, KEY_NAME, KEY_SEX, KEY_BIRTHDAY, KEY_ADDRESS, KEY_FACULTY},
                null, null, null,null, null);
        while (cursor.moveToNext()){
            Student student = new Student();
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setSex(cursor.getString(2));
            student.setBirthDay(cursor.getString(3));
            student.setAddress(cursor.getString(4));
            student.setFaculty(cursor.getString(5));
            students.add(student);
        }
        return students;
    }

    // Updating single student
    public int updateStudent(Student student) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, student.getName());
        cv.put(KEY_SEX, student.getSex());
        cv.put(KEY_BIRTHDAY, student.getBirthDay());
        cv.put(KEY_ADDRESS, student.getAddress());
        cv.put(KEY_FACULTY, student.getFaculty());
        int test = sqLiteDatabase.update(TABLE_STUDENTS,cv ," id = ?", new String[]{String.valueOf(student.getId())});
        return test;
    }

    // Deleting single student
    public void deleteStudent(Student student) {
        sqLiteDatabase.delete(TABLE_STUDENTS, "id = ?", new String[]{String.valueOf(student.getId())});
    }

}
