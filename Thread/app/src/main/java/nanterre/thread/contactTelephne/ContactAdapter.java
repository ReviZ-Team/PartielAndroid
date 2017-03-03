package nanterre.thread.contactTelephne;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nanterre.thread.Image;
import nanterre.thread.R;

/**
 * Created by adrie_000 on 03/02/2017.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_contact, parent, false);
        }

        ContactViewHolder viewHolder = (ContactViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ContactViewHolder();
            viewHolder.num = (TextView) convertView.findViewById(R.id.numeroTel);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);


            convertView.setTag(viewHolder);
        }

        Contact contact = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.num.setText(contact.getNum());
        viewHolder.name.setText(contact.getName());

        return convertView;
    }

    private class ContactViewHolder {
        public TextView num;
        public TextView name;
    }
}