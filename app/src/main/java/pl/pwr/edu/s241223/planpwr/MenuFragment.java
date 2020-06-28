package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;

public class MenuFragment extends Fragment {
    private SubjectViewModel subjectViewModel;

    private TextView tvName;
    private TextView tvTerm;
    private TextView tvProf;
    private TextView tvRoom;

    private ImageButton bBack;
    private ImageButton bEdit;
    private ImageButton bDelete;

    private Bundle bundle;
    private Subject subject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_subject_menu, container, false);

        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);


        bundle = getArguments();
        assert bundle != null;
        subject = (Subject) bundle.getSerializable("Subject");

        assert subject != null;
        subject.setClickedFlag(false);

        tvName = view.findViewById(R.id.tvName);
        tvTerm = view.findViewById(R.id.tvTerm);
        tvProf = view.findViewById(R.id.tvProf);
        tvRoom = view.findViewById(R.id.tvRoom);

        bBack = view.findViewById(R.id.bBack);
        bEdit = view.findViewById(R.id.bEdit);
        bDelete = view.findViewById(R.id.bDelete);

        tvName.setText(subject.getName());
        tvRoom.setText(subject.getRoom());
        tvProf.setText(subject.getProf());
        tvTerm.setText(subject.getTerm());

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                subjectViewModel.update(subject);
                getActivity().getSupportFragmentManager().popBackStack();
                System.out.println("quit");
            }
        });

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Subject", subject);
                InputSubjectFragment inputSubject = new InputSubjectFragment();
                inputSubject.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, inputSubject);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectViewModel.delete(subject);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;

    }
}
