package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NoteCardConverter {

    @TypeConverter
    public String fromTestCardList(ArrayList<NoteCard> noteCardList) {
        if (noteCardList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<NoteCard>>() {}.getType();
        String json = gson.toJson(noteCardList, type);
        return json;
    }

    @TypeConverter
    public ArrayList<NoteCard> toTestCardList(String listString) {
        if (listString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<NoteCard>>() {}.getType();
        ArrayList<NoteCard> noteCardsList = gson.fromJson(listString, type);
        return noteCardsList;
    }
}