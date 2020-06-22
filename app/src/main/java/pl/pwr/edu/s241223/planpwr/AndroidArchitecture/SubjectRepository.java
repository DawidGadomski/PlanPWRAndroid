package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectRepository {
    private SubjectDao subjectDao;
    private LiveData<List<Subject>> allSubjects;

    public SubjectRepository(Application application){
        SubjectDatabase database = SubjectDatabase.getInstance(application);
        subjectDao = database.subjectDao();
        allSubjects = subjectDao.getAllSubjects();
    }

    public void insert(Subject subject){
        System.out.println(subject.getName());
        new InsertSubjectAsyncTask(subjectDao).execute();
    }

    public void update(Subject subject){
        new UpdateSubjectAsyncTask(subjectDao).execute();
    }

    public void delete(Subject subject){
        new DeleteSubjectAsyncTask(subjectDao).execute();
    }

    public LiveData<List<Subject>> getAllSubjects(){
        return allSubjects;
    }

    /*
    Funkcja getAllSubjects jest możliwa do wykonania w backgroundzie, natomiast Room nie pozwoli na
    prace z bazą danych w backgroundzie bo to może zamrozić aplikacje, w odpowiedzi na ten problem
    tworzymy async taski !
     */

    private static class InsertSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{
        private SubjectDao subjectDao;

        private InsertSubjectAsyncTask(SubjectDao subjectDao){
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            for (Subject subject : subjects){
                System.out.println(subject.getName());
            }
//            subjectDao.insert(subjects[0]);
            return null;
        }
    }
    private static class UpdateSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{
        private SubjectDao subjectDao;

        private UpdateSubjectAsyncTask(SubjectDao subjectDao){
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.update(subjects[0]);
            return null;
        }
    }
    private static class DeleteSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{
        private SubjectDao subjectDao;

        private DeleteSubjectAsyncTask(SubjectDao subjectDao){
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.delete(subjects[0]);
            return null;
        }
    }
}
