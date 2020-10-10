package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class SettingsFragment extends Fragment {
    private TabLayout tabLayoutSettings;
    private ViewPager viewPagerSettings;
    private ViewPagerAdapter viewPagerAdapter;

    private AboutSettingsFragment aboutSettingsFragment;
    private ColorSettingsFragment colorSettingsFragment;
    private AdvancedSettingsFragment advancedSettingsFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        tabLayoutSettings = view.findViewById(R.id.tLayoutSettings);
        viewPagerSettings = view.findViewById(R.id.viewPagerSettings);

        colorSettingsFragment = new ColorSettingsFragment();
        aboutSettingsFragment = new AboutSettingsFragment();
        advancedSettingsFragment = new AdvancedSettingsFragment();

        tabLayoutSettings.setupWithViewPager(viewPagerSettings);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(colorSettingsFragment, "Colors");
        viewPagerAdapter.addFragment(aboutSettingsFragment, "About");
        viewPagerAdapter.addFragment(advancedSettingsFragment, "Advanced");
        viewPagerSettings.setAdapter(viewPagerAdapter);

        return view;


    }
}
