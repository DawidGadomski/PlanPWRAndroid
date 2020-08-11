package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class TestCardConverter {

    @TypeConverter
    public String fromTestCardList(ArrayList<TestCard> testCardList) {
        if (testCardList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TestCard>>() {}.getType();
        String json = gson.toJson(testCardList, type);
        return json;
    }

    @TypeConverter
    public ArrayList<TestCard> toTestCardList(String listString) {
        if (listString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TestCard>>() {}.getType();
        ArrayList<TestCard> testCardsList = gson.fromJson(listString, type);
        return testCardsList;
    }
}