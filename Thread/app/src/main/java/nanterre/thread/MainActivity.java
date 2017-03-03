package nanterre.thread;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    Communicator c;
    Handler handler;

    List images;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init bdd
        SugarContext.init(this);

        ListView listView = (ListView) findViewById(R.id.listImage);


        images = new ArrayList<Image>();
         adapter = new ImageAdapter(MainActivity.this, images);
        listView.setAdapter(adapter);


        //delete all in table
        List<Image> imageBD = Image.findWithQuery(Image.class, "Select * from Image");
        for (Image im : imageBD) {
            im.delete();
        }


        Button boutonAdd = (Button) findViewById(R.id.buttonAdd);
        boutonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                        c = new Communicator(MainActivity.this);
                        c.delegate = MainActivity.this;
                        c.execute("http://lorempixel.com/400/200/");

            }
        });

        Button boutonBD = (Button) findViewById(R.id.bd);
        boutonBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                List<Image> imageBD = Image.findWithQuery(Image.class, "Select * from Image");


                for (int i = 0; i < imageBD.size(); i++) {
                    images.add(imageBD.get(i));
                    adapter.notifyDataSetChanged();
                }

                for (Image im : imageBD) {
                    im.delete();
                }


            }
        });


    }

    @Override
    public void processFinish(byte [] bt) {

        Image i = new Image(bt,"Image","Random");

        images.add(i);

        i.save();

        adapter.notifyDataSetChanged();
    }
}
