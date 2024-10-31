package com.example.letiendat_22520214_lab03;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letiendat_22520214_lab03.contact.Contact;
import com.example.letiendat_22520214_lab03.contact.DbAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private List<String> contactList;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        DbAdapter db = new DbAdapter(this);
        db.open();
        lv = findViewById(R.id.listview);
        contactList = new ArrayList<>();
        // Inserting Contacts
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts=
        List<Contact> contacts = db.getAllContacts();
        for(Contact item : contacts){
            contactList.add("Id: " + item.getId() + ", Name: " + item.getName() + ", Phone: " + item.getPhoneNumber());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contacts.get(i);
                contacts.remove(i);
                contactList.remove(i);
                db.deleteContact(contact);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}