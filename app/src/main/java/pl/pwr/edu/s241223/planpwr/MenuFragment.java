package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;

import static android.view.View.GONE;

public class MenuFragment extends Fragment {
    private SubjectViewModel subjectViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView tvName;
    private TextView tvTerm;
    private TextView tvProf;
    private TextView tvRoom;

    private ImageButton bBack;
    private ImageButton bEdit;
    private ImageButton bDelete;
    private ImageButton ibAddTest;
    private ImageButton ibAddAbsence;
    private ImageButton ibConfirmAbsence;
    private ImageButton ibRemoveAbsence;
    private ImageButton ibLinks;

    private ImageButton bEditAbsences;

    private LinearLayout lAbsences;
    private ViewGroup.LayoutParams layoutParams;
    private Button bAbsence;



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
        ibAddTest = view.findViewById(R.id.ibAddTest);
        ibAddAbsence = view.findViewById(R.id.ibAddAbsence);
        ibConfirmAbsence = view.findViewById(R.id.ibConfirmAbsence);
        ibRemoveAbsence = view.findViewById(R.id.ibRemoveAbsence);
        bEditAbsences = view.findViewById(R.id.bEditAbsences);
        ibLinks = view.findViewById(R.id.ibLinks);

        tvName.setText(subject.getName());
        tvRoom.setText(subject.getRoom());
        tvProf.setText(subject.getProf());
        tvTerm.setText(subject.getTerm());

        lAbsences = view.findViewById(R.id.lAbsences);

        recyclerView = view.findViewById(R.id.rViewTestList);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recycleViewAdapter = new RecycleViewAdapterForTests(subject.getTestList());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleViewAdapter);

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();

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

        ibAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                Bundle b = new Bundle();
                b.putSerializable("Subject", subject);

                AddTestFragment addTestFragment = new AddTestFragment();
                addTestFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, addTestFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        bEditAbsences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bEditAbsences.setVisibility(GONE);
                ibAddAbsence.setVisibility(View.VISIBLE);
                ibConfirmAbsence.setVisibility(View.VISIBLE);
                ibRemoveAbsence.setVisibility(View.VISIBLE);

            }
        });

        ibConfirmAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bEditAbsences.setVisibility(View.VISIBLE);
                ibAddAbsence.setVisibility(GONE);
                ibConfirmAbsence.setVisibility(GONE);
                ibRemoveAbsence.setVisibility(GONE);

                subjectViewModel.update(subject);
            }
        });

        ibAddAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject.incrementAllAbsences();

                initAbsencesPanel();
            }
        });

        ibRemoveAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject.decrementAllAbsences();
                if(subject.getAbsences() > subject.getAllAbsences()){
                    subject.decrementAbsences();
                }
                initAbsencesPanel();
            }
        });

        ibLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Subject", subject);
                LinksFragment linksFragment = new LinksFragment();
                linksFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment_container, linksFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        initAbsencesPanel();


        return view;

    }

    private void initAbsencesPanel() {
        lAbsences.removeAllViews();

        int count = subject.getAbsences();
        if (subject.getAllAbsences() < 0) {
            subject.setAllAbsences(0);
        }

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 1; i <= subject.getAllAbsences(); i++) {
            bAbsence = new Button(getContext());
            bAbsence.setId(i);

            bAbsence.setBackground(getContext().getResources().getDrawable(R.drawable.button_green));

            if (count != 0) {
                bAbsence.setBackground(getContext().getResources().getDrawable(R.drawable.button_red));
                count -= 1;
            }

            bAbsence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (view.getBackground().getConstantState()==getContext().getResources().getDrawable(R.drawable.button_green).getConstantState()) {

                        view.setBackground(getContext().getResources().getDrawable(R.drawable.button_red));
                        subject.incrementAbsences();
                        view.postInvalidate();
                        subjectViewModel.update(subject);

                    } else {
                        view.setBackground(getContext().getResources().getDrawable(R.drawable.button_green));
                        subject.decrementAbsences();
                        view.postInvalidate();
                        subjectViewModel.update(subject);
                    }
                }

            });
            lAbsences.addView(bAbsence);
        }
    }
}


