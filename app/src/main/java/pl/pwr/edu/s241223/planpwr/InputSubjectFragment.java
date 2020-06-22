package pl.pwr.edu.s241223.planpwr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;
import java.util.TreeMap;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.SubjectViewModel;

public class InputSubjectFragment extends Fragment {
    private InputSubjectFragmentListener inputSubjectFragmentListener;
    private AlertDialog.Builder builder;

    private SubjectViewModel subjectViewModel;

    private EditText etName, etTime, etTerm, etRoom, etProf;
    private Button bAccept, bCancel;
    private String name, term, time, prof, room, type, week;
    private RadioButton rLab;
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

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> dataMap = createDataOfSubject();
                subjectViewModel.setSubjectData(dataMap);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PlanFragment()).commit();

            }
        });

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

    //    public void sendBack(Map<String, Object>){
//        if()
//    }

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
