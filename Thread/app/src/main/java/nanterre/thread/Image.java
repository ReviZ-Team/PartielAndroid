package nanterre.thread;

import android.graphics.Bitmap;

import com.orm.SugarRecord;


/**
 * Created by adrie_000 on 20/01/2017.
 */

public class Image extends SugarRecord {
    private byte[] affiche;
    private String titre;
    private String description;

    public Image(){}

    public Image(byte[] affiche, String titre, String description) {
        this.affiche = affiche;
        this.titre = titre;
        this.description = description;
    }

    public byte[] getAffiche() {
        return affiche;
    }

    public void setAffiche(byte[] affiche) {
        this.affiche = affiche;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
