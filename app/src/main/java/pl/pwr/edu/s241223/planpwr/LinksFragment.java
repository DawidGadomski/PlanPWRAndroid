package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;

public class LinksFragment extends Fragment {
    private ImageButton ibAddLink;
    private ImageButton ibBackFromLinks;

    private SubjectViewModel subjectViewModel;
    private Bundle bundle;
    private Subject subject;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_links, container, false);

        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);

        bundle = getArguments();
        assert bundle != null;
        subject = (Subject) bundle.getSerializable("Subject");

        assert subject != null;

        recyclerView = view.findViewById(R.id.rViewLinks);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAdapter = new RecycleViewAdapterForLinks(subject.getLinksList());

        ibAddLink = view.findViewById(R.id.ibADdLink);
        ibBackFromLinks = view.findViewById(R.id.ibBackFromLinks);



        ibAddLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                Bundle b = new Bundle();
                b.putSerializable("Subject", subject);
                AddLinkFragment addLinkFragment = new AddLinkFragment();
                addLinkFragment.setArguments(b);

                fragmentTransaction.replace(R.id.fragment_container, addLinkFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        ibBackFromLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });




        return view;
    }
}
