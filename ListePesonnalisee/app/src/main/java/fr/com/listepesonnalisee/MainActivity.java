package fr.com.listepesonnalisee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MonObjet> objets;
    ListView listView;
    MonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Création de l' Arraylist
        objets = new ArrayList<MonObjet>();

        // Création des données
        MonObjet a = new MonObjet("Jean", "Paul");
        MonObjet b = new MonObjet("Pascal", "Orsini");
        MonObjet c = new MonObjet("Dark", "Small");
        MonObjet d = new MonObjet("Pierre", "Dur");

        // Insertion des données dans la liste
        objets.add(a);
        objets.add(b);
        objets.add(c);
        objets.add(d);

        // on récupère l'element de la liste
        listView = (ListView) findViewById(R.id.maListe);

        // On fait appel à notre adapter qui va formater notre liste
        adapter= new MonAdapter(MainActivity.this, objets);
        listView.setAdapter(adapter);
    }
}