package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Subject.class, version = 4)
public abstract class SubjectDatabase extends RoomDatabase {
    private static SubjectDatabase instance;
    public abstract SubjectDao subjectDao();

    public static synchronized SubjectDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SubjectDatabase.class, "subject_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
//            new PopulateDbAsyncTask(instance).execute();
//          new ClearDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private SubjectDao subjectDao;
        private PopulateDbAsyncTask(SubjectDatabase db){
            subjectDao = db.subjectDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            subjectDao.insert(new Subject(100, 100, 150, 150, "123", "qwe", 120, "asd",
                    "zxc", 1, "Even", 0, 1, Color.parseColor("#FFFF00FF")));
            return null;
        }
    }
    private static class ClearDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private SubjectDao subjectDao;
        private ClearDbAsyncTask(SubjectDatabase db){
            subjectDao = db.subjectDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            subjectDao.deleteSubjects();
            return null;
        }
    }
}


