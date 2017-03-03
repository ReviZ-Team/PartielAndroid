package nanterre.thread;

/**
 * Created by adrie_000 on 10/02/2017.
 */

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.os.AsyncTask;
        import android.util.Log;

        import org.apache.http.HttpResponse;
        import org.apache.http.params.CoreProtocolPNames;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.DefaultHttpClient;



        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.*;
        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;

public class CommunicatorJSon extends AsyncTask<String,String,String> {

    public AsyncResponseJSon delegate=null;
    private int statusPost;
    private ProgressDialog dialog;
    private Activity activity;
    private String loadingTitle;
    private String loadingText;
    private String token;

    public CommunicatorJSon(Activity activity,String loadingTitle,String loadingText,String token)
    {
        super();
        this.activity=activity;
        this.loadingTitle=loadingTitle;
        this.loadingText=loadingText;
        this.token=token;

    }

    public String executeHttpGet(String URL) throws Exception
    {
        BufferedReader in = null;
        try
        {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "text/plain; charset=utf-8");

            if(token != null)
            {
                request.setHeader("Authorization", "Basic "+token);
            }

            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            statusPost = response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null)
            {
                sb.append(line + NL);
            }
            in.close();
            String page = sb.toString();
            System.out.println(page);
            return page;
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    Log.d("BBB", e.toString());
                }
            }
        }
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    protected String doInBackground(String[] params) {
        try {
            return executeHttpGet((String) params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String page)
    {
        dialog.dismiss();
        delegate.processFinish(page);
        //kill task
        this.cancel(true);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Create a progress dialog
        dialog = new ProgressDialog(activity);
        // Set progress dialog title
        dialog.setTitle(loadingTitle);
        // Set progress dialog message
        dialog.setMessage(loadingText);
        dialog.setIndeterminate(false);
        // Show progress dialog
        dialog.show();
    }


    public int getStatusPost()
    {
        return this.statusPost;
    }

}