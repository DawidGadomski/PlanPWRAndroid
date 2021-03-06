package pl.pwr.edu.s241223.planpwr.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;
import java.util.TreeMap;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;
import pl.pwr.edu.s241223.planpwr.R;

public class InputSubjectFragment extends Fragment {
    private InputSubjectFragmentListener inputSubjectFragmentListener;
    private AlertDialog.Builder builder;

    private SubjectViewModel subjectViewModel;

    private Bundle bundle;
    private Subject subjectToEdit;

    private EditText etName, etTime, etTerm, etRoom, etProf;
    private Button bAccept, bCancel;
    private String name, term, time, prof, room, type, week;
    private RadioButton rLab;
    private RadioButton rbPro;
    private RadioButton rbSem;
    private RadioButton rLect;
    private RadioButton rWeek;
    private RadioButton rOddWeek;
    private RadioButton rEvenWeek;

    public interface InputSubjectFragmentListener{
        void onInputSubjectSent(Map<String, Object> input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fragment_input_subject, container, false);

//        builder = new AlertDialog.Builder(view.getContext());
//        builder.setMessage("Uzupełnij pole Nazwa oraz Czas")
//                .setCancelable(false)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        view.setVisible(false);
//                    }
//                });

        etName = view.findViewById(R.id.edName);
        etTime = view.findViewById(R.id.edTime);
        etTerm = view.findViewById(R.id.edTerm);
        etRoom = view.findViewById(R.id.edRoom);
        etProf = view.findViewById(R.id.edProf);

        bAccept = view.findViewById(R.id.bAccept);
        bCancel = view.findViewById(R.id.bCancel);

        rWeek = view.findViewById(R.id.rbWeek);
        rEvenWeek = view.findViewById(R.id.rbEvenWeek);
        rOddWeek = view.findViewById(R.id.rbOddWeek);
        rLect = view.findViewById(R.id.rbLect);
        rLab = view.findViewById(R.id.rbLab);
        rbPro = view.findViewById(R.id.rbPro);
        rbSem = view.findViewById(R.id.rbSem);

        bundle = getArguments();
        if(bundle != null) {
            subjectToEdit = (Subject) bundle.getSerializable("Subject");
            etName.setText(subjectToEdit.getName());
            etTime.setText(String.valueOf(subjectToEdit.getTime()));
            etTerm.setText(subjectToEdit.getTerm());
            etRoom.setText(subjectToEdit.getRoom());
            etProf.setText(subjectToEdit.getProf());
            if(subjectToEdit.getType()==1){
                rLab.setChecked(true);
            }
            else if (subjectToEdit.getType()==2){
                rLect.setChecked(true);
            }
            else if (subjectToEdit.getType()==3){
                rbPro.setChecked(true);
            }
            else if (subjectToEdit.getType()==4){
                rbSem.setChecked(true);
            }

            if(subjectToEdit.getWeek().equals("TN")){
                rOddWeek.setChecked(true);
            }
            else if(subjectToEdit.getWeek().equals("TP")){
                rEvenWeek.setChecked(true);
            }
            else rWeek.setChecked(true);

            bAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, Object> dataMap = createDataOfSubject();
                    subjectToEdit.updateSubject(dataMap);
                    subjectViewModel.update(subjectToEdit);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PlanFragment()).commit();

                }
            });
        }
        else {
            bAccept.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    Map<String, Object> dataMap = createDataOfSubject();
                    if(rLect.isChecked()){
                        subjectViewModel.insert(new Subject(dataMap, ContextCompat.getColor(getContext(), R.color.lectureColor)));
                    }
                    else if(rLab.isChecked()){
                        subjectViewModel.insert(new Subject(dataMap, ContextCompat.getColor(getContext(), R.color.labColor)));
                    }
                    else if(rbSem.isChecked()){
                        subjectViewModel.insert(new Subject(dataMap, ContextCompat.getColor(getContext(), R.color.seminaryColor)));
                    }
                    else if(rbPro.isChecked()){
                        subjectViewModel.insert(new Subject(dataMap, ContextCompat.getColor(getContext(), R.color.projectColor)));
                    }

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PlanFragment()).commit();

                }
            });
        }

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlanFragment()).commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subjectViewModel = new ViewModelProvider(requireActivity()).get(SubjectViewModel.class);
    }



    public void setName(String n) {
        this.name = n;
    }

    public void setTerm(String t) {
        this.term = t;
    }

    public void setTime(String t) {
        this.time = t;
    }

    public void setProf(String p) {
        this.prof = p;
    }

    public void setRoom(String r) {
        this.room = r;
    }

    public void setType(String t) {
        this.type = t;
    }

    public void setWeek(String w) {
        this.week = w;
    }

    public String getName() {
        return this.name;
    }

    public String getTerm() {
        return this.term;
    }

    public String getTime() {
        return this.time;
    }

    public String getProf() {
        return this.prof;
    }

    public String getRoom() {
        return this.room;
    }

    public String getType() {
        return this.type;
    }

    public String getWeek() {
        return this.week;
    }

    /***
     * Zebranie danych z text area i dodanie ich do mapy która będzie zwracana przez formularz
     */
    public Map<String, Object> createDataOfSubject(){
        setName(etName.getText().toString());
        setTerm(etTerm.getText().toString());
        setTime(etTime.getText().toString());
        setProf(etProf.getText().toString());
        setRoom(etRoom.getText().toString());
        if (rLab.isChecked()) {
            setType("1");
        }
        if (rLect.isChecked()) {
            setType("2");
        }
        if (rWeek.isChecked()) {
            setWeek("week");
        }
        if (rOddWeek.isChecked()) {
            setWeek("TN");
        }
        if (rEvenWeek.isChecked()) {
            setWeek("TP");
        }
        Map<String, Object> dataMap = new TreeMap<>();
        dataMap.put("name", getName());
        dataMap.put("term", getTerm());
        dataMap.put("time", getTime());
        dataMap.put("prof", getProf());
        dataMap.put("room", getRoom());
        dataMap.put("type", getType());
        dataMap.put("week", getWeek());

        return dataMap;
    }
}
