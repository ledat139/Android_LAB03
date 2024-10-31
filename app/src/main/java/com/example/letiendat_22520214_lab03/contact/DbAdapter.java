package com.example.letiendat_22520214_lab03.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbAdapter {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private final Context context;
    private DatabaseHandler dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() {
        dbHelper = new DatabaseHandler(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }


    // Adding new contact
    public void addContact(Contact contact) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, contact.getName());
        inititalValues.put(KEY_PH_NO, contact.getPhoneNumber());
        sqLiteDatabase.insert(TABLE_CONTACTS, null, inititalValues);
    }
    // Getting single contact
    public Contact getContact(int id) {
        String queryString = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + " = ?";
        String[] whereArgs  = { String.valueOf(id) };
        Cursor cursor = sqLiteDatabase.rawQuery(queryString, whereArgs);
        Contact contact = new Contact();
        while (cursor.moveToNext()){
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setPhoneNumber(cursor.getString(2));
        }
        return contact;
    }
    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO},
                null, null, null,null, null);
        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setPhoneNumber(cursor.getString(2));
            contacts.add(contact);
        }
        return contacts;
    }
    // Updating single contact
    public int updateContact(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, contact.getName());
        cv.put(KEY_PH_NO, contact.getPhoneNumber());
        int test = sqLiteDatabase.update(TABLE_CONTACTS,cv ," id = ?", new String[]{String.valueOf(contact.getId())});
        return test;
    }

    // Deleting single contact
     public void deleteContact(Contact contact) {
         sqLiteDatabase.delete(TABLE_CONTACTS, "id = ?", new String[]{String.valueOf(contact.getId())});
     }


}
