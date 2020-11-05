package pl.pwr.edu.s241223.planpwr.Fragments;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.NoteCard;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;
import pl.pwr.edu.s241223.planpwr.PlanComponent.NoteBoard;
import pl.pwr.edu.s241223.planpwr.R;

public class NotesFragment extends Fragment {
    private ImageButton ibAddNote;
    private ImageButton ibBackFromNote;

    private NoteBoard noteBoard;
    private SubjectViewModel subjectViewModel;
    private Bundle bundle;
    private Subject subject;

    private ArrayList<NoteCard> notesList;

    private GestureDetector gestureDetector;
    private GestureListener gestureListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_notes, container, false);

        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);

        noteBoard = view.findViewById(R.id.noteBoard);
        noteBoard.setOnTouchListener(handleTouchOnBoard);
        gestureListener = new GestureListener();
        gestureDetector = new GestureDetector(getContext(), gestureListener);

        bundle = getArguments();
        assert bundle != null;
        subject = (Subject) bundle.getSerializable("Subject");

        assert subject != null;

        ibAddNote = view.findViewById(R.id.ibAddNote);
        ibBackFromNote = view.findViewById(R.id.ibBackFromNotes);

        ibAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                Bundle b = new Bundle();
                b.putSerializable("Subject", subject);
                AddNoteFragment addNoteFragment = new AddNoteFragment();
                addNoteFragment.setArguments(b);

                fragmentTransaction.replace(R.id.fragment_container, addNoteFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ibBackFromNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);
        subjectViewModel.getAllSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                noteBoard.setNotesList(subject.getNotesList());
                noteBoard.postInvalidate();
            }
        });
    }

    private View.OnTouchListener handleTouchOnBoard = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            for (final NoteCard note : subject.getNotesList()) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();

                if (note.isOver(x, y)) {
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
                            bundle.putSerializable("Note", note);
                            AddNoteFragment addNoteFragment = new AddNoteFragment();
                            addNoteFragment.setArguments(bundle);

                            fragmentTransaction.replace(R.id.fragment_container, addNoteFragment);
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

                            note.setClickedFlag(true);

                            return true;
                        case (MotionEvent.ACTION_MOVE):

                            note.move(x, y);
                            noteBoard.postInvalidate();

                            return true;
                        case (MotionEvent.ACTION_UP):

                            note.setClickedFlag(false);
//                            subjectViewModel.update(subject);
                            noteBoard.postInvalidate();

                            return true;
                        case (MotionEvent.ACTION_CANCEL):

                            return true;
                        case (MotionEvent.ACTION_OUTSIDE):

                            return true;
                        default:

                            gestureDetector.onTouchEvent(motionEvent);
                            note.setClickedFlag(false);
                            noteBoard.postInvalidate();

                            return true;
                    }
                }
            }
            gestureDetector.onTouchEvent(motionEvent);
            noteBoard.postInvalidate();

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
