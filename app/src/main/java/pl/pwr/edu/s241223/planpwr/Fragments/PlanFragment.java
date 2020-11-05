package pl.pwr.edu.s241223.planpwr.Fragments;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;
import pl.pwr.edu.s241223.planpwr.PlanComponent.Grid;
import pl.pwr.edu.s241223.planpwr.R;


public class PlanFragment extends Fragment {
    private SubjectViewModel subjectViewModel;

    private Grid grid;
    private GestureDetector gestureDetector;
    private GestureListener gestureListener;
    private List<Subject> subjectsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        grid = view.findViewById(R.id.grid);
        grid.setOnTouchListener(handleTouchOnGrid);
        gestureListener = new GestureListener();
        gestureDetector = new GestureDetector(getContext(), gestureListener);

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
                subjectsList = subjects;
                grid.postInvalidate();
            }
        });
    }

    private View.OnTouchListener handleTouchOnGrid = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            for (final Subject subject : subjectsList) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();

                if (subject.isOver(x, y)) {
                    gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
                        @Override
                        public boolean onSingleTapConfirmed(MotionEvent e) {
                            return false;
                        }

                        @Override
                        public boolean onDoubleTap(MotionEvent e) {

                            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Subject", subject);
                            MenuFragment menuFragment = new MenuFragment();
                            menuFragment.setArguments(bundle);

                            fragmentTransaction.replace(R.id.fragment_container, menuFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                            return true;
                        }

                        @Override
                        public boolean onDoubleTapEvent(MotionEvent e) {
                            return false;
                        }
                    });
                    gestureDetector.onTouchEvent(motionEvent);

                    switch (action) {
                        case (MotionEvent.ACTION_DOWN):

                            subject.setClickedFlag(true);

                            return true;
                        case (MotionEvent.ACTION_MOVE):

                            subject.move(x, y);
                            grid.postInvalidate();

                            return true;
                        case (MotionEvent.ACTION_UP):

                            subject.setClickedFlag(false);
                            subjectViewModel.update(subject);
                            grid.postInvalidate();

                            return true;
                        case (MotionEvent.ACTION_CANCEL):

                            return true;
                        case (MotionEvent.ACTION_OUTSIDE):

                            return true;
                        default:

                            gestureDetector.onTouchEvent(motionEvent);
                            subject.setClickedFlag(false);
                            grid.postInvalidate();

                            return true;
                    }
                }
            }
            gestureDetector.onTouchEvent(motionEvent);
            grid.postInvalidate();

            return true;
        }
    };

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        // event when long press occurs
        @Override
        public void onLongPress(MotionEvent e) {
            System.out.println("long");

        }
    }
}