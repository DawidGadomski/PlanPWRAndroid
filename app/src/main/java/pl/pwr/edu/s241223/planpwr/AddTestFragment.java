package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.TestCard;

public class AddTestFragment extends Fragment {
    private Bundle bundle;
    private int pos;
    private SubjectViewModel subjectViewModel;

    private EditText etTestName;
    private EditText etTestDate;

    private Subject subject;

    private Button bAddTest;
    private Button bCancleTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_test, container, false);
        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);

        etTestName = view.findViewById(R.id.etTestName);
        etTestDate = view.findViewById(R.id.etTestDate);

        bundle = getArguments();
        assert bundle != null;
        subject = (Subject) bundle.getSerializable("Subject");


        if(bundle.containsKey("position")){
            pos = bundle.getInt("position");
            etTestName.setText(subject.getTestList().get(pos).getTestName());
            etTestDate.setText(subject.getTestList().get(pos).getTestDate());
        }



        bAddTest = view.findViewById(R.id.ibAddTest);
        bCancleTest = view.findViewById(R.id.bCancleTest);

        bAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject.addTest(etTestName.getText().toString(), etTestDate.getText().toString());
                subjectViewModel.update(subject);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        bCancleTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }
}
