package nanterre.thread;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    Communicator c;

    List images;
    ImageAdapter adapter;
    EditText magicword;
    Button savemagic;
    TextView savedText;

    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magicword = (EditText) findViewById(R.id.editText_motmagique);
        savemagic = (Button) findViewById(R.id.button_saveMotMagique);
        savedText = (TextView) findViewById(R.id.editText_savedText);


        ListView listView = (ListView) findViewById(R.id.listImage);
        images = new ArrayList<Image>();
        adapter = new ImageAdapter(MainActivity.this, images);
        listView.setAdapter(adapter);



        Button boutonAdd = (Button) findViewById(R.id.buttonAdd);
        boutonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c = new Communicator(MainActivity.this);
                c.delegate = MainActivity.this;
                c.execute("http://lorempixel.com/400/200/");
            }
        });




        savemagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             saveMagicWord();

                showMagicWord();
            }
        });



    }


        public void saveMagicWord(){
         sharedPref = getSharedPreferences("magicword", Context.MODE_PRIVATE); //
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("magicword", magicword.getText().toString());// Pour ajouter ou modifier des couples clé valeur
        editor.apply(); // comme un commit
        Toast.makeText(this, "saved !", Toast.LENGTH_LONG).show();
    }

    public void showMagicWord(){
        sharedPref = getSharedPreferences("magicword", Context.MODE_PRIVATE);
        String mesMotsMagiques = sharedPref.getString("magicword","");
        Toast.makeText(this, "saved !", Toast.LENGTH_LONG).show();
        savedText.setText(magicword.getText().toString());
    }

    @Override
    public void processFinish(Bitmap bmp) {
        images.add(new Image(bmp, "Image", "Paysage"));
        adapter.notifyDataSetChanged();
    }
}

