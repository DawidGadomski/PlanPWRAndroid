package pl.pwr.edu.s241223.planpwr.Fragments;

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

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;
import pl.pwr.edu.s241223.planpwr.R;

public class AddNoteFragment extends Fragment {
    private SubjectViewModel subjectViewModel;
    private Subject subject;
    private Bundle bundle;
    private int pos;

    private Button bAddNote;
    private Button bCancelNote;

    private EditText etText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_note, container, false);

        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);

        bundle = getArguments();
        assert bundle != null;
        subject = (Subject) bundle.getSerializable("Subject");

        if(bundle.containsKey("position")){
            pos = bundle.getInt("position");
            etText.setText(subject.getNotesList().get(pos).getText());
        }

        bAddNote = view.findViewById(R.id.bAddNote);
        bCancelNote = view.findViewById(R.id.bCancleNote);

        etText = view.findViewById(R.id.etNoteText);

        bAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteText = etText.getText().toString();

                subject.addNote(noteText);
                subjectViewModel.update(subject);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        bCancelNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }
}
