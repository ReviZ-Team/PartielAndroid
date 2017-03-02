package fr.com.listepesonnalisee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Uchuujin on 02/03/2017.
 */

public class MonAdapter extends ArrayAdapter<MonObjet> {
    //events est la liste des models à afficher
    public MonAdapter(Context context, List<MonObjet> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            // MODIFIER LE R.layout.row_list_objet AVEC LE NOM DE LAYOUT VOULU
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_list_objet,parent, false);
        }

        // ON MATCH LES ELEMENT DU LAYOUT
        EventViewHolder viewHolder = (EventViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new EventViewHolder();
            viewHolder.nom =(TextView) convertView.findViewById(R.id.nom);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.prenom);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Event> events
        MonObjet objet = getItem(position);

        //ON REMPLI LA VUE AVEC LES ELEMENTS
        viewHolder.nom.setText(objet.getNom());
        viewHolder.prenom.setText(objet.getPrenom());

        return convertView;
    }

    private class EventViewHolder{
        public TextView nom;
        public TextView prenom;
    }
}
