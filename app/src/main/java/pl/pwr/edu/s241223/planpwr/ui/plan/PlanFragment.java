package pl.pwr.edu.s241223.planpwr.ui.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import pl.pwr.edu.s241223.planpwr.R;

public class PlanFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_plan, container, false);

        Grid grid = root.findViewById(R.id.grid);

        return root;
    }
}
