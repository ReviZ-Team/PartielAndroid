package nanterre.thread;

/**
 * Created by adrie_000 on 20/01/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by adrie_000 on 09/11/2016.
 */
public class ImageAdapter extends ArrayAdapter<Image>
{

    public ImageAdapter(Context context, List<Image> images) {
        super(context, 0, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_images,parent, false);
        }

        EventViewHolder viewHolder = (EventViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new EventViewHolder();
            viewHolder.affiche =(ImageView) convertView.findViewById(R.id.affiche);
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);

            convertView.setTag(viewHolder);
        }

        Image image = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.titre.setText(image.getTitre());
        viewHolder.description.setText(image.getDescription());

        Bitmap bitmap = BitmapFactory.decodeByteArray(image.getAffiche(), 0, image.getAffiche().length);

        viewHolder.affiche.setImageBitmap(bitmap);


        return convertView;
    }

    private class EventViewHolder{
        public ImageView affiche;
        public TextView titre;
        public TextView description;

    }
}

