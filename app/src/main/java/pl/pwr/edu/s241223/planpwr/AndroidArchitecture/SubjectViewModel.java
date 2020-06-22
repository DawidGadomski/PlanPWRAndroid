package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Map;

public class SubjectViewModel extends AndroidViewModel {
    private SubjectRepository repository;
    private LiveData<List<Subject>> allSubjects;

    private MutableLiveData<Map<String, Object>> subjectData;

    public SubjectViewModel(@NonNull Application application){
        super(application);
        repository = new SubjectRepository(application);
        allSubjects = repository.getAllSubjects();

        subjectData = new MutableLiveData<>();
    }

    public void setSubjectData(Map<String, Object> dataMap){
        subjectData.setValue(dataMap);
    }

    public LiveData<Map<String, Object>> getSubjectData(){
        return subjectData;
    }

    public void insert(Subject subject){
        repository.insert(subject);
    }

    public void update(Subject subject){
        repository.update(subject);
    }

    public void delete(Subject subject){
        repository.delete(subject);
    }

    public LiveData<List<Subject>> getAllSubjects(){
        return allSubjects;
    }
}
