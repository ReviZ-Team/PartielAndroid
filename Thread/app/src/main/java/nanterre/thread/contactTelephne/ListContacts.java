package nanterre.thread.contactTelephne;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nanterre.thread.Image;
import nanterre.thread.ImageAdapter;
import nanterre.thread.R;

public class ListContacts extends AppCompatActivity {


    private ArrayList<Contact> contacts;
    private ContactAdapter adapter;
    private ContentResolver cr;

    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        cr = getContentResolver();

        ListView listView = (ListView) findViewById(R.id.listContact);

        contacts = new ArrayList<Contact>();
        adapter = new ContactAdapter(ListContacts.this, contacts);
        listView.setAdapter(adapter);

        //showContacts();
        getNumber(this.getContentResolver());


    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
        else
        {
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {



                String hasPhone;
                String contactId = cursor.getString(cursor.getColumnIndex(hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))));


                if (Boolean.parseBoolean(hasPhone)) {
                    // You know it has a number so now query it like this
                    Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null);
                    while (phones.moveToNext()) {
                        String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));

                        Contact c = new Contact(phoneNumber,contactId);

                        contacts.add(c);
                        adapter.notifyDataSetChanged();
                    }
                    phones.close();
                }
            }

            cursor.close();
            adapter.notifyDataSetChanged();
        }
    }

    public void getNumber(ContentResolver cr)
    {
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        // use the cursor to access the contacts
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            // get display name
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Contact c = new Contact(phoneNumber,name);

            contacts.add(c);
            adapter.notifyDataSetChanged();
        }
    }

}
