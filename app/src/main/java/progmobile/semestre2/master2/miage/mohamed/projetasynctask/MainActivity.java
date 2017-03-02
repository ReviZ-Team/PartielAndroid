package progmobile.semestre2.master2.miage.mohamed.projetasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.pBAsync);
        mButton = (Button) findViewById(R.id.btnLaunch);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigCalcul calcul=new BigCalcul();
                calcul.execute();
            }
        });}

    public class BigCalcul extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            Toast.makeText(getApplicationContext(),"Début du traitement asynchrone", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int progress;
            boolean test = false;
            for(progress=0;progress<=100;progress++){
                for(int i=0; i<100000000; i++){}
                publishProgress(progress);
                progress++;
            }return test;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
        }
    }

}
