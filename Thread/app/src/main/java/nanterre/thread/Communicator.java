package nanterre.thread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



/**
 * Created by adrie_000 on 20/01/2017.
 */



public class Communicator extends AsyncTask<String,Void,Void> {

    private Activity activity;
    public AsyncResponse delegate=null;
    private byte [] bytes;

    public Communicator(Activity activity)
    {
        super();
        this.activity=activity;

    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            InputStream in = new URL(params[0]).openStream();

            Bitmap bmp = BitmapFactory.decodeStream(in);
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
            bytes = blob.toByteArray();

        } catch (Exception e) {

            System.out.println("ERROR");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        delegate.processFinish(bytes);
        //kill task
        this.cancel(true);
    }



}