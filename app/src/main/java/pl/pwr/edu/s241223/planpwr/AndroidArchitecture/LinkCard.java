package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import android.net.Uri;

public class LinkCard {
    private String nameOfTheSite;
    private Uri link;

    public LinkCard(String nameOfTheSite, Uri link){
        this.nameOfTheSite = nameOfTheSite;
        this.link = link;
    }



    public String getNameOfTheSite() {
        return nameOfTheSite;
    }

    public void setNameOfTheSite(String nameOfTheSite) {
        this.nameOfTheSite = nameOfTheSite;
    }

    public Uri getLink() {
        return link;
    }

    public void setLink(Uri link) {
        this.link = link;
    }
}
