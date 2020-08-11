package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class LinkCardConverter {

    @TypeConverter
    public String fromLinkCardList(ArrayList<LinkCard> linkCardsList) {
        if (linkCardsList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<LinkCard>>() {}.getType();
        String json = gson.toJson(linkCardsList, type);
        return json;
    }

    @TypeConverter
    public ArrayList<LinkCard> toLinkCardList(String listString) {
        if (listString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TestCard>>() {}.getType();
        ArrayList<LinkCard> linkCardsList = gson.fromJson(listString, type);
        return linkCardsList;
    }
}