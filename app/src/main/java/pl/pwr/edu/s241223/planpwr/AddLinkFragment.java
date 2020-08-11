package pl.pwr.edu.s241223.planpwr;

import android.net.Uri;
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

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.LinkCard;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;

public class AddLinkFragment extends Fragment {
    private SubjectViewModel subjectViewModel;
    private Subject subject;
    private Bundle bundle;

    private Button bAddLink;
    private Button bCancelLink;

    private EditText etNameOfSite;
    private EditText etLink;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_link, container, false);

        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);

        bundle = getArguments();
        assert bundle != null;
        subject = (Subject) bundle.getSerializable("Subject");

        bAddLink = view.findViewById(R.id.bAddLink);
        bCancelLink = view.findViewById(R.id.bCancleLink);

        etNameOfSite = view.findViewById(R.id.etNameOfSite);
        etLink = view.findViewById(R.id.etLink);

        bAddLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameOfSite = etNameOfSite.getText().toString();
                String link = etLink.getText().toString();

                subject.addLink(nameOfSite, link);
                subjectViewModel.update(subject);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        bCancelLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        return view;
    }
}
