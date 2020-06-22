package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Map;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;
import pl.pwr.edu.s241223.planpwr.PlanComponent.Grid;
import pl.pwr.edu.s241223.planpwr.Settings.MainWindowSettings;

public class PlanFragment extends Fragment {
    private SubjectViewModel subjectViewModel;
    private MainWindowSettings mainWindowSettings;
    private Grid grid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_plan, container, false);

        grid = view.findViewById(R.id.grid);
        //mainWindowSettings = new MainWindowSettings(grid);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);
        subjectViewModel.getAllSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                grid.setSubjectsList(subjects);
                Toast.makeText(requireActivity(), "OnChange", Toast.LENGTH_SHORT).show();
            }
        });
        subjectViewModel.getSubjectData().observe(getViewLifecycleOwner(), new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> stringObjectMap) {
                Subject subject = new Subject(grid, mainWindowSettings.getWorkSurfacePosX(), mainWindowSettings.getWorkSurfacePosY(), stringObjectMap);
                subjectViewModel.insert(subject);
                Toast.makeText(requireActivity(), "Insert", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
