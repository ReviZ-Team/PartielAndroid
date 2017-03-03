package nanterre.bundleexercice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int compteur =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            compteur = savedInstanceState.getInt("compteur");
            // Do here for resetting your values which means state
            // before the changes occured.
        } else{

        }


        Button send = (Button) findViewById(R.id.buttonCompteur);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                compteur++;
                Toast.makeText(getApplicationContext(), "compteur :" + compteur, Toast.LENGTH_SHORT).show();
                // Creating Bundle object
            }

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("compteur", compteur);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) return;
         compteur = savedInstanceState.getInt("compteur");
    }




}
